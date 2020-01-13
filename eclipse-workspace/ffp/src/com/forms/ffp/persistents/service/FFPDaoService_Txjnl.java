package com.forms.ffp.persistents.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.addressing.FFPJbA100;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP100;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP110;
import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP200;
import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP210;
import com.forms.ffp.persistents.bean.payment.returnrefund.FFPJbP300;
import com.forms.ffp.persistents.bean.tx.m100.FFPJbM100;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnl;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnlAction;
import com.forms.ffp.persistents.dao.addressing.FFPIDao_A100;
import com.forms.ffp.persistents.dao.payment.credittransfer.FFPIDao_P100;
import com.forms.ffp.persistents.dao.payment.credittransfer.FFPIDao_P110;
import com.forms.ffp.persistents.dao.payment.directdebit.FFPIDao_P200;
import com.forms.ffp.persistents.dao.payment.directdebit.FFPIDao_P210;
import com.forms.ffp.persistents.dao.payment.returnrefund.FFPIDao_P300;
import com.forms.ffp.persistents.dao.tx.m100.FFPIDao_M100;

@Service("FFPDaoService_Txjnl")
public class FFPDaoService_Txjnl implements FFPIDaoService_Txjnl {
	private static Logger _logger = LoggerFactory.getLogger(FFPDaoService_Txjnl.class);

	@Resource(name = "FFPIDao_TxJnl")
	private FFPIDao_TxJnl dao;

	@Resource(name = "FFPIDao_TxJnlAction")
	private FFPIDao_TxJnlAction dao_JnlAction;

	@Resource(name = "FFPIDao_P100")
	private FFPIDao_P100 dao_p100;

	@Resource(name = "FFPIDao_P110")
	private FFPIDao_P110 dao_p110;

	@Resource(name = "FFPIDao_P200")
	private FFPIDao_P200 dao_p200;

	@Resource(name = "FFPIDao_P210")
	private FFPIDao_P210 dao_p210;

	@Resource(name = "FFPIDao_P300")
	private FFPIDao_P300 dao_p300;

	@Resource(name = "FFPIDao_M100")
	private FFPIDao_M100 dao_m100;

	@Resource(name = "FFPIDao_A100")
	private FFPIDao_A100 dao_a100;
	
	@Override
	public int sInsert(FFPTxJnl jnl) throws Exception {
		int[] rs = dao.dInsert(jnl);
		return rs[0];
	}

	@Override
	public int updateJnl(FFPTxJnl jnl) throws Exception {
		int[] rs = dao.updateJnlStat(jnl);
		return rs[0];
	}

	@Override
	public int updateJnlStat(FFPJbBase form) throws Exception {
		int[] rs = dao.updateJnlStatAndAction(form);
		return rs[0];
	}

	@Override
	public Object inquiryByJnlNo(String jnlNo) throws Exception {
		FFPTxJnl txJnl = dao.inquiryByJnlNo(jnlNo);
		return loadTx(txJnl);
	}

	@Override
	public Object inquiryById(String transactionId, String endToEndId) throws Exception {
		FFPTxJnl txJnl = dao.inquiryById(transactionId, endToEndId);
		return loadTx(txJnl);
	}

