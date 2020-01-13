package com.forms.ffp.bussiness.participant.addressing.ffpadr01;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpadr01.ADDRESSINGSCHEMETYPE;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpadr01.AcctInfoType;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpadr01.CUSTOMERNAMETYPE;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpadr01.FFPADR01;
import com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.define.FFPConstantsTxJnl.TX_STATUS;
import com.forms.ffp.core.msg.FFPAdaptorMgr;
import com.forms.ffp.core.msg.FFPBaseResp;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageWrapper;
import com.forms.ffp.core.msg.participant.FFPSendTcpMessageResp;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.core.utils.FFPStringUtils;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.FFPTxJnlAction;
import com.forms.ffp.persistents.bean.addressing.FFPJbA100;
import com.forms.ffp.persistents.bean.addressing.FFPJbAddressing;
import com.forms.ffp.persistents.service.FFPIDaoService_Txjnl;
import com.forms.ffp.persistents.service.FFPIDaoService_TxjnlAction;
import com.forms.ffp.persistents.service.addressing.FFPIDaoService_A100;
import com.forms.ffp.persistents.service.addressing.FFPIDaoService_Addressing_Acct;

@Component("FFPAGENT.FFPADR01")
@Scope("prototype")
public class FFPTxFfpadr01 extends FFPTxBase {

	@Resource(name="FFPDaoService_A100")
	private FFPIDaoService_A100 a100Service;
	
	@Resource(name="FFPDaoService_Txjnl")
	private FFPIDaoService_Txjnl txJnlService;
	
	@Resource(name="FFPDaoService_TxjnlAction")
	private FFPIDaoService_TxjnlAction actionService;
	
	@Resource(name = "FFPDaoService_Addressing_Acct")
	private FFPIDaoService_Addressing_Acct acctService;
	
