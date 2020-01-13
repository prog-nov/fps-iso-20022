package com.forms.ffp.bussiness.iclfps.fpsadrs005;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_005_001_01.AddressingScheme;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_005_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_005_001_01.GroupHeader;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_005_001_01.MessageRoot;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.bussiness.utils.FFPJnlUtils;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.utils.FFPIDUtils;
import com.forms.ffp.core.utils.FFPStringUtils;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.FFPTxJnl;
import com.forms.ffp.persistents.bean.addressing.FFPJbA200;
import com.forms.ffp.persistents.service.FFPIDaoService_Txjnl;
import com.forms.ffp.persistents.service.addressing.FFPIDaoService_A200;

@Component("ICL.fps.adrs.005.001.01")
@Scope("prototype")
public class FFPTxFpsadrs005 extends FFPTxBase{
	
	@Resource(name = "FFPDaoService_Txjnl")
	private FFPIDaoService_Txjnl txJnlService;

	@Resource(name = "FFPDaoService_A200")
	private FFPIDaoService_A200 a200Service;
	
	@Override
	public void perform() throws Exception {
		FFPVo_Fpsadrs005 locVo = (FFPVo_Fpsadrs005) txVo;
		FFPJbA200 locJbA200 = locVo.getJbA200();
		
		if(!FFPStringUtils.isEmptyOrNull(locJbA200.getSts()))
			a200Service.sUpdateAddressing(locJbA200, locJbA200.getSts());	
	}

	@Override
	public boolean validate() throws Exception {
		return true;
	}

	@Override
	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception {
		if ("ICL.fps.adrs.005.001.01".equals(this.serviceName)) {
			txVo = new FFPVo_Fpsadrs005();
			FFPVo_Fpsadrs005 locVo = (FFPVo_Fpsadrs005) txVo;
			
			parseISO20022BizDataHead(bizData);
			Document doc = (Document) bizData.getContent().get(1).getValue();
			
			FFPJbA200 jbA200 = new FFPJbA200();
			
			MessageRoot mr = doc.getAdrStsUpdRpt();
			GroupHeader hdr = mr.getGrpHdr();
			locVo.setMsgId(hdr.getMsgId());
			locVo.setCreDtTm(hdr.getCreDtTm().toGregorianCalendar().getTime());
			AddressingScheme scheme = mr.getAdrSchme().get(0);
			
			List<FFPVo_Fpsadrs005.AdrSchme> asList = new ArrayList<>();
			FFPVo_Fpsadrs005.AdrSchme as = new FFPVo_Fpsadrs005().new AdrSchme();
			as.setAdrRptId(scheme.getAdrRptId());
			as.setCusId(scheme.getCusId());
			as.setDefaultIndicator(scheme.getDflt().name());
			as.setClrCd(scheme.getInstgAgt().getFinInstnId().getClrSysMmbId().getMmbId());
			as.setProxyId(scheme.getId());
			as.setProxyIdType(scheme.getTp().name());
			as.setStatus(scheme.getSts().name());
			//2016-08-18 22:15:00
			as.setStatusUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(scheme.getStsUpdTm()));
			asList.add(as);
			locVo.setAdrSchmes(asList);
			
			jbA200.setAdrReqId(scheme.getAdrRptId());
			jbA200.setSts(scheme.getSts().name());
			jbA200.setStsUpdTm(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(scheme.getStsUpdTm()));
			jbA200.setCusId(scheme.getCusId());
			jbA200.setProxyId(scheme.getId());
			jbA200.setProxyIdTp(scheme.getTp().value());
			jbA200.setClrCd(scheme.getInstgAgt().getFinInstnId().getClrSysMmbId().getMmbId());
			jbA200.setDflt(scheme.getDflt().value());
			
			Date locDate = new Date();
			FFPTxJnl txJnl = new FFPTxJnl();
			//A200 as Status Update Notification
			txJnl.setTxCode(FFPConstantsTxJnl.TX_CODE.TX_CODE_A200.getCode());
			txJnl.setJnlNo(FFPIDUtils.getJnlNo());
			txJnl.setTxStat(FFPConstantsTxJnl.TX_STATUS.TX_STAT_CREAT.getStatus());
			txJnl.setTxSrc(FFPConstants.TX_SOURCE_HKICL);
			txJnl.setCreateTs(locDate);
			txJnl.setLastUpdateTs(locDate);
			txJnl.setSrcRefNm(FFPIDUtils.getSrcRefNm());
			txJnl.setTxMode(FFPConstants.FPS_RECEIVE_MODE_REALTIME);
			jbA200.setTxJnl(txJnl);

			jbA200.getJnlActionList().add(FFPJnlUtils.getInstance().newJnlAction(
					jbA200.getTxJnl().getJnlNo(), locVo.getMsgId(), FFPConstants.MSG_DIRECTION_INWARD,
					FFPConstants.RELATION_SYSTEM_HKICL, locVo.getMsgDefIdr(), FFPConstantsTxJnl.MSG_STATUS.MSG_STAT_MSYNC.getStatus(), 
					locVo.getCreDtTm(), locDate, locDate, null));
			locVo.setJbA200(jbA200);
			
			a200Service.sInsert(jbA200);
		}
	}
}
