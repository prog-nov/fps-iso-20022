package com.forms.ffp.persistents.bean.addressing;

public class FFPJbAddressing {
	
	private String ProxyId;
	private String ProxyIdTp;
	private String CusId;
	private String AcctNum;
	private String AcctTp;
	private String AcctCur;
	private int AcctDef;
	private String SrvcTp;
	private String ClrCd;
	private String LstUpJnl;
	
	
	public void setLstUpJnl(String lstUpJnl) {
		LstUpJnl = lstUpJnl;
	}
	public String getLstUpJnl() {
		return LstUpJnl;
	}
	
	
	public String getClrCd() {
		return ClrCd;
	}
	public void setClrCd(String clrCd) {
		ClrCd = clrCd;
	}
	public String getAcctNum() {
		return AcctNum;
	}
	public void setAcctNum(String acctNum) {
		AcctNum = acctNum;
	}
	public String getProxyId() {
		return ProxyId;
	}
	public void setProxyId(String proxyId) {
		ProxyId = proxyId;
	}
	public String getProxyIdTp() {
		return ProxyIdTp;
	}
	public void setProxyIdTp(String proxyIdTp) {
		ProxyIdTp = proxyIdTp;
	}
	public String getCusId() {
		return CusId;
	}
	public void setCusId(String cusId) {
		CusId = cusId;
	}
	public String getAcctTp() {
		return AcctTp;
	}
	public void setAcctTp(String acctTp) {
		AcctTp = acctTp;
	}
	public String getAcctCur() {
		return AcctCur;
	}
	public void setAcctCur(String acctCur) {
		AcctCur = acctCur;
	}
	public int getAcctDef() {
		return AcctDef;
	}
	public void setAcctDef(int acctDef) {
		AcctDef = acctDef;
	}
	public String getSrvcTp() {
		return SrvcTp;
	}
	public void setSrvcTp(String srvcTp) {
		SrvcTp = srvcTp;
	}
	

}
