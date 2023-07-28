package Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author Ahmed Ramie
 *
 */
class Server {

	boolean hasToken = false;
	boolean sendData = false;
	int recordPort;

	void recordPort(int recport) {
		this.recordPort = recport;
	}

	void recordData() throws Exception {
		byte buff[] = new byte[256];
		DatagramSocket datagramSocket;
		DatagramPacket datagramPacket;
		String message;

		datagramSocket = new DatagramSocket(recordPort);
		datagramPacket = new DatagramPacket(buff, buff.length);
		datagramSocket.receive(datagramPacket);
		datagramSocket.close();

		message = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
		System.out.println("The message is " + message);
	}
}