package az.maqa.network.pg.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9000);
			System.out.println("Waiting for clients....");
			Socket socket = serverSocket.accept();

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Hello Client");

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String clientMessage = reader.readLine();

			System.out.println(clientMessage);

			serverSocket.close();
			out.close();
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
