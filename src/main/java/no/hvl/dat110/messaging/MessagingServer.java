package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MessagingServer {

	// server-side socket for accepting incoming TCP connections
	private ServerSocket welcomeSocket;

	public MessagingServer(int port) {

		try {

			this.welcomeSocket = new ServerSocket(port);

		} catch (IOException ex) {

			System.out.println("Messaging server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	// accept an incoming connection from a client
	// accept TCP connection on welcome socket and create messaging connection to be returned
	public MessageConnection accept() {

		MessageConnection connection = null;
		Socket ConnectionSocket = null;

	    try {
			ConnectionSocket = this.welcomeSocket.accept();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		connection = new MessageConnection(ConnectionSocket);

		return connection;

	}

	public void stop() {

		if (welcomeSocket != null) {

			try {
				welcomeSocket.close();
			} catch (IOException ex) {

				System.out.println("Messaging server: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

}
