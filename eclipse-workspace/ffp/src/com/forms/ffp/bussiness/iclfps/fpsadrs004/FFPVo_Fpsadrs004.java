package com.forms.ffp.bussiness.iclfps.fpsadrs004;

import java.util.Date;
import java.util.List;

import com.forms.ffp.bussiness.FFPVOBase;

public class FFPVo_Fpsadrs004 extends FFPVOBase{
	//GrpHd
	private String msgId;
	private Date  creDtTm;
	
	//OrgnlGrpHdr
	private String orgnlMsgId;
	
	private List<UndrlygDtls> dtls;
	
	public class UndrlygDtls{

		
		private String adrReqId;
		
		private String sts;
		
		
		//StsRsnInf
		//Rsn	
		private String cd;
		
		//AGT
		//FININSTNID
		//cLRSYSmmbid
		private String mmbId;
		
		private String id;
		
		private String tp;

		public String getAdrReqId() {
			return adrReqId;
		}

		public void setAdrReqId(String adrReqId) {
			this.adrReqId = adrReqId;
		}

		public String getSts() {
			return sts;
		}

		public void setSts(String sts) {
			this.sts = sts;
		}

		public String getCd() {
			return cd;
		}

		public void setCd(String cd) {
			this.cd = cd;
		}

		public String getMmbId() {
			return mmbId;
		}

		public void setMmbId(String mmbId) {
			this.mmbId = mmbId;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTp() {
			return tp;
		}

		public void setTp(String tp) {
			this.tp = tp;
		}
		
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

	public String getOrgnlMsgId() {
		return orgnlMsgId;
	}

	public void setOrgnlMsgId(String orgnlMsgId) {
		this.orgnlMsgId = orgnlMsgId;
	}

	public List<UndrlygDtls> getDtls() {
		return dtls;
	}

	public void setDtls(List<UndrlygDtls> dtls) {
		this.dtls = dtls;
	}
	
	

}
