package asgardengine.utility.binary;

import java.util.Arrays;

public class Hexadecimal {

	private final static char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	String hex = null; // the hexadecimal representation of the given byte array
	
	public Hexadecimal(byte[] bytes) {
		this.hex = toHex(bytes);
	}
	
	public Hexadecimal(byte singleByte) {
		this.hex = toHex(singleByte);
	}
	
	// convert this hexadecimal to its' corresponding byte representation
	public byte[] toBytes() {
		if (this.hex != null) {
		    int len = this.hex.length();
		    byte[] data = new byte[len / 2];
		    for (int i = 0; i < len; i += 2) {
		        data[i / 2] = (byte) ((Character.digit(this.hex.charAt(i), 16) << 4) + Character.digit(this.hex.charAt(i+1), 16));
		    }
		    return data;
		} else {
			return null;
		}
	}
	
	// helper function for equals
	private Byte[] toBytesWrap() {
		if (this.hex != null) {
		    int len = this.hex.length();
		    Byte[] data = new Byte[len / 2];
		    for (int i = 0; i < len; i += 2) {
		        data[i / 2] = (byte) ((Character.digit(this.hex.charAt(i), 16) << 4) + Character.digit(this.hex.charAt(i+1), 16));
		    }
		    return data;
		} else {
			return null;
		}
	}
	
	// convert any byte array to its' hexadecimal representation
	public static String toHex(byte[] bytes) {
		if (bytes != null) {
			 char[] hexChars = new char[bytes.length * 2];
			    for ( int i = 0; i < bytes.length; i++ ) {
			        int v = bytes[i] & 0xFF;
			        hexChars[i * 2] = hexArray[v >>> 4];
			        hexChars[i * 2 + 1] = hexArray[v & 0x0F];
			    }
			    return new String(hexChars);
		} else {
			return null;
		}
	}
	
	// overload for a single byte
	public static String toHex(byte singleByte) {
		byte[] b = new byte[1];
		b[0] = singleByte;
		return toHex(b);
	}
	
	@Override
	public String toString() {
		return this.hex.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof byte[]) { // can equal a byte array
				return Arrays.equals((byte[]) obj, this.toBytes());
			} else if (obj instanceof Byte[]) { // can equal a Byte array
				return Arrays.equals((Byte[]) obj, this.toBytesWrap());
			} else if (obj instanceof Hexadecimal) { // can equal another hexadecimal
				if (this.toString() != null && obj.toString() != null) {
					return this.toString().equals(obj.toString());
				} else if (this.toString() == null && obj.toString() == null) {
					return true;
				}
			} else if (obj instanceof Byte && this.hex.length() == 2) { // can equal a single byte
				return obj.equals(this.toBytes()[0]);
			}
		}
		return false;
	}

}
