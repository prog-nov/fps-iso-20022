package com.forms.ffp.bussiness.iclfps.fpsadrs005;

import java.util.Date;
import java.util.List;

import com.forms.ffp.bussiness.FFPVOBase;
import com.forms.ffp.persistents.bean.addressing.FFPJbA200;

public class FFPVo_Fpsadrs005 extends FFPVOBase{
	
	//GrpHdr
	private String msgId;
	private Date creDtTm;
	private List<AdrSchme> adrSchmes;
	private FFPJbA200 jbA200;
	
	private FFPVo_Fpsadrs005_AUN01REPLY reply;
	
	
	public FFPVo_Fpsadrs005_AUN01REPLY getReply() {
		return reply;
	}


	public void setReply(FFPVo_Fpsadrs005_AUN01REPLY reply) {
		this.reply = reply;
	}


	public String getMsgId() {
		return msgId;
	}


	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}


	public Date getCreDtTm() {
		return creDtTm;
	}


	public void setCreDtTm(Date creDtTm) {
		this.creDtTm = creDtTm;
	}


	public List<AdrSchme> getAdrSchmes() {
		return adrSchmes;
	}


	public void setAdrSchmes(List<AdrSchme> adrSchmes) {
		this.adrSchmes = adrSchmes;
	}


	public class AdrSchme{
		private String srcRefNm;
		private String adrRptId;
		private String proxyId;
		private String proxyIdType;
		private String cusId;
		private String status;
		private Date statusUpdateTime;
		private String defaultIndicator;
		//InstgAgt
		private String clrCd;
		
		public String getSrcRefNm() {
			return srcRefNm;
		}
		public void setSrcRefNm(String srcRefNm) {
			this.srcRefNm = srcRefNm;
		}
		public String getAdrRptId() {
			return adrRptId;
		}
		public void setAdrRptId(String adrRptId) {
			this.adrRptId = adrRptId;
		}
		public String getProxyId() {
			return proxyId;
		}
		public void setProxyId(String proxyId) {
			this.proxyId = proxyId;
		}
		public String getProxyIdType() {
			return proxyIdType;
		}
		public void setProxyIdType(String proxyIdType) {
			this.proxyIdType = proxyIdType;
		}
		public String getCusId() {
			return cusId;
		}
		public void setCusId(String cusId) {
			this.cusId = cusId;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDefaultIndicator() {
			return defaultIndicator;
		}
		public void setDefaultIndicator(String defaultIndicator) {
			this.defaultIndicator = defaultIndicator;
		}
		public Date getStatusUpdateTime() {
			return statusUpdateTime;
		}
		public void setStatusUpdateTime(Date statusUpdateTime) {
			this.statusUpdateTime = statusUpdateTime;
		}
		public String getClrCd() {
			return clrCd;
		}
		public void setClrCd(String clrCd) {
			this.clrCd = clrCd;
		}
		
		
	}


	public FFPJbA200 getJbA200() {
		return jbA200;
	}


	public void setJbA200(FFPJbA200 jbA200) {
		this.jbA200 = jbA200;
	}
	
	
}
