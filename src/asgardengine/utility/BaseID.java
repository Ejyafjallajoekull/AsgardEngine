package asgardengine.utility;

import java.util.Arrays;

import asgardengine.utility.binary.ByteUtilities;
import asgardengine.utility.binary.Hexadecimal;

/**
 * The BaseID class represents an binary identifier. 
 * 
 * @author Planters
 *
 */
public abstract class BaseID {

	private int length = 0;
	private byte[] iD = new byte[this.length]; // the ID of the entity

	/**
	 * The FILLER constant is a byte value to fill the ID byte array if the initially 
	 * specified array is to short to meet the length requirement.
	 */
	public static final byte FILLER = (byte) 0; // the byte to fill arrays, which are too short
	
	/**
	 * Create a BaseID of the specified length from the given byte array.
	 * If the byte array is longer than specified only the first elements are used.
	 * If the byte array is shorter than specified it will be filled with the byte value
	 * specified by the FILLER constant.
	 * 
	 * @param length - the length of the identification array
	 * @param bytes - the array to represent the ID
	 */
	public BaseID(int length, byte[] bytes) {
		if (length >= 0) {
			this.length = length;
		}
		this.iD = fill(this.length, bytes);
	}

	/**
	 * Convert this ID to its' binary representation.
	 * 
	 * @return the binary representation of this ID as byte array
	 */
	public abstract byte[] toByte();
	
	/**
	 * Fill a byte array of the specified length with the given byte array.
	 * If the byte array is longer than specified only the first elements are used.
	 * If the byte array is shorter than specified it will be filled with leading byte values
	 * specified by the FILLER constant.
	 * 
	 * @param length - the length of the array to fill
	 * @param bytes - the byte array to use as base
	 * @return a byte array of the given length based on the input array
	 */
	public static byte[] fill(int length, byte[] bytes) {
		if (length > 0) {
			byte[] b = new byte[length]; 
			if (bytes.length >= length) { // take the first bytes if to long
				for (int i = 0; i < b.length; i++) {
					b[i] = bytes[i];
				}
			} else if (bytes.length > 0) { // if too short, fill the array beginning with the last index
				for (int i = b.length - bytes.length; i < b.length; i++) {
					b[i] = bytes[i - b.length + bytes.length];
				}
				for (int i = 0; i < b.length - bytes.length; i++) { // fill the rest of the array
					b[i] = FILLER;
				}
			}
			return b;
		}
		return new byte[0];
	}
	
	/**
	 * Get the binary identification array.
	 * 
	 * @return a byte array that is a copy of internal one
	 */
	public byte[] getID() {
		return this.iD.clone();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof BaseID) {
				return Arrays.equals(((BaseID) obj).toByte(), this.toByte());
			} else if (obj instanceof byte[]) {
				return Arrays.equals((byte[]) obj, this.toByte());
			} else if (obj instanceof Byte[]) {
				return Arrays.equals((Byte[]) obj, ByteUtilities.wrap(this.toByte()));
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() { // must be hashable for hashmap generation to easily find objects associated with an ID
		return Arrays.hashCode(this.toByte());
	}

	
	@Override
	public String toString() {
		return Hexadecimal.toHex(this.toByte());
	}
	
}
