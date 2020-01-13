package com.forms.framework.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Element;

import com.forms.framework.exception.BatchJobException;
import com.forms.framework.util.DateUtil;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.framework.BatchBaseJob;

public class CheckFileAcDtJob extends BatchBaseJob
{

	List<Element> fileElements = null;

	String targetISB = "ISB";

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws BatchJobException
	{
		// TODO Auto-generated method stub
		super.init();
		try
		{
			Element fileList = this.actionElement.element("file-list");
			if (fileList == null)
			{
				throw new BatchJobException(
						"file-list confit not found in config");
			}
			fileElements = fileList.elements("file");
			if (fileElements == null || fileElements.size() == 0)
			{
				throw new BatchJobException(
						"file confit not found in config");
			}
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		}
	}

	@Override
	public boolean execute() throws BatchJobException
	{
		String fileName;
		int recLen = 0;
		String encoding;
		String target;
		try
		{
			for (Element fileElement : fileElements)
			{
				recLen = Integer.parseInt(fileElement.attributeValue("reclen"));
				encoding = fileElement.attributeValue("encoding");
				target = fileElement.attributeValue("target");
				if (targetISB.equals(target))
				{
					fileName = this.batchIsb + File.separator + targetISB
							+ File.separator + fileElement.getText();
				} else
				{
					fileName = this.batchData + File.separator
							+ fileElement.getText();
				}
				this.checkFileAcdt(fileName, recLen, encoding);
			}
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		}
		return true;
	}

	private void checkFileAcdt(String fileName, int recLen, String encoding)
			throws Exception
	{
		File file = new File(fileName);
		InputStream is = null;
		byte[] bytes = new byte[recLen];
		String footStr = "";
		String loc_fileAcDate;
		try
		{
			is = new FileInputStream(file);
			is.skip(file.length() - recLen);
			is.read(bytes);
			footStr = new String(bytes, encoding);
			loc_fileAcDate = DateUtil.dateToString(DateUtil.stringToDate(
					this.substrAcdtFromFoot(footStr), "yyyy/MM/dd"),
					DateUtil.BATCH_DATE_FORMAT);
			if (!loc_fileAcDate.equals(this.batchAcDate))
			{
				throw new DataPipeException("File " + file.getName()
						+ " acDate error.File acDate=" + loc_fileAcDate
						+ ",Batch acDate=" + this.batchAcDate);

			}
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					throw e;
				}
			}
		}
	}

	private String substrAcdtFromFoot(String footer)
	{
		return footer.substring(11, 21);
	}

}
