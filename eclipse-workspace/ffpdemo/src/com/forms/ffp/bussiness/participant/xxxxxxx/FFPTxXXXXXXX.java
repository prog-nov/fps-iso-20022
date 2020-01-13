package com.forms.ffp.bussiness.participant.xxxxxxx;

import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.ISO20022BusinessDataV01;
import com.forms.ffp.bussiness.FFPTxBase;
import com.forms.ffp.msg.iclfps.bussiness.FFPMsgFormContextBean;
import com.forms.ffp.msg.iclfps.creater.FFPMsgGenerator;

public class FFPTxXXXXXXX extends FFPTxBase
{

	@Override
	public void perform() throws Exception
	{
		FFPJbXXXXXXX objJb = (FFPJbXXXXXXX) txJb;
		
		if("PARTICIPANT.XXXXXXX".equals(this.serviceName))
		{
			FFPMsgXXXXXXX_Camt060 camt60 = new FFPMsgXXXXXXX_Camt060(objJb);
			FFPMsgFormContextBean mfcb = (FFPMsgFormContextBean) camt60.marshalMsgBean();
			String message = FFPMsgGenerator.getFpsMessage(mfcb);
			System.out.println(message);
//			TODO save db
			
			// send message
			
		}
		else
		{
//			TODO
		}
	}

	@Override
	public void validate()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void parsePraticipantFrmData(ISO20022BusinessDataV01 bizData)
	{
		txJb = new FFPJbXXXXXXX();
		// TODO Auto-generated method stub
	}
}
