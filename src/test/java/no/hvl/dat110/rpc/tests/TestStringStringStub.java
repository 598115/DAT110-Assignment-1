package no.hvl.dat110.rpc.tests;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCLocalStub;
import no.hvl.dat110.rpc.RPCUtils;

public class TestStringStringStub extends RPCLocalStub {

	public TestStringStringStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public String m(String str) {
		
		byte[] request = RPCUtils.marshallString(str);
		System.out.println("Marshalled bytearray length " + request.length);
		
		byte[] reply = rpcclient.call((byte)2,request);
		
		String strres = RPCUtils.unmarshallString(reply);
		
		return strres;
	}
}
