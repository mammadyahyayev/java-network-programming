package az.maqa.network.pg.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class EmailSocket {

	private static Socket smtpSocket;
	private static PrintWriter out;
	private static BufferedReader in;

	public static void main(String[] args) {
		try {
			smtpSocket = new Socket("localhost", 25);
			in = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
			out = new PrintWriter(smtpSocket.getOutputStream(), true);
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: hostname");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: hostname");
		}

		// If everything has been initialized then we want to write some data
		// to the socket we have opened a connection to on port 25
		if (smtpSocket != null && out != null && in != null) {
			try {
				/*
				 * STEP 1 Get a greeting by the server
				 */
				String responseLine;
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("220") != -1) {
						break;
					}
				}

				/*
				 * STEP 2 The client initiates its dialog by responding with a HELO command
				 * identifying itself
				 */

				out.println("HELO " + InetAddress.getLocalHost().getHostAddress());
				System.out.println("HELO " + InetAddress.getLocalHost().getHostAddress());
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("250") != -1) {
						break;
					}
				}

				/*
				 * STEP 3 The client notifies the receiver of the originating email address of
				 * the message in a MAIL FROM command.
				 */

				// out.println("MAIL From: " + fromto);
				out.println("MAIL From: mamedyahyayev@coder.com");
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("250") != -1) {
						break;
					}
				}

				/*
				 * STEP 4 The client notifies the receiver of the recipient email address of the
				 * message in a RCPT TO command.
				 */

				out.println("RCPT TO: jchamber009@gmail.com");
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("250") != -1) {
						break;
					}
				}

				/*
				 * STEP 5 Send DATA command
				 */

				out.println("DATA");
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("354") != -1) {
						break;
					}
				}

				/*
				 * STEP 6 Send Email Body
				 */

				out.println("From: mamedyahyayev@coder.com");
				out.println("To: jchamber009@gmail.com");
				out.println("Subject: Congratulations");
				out.println();
				out.println("Subject: TEST EMAIL"); // message body
				out.println("This is a test message"); // message body
				out.println("Thanks,"); // message body
				out.println();
				out.println(".");

				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("250") != -1) {
						break;
					}
				}

				/*
				 * STEP 7 Send Quit command
				 */

				out.println("QUIT");

				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("221") != -1) {
						break;
					}
				}

				System.out.println("Email successfully sent!");

				out.close();
				in.close();
				smtpSocket.close();

			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}

}
