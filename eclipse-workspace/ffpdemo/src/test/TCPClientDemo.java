package test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClientDemo {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("172.21.21.251", 4700);
		BufferedWriter bufOut = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		bufOut.write("really can do it?");
		bufOut.flush();
		s.close();
	}
}
