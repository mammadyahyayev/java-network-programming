package az.maqa.network.pg.file_transfer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {

	private final Socket socket;
	private BufferedReader reader;
	private BufferedOutputStream out;
	private BufferedInputStream fileReader;

	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out = new BufferedOutputStream(socket.getOutputStream());

			String fileName = reader.readLine();

			System.out.println(
					"File Name: " + fileName + " has been requested by " + socket.getInetAddress().getHostAddress());
			File file = new File(fileName);

			if (!file.exists()) {
				byte code = (byte) 0;
				out.write(code);
				closeConnection();
			} else {
				byte code = (byte) 1;
				out.write(code);

				fileReader = new BufferedInputStream(new FileInputStream(file));

				byte[] buffer = new byte[1024];

				int bytesRead = 0;
				while ((bytesRead = fileReader.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);

					out.flush();
				}

				closeConnection();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void closeConnection() {
		try {
			if (socket != null) {
				socket.close();
			}

			if (out != null) {
				out.close();
			}

			if (reader != null) {
				reader.close();
			}

			if (fileReader != null) {
				fileReader.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
