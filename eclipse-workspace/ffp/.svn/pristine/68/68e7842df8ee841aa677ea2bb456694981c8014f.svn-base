package com.forms.ffp.bussiness.iclfps.fpsadrs009;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_009_001_01.AddressingScheme;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_009_001_01.CustomerName;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_009_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_009_001_01.Language;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_009_001_01.Result;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.bussiness.iclfps.fpsadrs007.FFPVOFpsadrs007;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.persistents.bean.FFPJbBase;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110dtl;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120dtl;
import com.forms.ffp.persistents.service.FFPIDaoService_TxjnlAction;
import com.forms.ffp.persistents.service.addressing.FFPDaoService_A120;
import com.forms.ffp.persistents.service.addressing.FFPDaoService_A120dtl;



@Component("ICL.fps.adrs.009.001.01")
@Scope("prototype")
public class FFPTxFpsadrs009 extends FFPTxBase{
	
	@Resource(name="FFPDaoService_A120dtl")
	private FFPDaoService_A120dtl a120dtlService;
	
	@Resource(name="FFPDaoService_A120")
	private FFPDaoService_A120 a120Service;
	
	@Resource(name = "FFPDaoService_TxjnlAction")
	private FFPIDaoService_TxjnlAction actionService;

	@Override
	public void perform() throws Exception {
		if("ICL.fps.adrs.009.001.01".equals(serviceName)){

			FFPVOAdrs009 adrs = (FFPVOAdrs009)txVo; 
			String jnlNo = adrs.getList().get(0).getJnlNo();
			FFPTxJnl txjnl = a120Service.iqueryTXJNL(jnlNo);
			/**
			 * time out 
			 * ignored it
			 */
			if(FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.getStatus().equals(txjnl.getTxStat())){
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
					adrs.getCreateDate(), loc_date, loc_date,adrs.getOrgnlMsgId());
			
			FFPJbBase base = new FFPJbBase();
			List<FFPTxJnlAction> listAction = base.getJnlActionList();
			listAction.add(jnlAction);
			
			txjnl.setCreateTs(loc_date);
			
			/**
			 * update tb_tx_jnl status 
			 */
			txjnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus());
			txjnl.setLastUpdateTs(loc_date);			
			List<FFPJbA120dtl> listA = adrs.getList();
			base.setTxJnl(txjnl);
			
			a120dtlService.updateJnlStat(base);
			
			a120dtlService.sInsert(listA);
		}
		
	}

	@Override
	public boolean validate() throws Exception {
		FFPVOAdrs009 adrs009 = (FFPVOAdrs009)txVo;
		if( null == adrs009.getList()){
			return false;
		}
		return true;
		
	}
	
	@Override
	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception {
		if("ICL.fps.adrs.009.001.01".equals(serviceName)){
			FFPVOAdrs009 adrs009 =new FFPVOAdrs009();
			txVo=adrs009;
			parseISO20022BizDataHead(bizData);
			Document doc = (Document) bizData.getContent().get(1).getValue();
			adrs009.setMsgId(doc.getAdrEnqRpt().getGrpHdr().getMsgId());
			adrs009.setOrgnlMsgId(doc.getAdrEnqRpt().getOrgnlGrpHdr().getMsgId());
			FFPTxJnlAction action = actionService.inquiryJnlActionByMsgId(adrs009.getOrgnlMsgId());
			/**
			 * check if
			 */
			if(null == action) return;
			String jnlNo = action.getJnlNo();
			List<AddressingScheme> scheList = doc.getAdrEnqRpt().getAdrSchme();
			List<FFPJbA120dtl> list = new ArrayList<>();
			/**
			 * set data 
			 */
			adrs009.setList(list);
			FFPJbA120dtl a120 = null;
			for(AddressingScheme c: scheList){
				a120 = new FFPJbA120dtl();
				a120.setJnlNo(jnlNo);
				a120.setAdrReqId(c.getAdrReqId());
				a120.setSts(c.getSts().value());
				if(null != c.getStsRsnInf())
					a120.setRjCd(c.getStsRsnInf().getRsn().getCd());
				a120.setProxyId(c.getId());
				a120.setProxyIdTp(c.getTp().value());
				a120.setPurpCd(c.getPurp().getCd().value());
				/**
				 *  can`t find supOp
				 */
				Result result = c.getRslt();
				if(null == result) continue;
				for(CustomerName cus:result.getCusNm()){
					if(Language.EN.value().equals(cus.getLang().value())){
						a120.setLangEN(Language.EN.value());
						a120.setFullNmEN(cus.getFullNm());
						a120.setDispNmEN(cus.getDispNm());
					}else if(Language.ZH.value().equals(cus.getLang().value())){
						a120.setLangZH(Language.ZH.value());
						a120.setFullNmZH(cus.getFullNm());
						a120.setDispNmZH(cus.getDispNm());
					}
				}
				a120.setRsltCusId(result.getCusId());
				a120.setMmbId(result.getAgt().getFinInstnId().getClrSysMmbId().getMmbId());
				a120.setRsltCusTp(result.getCusTp().value());
				list.add(a120);
			}
		}
	}
}
