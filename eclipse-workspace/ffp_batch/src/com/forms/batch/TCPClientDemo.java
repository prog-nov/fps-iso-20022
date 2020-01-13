package com.forms.batch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.forms.ffp.core.utils.FFPStringUtils;

public class TCPClientDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		// 将文件流写入
		String filename;
			Socket s = new Socket("172.21.21.250", 8008);
			BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			filename="";
//			File file = new File("C:/Users/chengwenhua/Desktop/test", filename);
			// File file = new
			File file = new File("C:/Users/user/Desktop/xsd/request","FFPCTO01.xml");
			BufferedReader bufIn = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			String line = null;
			StringBuilder strb = new StringBuilder();
			while ((line = bufIn.readLine()) != null) {
				strb.append(line.trim());
			}
			line = strb.toString().replaceAll("[\\s&&[^\r\n]]*(?:[\r\n][\\s&&[^\r\n]]*)+", "");
			int len = line.length();
			line = FFPStringUtils.padZero(len, 8) + line;
			// s.shutdownInput();
			bufOut.write(line);
			bufOut.flush();
			bufIn.close();
			
			bufOut.close();
			s.close();
		}
		}

