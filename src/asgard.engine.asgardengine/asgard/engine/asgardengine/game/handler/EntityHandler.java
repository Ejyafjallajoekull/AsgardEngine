package asgard.engine.asgardengine.game.handler;

import java.util.HashMap;

import asgard.engine.asgardengine.game.entities.EntityID;
import asgard.engine.asgardengine.game.entities.GameEntity;

/**
 * The EntityHandler class provides basic data tracking and handling capabilities. <br>
 * This mainly involves ID handling, holding them in an internal HashMap with Key<br>
 * EntityID and Value GameEntity.
 * 
 * @author Planters
 *
 */
public final class EntityHandler {


	private static final int HASH_MAP_INIT = 1000000;
	
	private static byte[] lastID = new byte[EntityID.iDLength];
	private static HashMap<EntityID, GameEntity> iDMap = new HashMap<EntityID, GameEntity>(HASH_MAP_INIT);
	
	/**
	 * Adds a GameClass Entity to the handler. <br>
	 * GameEntities with the same EntityID will overwrite each other. <br>
	 * Only the last one will be contained by the handler.
	 * 
	 * @param value - the GameEntity to add to the handler
	 */
	public static void add(GameEntity value) {
		if (value != null) {
			iDMap.put(value.getEntityID(), value);
			byte[] id = value.getEntityID().toByte();
			if (isGreater(id)) {
				lastID = id;
			}	
		}
	}
	
	/**
	 * Get the GameEntity from its' unique EntityID.
	 * 
	 * @param key - the EntityID to look for
	 * @return the GameEntity corresponding to the EntityID key
	 */
	public static GameEntity get(EntityID key) {
		return iDMap.get(key);
	}
	
	/**
	 * Get the next valid EntityID not already in use.
	 * 
	 * @param index - the index to search for
	 * @return - an unused EntityID
	 */
	public static EntityID nextID() {
		byte[] last = lastID.clone(); // do not work on the original array
		int rep = 0;
		for (int i = last.length - 1; i >= 0; i--) { // increment the last byte first
			rep = Byte.toUnsignedInt(last[i]) + 1; // get unsigned byte representation
			last[i] = (byte) rep;
			if (rep != 256) { // stop incrementing if not over the byte limit of 255
				break;
			}
		}
		return new EntityID(last);
	}
	
	// is the byte array greater than the one added last?
	private static boolean isGreater(byte[] bytes) {
		if (bytes != null) {
				if (lastID != null) {
					if (lastID.length == bytes.length) {
						for (int i = lastID.length - 1; i >= 0; i--) { // start at the first entry
							if (Byte.toUnsignedInt(bytes[i]) > Byte.toUnsignedInt(lastID[i])) {
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
	
}
