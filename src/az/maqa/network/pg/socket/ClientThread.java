package az.maqa.network.pg.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientThread extends Thread {

	private final Socket socket;

	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
