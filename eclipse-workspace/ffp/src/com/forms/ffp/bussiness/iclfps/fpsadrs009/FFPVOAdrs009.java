package com.forms.ffp.bussiness.iclfps.fpsadrs009;

import java.util.Date;
import java.util.List;

import com.forms.ffp.bussiness.FFPVOBase;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120dtl;
/**
 * addressing enquiry report
 * @author yrd
 *
 */
public class FFPVOAdrs009 extends FFPVOBase {

	private String msgId;
	
	private Date creDtTm;
	
	private String orgnlMsgId;
	
	private List<FFPJbA120dtl> list;

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

	public List<FFPJbA120dtl> getList() {
		return list;
	}

	public void setList(List<FFPJbA120dtl> list) {
		this.list = list;
	}
	
	
	
	

}
