package asgardengine.utility;

import java.util.Arrays;

import asgardengine.utility.binary.ByteUtilities;
import asgardengine.utility.binary.Hexadecimal;

public abstract class BaseID {

	int length = 0;
	public static final byte FILLER = (byte) 0; // the byte to fill arrays, which are too short
	private byte[] iD = new byte[this.length]; // the ID of the entity
	
	public BaseID(int length, byte[] bytes) {
		if (length >= 0) {
			this.length = length;
		}
		this.iD = fill(this.length, bytes);
	}

	public abstract byte[] toByte();
	
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
