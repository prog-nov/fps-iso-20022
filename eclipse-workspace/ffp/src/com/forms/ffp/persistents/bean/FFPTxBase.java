package com.forms.ffp.persistents.bean;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.bussiness.FFPVOBase;
import com.forms.ffp.core.msg.iclfps.FFPISO20022MessageWrapper;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageWrapper;

public abstract class FFPTxBase
{
	protected String serviceName;

	protected FFPISO20022MessageWrapper wrapper;

	protected FFPVOBase txVo;

	public abstract void perform() throws Exception;

	public abstract boolean validate() throws Exception;

	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception
	{

	}

	protected void parseISO20022BizDataHead(ISO20022BusinessDataV01 bizData) throws Exception
	{
		BusinessApplicationHeaderV01 head = (BusinessApplicationHeaderV01) bizData.getContent().get(0).getValue();
		txVo.setBizMsgIdr(head.getBizMsgIdr());
		txVo.setBizSvc(head.getBizSvc().value());
		txVo.setCreateDate(head.getCreDt().toGregorianCalendar().getTime());
		txVo.setFromBic(head.getFr().getFIId().getFinInstnId().getBICFI());
		txVo.setFromClrSysMmbId(head.getFr().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
		txVo.setMsgDefIdr(head.getMsgDefIdr());
		txVo.setToBic(head.getTo().getFIId().getFinInstnId().getBICFI());
		txVo.setToClrSysMmbId(head.getTo().getFIId().getFinInstnId().getClrSysMmbId().getMmbId());
	}

	public void parseParticipantData(FFPParticipantMessageWrapper wrapper) throws Exception
	{

	}

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	public FFPVOBase getTxVo()
	{
		return txVo;
	}

	public void setTxVo(FFPVOBase txVo)
	{
		this.txVo = txVo;
	}

	public void setFFPISO20022MessageWrapper(FFPISO20022MessageWrapper wrapper)
	{
		txVo.setIclfpsWrapper(wrapper);
	}
}
