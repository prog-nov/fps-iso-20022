package com.forms.ffp.bussiness.iclfps.fpsadmi003;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_admi_003_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.persistents.bean.FFPTxBase;
import com.forms.ffp.persistents.bean.dt.FFPDtFpsParticipantMode;
import com.forms.ffp.persistents.dao.dt.FFPIDao_FpsParticipantMode;

@Component("com.forms.ffp.bussiness.iclfps.fpsadmi003.FFPTxFPSadmi003")
@Scope("prototype")
public class FFPTxFPSadmi003 extends FFPTxBase
{
	@Resource(name="FFPIDao_FpsParticipantMode")
	private FFPIDao_FpsParticipantMode dao;
	
	@Override
	public void perform() throws Exception
	{
		if ("ICL.fps.admi.003.001.01".equals(this.serviceName))
		{
			FFPMsgVo_fpsadmi003 loc_jb = (FFPMsgVo_fpsadmi003) txVo;
			FFPDtFpsParticipantMode mode = new FFPDtFpsParticipantMode();
			mode.setClearingCode(loc_jb.getClrSysMmbId());
			mode.setReceiptMode(loc_jb.getRcptMd());
			mode.setLastUpdateTs(loc_jb.getSwtchgTs());
			mode.setRefMsgId(loc_jb.getMsgId());
			dao.insertUpdate(mode);
		}
	}

	@Override
	public boolean validate() throws Exception
	{
		return true;
	}

	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception
	{
		if ("ICL.fps.admi.003.001.01".equals(this.serviceName))
		{
			txVo = new FFPMsgVo_fpsadmi003();
			FFPMsgVo_fpsadmi003 loc_jb = (FFPMsgVo_fpsadmi003) txVo;
			parseISO20022BizDataHead(bizData);
			
			Document doc = (Document) bizData.getContent().get(1).getValue();

			loc_jb.setMsgId(doc.getRcptMdSwtchgNoti().getGrpHdr().getMsgId());
			loc_jb.setClrSysMmbId(doc.getRcptMdSwtchgNoti().getRcptMdNoti().getRcptMdPty().getFinInstnId().getClrSysMmbId().getMmbId());
			loc_jb.setRcptMd(doc.getRcptMdSwtchgNoti().getRcptMdNoti().getRcptMd().value());
			loc_jb.setSwtchgTs(doc.getRcptMdSwtchgNoti().getRcptMdNoti().getSwtchgTm().toGregorianCalendar().getTime());
		}
	}
}
