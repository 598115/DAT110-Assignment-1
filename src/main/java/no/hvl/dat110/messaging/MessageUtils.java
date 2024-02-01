package no.hvl.dat110.messaging;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
        System.out.println("In messageUtils encapsu: " + message.getData().length);

		byte[] data = message.getData();
		byte header = (byte) message.getData().length;
		byte[] segment = new byte[128];
		segment[0] = header;

		System.out.println("Header size in msgutils: " + header);

		System.arraycopy(data, 0, segment, 1, data.length);

    	return segment;
		
	}

	public static Message decapsulate(byte[] segment) {
		
		int payloadSize = segment[0];
		byte[] payload = new byte[payloadSize];

		System.arraycopy(segment, 1, payload, 0, payloadSize);
		
        Message message = new Message(payload);
		
		return message;
		
	}
	
}
