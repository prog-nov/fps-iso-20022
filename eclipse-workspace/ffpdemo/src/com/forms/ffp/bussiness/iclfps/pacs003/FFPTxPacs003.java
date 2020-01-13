package com.forms.ffp.bussiness.iclfps.pacs003;

import java.io.StringWriter;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.forms.beneform4j.util.Tool;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.pacs_002_001.Document;
import com.forms.ffp.adaptor.jaxb.participant.response.HEAD;
import com.forms.ffp.adaptor.jaxb.participant.response.ROOT;
import com.forms.ffp.bussiness.FFPTxBase;
import com.forms.ffp.framework.pacs003.service.FFPIPacs003Service;
import com.forms.ffp.utils.FFPSendMsgUtils;
import com.forms.ffp.utils.FFPStringUtils;

public class FFPTxPacs003 extends FFPTxBase {
	@Resource(name = "pacs003Service")
	private FFPIPacs003Service pacsService;

	public void perform() throws Exception {

		FFPJbPacs003 pacs003 = (FFPJbPacs003) txJb;
		String groupName = "ffp.to.participant.sim";

		if ("rrs.pacs.003.001.07".equals(this.serviceName)) {
			// save to db
			pacsService.sInsert(pacs003);
			// 接收到HKICL发送过来的报文的处理步骤
			// 1.重新组合报文信息
			ROOT root = new ROOT();
			HEAD head = new HEAD();
			head.setResponseID(pacs003.getMsgId());
			head.setMessageType("FFPADRR1");
			root.setHEAD(head);

			JAXBContext context = JAXBContext.newInstance(new Class[] { ROOT.class });
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
			marshaller.setProperty("jaxb.encoding", "UTF-8");

			StringWriter sw = new StringWriter();
			marshaller.marshal(root, sw);
			String xmlStr = sw.toString();
			String message = FFPStringUtils.padZero(xmlStr.length(), 8) + xmlStr;

			// 2.将报文信息发送给HKICL
			// 参数：
			// 1 新的报文内容
			// 2 通过哪个消息队列
			// 3,4 根据业务定义
			String destination = FFPSendMsgUtils.selectQueue(message, groupName, "ack", "h");
			FFPSendMsgUtils.sendMsg(message, destination);
		} else {

		}

	}

	public void validate() {

	}

	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception {

		txJb = new FFPJbPacs003();
		FFPJbPacs003 pacs003 = (FFPJbPacs003) txJb;
		String transId = Tool.STRING.getRandomNumeric(32);
		String endToEndId = Tool.STRING.getRandomNumeric(32);
		String msgId = Tool.STRING.getRandomNumeric(32);
		pacs003.setMsgTranId(transId);
		pacs003.setEndToEndId(endToEndId);
		pacs003.setMsgReason("接受HKICL响应报文");
		pacs003.setMsgTransStatus("COMPL");
		pacs003.setMsgId(msgId);
		pacs003.setActionType("HKICL-ACK—FFP");
		pacs003.setActionTransStatus("COMPL");
		pacs003.setMsgContent("this is content");
	}
}
