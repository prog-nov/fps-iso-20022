package com.forms.ffp.bussiness.iclfps.pacs008;

import java.util.Date;

import javax.xml.bind.JAXBElement;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.FIToFIPaymentStatusReportV08;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.FPSTransactionStatusCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.GroupHeader531;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.ObjectFactory;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.OriginalGroupHeader71;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.PaymentTransaction801;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.StatusReason6Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.StatusReasonInformation92;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.iclfps.FFPMsgBaseHkiclMessage;
import com.forms.ffp.core.utils.FFPXMLUtils;

public class FFPMsgPacs008_Pacs002 extends FFPMsgBaseHkiclMessage
{
	private FFPVO_Pacs008 pacs008;
	
	private FFPVO_Pacs008_CdtTrfTxInf pacs008_txInf;

	private FFPVO_Pacs008_CTI02REPLY cti02Reply;

	// Setion E
	public FFPMsgPacs008_Pacs002(FFPVO_Pacs008 ip_pacs008, int ip_i)
	{
		super();
		this.pacs008 = ip_pacs008;
		this.pacs008_txInf = ip_pacs008.getCdtTrfTxInfList().get(ip_i);
		this.cti02Reply = ip_pacs008.getCdtTrfTxInfList().get(ip_i).getCti02Reply();
		this.sendType = FFPConstants.SEND_TYPE_ACK;
		this.msgTypeName = FFPJaxbConstants.JAXB_MSG_TYPE_PACS_002;
//		if (FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYC01.equals(this.pacs008.getBizSvc())){
//			this.priority = "H";
//		} else if(FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYC02.equals(this.pacs008.getBizSvc())){
//			this.priority = "M";
//		}
		this.priority = this.pacs008.getIclfpsWrapper().getPriority();
		this.msgBizSvc = this.pacs008.getBizSvc();
	}

	public JAXBElement<?> marshalMsgBizDataDocument()
	{
		Document loc_doc = createDocument();
		return (new ObjectFactory()).createDocument(loc_doc);
	}

	private Document createDocument()
	{
		ObjectFactory _objectfactory = new ObjectFactory();
		
		Document doc = _objectfactory.createDocument();
		FIToFIPaymentStatusReportV08 v08 = _objectfactory.createFIToFIPaymentStatusReportV08();
		doc.setFIToFIPmtStsRpt(v08);
		
		GroupHeader531 r531 = _objectfactory.createGroupHeader531();
		r531.setMsgId(this.getMsgID());
		r531.setCreDtTm(FFPXMLUtils.toGregorianDt(new Date()));
		v08.setGrpHdr(r531);
		
		OriginalGroupHeader71 oregro = _objectfactory.createOriginalGroupHeader71();
		oregro.setOrgnlMsgId(pacs008.getMsgId());
		oregro.setOrgnlMsgNmId(FFPJaxbConstants.JAXB_MSG_TYPE_PACS_008);
		v08.setOrgnlGrpInfAndSts(oregro);
		
		PaymentTransaction801 pt = _objectfactory.createPaymentTransaction801();
		pt.setOrgnlEndToEndId(pacs008_txInf.getEndToEndId());
		pt.setOrgnlTxId(pacs008_txInf.getTxId());

		if ("R".equals(this.cti02Reply.getRsltCd().toUpperCase()))
		{
			pt.setTxSts(FPSTransactionStatusCode.RJCT);
			StatusReasonInformation92 n92 = _objectfactory.createStatusReasonInformation92();
			StatusReason6Choice1 src1 = _objectfactory.createStatusReason6Choice1();
			src1.setPrtry(cti02Reply.getRejCd());
			n92.setRsn(src1);
			if(cti02Reply.getRejMsg() != null)
			{
				n92.getAddtlInf().add(cti02Reply.getRejMsg());
			}
			pt.setStsRsnInf(n92);
		} else if ("A".equals(this.cti02Reply.getRsltCd().toUpperCase()))
		{
			pt.setTxSts(FPSTransactionStatusCode.ACCP);
		}
		pt.setClrSysRef(pacs008.getClrSys());
		
		v08.getTxInfAndSts().add(pt);
		
		return doc;
	}

}
