package com.forms.ffp.bussiness.iclfps.pacs004;

import java.io.StringWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.forms.beneform4j.util.Tool;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.pacs_004_001.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.pacs_004_001.PaymentTransaction761;
import com.forms.ffp.adaptor.jaxb.participant.response.HEAD;
import com.forms.ffp.adaptor.jaxb.participant.response.ROOT;
import com.forms.ffp.bussiness.FFPTxBase;
import com.forms.ffp.bussiness.iclfps.camt060.FFPJbCamt060;
import com.forms.ffp.framework.pacs004.service.FFPIPacs004Service;
import com.forms.ffp.utils.FFPDateUtils;
import com.forms.ffp.utils.FFPSendMsgUtils;
import com.forms.ffp.utils.FFPStringUtils;
import com.forms.producebean.com.ffp.dao.TbMsgJnlAction;

public class FFPTxPacs004 extends FFPTxBase {
	@Resource(name = "pacs004Service")
	private FFPIPacs004Service pacsService;

	// pending
	public void perform() throws Exception {
		List<Object> list = txJb.getList();

		String groupName = "ffp.to.participant.sim";

		if ("rrs.pacs.004.001.07".equals(this.serviceName)) {
			// save to db
			for (Object obj : list) {
				FFPJbPacs004 pacs004 = (FFPJbPacs004) obj;
				pacsService.sInsert(pacs004);
				TbMsgJnlAction msgJnlAction = new TbMsgJnlAction();
				
				// 接收到HKICL发送过来的报文的处理步骤
				// 1.重新组合报文信息
				ROOT root = new ROOT();
				HEAD head = new HEAD();
				head.setResponseID(pacs004.getTbMsgJnlAction().get(0).getMsgTranId());
				head.setMessageType("FFPADRR1");
				root.setHEAD(head);

				JAXBContext context = JAXBContext.newInstance(new Class[] { ROOT.class });
				Marshaller marshaller = context.createMarshaller();
				marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
				marshaller.setProperty("jaxb.encoding", "UTF-8");

				StringWriter sw = new StringWriter();
				marshaller.marshal(root, sw);
				// 生成新的报文
				String xmlStr = sw.toString();
				// 对新的报文进行处理
				
				msgJnlAction.setMsgId(FFPDateUtils.MSG_ID + Tool.STRING.getRandomNumeric(8));
				msgJnlAction.setActionTransStatus(FFPDateUtils.MSG_PENDING);
				msgJnlAction.setActionType(FFPDateUtils.MSG_TO_BANK_ACK);
				pacs004.getTbMsgJnlAction().add(msgJnlAction);
				//pacsService.beSendToDb(pacs004);
				String message = FFPStringUtils.padZero(xmlStr.length(), 8) + xmlStr;

				// 2.将报文信息发送给HKICL
				// 参数：
				// 1 新的报文内容
				// 2 通过哪个消息队列
				// 3,4 根据业务定义
				String destination = FFPSendMsgUtils.selectQueue(message, groupName, "ack", "h");
				FFPSendMsgUtils.sendMsg(message, destination);
				// 确认报文
				msgJnlAction.setActionTransStatus(FFPDateUtils.MSG_PENDING);
				pacs004.getTbMsgJnlAction().add(msgJnlAction);
				//pacsService.confSendToDb(pacs004);
			}
		} else {

		}

	}

	public void validate() {

	}

	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception {
		txJb = new FFPJbCamt060();
		Document document = (Document) bizData.getDocument();
		List<PaymentTransaction761> txInf = document.getPmtRtr().getTxInf();
		for (PaymentTransaction761 ob : txInf) {
			FFPJbPacs004 pacs004 = new FFPJbPacs004();
			TbMsgJnlAction msgJnlAction = new TbMsgJnlAction();
			pacs004.getTbMsgJnl().setMsgTranId(ob.getRtrId());
			pacs004.getTbMsgJnl().setEndToEndId(ob.getOrgnlEndToEndId());
			pacs004.getTbMsgJnl().setMsgTransStatus(FFPDateUtils.MSG_COMPL);
			msgJnlAction.setMsgTranId(ob.getRtrId());
			msgJnlAction.setEndToEndId(ob.getOrgnlEndToEndId());
			msgJnlAction.setMsgId(FFPDateUtils.MSG_ID + Tool.STRING.getRandomNumeric(8));
			msgJnlAction.setActionType(FFPDateUtils.MSG_FROM_ICL_ACK);
			pacs004.getTbMsgJnlAction().add(msgJnlAction);
			pacs004.getTbMsgJnl().setMsgReason("reason");
			txJb.getList().add(pacs004);
		}

	}
}
