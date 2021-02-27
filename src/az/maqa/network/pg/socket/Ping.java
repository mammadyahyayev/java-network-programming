package az.maqa.network.pg.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ping {

	public static void main(String[] args) {

		String address = "192.168.1.108";
		try {
			Process p = Runtime.getRuntime().exec("ping " + address);

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			boolean isReachable = true;

			String commandOutput = "";
			while ((commandOutput = reader.readLine()) != null) {
				System.out.println(commandOutput);

				if (commandOutput.contains("Destination host unreachable.")) {
					isReachable = false;
					break;
				}

			}

			if (isReachable) {
				System.out.println("Host is reachable");
			} else {
				System.out.println("Host is unreachable");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
