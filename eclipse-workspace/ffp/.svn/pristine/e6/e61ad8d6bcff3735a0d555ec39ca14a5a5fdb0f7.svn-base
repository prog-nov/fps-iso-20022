package com.forms.ffp.bussiness.iclfps.camt056;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.iclfps.camt_056_001_06.CaseAssignment31;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_056_001_06.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_056_001_06.FIToFIPaymentCancellationRequestV06;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP110;
import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP200;
import com.forms.ffp.persistents.service.FFPIDaoService_Txjnl;
import com.forms.ffp.persistents.service.FFPIDaoService_TxjnlAction;
import com.forms.ffp.persistents.service.payment.credittransfer.FFPIDaoService_P110;
import com.forms.ffp.persistents.service.payment.directdebit.FFPIDaoService_P200;

@Component("ICL.camt.056.001.06")
@Scope("prototype")
public class FFPTxCamt056 extends FFPTxBase
{
	@Resource(name = "FFPDaoService_Txjnl")
	private FFPIDaoService_Txjnl txJnlService;

	@Resource(name = "FFPDaoService_P110")
	private FFPIDaoService_P110 daoService;
	
	@Resource(name = "FFPDaoService_P200")
	private FFPIDaoService_P200 daoServiceP200;

	@Resource(name = "FFPDaoService_TxjnlAction")
	private FFPIDaoService_TxjnlAction txJnlActionService;

	@Override
	public void perform() throws Exception
	{
		FFPVOCamt056 loc_camt056 = (FFPVOCamt056) txVo;

		Object object = txJnlService.inquiryById(loc_camt056.getOrgnlTxId(), loc_camt056.getOrgnlEndToEndId());
		if (object == null){
			return;
		}

		if (object instanceof FFPJbP110){
			FFPJbP110 jbP110DB = (FFPJbP110) object;
			List<FFPTxJnlAction> jnlActionList = jbP110DB.getJnlActionList();

			FFPTxJnl txJnl = jbP110DB.getTxJnl();
			FFPTxJnlAction FpsToFfpAction = FFPJnlUtils.getInstance().newJnlAction(
					txJnl.getJnlNo(), loc_camt056.getId(), FFPConstants.MSG_DIRECTION_INWARD, 
					"ICL", loc_camt056.getMsgDefIdr(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), 
					loc_camt056.getCreDtTm(), new Date(), new Date(), 
					null);
			jnlActionList.add(FpsToFfpAction);
			
			txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CANCEL.getStatus());
			if(loc_camt056.getCxlRsnInfPrtry()!=null){
				txJnl.setTxRejCode(loc_camt056.getCxlRsnInfPrtry());
			}
			List<String> addtlInf = loc_camt056.getAddtlInf();
			if (addtlInf != null && addtlInf.size() > 0){
				StringBuffer loc_strBuf = new StringBuffer();
				for(String str : addtlInf){
					loc_strBuf.append(str);
				}
				txJnl.setTxRejReason(loc_strBuf.toString());
			}
			jbP110DB.setTxJnl(txJnl);
			daoService.updateJnlStat(jbP110DB);
		} else if (object instanceof FFPJbP200){
			FFPJbP200 jbP200DB = (FFPJbP200) object;
			
			FFPTxJnlAction FpsToFfpAction = FFPJnlUtils.getInstance().newJnlAction(
					jbP200DB.getTxJnl().getJnlNo(), loc_camt056.getId(), FFPConstants.MSG_DIRECTION_INWARD, 
					"ICL", loc_camt056.getMsgDefIdr(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), 
					loc_camt056.getCreDtTm(), new Date(), null, 
					loc_camt056.getOrgnlClrSysRef());
			jbP200DB.getJnlActionList().add(FpsToFfpAction);
			
			FFPTxJnl txJnl = jbP200DB.getTxJnl();
			txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CANCEL.getStatus());
			if(loc_camt056.getCxlRsnInfPrtry()!=null){
				txJnl.setTxRejCode(loc_camt056.getCxlRsnInfPrtry());
			}
			List<String> addtlInf = loc_camt056.getAddtlInf();
			if (addtlInf != null && addtlInf.size() > 0)
			{
				StringBuffer loc_strBuf = new StringBuffer();
				for(String str : addtlInf){
					loc_strBuf.append(str);
				}
				txJnl.setTxRejReason(loc_strBuf.toString());
			}
			jbP200DB.setTxJnl(txJnl);
			daoServiceP200.updateJnlStat(jbP200DB);
		}
	}

	@Override
	public boolean validate() throws Exception
	{
		return true;
	}

	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception
	{

		txVo = new FFPVOCamt056();
		FFPVOCamt056 camt056 = (FFPVOCamt056) txVo;

		parseISO20022BizDataHead(bizData);
		Document doc = (Document) bizData.getContent().get(1).getValue();
		FIToFIPaymentCancellationRequestV06 fiToFIPmtCxlReq = doc.getFIToFIPmtCxlReq();

		CaseAssignment31 assgnmt = fiToFIPmtCxlReq.getAssgnmt();
		camt056.setId(assgnmt.getId());
		camt056.setAssgneMmBid(assgnmt.getAssgne().getAgt().getFinInstnId().getClrSysMmbId().getMmbId());
		camt056.setAssgnrMmBid(assgnmt.getAssgnr().getAgt().getFinInstnId().getClrSysMmbId().getMmbId());
		camt056.setCreDtTm(assgnmt.getCreDtTm().toGregorianCalendar().getTime());
		camt056.setOrgnlInstrId(doc.getFIToFIPmtCxlReq().getUndrlyg().getTxInf().getOrgnlInstrId());
		
		camt056.setOrgnlEndToEndId(doc.getFIToFIPmtCxlReq().getUndrlyg().getTxInf().getOrgnlEndToEndId());
		camt056.setOrgnlTxId(doc.getFIToFIPmtCxlReq().getUndrlyg().getTxInf().getOrgnlTxId());
		camt056.setOrgnlClrSysRef(doc.getFIToFIPmtCxlReq().getUndrlyg().getTxInf().getOrgnlClrSysRef());

		camt056.setCxlRsnInfPrtry(doc.getFIToFIPmtCxlReq().getUndrlyg().getTxInf().getCxlRsnInf().getRsn().getPrtry());
		List<String> addtlInfo = doc.getFIToFIPmtCxlReq().getUndrlyg().getTxInf().getCxlRsnInf().getAddtlInf();
		if (addtlInfo.size() > 0)
		{
			camt056.getAddtlInf().add(addtlInfo.get(0));
		}
	}
}
