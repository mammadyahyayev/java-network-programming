package az.maqa.network.pg.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServer {

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(9000);
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			while (true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				socket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
				System.out.println("Client: " + sentence);
				String stringData = "hello client!";
				sendData = stringData.getBytes();
				InetAddress clientIpAddress = receivePacket.getAddress();
				int clientPort = receivePacket.getPort();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIpAddress, clientPort);
				socket.send(sendPacket);
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
