package com.forms.batch.job.unit.iclfps.payment.certificate;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.core.utils.FFPStringUtils;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.EntityManager;
import com.forms.framework.util.PatternUtil;
import com.forms.framework.util.XmlUtil;

public class FPSInitCertificateFileReader extends BatchBaseJob
{
	private String filePath = null;
	private String fileNamePattern = null;
	private static int BATCH_SIZE = 500;
	
	public void init() throws BatchJobException
	{
		try
		{
			filePath = this.batchData + this.actionElement.element("parameters").elementText("local-file-path");
			fileNamePattern = this.actionElement.element("parameters").elementText("filename-pattern");
			
			PatternUtil patternUtil = new PatternUtil(this.batchAcDate);
			Map<String, String> replaceMap = new HashMap<String, String>();
			replaceMap.put("YYYYMMDD", this.batchAcDate.replaceAll("-", ""));
			replaceMap.put("clearingcode", this.clearingCode);
			fileNamePattern = patternUtil.patternReplace(replaceMap, fileNamePattern);
			batchLogger.info(String.format("Read FPS certificate list batch date : %s", this.batchAcDate));
			batchLogger.info(String.format("Read FPS certificate list batch file path : %s", filePath));
		}
		catch(Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
	}
	
	@Override
	public boolean execute() throws BatchJobException
	{
		Date loc_startTime = Calendar.getInstance().getTime();
		batchLogger.info("read FPS certificate list batch started at" + loc_startTime);
		
		try
		{
			File workingPath = new File(filePath);
			
			File[] readfiles = workingPath.listFiles(new FilenameFilter()
			{
				@Override
				public boolean accept(File dir, String name)
				{
					return name.matches(fileNamePattern);
				}
			});
			
			if (readfiles != null)
			{
				// query processed file list
				String loc_processedSql = "SELECT FILE_NAME FROM TB_BH_PROCESSED_FILE ORDER BY FILE_NAME";
				List<Object[]> loc_tmpList = EntityManager.queryArrayList(loc_processedSql);
				String[] processedFileList = new String[loc_tmpList.size()];
				if (loc_tmpList != null)
				{
					for (int i = 0; i < loc_tmpList.size(); i++)
						processedFileList[i] = (String) (loc_tmpList.get(i)[0]);
				}

				List<File> loc_fileList = new ArrayList<>();
				for (File loc_tmpFile : readfiles)
				{
					String loc_filename = loc_tmpFile.getName();
					if (Arrays.binarySearch(processedFileList, loc_filename) >= 0)
					{
						batchLogger.warn(loc_filename + " processed!!!");
					} else
					{
						loc_fileList.add(loc_tmpFile);
					}
				}

				if (loc_fileList.size() > 0)
				{
					// DELETE DATA FROM TMP TABLE
					String loc_deleteSql = "DELETE FROM TB_BH_INIT_ECERT WHERE FILE_NAME IN(";
					for (int index = 0; index < loc_fileList.size(); index++)
					{
						loc_deleteSql = loc_deleteSql + "'" + loc_fileList.get(index).getName() + "'";
						if (index < loc_fileList.size() - 1)
							loc_deleteSql = loc_deleteSql + ",";
						else
							loc_deleteSql = loc_deleteSql + ")";
					}
					EntityManager.update(loc_deleteSql);

					this.processor(loc_fileList);

					String loc_sql = "INSERT INTO TB_BH_PROCESSED_FILE(FILE_NAME, PROCESSED_TS) VALUES (?,?)";
					// insert processed file & delete files
					for (File tmp : loc_fileList)
					{
						EntityManager.update(loc_sql, tmp.getName(), loc_startTime);
						tmp.delete();
					}
				}
			}

			Date loc_endTime = Calendar.getInstance().getTime();
			batchLogger.info("read FPS certificate list batch response file end at" + loc_endTime);
			batchLogger.info("read FPS certificate list batch response file use " + (loc_endTime.getTime() - loc_startTime.getTime()) / 1000);
			return true;
		}
		catch(Exception ex)
		{
			throw new BatchJobException(ex);
		}
	}
	
	
	private void processor(List<File> loc_fileList) throws Exception
	{
		batchLogger.info("Started processor FPS certificate list batch file");
		
		String sql = "INSERT INTO TB_BH_INIT_ECERT(ID, STATUS, FILE_NAME, ECERT_KEY, EFFECTIVE_DATE, EXPIRY_DATE, CREATE_DATE, LAST_MODIFY_DATE) VALUES(?,?,?,?,?,?,?,?)";
	
		for(File cert_file : loc_fileList)
		{
			try 
			{
				Element loc_eleRoot = XmlUtil.loadRootElement(filePath + cert_file.getName());
				//String ex = loc_eleRoot.element("CertListRpt").element("GrpHdr").elementText("MsgId");
				String totalCert = loc_eleRoot.element("CertListRpt").element("GrpHdr").elementText("TotalCert");
				
				batchLogger.info(String.format("The FPS certificate file[%s] contains total cert is %s", cert_file.getName(), totalCert));
				Iterator<?> certs = loc_eleRoot.element("CertListRpt").element("Certs").elementIterator();
				//List<Element> list = loc_eleRoot.element("CertListRpt").element("Certs").elements();
				//System.out.println(list.size());
				int count;
				Object[] res = EntityManager.queryArray("SELECT ID FROM TB_BH_INIT_ECERT ORDER BY ID DESC");//Get max ID	
				if(res == null) count = 0;
				else count = (int)res[0];
				
				int batFlag = 0;
				Object[][] batchObj = new Object[BATCH_SIZE][1];
				while(certs.hasNext())
				{
					List<Object> param = new ArrayList<Object>();
					batFlag+=1;
					Element cert = (Element) certs.next();
					String effDate = cert.elementText("EffDate");
					String expDate = cert.elementText("ExpDate");
					String eCert = cert.elementText("eCert");
					if(FFPStringUtils.isEmptyOrNull(effDate) || FFPStringUtils.isEmptyOrNull(expDate) || FFPStringUtils.isEmptyOrNull(eCert))
					{
						batchLogger.warn(String.format("Invalid FPS digital certificate[eCert:%s][effective date:%s][expiry date:%s] in file[%s]", eCert, effDate, expDate, cert_file.getName()));
						continue;
					}
					param.add(++count);
					param.add("I");
					param.add(cert_file.getName());
					param.add(eCert);
					param.add(FFPDateUtils.getSqlTimestamp(effDate, FFPDateUtils.ISO_TIME_FORMAT3));
					param.add(FFPDateUtils.getSqlTimestamp(expDate, FFPDateUtils.ISO_TIME_FORMAT3));
					param.add(new Date());
					param.add(new Date());
					
					batchObj[batFlag - 1] = param.toArray();
					if(batFlag % BATCH_SIZE == 0)
					{
						EntityManager.batch(sql, batchObj);
						batchObj = new Object[BATCH_SIZE][1];
						batFlag = 0;
					}
				}
				
				if(batFlag > 0)
				{
					Object[][] remObj = new Object[batFlag][1];
					System.arraycopy(batchObj, 0, remObj, 0, batFlag);
					EntityManager.batch(sql, remObj);
				}
			}
			catch(Exception ex)
			{
				batchLogger.error(String.format("Error on processing FPS certificate file : %s", cert_file.getName()), ex);
				throw ex;
			}
		}
	}
}
