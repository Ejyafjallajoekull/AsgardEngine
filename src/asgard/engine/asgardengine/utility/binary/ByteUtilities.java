package asgard.engine.asgardengine.utility.binary;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.BitSet;

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
	 * Fuse two byte arrays.
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
	public static final int toInt(byte[] bytes) {
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
	
	/**
	 * Get the byte representation of the specified double.
	 * 
	 * @param number - the double to represent as byte array
	 * @return an array of bytes representing the double
	 */
	public static final byte[] doubleToByte(double number) {
		return (ByteBuffer.allocate(Double.BYTES).putDouble(number)).array();
	}
	
	/**
	 * Get the byte representation of the specified string, with the first integer 
	 * of the bytes being the length of the following array representing the actual string 
	 * so it can be detected in a larger byte array. <br>
	 * Requesting null will return a byte array representing the integer -1.
	 * 
	 * @param string - the string to transform into an array of bytes
	 * @return the byte representation of the specified string
	 */
	public static final byte[] stringToByte(String string) {
		if (string != null) {
			byte[][] bytes = new byte[2][];
			bytes[1] = string.getBytes(); // the byte representation of the string
			bytes[0] = ByteUtilities.integerToByte(bytes[1].length); // the length of the byte array representing the string
			return ByteUtilities.join(bytes);
		} else {
			return ByteUtilities.integerToByte(-1);
		}
	}
	
	/**
	 * Get the byte representation of the specified file, with the first integer 
	 * of the bytes being the length of the following array representing the path as a 
	 * string so it can be detected in a larger byte array. <br>
	 * Requesting null will return a byte array representing the integer -1.
	 * 
	 * @param file - the file to transform into an array of bytes
	 * @return the byte representation of the specified file
	 */
	public static final byte[] fileToByte(File file) {
		if (file != null) {
			byte[][] bytes = new byte[2][];
			bytes[1] = file.getPath().getBytes(); // the byte representation of the string
			bytes[0] = ByteUtilities.integerToByte(bytes[1].length); // the length of the byte array representing the string
			return ByteUtilities.join(bytes);
		} else {
			return ByteUtilities.integerToByte(-1);
		}
	}
	
	/**
	 * Get the byte representation of the specified file, with the first integer 
	 * of the bytes being the length of the following array representing the path as a 
	 * string so it can be detected in a larger byte array. <br>
	 * Requesting null will return a byte array representing the integer -1.
	 * 
	 * @param file - the file to transform into an array of bytes
	 * @param absolutePath - true to specify the absolute path, false to specify the 
	 * path specified by the file (may be relative or absolute)
	 * @return the byte representation of the specified file
	 */
	public static final byte[] fileToByte(File file, boolean absolutePath) {
		if (file != null) {
			byte[][] bytes = new byte[2][];
			if (absolutePath) {
				bytes[1] = file.getAbsolutePath().getBytes(); // the byte representation of the string
			} else {
				bytes[1] = file.getPath().getBytes(); // the byte representation of the string				
			}
			bytes[0] = ByteUtilities.integerToByte(bytes[1].length); // the length of the byte array representing the string
			return ByteUtilities.join(bytes);
		} else {
			return ByteUtilities.integerToByte(-1);
		}
	}
	
	/**
	 * Get the byte representation of an array of booleans. Each bit will represent 
	 * one boolean.
	 * 
	 * @param bools - the array of boolean values to transform into an array of bytes
	 * @return the byte representation of the boolean array
	 */
	public static final byte[] booleansToByte(boolean[] bools) {
		if (bools != null) {
			BitSet bs = new BitSet(bools.length);
			for (int i = 0; i < bools.length; i++) {
				bs.set(i, bools[i]);
			}
			byte[] bytes = new byte[(int) Math.ceil(bools.length / (double) Byte.SIZE)];
			byte[] bitsetBytes = bs.toByteArray();
			if (bytes.length > bitsetBytes.length) {
				for (int i = 0; i < bitsetBytes.length; i++) {
					bytes[i] = bitsetBytes[i];
				}
				return bytes;
			} else {
				return bitsetBytes;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Get the byte representation of the specified array of integers. 
	 * The resulting byte array is length prefixed by an integer.
	 * If null is passed an length prefix of -1 is returned.
	 * 
	 * @param array - an array of integers to transform into a byte array
	 * @return a the byte array representation of the specified integer array
	 */
	public static final byte[] intArrayToByte(int[] array) {
		if (array != null) {
			// save all the ints and a length prefix
			byte[][] bytes = new byte[array.length+1][];
			bytes[0] = ByteUtilities.integerToByte(array.length);
			for (int i = 1; i <= array.length; i++) {
				bytes[i] = ByteUtilities.integerToByte(array[i-1]);
			}
			return ByteUtilities.join(bytes);
		} else {
			return ByteUtilities.integerToByte(-1);
		}
	}

}
