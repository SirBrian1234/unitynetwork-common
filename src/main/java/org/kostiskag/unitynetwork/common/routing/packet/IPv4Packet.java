package org.kostiskag.unitynetwork.common.routing.packet;

import org.kostiskag.unitynetwork.common.utilities.HashUtilities;

import java.io.IOException;
import java.net.InetAddress;

/**
 *
 * @author Konstantinos Kagiampakis
 */
public class IPv4Packet {
	
	public static final int IPversion = 69;
	public static final int MIN_LEN = 20;
	
    public static boolean isIPv4(byte[] packet) {
    	if (packet != null) {
	    	if (packet.length >= MIN_LEN) {
		        int version = HashUtilities.bytesToUnsignedInt(new byte[] {packet[0]});
		        if (version == IPversion) {
		        	return true;
		        }
	    	}
    	}
        return false;
    }    
    
    public static InetAddress getSourceAddress(byte[] packet) throws IOException {
        if (isIPv4(packet)) {
	    	byte[] addr = new byte[4];
	        for (int i=0; i<4; i++){
	             addr[i] = packet[12+i];             
	        }
	        return InetAddress.getByAddress(addr);
        }
        throw new IOException ("This is not an IPv4 packet.");
    }

    public static InetAddress getDestAddress(byte[] packet) throws IOException {
    	if (isIPv4(packet)) {
	    	byte[] addr = new byte[4];
	        for (int i=0; i<4; i++){
	             addr[i] = packet[16+i];             
	        }
	        return InetAddress.getByAddress(addr);
    	}
    	throw new IOException ("This is not an IPv4 packet.");
    }
}