	private Object loadTx(FFPTxJnl txJnl) {

		String txCode = txJnl.getTxCode();

		if (FFPConstantsTxJnl.TX_CODE.TX_CODE_P100.getCode().equals(txCode)) {
			FFPJbP100 p100 = dao_p100.inquiryByJnlNo(txJnl);
			if (p100 == null) {
				_logger.warn("!!!!! NOT FOUND DATA(" + txJnl.getJnlNo() + ") IN TB_TX_P100!!!!!");
				return null;
			}
			List<FFPTxJnlAction> jnlActionList = dao_JnlAction.inquiryByJnlNo(txJnl.getJnlNo());
			p100.setJnlActionList(jnlActionList);
			p100.setTxJnl(txJnl);
			return p100;
		} else if (FFPConstantsTxJnl.TX_CODE.TX_CODE_P110.getCode().equals(txCode)) {
			FFPJbP110 p110 = dao_p110.inquiryByJnlNo(txJnl);
			if (p110 == null) {
				_logger.warn("!!!!! NOT FOUND DATA(" + txJnl.getJnlNo() + ") IN TB_TX_P110!!!!!");
				return null;
			}
			List<FFPTxJnlAction> jnlActionList = dao_JnlAction.inquiryByJnlNo(txJnl.getJnlNo());
			p110.setJnlActionList(jnlActionList);
			p110.setTxJnl(txJnl);
			return p110;
		} else if (FFPConstantsTxJnl.TX_CODE.TX_CODE_P200.getCode().equals(txCode)) {
			FFPJbP200 P200 = dao_p200.inquiryByJnlNo(txJnl.getJnlNo());
			if (P200 == null) {
				_logger.warn("!!!!! NOT FOUND DATA(" + txJnl.getJnlNo() + ") IN TB_TX_P200DAT!!!!!");
				return null;
			}
			List<FFPTxJnlAction> jnlActionList = dao_JnlAction.inquiryByJnlNo(txJnl.getJnlNo());
			P200.setJnlActionList(jnlActionList);
			P200.setTxJnl(txJnl);
			return P200;
		} else if (FFPConstantsTxJnl.TX_CODE.TX_CODE_P210.getCode().equals(txCode)) {
			FFPJbP210 P210 = dao_p210.inquiryByJnlNo(txJnl.getJnlNo());
			if (P210 == null) {
				_logger.warn("!!!!! NOT FOUND DATA(" + txJnl.getJnlNo() + ") IN TB_TX_P210DAT!!!!!");
				return null;
			}
			List<FFPTxJnlAction> jnlActionList = dao_JnlAction.inquiryByJnlNo(txJnl.getJnlNo());
			P210.setJnlActionList(jnlActionList);
			P210.setTxJnl(txJnl);
			return P210;
		} else if (FFPConstantsTxJnl.TX_CODE.TX_CODE_P300.getCode().equals(txCode)) {
			FFPJbP300 P300 = dao_p300.inquiryP300ByJnlNo(txJnl.getJnlNo());
			if (P300 == null) {
				_logger.warn("!!!!! NOT FOUND DATA(" + txJnl.getJnlNo() + ") IN TB_TX_P300DAT!!!!!");
				return null;
			}
			List<FFPTxJnlAction> jnlActionList = dao_JnlAction.inquiryByJnlNo(txJnl.getJnlNo());
			P300.setJnlActionList(jnlActionList);
			P300.setTxJnl(txJnl);
			return P300;
		} else if (FFPConstantsTxJnl.TX_CODE.TX_CODE_M100.getCode().equals(txCode)) {
			FFPJbM100 M100 = dao_m100.inquiryM100ByJnlNoOrMsgId(txJnl.getJnlNo(), null);
			if (M100 == null) {
				_logger.warn("!!!!! NOT FOUND DATA(" + txJnl.getJnlNo() + ") IN TB_TX_M100!!!!!");
				return null;
			}
			M100.setTxJnl(txJnl);
			return M100;
		} else if (FFPConstantsTxJnl.TX_CODE.TX_CODE_A100.getCode().equals(txCode)) {
			FFPJbA100 a100 = dao_a100.inqueryJbA100ByJnlNo(txJnl.getJnlNo());
			if (a100 == null) {
				_logger.warn("!!!!! NOT FOUND DATA(" + txJnl.getJnlNo() + ") IN TB_TX_A100!!!!!");
				return null;
			}
			List<FFPTxJnlAction> jnlActionList = dao_JnlAction.inquiryByJnlNo(txJnl.getJnlNo());
			a100.setJnlActionList(jnlActionList);
			a100.setTxJnl(txJnl);
			return a100;

		} else {
			return null;
		}
	}

}
