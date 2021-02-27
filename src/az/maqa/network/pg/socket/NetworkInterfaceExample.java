package az.maqa.network.pg.socket;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

public class NetworkInterfaceExample {

	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("127.0.0.1");

			NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);

			if (networkInterface != null) {

				System.out.println("NIC name: " + networkInterface.getName());
				System.out.println("NIC display name: " + networkInterface.getDisplayName());
				System.out.println(
						"NIC get harware address (MAC) " + convertByteToString(networkInterface.getHardwareAddress()));
				System.out.println("MTU: " + networkInterface.getMTU());
				System.out.println("Index: " + networkInterface.getIndex());
				NetworkInterface parentInterface = networkInterface.getParent();
				if (parentInterface != null) {
					System.out.println("Parent interface: " + parentInterface.getDisplayName());
				} else {
					System.out.println("No parent interface!");
				}
				System.out.println("Is loopback?  " + networkInterface.isLoopback());
				System.out.println("Is up " + networkInterface.isUp());
				System.out.println("Is virtual " + networkInterface.isVirtual());
				System.out.println("Support multicast?  " + networkInterface.supportsMulticast());
				// Enumeration<InetAddress> nifAddresses = networkInterface.getInetAddresses();
				List<InterfaceAddress> list = networkInterface.getInterfaceAddresses();
				for (int i = 0; i < list.size(); i++) {
					System.out.println("Ip address: " + (list.get(i)).getAddress().getHostAddress());
				}
				System.out.println("");
				System.out.println("__________________________________________________");
				System.out.println("");

			} else {
				System.out.println("Interface not found!!!");
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}

	}

	public static String convertByteToString(byte[] mac) {
		if (mac == null)
			return null;

		StringBuilder sb = new StringBuilder(18);
		for (byte b : mac) {
			if (sb.length() > 0)
				sb.append(':');
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

}
