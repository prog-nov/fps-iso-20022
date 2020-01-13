package com.forms.framework.job.common.httpservice;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConn extends BaseHttpConn
{

	/** URL Object */
	private URL prv_obj_url = null;

	/** HttpURLConnection */
	private HttpURLConnection prv_obj_conn = null;

	public HttpConn(String ip_url) throws Exception
	{
		try
		{
			prv_obj_url = new URL(ip_url);
		} catch (Exception e)
		{
			throw new Exception(e);
		}
	}

	@Override
	protected HttpURLConnection getHttpURLConnection() throws Exception
	{
		prv_obj_conn = (HttpURLConnection) prv_obj_url.openConnection();
		return prv_obj_conn;
	}
}
