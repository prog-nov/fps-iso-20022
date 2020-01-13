package com.forms.ffp.bussiness.iclfps.camt052;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.iclfps.camt_052_001_06.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.tx.inquiry.I100.FFPJbI100;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnl;
import com.forms.ffp.persistents.dao.FFPIDao_TxJnlAction;
import com.forms.ffp.persistents.dao.tx.inquiry.I100.FFPIDao_I100;

@Component("ICL.camt.052.001.06")
@Scope("prototype")
public class FFPTxCamt052 extends FFPTxBase
{
	@Resource(name="FFPIDao_I100")
	private FFPIDao_I100 dao;
	
	@Resource(name="FFPIDao_TxJnl")
	private FFPIDao_TxJnl jnlDao;
	
	@Resource(name="FFPIDao_TxJnlAction")
	private FFPIDao_TxJnlAction actionDao;
	
	@Override
	public void perform() throws Exception
	{
		if ("ICL.camt.052.001.06".equals(this.serviceName))
		{
			FFPVOCamt052 loc_jb = (FFPVOCamt052) txVo;
			FFPJbI100 i100 = dao.inquiryLastI100DATByCcy(loc_jb.getAcctCcy());
			i100.setTxJnl(jnlDao.inquiryByJnlNo(i100.getJnlNo()));
			i100.setJnlActionList(actionDao.inquiryByJnlNo(i100.getJnlNo()));
			
			i100.setBalanceTypeCode(loc_jb.getBalTpCd());
			i100.setBalance(loc_jb.getBalAmt());
			i100.setCreditDebitInd(loc_jb.getBalCdtDbtInd());
			i100.setBalanceRptTs(loc_jb.getRpdCreDtTm());
//			dao.updateI100(i100);
			
			Date loc_date = new Date();
			FFPTxJnlAction action = FFPJnlUtils.getInstance().newJnlAction(i100.getJnlNo(), txVo.getBizMsgIdr(),
						FFPConstants.MSG_DIRECTION_INWARD, FFPConstants.RELATION_SYSTEM_HKICL, txVo.getMsgDefIdr(),
						FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), txVo.getCreateDate(), loc_date, loc_date, i100.getJnlActionList().get(0).getMsgId());
			i100.getJnlActionList().add(action);
			
			i100.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus());
			i100.getTxJnl().setLastUpdateTs(loc_date);
			
			dao.updateJbI100(i100);
		}
	}

	@Override
	public boolean validate() throws Exception
	{
		return true;
	}

	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception
	{
		if ("ICL.camt.052.001.06".equals(this.serviceName))
		{
			txVo = new FFPVOCamt052();
			FFPVOCamt052 loc_jb = (FFPVOCamt052) txVo;
			parseISO20022BizDataHead(bizData);
			
			Document doc = (Document) bizData.getContent().get(1).getValue();
			loc_jb.setMsgId(doc.getBkToCstmrAcctRpt().getGrpHdr().getMsgId());
			loc_jb.setCreateDate(doc.getBkToCstmrAcctRpt().getGrpHdr().getCreDtTm().toGregorianCalendar().getTime());
			loc_jb.setRpdId(doc.getBkToCstmrAcctRpt().getRpt().getId());
			loc_jb.setRpdCreDtTm(doc.getBkToCstmrAcctRpt().getRpt().getCreDtTm().toGregorianCalendar().getTime());
			loc_jb.setAcctId(doc.getBkToCstmrAcctRpt().getRpt().getAcct().getId().getOthr().getId());
			loc_jb.setAcctCcy(doc.getBkToCstmrAcctRpt().getRpt().getAcct().getCcy().value());
			loc_jb.setBalTpCd(doc.getBkToCstmrAcctRpt().getRpt().getBal().getTp().getCdOrPrtry().getCd().value());
			loc_jb.setBalAmtCcy(doc.getBkToCstmrAcctRpt().getRpt().getBal().getAmt().getCcy().value());
			loc_jb.setBalAmt(doc.getBkToCstmrAcctRpt().getRpt().getBal().getAmt().getValue());
			loc_jb.setBalCdtDbtInd(doc.getBkToCstmrAcctRpt().getRpt().getBal().getCdtDbtInd().value());
			loc_jb.setBalDtTm(doc.getBkToCstmrAcctRpt().getRpt().getBal().getDt().getDtTm().toGregorianCalendar().getTime());;
		}
	}
}
