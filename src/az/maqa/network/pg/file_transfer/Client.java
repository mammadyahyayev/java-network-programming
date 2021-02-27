package az.maqa.network.pg.file_transfer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try {
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader reader = new BufferedReader(in);

			String ipAddress = "";
			String fileName = "";

			boolean isValid = false;

			while (!isValid) {
				System.out.print("Please enter the valid ip address: ");
				ipAddress = reader.readLine();
				isValid = validateIpAddress(ipAddress);
			}

			System.out.print("Please enter the filename: ");
			fileName = reader.readLine();

			Socket socket = new Socket(ipAddress, 9090);
			BufferedInputStream input = new BufferedInputStream(socket.getInputStream());
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			out.println(fileName);

			int code = input.read();
			if (code == 1) {

				BufferedOutputStream outputFile = new BufferedOutputStream(
						new FileOutputStream("D:\\" + fileName));
				byte[] buffer = new byte[1024];
				int bytesRead = 0;
				while ((bytesRead = input.read(buffer)) != -1) {
					System.out.print("."); // acts as a download indicator
					outputFile.write(buffer, 0, bytesRead);
					outputFile.flush();
				}
				System.out.println();
				System.out.println("File: " + fileName + " was successfully downloaded!");

			} else {
				System.out.println("File is not present on the server");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static boolean validateIpAddress(String ipAddress) {
		String[] numbers = ipAddress.split("\\.");

		if (numbers.length != 4) {
			System.out.println("Ip Address not valid!!!");
			return false;
		}

		for (String str : numbers) {
			int number = Integer.parseInt(str);
			if (number < 0 || number > 256) {
				System.out.println("Ip Address not valid!!!");
				return false;
			}
		}

		return true;
	}

}
