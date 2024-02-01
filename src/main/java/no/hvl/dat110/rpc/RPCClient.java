package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.*;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}

	// connect using the RPC client
	public void connect() {
		connection = msgclient.connect();
	}

	// disconnect by closing the underlying messaging connection
	public void disconnect() {
		
		connection.close();
	}

	/*
	 Make a remote call om the method on the RPC server by sending an RPC request message and receive an RPC reply message

	 rpcid is the identifier on the server side of the method to be called
	 param is the marshalled parameter of the method to be called
	 */

	public byte[] call(byte rpcid, byte[] param) {

		byte[] encapped = RPCUtils.encapsulate(rpcid, param);
		System.out.println("Encapped length: " + encapped.length);
		Message requestMessage = new Message(encapped);
		System.out.println("In call before send: " + requestMessage.getData().length);
		connection.send(requestMessage);
				
	    Message responseMessage = connection.receive();
		System.out.println("Responemessage from call: " + responseMessage.getData().length);
        byte[] returnval = responseMessage.getData();

		return returnval;
		
	}

}
