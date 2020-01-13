package com.forms.framework.job.common.httpservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public abstract class BaseHttpConn
{
	public boolean HttpIsConnect() throws Exception
	{
		int loc_int_conn = getHttpURLConnection().getResponseCode();
		return HttpURLConnection.HTTP_OK == loc_int_conn;
	}

	public void HttpGetFile(String loc_str_targetPath) throws Exception
	{
		FileOutputStream loc_obj_outStream = null;
		InputStream loc_obj_inStream = null;
		try
		{
			File file = new File(loc_str_targetPath);
			if (!file.getParentFile().exists())
			{
				file.getParentFile().mkdirs();
			}
			loc_obj_outStream = new FileOutputStream(file);
			loc_obj_inStream = getHttpURLConnection().getInputStream();

			int bufferSize = 2048;
			byte[] bytes = new byte[bufferSize];
			int readSize = 0;

			while ((readSize = loc_obj_inStream.read(bytes, 0, bytes.length)) != -1)
			{
				loc_obj_outStream.write(bytes, 0, readSize);
			}
		} catch (IOException e)
		{
			throw e;
		} finally
		{
			if (loc_obj_outStream != null)
			{
				loc_obj_outStream.close();
			}
			if (loc_obj_inStream != null)
			{
				loc_obj_inStream.close();
			}
		}
	}

	public void closeConn() throws Exception
	{
		if (getHttpURLConnection() != null)
		{
			getHttpURLConnection().disconnect();
		}
	}

	protected abstract HttpURLConnection getHttpURLConnection() throws Exception;

}
