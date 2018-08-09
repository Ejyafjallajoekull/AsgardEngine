package asgard.engine.asgardengine.game.handler;

import java.util.HashMap;

import asgard.engine.asgardengine.game.classes.ClassID;
import asgard.engine.asgardengine.game.classes.GameClass;

/**
 * The ClassHandler class provides basic data tracking and handling capabilities. <br>
 * This mainly involves ID handling, holding them in an internal HashMap with Key<br>
 * ClassID and Value GameClass.
 * 
 * @author Planters
 *
 */
public final class ClassHandler {

	private static final int HASH_MAP_INIT = 1000000;
	
	private static byte[][] iDMatrix = new byte[(int) Math.pow(256, ClassID.INDEX_LENGTH)][ClassID.ID_LENGTH]; // create an array of all possible indices
	private static HashMap<ClassID, GameClass> iDMap = new HashMap<ClassID, GameClass>(HASH_MAP_INIT);
	
	/**
	 * Adds a GameClass to the handler. <br>
	 * GameClasses with the same ClassID will overwrite each other. <br>
	 * Only the last one will be contained by the handler.
	 * 
	 * @param value - the GameClass to add to the handler
	 */
	public static void add(GameClass value) {
		if (value != null) {
			iDMap.put(value.getClassID(), value);
			byte[] id = value.getClassID().getID();
			int index = integerRepresentation(value.getClassID().getIndex());
			if (isGreater(index, id)) {
				iDMatrix[index] = id;
			}	
		}
	}
	
	/**
	 * Get the GameClass from its' unique ClassID.
	 * 
	 * @param key - the ClassID to look for
	 * @return the GameClass corresponding to the ClassID key
	 */
	public static GameClass get(ClassID key) {
		return iDMap.get(key);
	}
	
	/**
	 * Get the next valid ClassID not already in use considering the given index.
	 * 
	 * @param index - the index to search for
	 * @return - an unused ClassID with the given index
	 */
	public static ClassID nextID(byte[] index) {
		if (index != null) {
			byte[] last = iDMatrix[integerRepresentation(index)].clone(); // do not work on the original array
			int rep = 0;
			for (int i = last.length - 1; i >= 0; i--) { // increment the last byte first
				rep = Byte.toUnsignedInt(last[i]) + 1; // get unsigned byte representation
				last[i] = (byte) rep;
				if (rep != 256) { // stop incrementing if not over the byte limit of 255
					break;
				}
			}
			return new ClassID(last, index);
		} else {
			return null;
		}
	}
	
	// is the byte array greater than the one in the matrix at the given index?
	private static boolean isGreater(int index, byte[] bytes) {
		if (bytes != null) {
			byte[] last = iDMatrix[index];
				if (last != null) {
					if (last.length == bytes.length) {
						for (int i = last.length - 1; i >= 0; i--) { // start at the first entry
							if (Byte.toUnsignedInt(bytes[i]) > Byte.toUnsignedInt(last[i])) {
								return true;
							}
						}
					}
					return false;
				} else {
					return true;
				}
		}
		return false;
	}
	
//	private static byte[] byteRepresentation(int integer) {
//		if (integer >= 0) {
//			int exp = 1;
//			int rep = integer;
//			while (Math.floorDiv(integer, (int) Math.pow(256, exp)) != 0) {
//				exp++;
//			}
//			byte[] bytes = new byte[exp];
//			exp--; // go one step back so the integer can be diveded again
//			int sub = 0;
//			for (int i = 0; i <= exp; i++) {
//				sub = Math.floorDiv(rep, (int) Math.pow(256, exp-i));
//				bytes[i] = (byte) sub;
//				rep -= sub * Math.pow(256, exp-i);
//			}
//			return bytes;
//		} else {
//			return null;
//		}
//	}
	
	private static int integerRepresentation(byte[] bytes) {
		if (bytes != null) {
			int rep = 0;
			int exp = 0;
			for (int i = bytes.length -1; i >= 0; i--) { // count down from the last byte
				rep += Byte.toUnsignedInt(bytes[i]) * Math.pow(256, exp);
				exp++;
			}
			return rep;
		} else {
			return -1;
		}
	}

}
