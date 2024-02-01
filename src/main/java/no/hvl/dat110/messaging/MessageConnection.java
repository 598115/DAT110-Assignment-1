package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

    // encapsulate the data contained in the Message and write to the output stream
	public void send(Message message) {

		byte[] data = MessageUtils.encapsulate(message);
		try {
			outStream.write(data);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

    // read a segment from the input stream and decapsulate data into a Message
	public Message receive() {

		byte[] data = new byte[128];
		
		try {
			inStream.read(data);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		Message message = MessageUtils.decapsulate(data);
		return message;		
	}

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}