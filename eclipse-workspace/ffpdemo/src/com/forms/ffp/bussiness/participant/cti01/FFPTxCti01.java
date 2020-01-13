package com.forms.ffp.bussiness.participant.cti01;

import javax.annotation.Resource;

import com.forms.beneform4j.util.Tool;
import com.forms.ffp.adaptor.jaxb.participant.request.HEAD;
import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;
import com.forms.ffp.bussiness.FFPTxBase;
import com.forms.ffp.bussiness.iclfps.camt060.FFPJbCamt060;
import com.forms.ffp.bussiness.iclfps.camt060.FFPMsgCamt060;
import com.forms.ffp.framework.cti01.service.FFPICti01Service;
import com.forms.ffp.msg.iclfps.bussiness.FFPMsgFormContextBean;
import com.forms.ffp.msg.iclfps.creater.FFPMsgGenerator;
import com.forms.ffp.utils.FFPDateUtils;
import com.forms.ffp.utils.FFPSendMsgUtils;

public class FFPTxCti01 extends FFPTxBase {
	@Resource(name = "cti01Service")
	private FFPICti01Service cti01Service;

	public void perform() throws Exception {

		FFPJbCti01 cti01 = (FFPJbCti01) txJb;
		String groupName = "ffp.to.hkicl.sim";

		if ("PARTICIPANT.cti01".equals(this.serviceName)) {
			// to DB
			cti01Service.sInsert(cti01);

			// 接收到银行发送过来的报文的处理步骤
			// 1.重新组合报文信息
			FFPJbCamt060 objJb = new FFPJbCamt060();
			// objJb.setBicCode(cti01.getBicCode());
			// objJb.setClearCode(cti01.getClearCode());

			// 仅做为测试
			objJb.setMsgId(cti01.getRequestRefno());

			FFPMsgCamt060 camt60 = new FFPMsgCamt060(objJb);
			FFPMsgFormContextBean mfcb = (FFPMsgFormContextBean) camt60.marshalMsgBean();
			String message = FFPMsgGenerator.getFpsMessage(mfcb);
			FFPJbOSend sendToDbToICL = SendToDbToICL(message);
			cti01Service.beSendToDb(sendToDbToICL);

			// 2.将报文信息发送给HKICL
			// 参数：
			// 1 新的报文内容
			// 2 通过哪个消息队列
			// 3,4 根据业务定义
			String destination = FFPSendMsgUtils.selectQueue(message, groupName, "ack", "h");
			FFPSendMsgUtils.sendMsg(message, destination);
			FFPJbOSend confSendToDb = confSendToDb(sendToDbToICL);
			cti01Service.confSendToDb(confSendToDb);
		}
	}

	public void validate() throws Exception {

	}

	public void parseParticipantFrmData(ROOT root) throws Exception {
		txJb = new FFPJbCti01();
		FFPJbCti01 cti = (FFPJbCti01) txJb;

		HEAD head = root.getHEAD();
		String accountingDate = head.getAccountingDate();
		String requestRefno = head.getRequestRefno();
		// System.out.println(accountingDate);
		System.out.println(requestRefno);

		cti.setAccountingDate(accountingDate);
		cti.setRequestRefno(requestRefno);

		// begin by zhangying
		
		cti.setMsgTranId(FFPDateUtils.MSG_TRANS_ID+Tool.STRING.getRandomNumeric(8));
		cti.setEndToEndId(FFPDateUtils.MSG_END_TO_END_ID+Tool.STRING.getRandomNumeric(8));
		cti.setMsgTransStatus(FFPDateUtils.MSG_PENDING);
		cti.setMsgId(FFPDateUtils.MSG_ID+Tool.STRING.getRandomNumeric(8));
		cti.setActionType(FFPDateUtils.MSG_FROM_BANK_REQ);
		cti.setActionTransStatus(FFPDateUtils.MSG_CREATE);
		cti.setMsgContent("content");
	}

	public FFPJbOSend SendToDbToICL(String message) throws Exception {
		FFPJbCti01 cti01 = (FFPJbCti01) txJb;
		FFPJbOSend oSend = new FFPJbOSend();
		oSend.setMsgTranId(cti01.getMsgTranId());
		oSend.setOMsgId(FFPDateUtils.MSG_ID+Tool.STRING.getRandomNumeric(8));
		oSend.setActionType(FFPDateUtils.MSG_TO_ICL_REQ);
		oSend.setEndToEndId(cti01.getEndToEndId());
		oSend.setActionTransStatus(FFPDateUtils.MSG_PENDING);
		oSend.setOActionTransStatus(FFPDateUtils.MSG_CREATE);
		oSend.setMsgId(cti01.getMsgId());
		oSend.setMsgContent(message);
		return oSend;
	}

	public FFPJbOSend confSendToDb(FFPJbOSend Osend) {
		Osend.setOActionTransStatus(FFPDateUtils.MSG_PENDING);
		return Osend;
	}

}
