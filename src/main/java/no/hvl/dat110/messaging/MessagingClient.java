package no.hvl.dat110.messaging;


import java.net.Socket;

public class MessagingClient {

	// name/IP address of the messaging server
	private String server;

	// server port on which the messaging server is listening
	private int port;
	
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	// setup of a messaging connection to a messaging server
	public MessageConnection connect () {

        Socket clientSocket = null;
		try {
			clientSocket = new Socket(this.server, this.port);	
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		MessageConnection connection = new MessageConnection(clientSocket);	
		return connection;
	}
}