	@Override
	public void perform() throws Exception {
		
		FFPVo_Ffpadr01 locVo = (FFPVo_Ffpadr01)txVo;
		FFPJbA100 jbA100 = locVo.getJbA100();
		FFPMsgFfpadr01_Fpsadrs001 msgAdrs001 = null;
		FFPMsgFfpadr01_Fpsadrs002 msgAdrs002 = null;
		
		List<FFPTxJnlAction> jnlActionList = jbA100.getJnlActionList();
		
		String msgType = locVo.getJbA100().getMsgType();
		if("NWRG".equalsIgnoreCase(msgType)){
			msgAdrs001 = new FFPMsgFfpadr01_Fpsadrs001(locVo);
			FFPTxJnlAction adrs001Action_SendToICL = FFPJnlUtils.getInstance().newJnlAction(
					jbA100.getTxJnl().getJnlNo(), msgAdrs001.getMsgID(), FFPConstants.MSG_DIRECTION_OUTWARD, 
					FFPConstants.RELATION_SYSTEM_HKICL, msgAdrs001.getMsgTypeName(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(), 
					msgAdrs001.getCreDt(), new Date(), null, null);
			jnlActionList.add(adrs001Action_SendToICL);
			
			try {
				FFPBaseResp respAdr001 = FFPAdaptorMgr.getInstance().execute(msgAdrs001);
				FFPSendMessageResp resp = (FFPSendMessageResp)respAdr001;
				
				adrs001Action_SendToICL.setMsgStatus(resp.getMessageStatus());
				adrs001Action_SendToICL.setMsgComplTs(new Date());
				//do not update txJnl , waiting for DB update.
			} catch (Exception e) {
				adrs001Action_SendToICL.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus());
				adrs001Action_SendToICL.setMsgComplTs(new Date());
			}
		}else if("AMND".equalsIgnoreCase(msgType)){
			//judge send to ICL or not?
			if(isSameData(locVo)){
				acctService.sUpdateAdrs(locVo.getJbA100());
				return;
			}
			msgAdrs002 = new FFPMsgFfpadr01_Fpsadrs002(locVo);
			FFPTxJnlAction adrs002Action_SendToICL = FFPJnlUtils.getInstance().newJnlAction(
					jbA100.getTxJnl().getJnlNo(), msgAdrs002.getMsgID(), FFPConstants.MSG_DIRECTION_OUTWARD, 
					FFPConstants.RELATION_SYSTEM_HKICL, msgAdrs002.getMsgTypeName(),FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), 
					msgAdrs002.getCreDt(), new Date(), null, null);
			jnlActionList.add(adrs002Action_SendToICL);
			
			try {
				FFPBaseResp respAdr002 = FFPAdaptorMgr.getInstance().execute(msgAdrs002);
				FFPSendMessageResp resp = (FFPSendMessageResp)respAdr002;
				
				adrs002Action_SendToICL.setMsgStatus(resp.getMessageStatus());
				adrs002Action_SendToICL.setMsgComplTs(new Date());
			} catch (Exception e) {
				adrs002Action_SendToICL.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus());
				adrs002Action_SendToICL.setMsgComplTs(new Date());
			}
		}
		a100Service.updateJnlStat(jbA100);
		
		long startTm = System.currentTimeMillis();
		long currTm = startTm;
		while(currTm <= (startTm+60000)){
			currTm = System.currentTimeMillis();
			//every 5 sec read database
			if((currTm-startTm)%5000 == 0){
				System.out.println("************************每5秒，查表了****************************");
				FFPJbA100 jbA100Db = (FFPJbA100) txJnlService.inquiryByJnlNo(jbA100.getTxJnl().getJnlNo());
				
				
				if(!TX_STATUS.TX_STAT_CREAT.getStatus().equalsIgnoreCase(jbA100Db.getTxJnl().getTxStat())){
					if(TX_STATUS.TX_STAT_APPST.getStatus().equalsIgnoreCase(jbA100Db.getTxJnl().getTxStat())){
						if("SVID".equalsIgnoreCase(jbA100Db.getProxyIdTp())){
							for(FFPJbAddressing adrs: jbA100.getJbAdrsList()){
								adrs.setProxyId(jbA100Db.getProxyId());
							}
						}
						jbA100Db.setJbAdrsList(jbA100.getJbAdrsList());
						acctService.sUpdateAdrs(jbA100Db);
					}
				
					if("NWRG".equalsIgnoreCase(msgType)){
						msgAdrs001.getResponseDataFromDB(jbA100Db);
					}else if("AMND".equalsIgnoreCase(msgType)){
						msgAdrs002.getResponseDataFromDB(jbA100Db);
					}
					
					
					FFPMsgFfpadr01_Ffpamr01 amr01 = new FFPMsgFfpadr01_Ffpamr01(locVo.getReply());
					FFPTxJnlAction action_ResponseToAgent  = FFPJnlUtils.getInstance().newJnlAction(
							jbA100.getTxJnl().getJnlNo(), amr01.getResRefNo(), FFPConstants.MSG_DIRECTION_OUTWARD,
							amr01.getResponseID(), amr01.getMsgType(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_CREAT.getStatus(),
							amr01.getCreateDate(), new Date(), null, amr01.getReqRefNo());
					jnlActionList.add(action_ResponseToAgent);
					try {
						FFPSendTcpMessageResp resp = FFPAdaptorMgr.getInstance().execute(amr01, locVo.getParticipantWrapper().getSocket());
						action_ResponseToAgent.setMsgComplTs(new Date());
						if(resp.isTimeOut()){
							action_ResponseToAgent.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_TMOUT.getStatus());
						}else{
							action_ResponseToAgent.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus());
							jbA100.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_COMPL.getStatus());
						}
					} catch (Exception e) {
						action_ResponseToAgent.setMsgStatus(FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_REJCT.getStatus());
						action_ResponseToAgent.setMsgComplTs(new Date());
					}finally {
						a100Service.updateJnlStat(jbA100);
					}
					return ;
				}
			}
		}
		//1 min later TIME OUT 
		jbA100.getTxJnl().setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.getStatus());
		a100Service.updateJnlStat(jbA100);
	}

	private boolean isSameData(FFPVo_Ffpadr01 locVo) {
		FFPJbA100 a100New = locVo.getJbA100();
		//1.get data from db
		FFPJbA100 a100Db = a100Service.inqueryAdrs(locVo.getJbA100().getCusId(), locVo.getJbA100().getProxyId(), locVo.getJbA100().getProxyIdTp());
		if(a100Db == null)
			return false;
			//2.compare
		Map<String,String> pro = new HashMap<>();
		pro.put("clrCd", null);
		pro.put("fullNmEn", null);
		pro.put("dispNmEn", null);
		pro.put("fullNmZh", null);
		pro.put("dispNmZh", null);
		pro.put("cusTp", null);
		pro.put("supOpCd", null);
		pro.put("dflt", null);
		pro.put("purpCd", null);
		
		return isEqual(a100Db, a100New, pro);
	}
	
	/**
     * Compare two object and return if equal
     * @param source source object
     * @param target target object
     * @param comparedProperties only compare fields in this map
     * @return  rue-equal
     */
    private boolean isEqual(Object source, Object target, Map<String,String> comparedProperties) {
    	ObjectMapper mapper = new ObjectMapper();
    	
        if(null == source || null == target) {
            return null == source && null == target;
        }
        if(!Objects.equals(source.getClass().getName(), target.getClass().getName())){
            return false;
        }
        Map<String, Object> sourceMap= mapper.convertValue(source, new TypeReference<Map<String,Object>>(){});
        Map<String, Object> targetMap = mapper.convertValue(target, new TypeReference<Map<String,Object>>(){});
        
        for(String k:sourceMap.keySet()){
        	System.out.println(k);
            if(comparedProperties!=null&&!comparedProperties.containsKey(k)){
                continue;
            }
            Object v=sourceMap.get(k);
            Object targetValue=targetMap.get(k);
            if(!Objects.equals(v,targetValue)){return false;}
        }
        return true;
    }

	@Override
	public boolean validate() throws Exception {
		return true;
	}
	
	public void parseParticipantData(FFPParticipantMessageWrapper wrapper) throws Exception{
		
		ROOT root = wrapper.getRequestRoot();
		if ("FFPAGENT.FFPADR01".equals(this.serviceName)){
			txVo = new FFPVo_Ffpadr01();
			FFPVo_Ffpadr01 locVo = (FFPVo_Ffpadr01)txVo;
			FFPJbA100 jbA100 = new FFPJbA100();
			HEAD head = root.getHEAD();
			FFPADR01 adr = (FFPADR01)root.getBODY();
			jbA100.setMsgType(adr.getMsgType());
			
			ADDRESSINGSCHEMETYPE scheme = adr.getAdrSchme();
			jbA100.setAdrReqId(FFPIDUtils.getAdrReqId());
			jbA100.setClrCd(scheme.getClrCd());
			//registration
			if(!FFPStringUtils.isEmptyOrNull(scheme.getProxyId())){
				jbA100.setProxyId(scheme.getProxyId());
			}
			jbA100.setProxyIdTp(scheme.getProxyIdTp());
			jbA100.setCusId(scheme.getCusId());
			jbA100.setCusTp(scheme.getCusTp());
			jbA100.setDflt(scheme.getDflt().value());
			jbA100.setPurpCd(scheme.getPurpCd());
			List<CUSTOMERNAMETYPE> cusNmList = scheme.getCusNm();
			if(cusNmList.size() == 1 ){
				//en
				jbA100.setLangEn(cusNmList.get(0).getLang().name());
				jbA100.setFullNmEn(cusNmList.get(0).getFullNm());
				jbA100.setDispNmEn(cusNmList.get(0).getDispNm());
			}else if(cusNmList.size() == 2){
				//en
				jbA100.setLangEn(cusNmList.get(0).getLang().name());
				jbA100.setFullNmEn(cusNmList.get(0).getFullNm());
				jbA100.setDispNmEn(cusNmList.get(0).getDispNm());
				//zh
				jbA100.setLangEn(cusNmList.get(1).getLang().name());
				jbA100.setFullNmEn(cusNmList.get(1).getFullNm());
				jbA100.setDispNmEn(cusNmList.get(1).getDispNm());
			}
			jbA100.setClrCd(scheme.getClrCd());
			if(scheme.getSupOpCd().size()>0){
				jbA100.setSupOpCd(scheme.getSupOpCd().get(0));
			}
			
			FFPTxJnl txJnl = new FFPTxJnl();
			txJnl.setJnlNo(FFPIDUtils.getJnlNo());
			txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus());
			txJnl.setTxSrc(FFPConstants.TX_SOURCE_FFPAGENT);
			txJnl.setTransactionId(FFPIDUtils.getTransactionId());
			txJnl.setEndToEndId(FFPIDUtils.getEndToEndId());
			txJnl.setTxCode(FFPConstantsTxJnl.TX_CODE.TX_CODE_A100.getCode());
			txJnl.setTxMode(FFPConstants.FPS_RECEIVE_MODE_REALTIME);
			txJnl.setSrcRefNm(adr.getSrcRefNm());
			Date loc_date = new Date();
			txJnl.setCreateTs(loc_date);
			txJnl.setLastUpdateTs(loc_date);
			jbA100.setTxJnl(txJnl);

			List<FFPJbAddressing> jbAdrsList = jbA100.getJbAdrsList();
			List<AcctInfoType> acctList = scheme.getAcctInfo();
			for(int i=0; i<acctList.size(); i++){
				FFPJbAddressing jbAdr = new FFPJbAddressing();
				jbAdr.setCusId(scheme.getCusId());
				if(!FFPStringUtils.isEmptyOrNull(scheme.getProxyId()))
					jbAdr.setProxyId(scheme.getProxyId());
				jbAdr.setProxyIdTp(scheme.getProxyIdTp());
				jbAdr.setClrCd(scheme.getClrCd());
				
				AcctInfoType info = acctList.get(i);
				jbAdr.setAcctCur(info.getAcctCur());
				jbAdr.setAcctNum(info.getAcctNum());
				jbAdr.setAcctTp(info.getAcctTp());
				jbAdr.setAcctDef(i+1);
				jbAdr.setSrvcTp(info.getSrvcTp().value());
				jbAdr.setLstUpJnl(txJnl.getJnlNo());
				jbAdrsList.add(jbAdr);
			}
			jbA100.setJbAdrsList(jbAdrsList);
			
			
			List<FFPTxJnlAction> jnlActionList = jbA100.getJnlActionList();

			jnlActionList.add(FFPJnlUtils.getInstance().newJnlAction(
					txJnl.getJnlNo(), head.getRequestRefno(), FFPConstants.MSG_DIRECTION_INWARD, 
					head.getRequestID(), head.getMessageType(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(),
					FFPDateUtils.convertStringToDate(head.getTransactionDate() + head.getTransactionTime(), FFPDateUtils.INT_TIMESTAMP_FORMAT),
					loc_date, loc_date, null));
			
			locVo.setJbA100(jbA100);
			
			locVo.setParticipantWrapper(wrapper);
			a100Service.sInsert(jbA100);
		}
	}
}
