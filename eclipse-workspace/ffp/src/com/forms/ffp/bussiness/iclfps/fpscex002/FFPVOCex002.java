package com.forms.ffp.bussiness.iclfps.fpscex002;

import java.util.Date;

import com.forms.ffp.bussiness.FFPVOBase;
/**
 * The certificate exchange response message
 * @author yrd
 *
 */
public class FFPVOCex002 extends FFPVOBase {
	private String msgId;
	private Date creDtTm;
	
	private String orgnlMsgId;
	private String isMsgRjct;
	private String msgRjctCd;
	private String msgAcptNewCertCnt;
	private String msgAcptRevokedCertCnt;
	private String msgRjctNewCertCnt;
	private String msgRjctRevokedCertCnt;
	
	private String rjctNewCertCd;
	private String rjctNewCerteCert;
	private String rjctRevokedCertCd;
	private String rjctRevokedCerteCert;
	
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
	public String getIsMsgRjct() {
		return isMsgRjct;
	}
	public void setIsMsgRjct(String isMsgRjct) {
		this.isMsgRjct = isMsgRjct;
	}
	public String getMsgRjctCd() {
		return msgRjctCd;
	}
	public void setMsgRjctCd(String msgRjctCd) {
		this.msgRjctCd = msgRjctCd;
	}
	public String getMsgAcptNewCertCnt() {
		return msgAcptNewCertCnt;
	}
	public void setMsgAcptNewCertCnt(String msgAcptNewCertCnt) {
		this.msgAcptNewCertCnt = msgAcptNewCertCnt;
	}
	public String getMsgAcptRevokedCertCnt() {
		return msgAcptRevokedCertCnt;
	}
	public void setMsgAcptRevokedCertCnt(String msgAcptRevokedCertCnt) {
		this.msgAcptRevokedCertCnt = msgAcptRevokedCertCnt;
	}
	public String getMsgRjctNewCertCnt() {
		return msgRjctNewCertCnt;
	}
	public void setMsgRjctNewCertCnt(String msgRjctNewCertCnt) {
		this.msgRjctNewCertCnt = msgRjctNewCertCnt;
	}
	public String getMsgRjctRevokedCertCnt() {
		return msgRjctRevokedCertCnt;
	}
	public void setMsgRjctRevokedCertCnt(String msgRjctRevokedCertCnt) {
		this.msgRjctRevokedCertCnt = msgRjctRevokedCertCnt;
	}
	public String getRjctNewCertCd() {
		return rjctNewCertCd;
	}
	public void setRjctNewCertCd(String rjctNewCertCd) {
		this.rjctNewCertCd = rjctNewCertCd;
	}
	public String getRjctNewCerteCert() {
		return rjctNewCerteCert;
	}
	public void setRjctNewCerteCert(String rjctNewCerteCert) {
		this.rjctNewCerteCert = rjctNewCerteCert;
	}
	public String getRjctRevokedCertCd() {
		return rjctRevokedCertCd;
	}
	public void setRjctRevokedCertCd(String rjctRevokedCertCd) {
		this.rjctRevokedCertCd = rjctRevokedCertCd;
	}
	public String getRjctRevokedCerteCert() {
		return rjctRevokedCerteCert;
	}
	public void setRjctRevokedCerteCert(String rjctRevokedCerteCert) {
		this.rjctRevokedCerteCert = rjctRevokedCerteCert;
	}
	
	
}
