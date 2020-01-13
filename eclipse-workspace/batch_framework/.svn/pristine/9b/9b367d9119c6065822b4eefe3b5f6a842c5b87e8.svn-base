package batch.job.unit.httpdownload;

import java.io.File;
import java.util.Properties;

import com.forms.framework.exception.BatchJobException;
import com.forms.framework.job.HttpDownloadFileJob;
import com.forms.framework.job.common.httpservice.BaseHttpConn;
import com.forms.framework.job.common.httpservice.HttpUtil;
import com.forms.framework.util.ResourceUtil;

public class HttpDownloadFile extends HttpDownloadFileJob
{
	private String xmlurlStr;
	
	private String xsdurlStr;
	
	private String typexsdurlStr;

	private String xmlFileName;
	
	private String xsdFileName;
	
	private String typeFileName;
	
	@Override
	public void init() throws BatchJobException
	{
		try
		{
			xmlFileName = this.jobConfiger.getElementsByPath(
					"job-config.private-settings.interface-xml-file").get(0)
					.getText();
			if (xmlFileName == null || "".equals(xmlFileName))
			{
				throw new BatchJobException(
						"interface-xml-file config not found");
			}
			
			xsdFileName = this.jobConfiger.getElementsByPath(
					"job-config.private-settings.interface-xsd-file").get(0)
					.getText();
			if (xsdFileName == null || "".equals(xsdFileName))
			{
				throw new BatchJobException(
						"interface-xsd-file config not found");
			}
			
			typeFileName = this.jobConfiger.getElementsByPath(
					"job-config.private-settings.interface-typexsd-file").get(0)
					.getText();
			if (typeFileName == null || "".equals(typeFileName))
			{
				throw new BatchJobException(
						"interface-typexsd-file config not found");
			}
			
			String loc_httpproperty = this.jobConfiger.getElementsByPath(
					"job-config.private-settings.property-resource").get(0)
					.getText();
			if (loc_httpproperty == null || "".equals(loc_httpproperty))
			{
				throw new BatchJobException(
						"property-resource config not found");
			}
			
			if(this.jobConfiger.getElementsByPath(
				"job-config.private-settings.connectTime").size()>0)
			{
				connectTimes=Integer.parseInt(this.jobConfiger.getElementsByPath(
					"job-config.private-settings.connectTime").get(0)
					.getTextTrim());
			}
			
			if(this.jobConfiger.getElementsByPath(
				"job-config.private-settings.sleepTime").size()>0)
			{
				sleepTime=Integer.parseInt(this.jobConfiger.getElementsByPath(
					"job-config.private-settings.sleepTime").get(0)
					.getTextTrim());
			}
			
			String loc_url_xml = this.jobConfiger.getElementsByPath(
				"job-config.private-settings.xml-url-path").get(0).getTextTrim();
			
			String loc_url_xsd = this.jobConfiger.getElementsByPath(
				"job-config.private-settings.xsd-url-path").get(0).getTextTrim();
			
			String loc_url_typexsd = this.jobConfiger.getElementsByPath(
				"job-config.private-settings.typexsd-url-path").get(0).getTextTrim();
			
			if (loc_url_xml == null || "".equals(loc_url_xml) ||
					loc_url_xsd == null || "".equals(loc_url_xsd) ||
					loc_url_typexsd == null || "".equals(loc_url_typexsd))
			{
				throw new BatchJobException("url-path config not found");
			}
			Properties pro = (Properties) ResourceUtil.getInstance()
					.getResource(loc_httpproperty, ResourceUtil.RESOURCE_PROPERTIES_TYPE);
			xmlurlStr = pro.getProperty(loc_url_xml);
			xsdurlStr = pro.getProperty(loc_url_xsd);
			typexsdurlStr = pro.getProperty(loc_url_typexsd);

		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean execute() throws BatchJobException
	{
		this.getFXC02HttpDownFile(this.xmlurlStr, this.xmlFileName);
		this.getFXC02HttpDownFile(this.xsdurlStr, this.xsdFileName);
		this.getFXC02HttpDownFile(this.typexsdurlStr, this.typeFileName);
		return true;
	}
	
	private boolean getFXC02HttpDownFile(String urlStr, String filename) throws BatchJobException
	{
		BaseHttpConn loc_http_conn = null;
		try
		{
			int i=0;
			while (i < connectTimes)
			{
				i++;
				try {
					if(i>1){
						this.batchLogger.info("retry download file to "
								+ this.batchData + File.separator + filename);
					}
					loc_http_conn = HttpUtil.getHttpInterface(urlStr);
					if (!loc_http_conn.HttpIsConnect()) {
						throw new Exception("connect URL " + urlStr + "fail");
					}
					loc_http_conn.HttpGetFile(this.batchData
							+ File.separator + filename);
					this.batchLogger.info("download file to "
							+ this.batchData + File.separator + filename);
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
