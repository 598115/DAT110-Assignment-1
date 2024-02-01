package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	// implement marshalling, call and unmarshalling for write RPC method
	public void write (String message) {
		
	byte[] request = RPCUtils.marshallString(message);

	byte[] respone = rpcclient.call((byte)Common.WRITE_RPCID, request);       

	RPCUtils.unmarshallString(respone);                       

	}
}
