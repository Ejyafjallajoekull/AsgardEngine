package asgardengine.utility.binary;

import java.nio.ByteBuffer;

public class ByteUtilities {

	// wrap bytes in a Byte array
	/**
	 * Converts an array of primitive byte to its' object representation.
	 * 
	 * @param bytes - the byte array to convert
	 * @return the Byte array representation of the source
	 */
	public static Byte[] wrap(byte[] bytes) {
		if (bytes != null) {
			Byte[] b = new Byte[bytes.length];
			for (int i = 0; i < bytes.length; i++) {
				b[i] = bytes[i];
			}
			return b;
		} else {
			return null;
		}
	}
	
	/**
	 * Fuse to byte arrays.
	 * 
	 * @param extend - the byte array to be extended
	 * @param with - the byte array to append
	 * @return a joined byte array
	 */
	public static byte[] join(byte[] extend, byte[] with) {
		if (extend != null || with != null) {
			if(extend == null) {
				return with.clone();
			} else if (with == null) {
				return extend.clone();
			}
			byte[] joined = new byte[extend.length + with.length];
			for (int i = 0; i < extend.length; i++) {
				joined[i] = extend[i];
			}
			for (int i = extend.length; i < joined.length; i++) {
				joined[i] = with[i - extend.length];
			}
			return joined;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Fuse byte arrays.
	 * 
	 * @param arrays - all the byte arrays to fuse together
	 * @return a single byte array containing all source arrays
	 */
	public static byte[] join(byte[][] arrays) {
		if (arrays != null) {
			int size = 0; // the combined length of all arrays
			for (int i = 0; i < arrays.length; i++) {
				if (arrays[i] != null) {
					size += arrays[i].length;
				}
			}
			byte[] joined = new byte[size];
			byte[] bytes = null;
			for (int i = arrays.length - 1; i >= 0; i--) { // iterate from last to first
				bytes = arrays[i];
				if (bytes != null) { // skip nulls
					for (int n = bytes.length - 1; n >= 0 && size > 0; n--) { // iterate from last to first and decrement the total size
						size--; // do this first to get the right index
						joined[size] = bytes[n];
					}
				}
			}
			return joined;
		} else {
			return null;
		}
	}
	
	// create an int from a byte array
	public static final int toInt(byte[] bytes) throws IllegalArgumentException {
		if (bytes.length >= Integer.BYTES) { // do not check this since an exception should be thrown when passing less than 4 bytes
			return ByteBuffer.wrap(bytes).getInt();
		} else {
			throw new IllegalArgumentException("Less than " + Integer.BYTES + " bytes to read.");
		}
	}
	
	// create a byte array from an int
	public static final byte[] integerToByte(int integer) {
		return (ByteBuffer.allocate(Integer.BYTES).putInt(integer)).array();
	}
	
	// create a byte array from an long
	public static final byte[] longToByte(long number) {
		return (ByteBuffer.allocate(Long.BYTES).putLong(number)).array();
	}

}
