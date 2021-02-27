package az.maqa.network.pg.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class InetAddressExample {

	public static void main(String[] args) {

		try {
			InetAddress address = InetAddress.getLocalHost();
			System.out.println("Host address: " + address.getHostAddress());
			System.out.println("Host name: " + address.getHostName());

			// we can create socket with inetaddress properties
			Socket socket = new Socket(address, 9000);

			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
