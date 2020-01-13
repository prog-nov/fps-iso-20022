package com.forms.ffp.bussiness;

import java.util.ArrayList;
import java.util.List;

import com.forms.producebean.com.ffp.dao.TbMsgJnl;
import com.forms.producebean.com.ffp.dao.TbMsgJnlAction;

public class FFPJbBase {
	private String clearCode;
	private String bicCode;

	private TbMsgJnl tbMsgJnl;
	private List<TbMsgJnlAction> tbMsgJnlAction;
	private List<Object> list = new ArrayList<>();

	public String getClearCode() {
		return clearCode;
	}

	public void setClearCode(String clearCode) {
		this.clearCode = clearCode;
	}

	public String getBicCode() {
		return bicCode;
	}

	public void setBicCode(String bicCode) {
		this.bicCode = bicCode;
	}

	public TbMsgJnl getTbMsgJnl() {
		if(tbMsgJnl == null){
			tbMsgJnl = new TbMsgJnl();
		}
		return tbMsgJnl;
	}

	public void setTbMsgJnl(TbMsgJnl tbMsgJnl) {
		this.tbMsgJnl = tbMsgJnl;
	}

	public List<TbMsgJnlAction> getTbMsgJnlAction() {
		if(tbMsgJnlAction == null){
			tbMsgJnlAction = new ArrayList<TbMsgJnlAction>();
		}
		return tbMsgJnlAction;
	}

	public void setTbMsgJnlAction(List<TbMsgJnlAction> tbMsgJnlAction) {
		this.tbMsgJnlAction = tbMsgJnlAction;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

}
