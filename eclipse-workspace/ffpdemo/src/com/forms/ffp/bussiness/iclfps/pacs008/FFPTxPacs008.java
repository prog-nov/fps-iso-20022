package com.forms.ffp.bussiness.iclfps.pacs008;

import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.ISO20022BusinessDataV01;
import com.forms.ffp.bussiness.FFPTxBase;

public class FFPTxPacs008 extends FFPTxBase
{

	@Override
	public void perform()
	{
		if("HKICL.pacs.008".equals(this.serviceName))
		{
//			TODO Save DB
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
	public void parseISO20022BizData(ISO20022BusinessDataV01 envelope)
	{
		// TODO Auto-generated method stub
		
	}
}
