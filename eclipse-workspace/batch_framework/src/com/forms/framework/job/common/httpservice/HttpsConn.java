package com.forms.framework.job.common.httpservice;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsConn extends BaseHttpConn
{
	/** URL Object */
	private URL prv_obj_url = null;

	/** HttpURLConnection */
	private HttpsURLConnection prv_obj_conn = null;

	public HttpsConn(String ip_url) throws Exception
	{
		try
		{
			prv_obj_url = new URL(ip_url);
		} catch (Exception e)
		{
			throw new Exception(e);
		}
	}

	private static class TrustAnyTrustManager implements X509TrustManager
	{

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException
		{
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException
		{
		}

		public X509Certificate[] getAcceptedIssuers()
		{
			return new X509Certificate[] {};
		}
	}

	 private static class TrustAnyHostnameVerifier implements HostnameVerifier
	{
		public boolean verify(String hostname, SSLSession session)
		{
			return true;
		}
	}

	@Override
	protected HttpURLConnection getHttpURLConnection() throws Exception
	{
		try
		{
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			prv_obj_conn = (HttpsURLConnection) prv_obj_url.openConnection();
			prv_obj_conn.setSSLSocketFactory(sc.getSocketFactory());
			 prv_obj_conn.setHostnameVerifier(new TrustAnyHostnameVerifier());

			prv_obj_conn.setDoInput(true);
			prv_obj_conn.setDoOutput(true);

			prv_obj_conn.connect();
		} catch (Exception e)
		{
			throw e;
		}
		return prv_obj_conn;
	}
}
