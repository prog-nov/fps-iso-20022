package com.forms.ffp.bussiness.iclfps.camt060;


import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.AccountReportingRequestV03;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.GroupHeader591;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.ISO20022BusinessDataV01;
import com.forms.ffp.bussiness.FFPTxBase;
import com.forms.ffp.msg.iclfps.bussiness.FFPMsgFormContextBean;
import com.forms.ffp.msg.iclfps.creater.FFPMsgGenerator;
import com.forms.ffp.utils.FFPSendMsgUtils;

public class FFPTxCamt060 extends FFPTxBase
{
	
	
	public void perform() throws Exception
	{
		FFPJbCamt060 objJb = (FFPJbCamt060) txJb;
		String group = "";
		
		if("rrs.camt.060.001.03".equals(this.serviceName)){
			//1.解析对象成字符串
			FFPMsgCamt060 camt60 = new FFPMsgCamt060(objJb);
			FFPMsgFormContextBean mfcb = (FFPMsgFormContextBean) camt60.marshalMsgBean();
			String message = FFPMsgGenerator.getFpsMessage(mfcb);
			//2.根据业务读取需要发送的队列的名称，选择配置文件中发送队列的名称
			String destination = FFPSendMsgUtils.selectQueue(message, group, "ack", "h");
			//3.将消息发送到指定的队列当中去
			FFPSendMsgUtils.sendMsg(message, destination);
			
		}else{
			//TODO
		}
	}
	
	public void validate(){
		
	}
	
	
	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData){
		txJb = new FFPJbCamt060();
		FFPJbCamt060 objJb = (FFPJbCamt060)txJb;
		
		//随便取document中的2条内容，放入txJb对象当中去
		Document document = (Document)bizData.getDocument();
		AccountReportingRequestV03 acctRptgReq = document.getAcctRptgReq();
		GroupHeader591 grpHdr = acctRptgReq.getGrpHdr();
		
		String msgId = grpHdr.getMsgId();
		
		objJb.setMsgId(msgId);
		
//		System.out.println("msgId:"+msgId);
	}
	
	
}
