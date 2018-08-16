package asgard.engine.asgardengine.utility.binary;

import java.util.Arrays;

/**
 * The Hexadecimal class represents the hexadecimal number representation of a byte sequence.
 * 
 * @author Planters
 *
 */
public class Hexadecimal {

	private final static char[] HEX_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	private String hex = null; // the hexadecimal representation of the given byte array
		
	/**
	 * Create a hexadecimal number from an array of bytes.
	 * 
	 * @param bytes - the byte array to represent as hexadecimal number
	 */
	public Hexadecimal(byte[] bytes) {
		this.hex = Hexadecimal.toHex(bytes);
	}
	
	/**
	 * Create a hexadecimal number form a single byte.
	 * 
	 * @param singleByte - the byte to represent as hexadecimal number
	 */
	public Hexadecimal(byte singleByte) {
		this.hex = Hexadecimal.toHex(singleByte);
	}
	
	/**
	 * Create a hexadecimal number form a single byte.
	 * 
	 * @param singleByte - the byte to represent as hexadecimal number
	 */
	public Hexadecimal(Byte singleByte) {
		if (singleByte != null) {
			this.hex = Hexadecimal.toHex(singleByte.byteValue());
		} else {
			this.hex = null;
		}
	}
	
	// convert this hexadecimal to its corresponding byte representation
	/**
	 * Get the byte representation of this hexadecimal number.
	 * 
	 * @return this hexadecimal number as byte array
	 */
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
	
	/**
	 * Convert the specified byte array to its hexadecimal representation.
	 * 
	 * @param bytes - the byte array to convert
	 * @return the hexadecimal representation of the specified byte sequence
	 */
	public static String toHex(byte[] bytes) {
		if (bytes != null) {
			 char[] hexChars = new char[bytes.length * 2];
			    for (int i = 0; i < bytes.length; i++) {
			        int v = bytes[i] & 0xFF;
			        hexChars[i * 2] = Hexadecimal.HEX_ARRAY[v >>> 4];
			        hexChars[i * 2 + 1] = Hexadecimal.HEX_ARRAY[v & 0x0F];
			    }
			    return new String(hexChars);
		} else {
			return null;
		}
	}
	
	/**
	 * Convert the specified byte to its hexadecimal representation.
	 * 
	 * @param singleByte - the byte to convert
	 * @return the hexadecimal representation of the specified byte
	 */
	public static String toHex(byte singleByte) {
		return Hexadecimal.toHex(new byte[] {singleByte});
	}
	
	/**
	 * Convert the specified byte to its hexadecimal representation.
	 * Returns null if the specified byte is null.
	 * 
	 * @param singleByte - the byte to convert
	 * @return the hexadecimal representation of the specified byte
	 */
	public static String toHex(Byte singleByte) {
		if (singleByte != null) {
			return Hexadecimal.toHex(new byte[] {singleByte.byteValue()});
		} else {
			return null;
		}
	}
	
	/**
	 * Get the string representation of this hexadecimal number.
	 * 
	 * @return the Hexadecimal as string
	 */
	@Override
	public String toString() {
		return this.hex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.hex == null) ? 0 : this.hex.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (this.hex == null && ((Hexadecimal) obj).hex == null) {
				return true;
			} else if (this.hex != null) {
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
		}
		return false;
	}

}
