package com.forms.framework.job.common.httpservice;

public class HttpUtil
{
	private static final String CON_HTTP = "HTTP:";

	private static final String CON_HTTPS = "HTTPS:";

	public static BaseHttpConn getHttpInterface(String url)
			throws Exception
	{
		if (url != null && url.toUpperCase().startsWith(CON_HTTP))
		{
			return new HttpConn(url);
		} else if (url != null && url.toUpperCase().startsWith(CON_HTTPS))
		{
			return new HttpsConn(url);
		} else
		{
			throw new Exception("url:" + url + " is error");
		}
	}
}
