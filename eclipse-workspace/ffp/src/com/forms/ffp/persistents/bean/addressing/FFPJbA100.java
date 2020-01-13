package com.forms.ffp.persistents.bean.addressing;


import java.util.ArrayList;
import java.util.List;

import com.forms.ffp.persistents.bean.FFPJbBaseFin;

public class FFPJbA100 extends FFPJbBaseFin
{
	private static final long serialVersionUID = 1L;

	private String AdrReqId;
	private String MsgType;
	private String ProxyId;
	private String ProxyIdTp;
	private String ClrCd;
	private String LangEn;
	private String FullNmEn;
	private String DispNmEn;
	private String LangZh;
	private String FullNmZh;
	private String DispNmZh;
	private String CusId;
	private String CusTp;
	private String SupOpCd;
	private String Dflt;
	private String PurpCd;
	
	private List<FFPJbAddressing> jbAdrsList;
	
	public String getAdrReqId() {
		return AdrReqId;
	}
	public void setAdrReqId(String adrReqId) {
		AdrReqId = adrReqId;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
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
	public String getLangEn() {
		return LangEn;
	}
	public void setLangEn(String langEn) {
		LangEn = langEn;
	}
	public String getFullNmEn() {
		return FullNmEn;
	}
	public void setFullNmEn(String fullNmEn) {
		FullNmEn = fullNmEn;
	}
	public String getDispNmEn() {
		return DispNmEn;
	}
	public void setDispNmEn(String dispNmEn) {
		DispNmEn = dispNmEn;
	}
	public String getLangZh() {
		return LangZh;
	}
	public void setLangZh(String langZh) {
		LangZh = langZh;
	}
	public String getFullNmZh() {
		return FullNmZh;
	}
	public void setFullNmZh(String fullNmZh) {
		FullNmZh = fullNmZh;
	}
	public String getDispNmZh() {
		return DispNmZh;
	}
	public void setDispNmZh(String dispNmZh) {
		DispNmZh = dispNmZh;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCusId() {
		return CusId;
	}
	public void setCusId(String cusId) {
		CusId = cusId;
	}
	public String getCusTp() {
		return CusTp;
	}
	public void setCusTp(String cusTp) {
		CusTp = cusTp;
	}
	public String getSupOpCd() {
		return SupOpCd;
	}
	public void setSupOpCd(String supOpCd) {
		SupOpCd = supOpCd;
	}
	public String getDflt() {
		return Dflt;
	}
	public void setDflt(String dflt) {
		Dflt = dflt;
	}
	public String getPurpCd() {
		return PurpCd;
	}
	public void setPurpCd(String purpCd) {
		PurpCd = purpCd;
	}
	
	public String getClrCd() {
		return ClrCd;
	}
	public void setClrCd(String clrCd) {
		ClrCd = clrCd;
	}
	public List<FFPJbAddressing> getJbAdrsList() {
		if(jbAdrsList == null){
			return new ArrayList<>();
		}
		return jbAdrsList;
	}
	public void setJbAdrsList(List<FFPJbAddressing> jbAdrsList) {
		this.jbAdrsList = jbAdrsList;
	}
	
	
}
