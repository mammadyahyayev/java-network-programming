package az.maqa.network.pg.file_transfer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {

		try {
			ServerSocket serverSocket = new ServerSocket(9090);

			boolean isStop = false;

			while (!isStop) {
				Socket socket = serverSocket.accept();
				ClientThread clientThread = new ClientThread(socket);
				clientThread.start();
			}

			serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
