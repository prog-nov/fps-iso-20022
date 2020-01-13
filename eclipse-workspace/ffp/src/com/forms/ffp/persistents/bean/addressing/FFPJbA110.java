package com.forms.ffp.persistents.bean.addressing;


import java.util.List;

import com.forms.ffp.persistents.bean.FFPJbBaseFin;

public class FFPJbA110 extends FFPJbBaseFin
{
	private static final long serialVersionUID = 1L;

	/**
	 *   `JNL_NO` varchar(35) NOT NULL,
  		`SRC_REF_NM` varchar(30) DEFAULT NULL,
  		`ADR_REQ_ID` varchar(35) DEFAULT NULL,
  		`PROXY_ID` varchar(34) DEFAULT NULL,
  		`PROXY_ID_TP` varchar(4) DEFAULT NULL,
	 */
	
	private String srcRefNm;
	
	private String adrReqId;
	
	private String proxyId;
	
	private String proxyIdTp;
	
	private List<FFPJbA110dtl> adrList;
	
	private String stat;
	
	private String noOfAdr;
	
	
	
	public String getNoOfAdr() {
		return noOfAdr;
	}

	public void setNoOfAdr(String noOfAdr) {
		this.noOfAdr = noOfAdr;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public List<FFPJbA110dtl> getAdrList() {
		return adrList;
	}

	public void setAdrList(List<FFPJbA110dtl> adrList) {
		this.adrList = adrList;
	}

	public String getSrcRefNm() {
		return srcRefNm;
	}

	public void setSrcRefNm(String srcRefNm) {
		this.srcRefNm = srcRefNm;
	}

	public String getAdrReqId() {
		return adrReqId;
	}

	public void setAdrReqId(String adrReqId) {
		this.adrReqId = adrReqId;
	}

	public String getProxyId() {
		return proxyId;
	}

	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}

	public String getProxyIdTp() {
		return proxyIdTp;
	}

	public void setProxyIdTp(String proxyIdTp) {
		this.proxyIdTp = proxyIdTp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
