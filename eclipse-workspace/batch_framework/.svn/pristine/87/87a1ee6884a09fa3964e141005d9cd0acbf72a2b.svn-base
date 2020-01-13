/**
 * updateDate:2012-03-22
 *	ModifiedBy:LYZ
 *	ModifiedReason:update http download job
 *	updateVersion:7 month
 */
package com.forms.framework.job;

import java.io.File;
import java.util.Properties;

import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.job.common.httpservice.BaseHttpConn;
import com.forms.framework.job.common.httpservice.HttpUtil;
import com.forms.framework.util.ResourceUtil;

public class HttpDownloadFileJob extends BatchBaseJob
{

	protected String urlStr;

	protected String fileName;
	
	protected int connectTimes=1;
	
	protected int sleepTime=30*1000;

	@Override
	public void init() throws BatchJobException
	{
		super.init();
		try
		{
			fileName = this.jobConfiger.getElementsByPath(
					"job-config.private-settings.interface-file").get(0)
					.getText();
			if (fileName == null || "".equals(fileName))
			{
				throw new BatchJobException(
						"interface-file config not found");
			}
			String loc_httpproperty = this.jobConfiger.getElementsByPath(
					"job-config.private-settings.property-resource").get(0)
					.getText();
			if (loc_httpproperty == null || "".equals(loc_httpproperty))
			{
				throw new BatchJobException(
						"property-resource config not found");
			}
			String loc_urlname = this.jobConfiger.getElementsByPath(
					"job-config.private-settings.url-path").get(0)
					.getTextTrim();
			if(this.jobConfiger.getElementsByPath(
			"job-config.private-settings.connectTime").size()>0){
				connectTimes=Integer.parseInt(this.jobConfiger.getElementsByPath(
				"job-config.private-settings.connectTime").get(0)
				.getTextTrim());
			}
			if(this.jobConfiger.getElementsByPath(
			"job-config.private-settings.sleepTime").size()>0){
				sleepTime=Integer.parseInt(this.jobConfiger.getElementsByPath(
				"job-config.private-settings.sleepTime").get(0)
				.getTextTrim());
			}
			if (loc_urlname == null || "".equals(loc_urlname))
			{
				throw new BatchJobException("url-path config not found");
			}
			Properties pro = (Properties) ResourceUtil.getInstance().getResource(loc_httpproperty, ResourceUtil.RESOURCE_PROPERTIES_TYPE);
			urlStr = pro.getProperty(loc_urlname);

		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
	}

	@Override
	public boolean execute() throws BatchJobException
	{
		BaseHttpConn loc_http_conn = null;
		try
		{
			int i=0;
			while (i < connectTimes) {
				i++;
				try {
					if(i>1){
						this.batchLogger.info("retry download file to "
								+ this.batchData + File.separator + fileName);
					}
					loc_http_conn = HttpUtil.getHttpInterface(urlStr);
					if (!loc_http_conn.HttpIsConnect()) {
						throw new Exception("connect URL " + urlStr + "fail");
					}
					loc_http_conn.HttpGetFile(this.batchData
							+ File.separator + fileName);
					this.batchLogger.info("download file to "
							+ this.batchData + File.separator + fileName);
					break;
				} catch (Exception e) {
					if (i < connectTimes) {
						this.batchLogger.warn("Connect times :" + i + " ;connect URL " + urlStr + "fail!");
						Thread.sleep(sleepTime);
						continue;
					} else {
						throw new Exception(e);
					}
				}
			}
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		} finally
		{
			try
			{
				loc_http_conn.closeConn();
			} catch (Exception ip_e)
			{
				throw new BatchJobException(ip_e);
			}
		}

		return true;
	}

}
