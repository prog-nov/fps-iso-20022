package com.forms.ffp.bussiness.iclfps.fpsadrs007;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_007_001_01.AddressingScheme;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_007_001_01.CustomerName;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_007_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_007_001_01.Language;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_007_001_01.MessageRoot;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_007_001_01.Summary;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110dtl;
import com.forms.ffp.persistents.service.FFPIDaoService_Txjnl;
import com.forms.ffp.persistents.service.FFPIDaoService_TxjnlAction;
import com.forms.ffp.persistents.service.addressing.FFPDaoService_A110;

@Component("ICL.fps.adrs.007.001.01")
@Scope("prototype")
public class FFPTxAdrs007 extends FFPTxBase {

	@Resource(name = "FFPDaoService_A110")
	private FFPDaoService_A110 adrDao;

	@Resource(name = "FFPDaoService_TxjnlAction")
	private FFPIDaoService_TxjnlAction actionService;

	@Resource(name = "FFPDaoService_Txjnl")
	private FFPIDaoService_Txjnl txJnlService;

	@Override
	public void perform() throws Exception {
		if ("ICL.fps.adrs.007.001.01".equals(this.serviceName)) {
			FFPVOFpsadrs007 adrs = (FFPVOFpsadrs007) txVo;
			FFPTxJnlAction action = actionService.inquiryJnlActionByMsgId(adrs.getOrgnlMsgId());
			if (null == action)
				return;
			String jnlNo = action.getJnlNo();
			FFPTxJnl txjnl = adrDao.inqueryTXJNL(jnlNo);
			/**
			 * TIME OUT 
			 * ignore
			 */
			if(FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.equals(txjnl.getTxStat())){
				return;
			}
			
			
			/**
			 * record fpsadrs007
			 */
			Date loc_date = new Date();
			FFPTxJnlAction jnlAction = FFPJnlUtils.getInstance().newJnlAction(jnlNo, adrs.getMsgId(),
					FFPConstants.MSG_DIRECTION_INWARD,
					adrs.getFromClrSysMmbId() != null ? adrs.getFromClrSysMmbId() : adrs.getFromBic(),
					adrs.getMsgDefIdr(),FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(),
					adrs.getRjCd(), adrs.getStatus(),adrs.getCreateDate(), loc_date, loc_date,
					adrs.getOrgnlMsgId(), null);
			
			FFPJbA110 jba110 = new FFPJbA110();
			jba110.getJnlActionList().add(jnlAction);
			jba110.setTxJnl(txjnl);
			txjnl.setLastUpdateTs(loc_date);
			
			if("RJCT".equals(adrs.getStatus())){
				txjnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_FPS_REJCT.getStatus());
				txjnl.setTxRejCode(adrs.getRjCd());	
			}else{
				txjnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus());
				List<FFPJbA110dtl> listA = adrs.getList();
				jba110.setAdrList(listA);
				/**
				 * insert a110Dtl msg into db
				 */
				adrDao.dInsertA110dtl(jba110);
			}
			/**
			 * update txjnl status and insert action msg
			 */
			adrDao.updateJnlStat(jba110);
		}
	}

	@Override
	public boolean validate() throws Exception {
		FFPVOFpsadrs007 adrs007 = (FFPVOFpsadrs007)txVo;
		if(null == adrs007.getList()){
			return false;
		}
		return true;

	}

	@Override
	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception {

		if ("ICL.fps.adrs.007.001.01".equals(this.serviceName)) {
			FFPVOFpsadrs007 adrs = new FFPVOFpsadrs007();
			this.txVo = adrs;

			parseISO20022BizDataHead(bizData);
			Document doc = (Document) bizData.getContent().get(1).getValue();
			MessageRoot root = doc.getAdrSmryRpt();
			adrs.setMsgId(root.getGrpHdr().getMsgId());
			adrs.setOrgnlMsgId(root.getOrgnlGrpHdr().getMsgId());
			/**
			 * get jnlNo
			 */
			FFPTxJnlAction action = actionService.inquiryJnlActionByMsgId(adrs.getOrgnlMsgId());
			if (null == action)
				return;
			String jnlNo = action.getJnlNo();
			List<AddressingScheme> list = root.getAdrSchme();
			List<FFPJbA110dtl> dtls = new ArrayList<>();

			/**
			 * if no records , ..
			 */
			adrs.setList(dtls);

			FFPJbA110dtl a110 = null;
			if (null != list) {
				for (AddressingScheme c : list) {
					/*
					 * number
					 */
					adrs.setStatus(c.getSts().value());
					adrs.setNoOfAdr(c.getNoOfAdr());
					if (null != c.getStsRsnInf())
						adrs.setRjCd(c.getStsRsnInf().getRsn().getCd());

					List<Summary> sum = c.getSmry();
					/**
					 * if rjct,A110dtl only insert only one message
					 */
					if (null == sum) {
						a110 = new FFPJbA110dtl();
						a110.setJnlNo(jnlNo);
						a110.setAdrReqId(c.getAdrReqId());
						a110.setSts(c.getSts().value());
						a110.setNoOfAdr(c.getNoOfAdr());
						if (null != c.getStsRsnInf())
							a110.setRjCd(c.getStsRsnInf().getRsn().getCd());
						break;
					}

					for (Summary s : sum) {
						a110 = new FFPJbA110dtl();
						a110.setJnlNo(jnlNo);
						a110.setAdrReqId(c.getAdrReqId());
						a110.setSts(c.getSts().value());
						a110.setNoOfAdr(c.getNoOfAdr());
						a110.setProxyId(c.getId());
						a110.setProxyIdTp(c.getTp().value());

						/**
						 * CusName
						 */
						for (CustomerName cus : s.getCusNm()) {
							if (cus.getLang().compareTo(Language.EN) == 0) {
								a110.setLangEN(Language.EN.value());
								a110.setDispNmEN(cus.getDispNm());
							}
						}

						a110.setMmbId(s.getAgt().getFinInstnId().getClrSysMmbId().getMmbId());

						a110.setDefInd(s.getDflt().value());

						a110.setPurpCd(s.getPurp().getCd().value());

						a110.setCreDtTm(s.getCreDtTm());

						a110.setLstUpdDtTm(s.getLstUpdDtTm());

						dtls.add(a110);

					}

				}
			}

		}
	}

}
