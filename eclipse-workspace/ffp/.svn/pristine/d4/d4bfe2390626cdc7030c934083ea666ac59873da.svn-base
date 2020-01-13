package com.forms.ffp.bussiness.iclfps.admi004;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.adaptor.jaxb.iclfps.admi_004_001_02.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.admi_004_001_02.Event21;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.core.utils.FFPEmailUtils;
import com.forms.ffp.persistents.bean.FFPTxBase;

@Component("ICL.admi.004.001.02")
@Scope("prototype")
public class FFPTxAdmi004 extends FFPTxBase
{

	@Override
	public void perform() throws Exception
	{
		FFPVO_Admi004 admi004 = (FFPVO_Admi004)txVo;
		if(admi004 == null)
			return;
		if("BBLT".equals(admi004.getEvtCd()))
		{
			FFPEmailUtils.sendMaintainEmail("FFP System Error(" + admi004.getEvtCd()+")", admi004.getEvtCd() + admi004.getEvtDesc());
		}
	}

	@Override
	public boolean validate() throws Exception
	{
		return true;
	}

	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception
	{
		if ("ICL.admi.004.001.02".equals(this.serviceName))
		{
			FFPVO_Admi004 admi004 = new FFPVO_Admi004();
			this.txVo = admi004;
			Document doc = (Document) bizData.getContent().get(1).getValue();
			Event21 eventInf = doc.getSysEvtNtfctn().getEvtInf();
			admi004.setEvtCd(eventInf.getEvtCd());
			admi004.setEvtParam(eventInf.getEvtParam());
			admi004.setEvtDesc(eventInf.getEvtDesc());

			/**
			 * toString ...
			 */
			String eventTmStr = FFPDateUtils.getDateStr(eventInf.getEvtTm().toString(), FFPDateUtils.ITA_TIMESTAMP_FORMAT, FFPDateUtils.WEB_TIMESTAMP_FORMAT);
			admi004.setEvtTm(FFPDateUtils.getDate(eventTmStr, FFPDateUtils.WEB_TIMESTAMP_FORMAT));

		}

	}

}
