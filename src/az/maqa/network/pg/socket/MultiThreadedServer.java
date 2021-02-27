package az.maqa.network.pg.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			boolean stop = false;
			serverSocket = new ServerSocket(9000);
			System.out.println("Waiting for clients...");
			while (!stop) {
				Socket socket = serverSocket.accept();
				ClientThread clientThread = new ClientThread(socket);
				clientThread.start();
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
