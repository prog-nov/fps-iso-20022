package com.forms.batch.job.unit.participant.directdebit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBElement;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.FIToFIPaymentStatusReportV08;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.PaymentTransaction801;
import com.forms.ffp.core.msg.iclfps.FFPHkiclMessageConverter;
import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

public class DirectDebitOutwardBatchResponseFileReader extends BatchBaseJob {
	private static String PACS_002_MESSAGE_IDENTIFIER = "pacs.002.001.08";
	private static Connection conn = null;

	public void init() throws BatchJobException {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean execute() throws BatchJobException {
		// TODO Auto-generated method stub
		try {
			String responseRootPath = this.batchData
					+ this.actionElement.element("parameters").elementText("local-file-path");

			ArrayList<String> resFiles = getDirectoryFile(responseRootPath);
			
			if(resFiles != null && resFiles.size() >= 1){
				HashMap<String, String> msgsMap = getMsgsFromFile(resFiles);

				if (msgsMap != null) {
					conn = ConnectionManager.getInstance().getConnection();
					storeMsgToDB(msgsMap); 
				} else {
					throw new Exception("No Message Data Found!");
				}

				for (int i = 0; i < resFiles.size(); i++) {
					deleteFile(resFiles.get(i));
				}
			}
			
			return true;
		} catch (Exception e) {
			this.batchLogger.error(e.getMessage());
			throw new BatchJobException("Error Occurred In DirectDebitOutwardBatchResponseFileReader!");
		} finally{
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					this.batchLogger.error(e.getMessage());
					throw new BatchJobException("Error Occurred In Closing The Database!");
				}
			}
		}
	}

	// get content of the message files
	private HashMap<String, String> getMsgsFromFile(ArrayList<String> resFiles) {

		HashMap<String, String> msgsMap = new HashMap<>();

		for (int i = 0; i < resFiles.size(); i++) {
			String absolutePath = resFiles.get(i);
			String[] realPathArr = absolutePath.split("\\\\");
			String fileName = realPathArr[realPathArr.length - 1];

			String content = readLineFromFile(absolutePath);
			
			msgsMap.put(fileName, content);

		}

		return msgsMap;
	}

	// get files list from specified directory
	public ArrayList<String> getDirectoryFile(String dirPath) {
		String pattern = this.actionElement.element("parameters").elementText("filename-pattern")
				.replaceAll("#\\{clearingcode\\}", this.clearingCode).replaceAll("\\(", "").replaceAll("\\)", "");
		ArrayList<String> retFiles = new ArrayList<>();
		File dir = new File(dirPath);

		if (dir.exists()) {
			File[] cFiles = dir.listFiles();
			for (File f : cFiles) {
				if (f.isDirectory()) {
					retFiles.addAll(getDirectoryFile(f.getAbsolutePath()));
				} else {
					String[] realPathArr = f.getAbsolutePath().split("\\\\");
					String fileName = realPathArr[realPathArr.length - 1];
					if (fileName.matches("^" + pattern + "$")) {
						retFiles.add(f.getAbsolutePath());
						this.batchLogger.info(String.format("The File %s Is Found", f.getAbsolutePath()));
					}
				}
			}
		}

		return retFiles;
	}

	public String readLineFromFile(String file) {
		BufferedReader br = null;
		StringBuffer strBuf = new StringBuffer();

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));
			String content = "";
			while ((content = br.readLine()) != null) {
				strBuf.append(content);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.batchLogger.error(e.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				this.batchLogger.error(e.getMessage());
			}
		}

		return strBuf.toString();
	}

	// store msg to DB
	private void storeMsgToDB(HashMap<String, String> msgsMap) {
		Date insertStartTime = Calendar.getInstance().getTime();
		this.batchLogger.info(String.format("Start To Insert Into tb_tx_res_fpspddr_temp At %s", insertStartTime));
		Iterator<Entry<String, String>> it = msgsMap.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			String fileName = (String) entry.getKey();
			String content = (String) entry.getValue();
			try {

				FpsMessageEnvelope fpsMsg = FFPHkiclMessageConverter.parseObject(content);

				List<JAXBElement<?>> jaxbElems = fpsMsg.getFpsPylds().getBizData().get(0).getContent();
				BusinessApplicationHeaderV01 header = (BusinessApplicationHeaderV01) jaxbElems.get(0).getValue();
				Object document = jaxbElems.get(1).getValue();
				if (PACS_002_MESSAGE_IDENTIFIER.equals(header.getMsgDefIdr().toLowerCase())) {

					FIToFIPaymentStatusReportV08 fitofipmtstsrpt = ((Document) document).getFIToFIPmtStsRpt();

					// insert into tb_tx_res_fpspddr_temp
					String insert_tb_tx_fpspddr_sql = "INSERT INTO tb_tx_res_fpspddr_temp(FID, FILE_NAME, `STATUS`, CREATE_TIME, `FROM`, `TO`, BIZ_MSG_IDR, MSG_DEF_IDR, BIZ_SVC, CRE_DT, MSG_ID, CRE_DT_TM, ORGNL_MSG_ID, ORGNL_MSG_NM_ID, ORGNL_END_TO_END_ID, ORGNL_TX_ID, TXSTS, RSN, ADDITIONAL_INF) "
							+ "SELECT ?,?,?,now(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,? "
							+ "WHERE not exists (select 1 from tb_tx_res_fpspddr_temp WHERE FILE_NAME = ? OR MSG_ID = ?); ";
					ArrayList<Object> fpspddr_insert_list = new ArrayList<>();
					fpspddr_insert_list.add(new Date().getTime());
					fpspddr_insert_list.add((fileName.split("\\."))[0]);
					fpspddr_insert_list.add("I");
					fpspddr_insert_list.add(header.getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
					fpspddr_insert_list.add(header.getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
					fpspddr_insert_list.add(header.getBizMsgIdr());
					fpspddr_insert_list.add(header.getMsgDefIdr());
					fpspddr_insert_list.add(header.getBizSvc().value());
					fpspddr_insert_list.add(header.getCreDt().toString());

					fpspddr_insert_list.add(fitofipmtstsrpt.getGrpHdr().getMsgId());
					fpspddr_insert_list.add(fitofipmtstsrpt.getGrpHdr().getCreDtTm().toString());
					fpspddr_insert_list.add(fitofipmtstsrpt.getOrgnlGrpInfAndSts().getOrgnlMsgId());
					fpspddr_insert_list.add(fitofipmtstsrpt.getOrgnlGrpInfAndSts().getOrgnlMsgNmId());

					PaymentTransaction801 pt801 = fitofipmtstsrpt.getTxInfAndSts().get(0);
					fpspddr_insert_list.add(pt801.getOrgnlEndToEndId());
					fpspddr_insert_list.add(pt801.getOrgnlTxId());

					fpspddr_insert_list.add(pt801.getTxSts().value());

					if (pt801.getTxSts().value().equals("RJCT") && pt801.getStsRsnInf() != null) {

						if (pt801.getStsRsnInf().getRsn() != null && pt801.getStsRsnInf().getRsn().getPrtry() != null) {
							fpspddr_insert_list.add(pt801.getStsRsnInf().getRsn().getPrtry());
						} else {
							fpspddr_insert_list.add("");
						}

						if (pt801.getStsRsnInf().getAddtlInf() != null) {
							List<String> addtlInfList = pt801.getStsRsnInf().getAddtlInf();
							StringBuffer addtlInfStrBuf = new StringBuffer();
							for (int i = 0; i < addtlInfList.size(); i++) {
								addtlInfStrBuf.append(addtlInfList.get(i));
							}
							fpspddr_insert_list.add(addtlInfStrBuf.toString());
						} else {
							fpspddr_insert_list.add("");
						}

					} else {

						fpspddr_insert_list.add("");
						fpspddr_insert_list.add("");

					}

					fpspddr_insert_list.add((fileName.split("\\."))[0]);
					fpspddr_insert_list.add(fitofipmtstsrpt.getGrpHdr().getMsgId());
					EntityManager.update(conn, insert_tb_tx_fpspddr_sql, fpspddr_insert_list.toArray());

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				this.batchLogger.error(e.getMessage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				this.batchLogger.error(e.getMessage());
			} 
			
		}

		Date insertEndTime = Calendar.getInstance().getTime();
		this.batchLogger.info(String.format("Finish Inserting Into tb_tx_res_fpspddr_temp At %s", insertEndTime));
		this.batchLogger.info(String.format("Inserting Data Into tb_tx_res_fpspddr_temp Use %.3f Seconds",
				(double) (insertEndTime.getTime() - insertStartTime.getTime()) / 1000));

	};

	// delete file
	private void deleteFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new IOException(String.format("The File(%s) Is Not Exists!", filePath));
		}

		file.delete();
		this.batchLogger.info(String.format("The File(%s) Has Been Deleted!", filePath));
	}

}
