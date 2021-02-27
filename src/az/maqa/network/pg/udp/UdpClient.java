package az.maqa.network.pg.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpClient {

	public static void main(String[] args) {
		try {
			DatagramSocket clientSocket = new DatagramSocket(0);

			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];

			// this client waits 3 second if it doesn't get any signal from server then
			// throws exception: received timed out
			clientSocket.setSoTimeout(3000); // 3 sec
			String message = "Hello Server";
			// convert message to bytes and equals it to sendData byte array
			sendData = message.getBytes();

			InetAddress serverAddress = InetAddress.getByName("localhost");

			// sending datagram packet
			DatagramPacket packet = new DatagramPacket(sendData, sendData.length, serverAddress, 9000);
			clientSocket.send(packet);

			// receiving datagram packet
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			receiveData = receivePacket.getData();
			String stringReceiveData = new String(receiveData);
			System.out.println("Server: " + stringReceiveData);

			clientSocket.close();

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
