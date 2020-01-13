package com.forms.batch.job.unit.transactioncheck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.forms.ffp.adaptor.jaxb.participant.response.ROOT;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpchk01.FFPCHK01;
import com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageConverter;
import com.forms.ffp.core.utils.FFPIDUtils;

public class TransactionCheckAgent {
	private final static int DEFAULT_PORT = 8888;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Agent开启!");

		BufferedReader br = null;
		BufferedWriter bw = null;
		Socket socket = null;
		try {
			// 1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
			ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);

			// 循环监听等待客户端的连接
			while (true) {
				// 调用accept()方法开始监听，等待客户端的连接
				socket = serverSocket.accept();

				InetAddress address = socket.getInetAddress();

				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				StringBuffer text = new StringBuffer();
				char[] mesBuffer = new char[1024];
				int count = 0;
				while ((count = br.read(mesBuffer)) != -1) {
					text.append(new String(mesBuffer, 0, count));
				}
				System.out.println((new Date()) + " 接收客户端" + address.getHostAddress() + "消息：\n" + text.toString());
				socket.shutdownInput();

				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
				String message = generateFFPCHK01ResponseMessage();

				System.out.println((new Date()) + " 对客户端" + address.getHostAddress() + "发送消息：\n" + message);
				bw.write(message);
				bw.flush();

				socket.shutdownOutput();

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (br != null) {
					br.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String generateFFPCHK01ResponseMessage() {
		String msg = "";

		ROOT root = new ROOT();
		HEAD head = new HEAD();
		FFPCHK01 body = new FFPCHK01();
		Date date = new Date();
		SimpleDateFormat ymd = new SimpleDateFormat("YYYYMMDD");
		SimpleDateFormat hms = new SimpleDateFormat("hhmmss");
		head.setRequestID(FFPConstants.MSG_CODE_AGENT);
		head.setTransactionDate(ymd.format(date));
		head.setTransactionTime(hms.format(date));
		head.setRequestRefno(FFPIDUtils.getRefno());
		head.setResponseID(FFPConstants.MSG_CODE_FFP);
		head.setMessageType("FFPCHK01");
		head.setResponseRefno(FFPIDUtils.getRefno());
		head.setResponseStatus("N");
		head.setFinalNode("2");

		body.setFilePath("C:/Users/admins/Desktop/");
		body.setFileName("FFPTXNJNL_20180326.csv");
		body.setRecordCount("4");

		root.setHEAD(head);
		root.setBODY(body);

		try {
			msg = FFPParticipantMessageConverter.packageReponseObject2Xml(root);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return msg;
	}
}
