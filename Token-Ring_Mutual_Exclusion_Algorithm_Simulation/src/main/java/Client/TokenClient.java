package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Ahmed Ramie
 *
 */
class TokenClient12 {
	InetAddress inetAddress;
	int sendport, recordPort;
	boolean hasToken = true;
	boolean setSendData = false;
	TokenClient12 tkcl, tkser;

	TokenClient12(InetAddress inetAddress) {

		this.inetAddress = inetAddress;
	}

	void setSendPort(int sendport) {
		this.sendport = sendport;
	}

	void setRecPort(int recport) {
		this.recordPort = recport;
	}

	void sendData() throws Exception {
		BufferedReader bufferedReader;
		String token = "Token";
		DatagramSocket datagramSocket;
		DatagramPacket datagramPacket;

		if (setSendData == true) {
			System.out.println("sending ");
			System.out.println("Enter the Data");
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			token = "Client….. " + bufferedReader.readLine();
			System.out.println("now sending");

		}
		datagramSocket = new DatagramSocket(sendport);
		datagramPacket = new DatagramPacket(token.getBytes(), token.length(), inetAddress, sendport - 1000);
		datagramSocket.send(datagramPacket);
		datagramSocket.close();
		setSendData = false;
		hasToken = false;
	}

	void recData() throws Exception {
		String message;
		byte buffer[] = new byte[256];
		DatagramSocket datagramSocket;
		DatagramPacket datagramPacket;

		datagramSocket = new DatagramSocket(recordPort);
		datagramPacket = new DatagramPacket(buffer, buffer.length);
		datagramSocket.receive(datagramPacket);
		datagramSocket.close();
		message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
		System.out.println("The data is " + message);

		if (message.equals("Token")) {
			hasToken = true;
		}
	}

}