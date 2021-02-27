package az.maqa.network.pg.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiUserServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			boolean stop = false;
			serverSocket = new ServerSocket(9000);
			System.out.println("Waiting for clients...");
			while (!stop) {
				Socket socket = serverSocket.accept();
				PrintStream out = new PrintStream(socket.getOutputStream(), true);
				out.println("Hello Client enter your name");

				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String clientName = reader.readLine();

				out.println("Enter your message");

				String clientMessage = reader.readLine();

				System.out.println(clientName + ":" + clientMessage);

				socket.close();
				reader.close();
				out.close();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
