package com.forms.ffp.bussiness.iclfps.fpsadrs007;

import java.util.Date;
import java.util.List;

import com.forms.ffp.bussiness.FFPVOBase;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110dtl;
/**
 * addressing registration summary report
 * @author yrd
 *
 */
public class FFPVOFpsadrs007 extends FFPVOBase {

	private String msgId;
	
	private String status;
	
	private String noOfAdr;
	
	private String rjCd;
	
	
	private Date creDtTm;
	
	private String orgnlMsgId;

	private List<FFPJbA110dtl> list;
	
	

	public String getRjCd() {
		return rjCd;
	}

	public void setRjCd(String rjCd) {
		this.rjCd = rjCd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNoOfAdr() {
		return noOfAdr;
	}

	public void setNoOfAdr(String noOfAdr) {
		this.noOfAdr = noOfAdr;
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

	public List<FFPJbA110dtl> getList() {
		return list;
	}

	public void setList(List<FFPJbA110dtl> list) {
		this.list = list;
	}
	
//	
//	
}
