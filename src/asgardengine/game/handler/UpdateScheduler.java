package asgardengine.game.handler;

import java.util.ArrayList;

/**
 * The UpdateScheduler class handles all relevant updates of objects registered to it via 
 * the Updatable interface.
 * 
 * @author Planters
 *
 */
public final class UpdateScheduler {
	
	private static ArrayList<Updatable> updatableObjects = new ArrayList<Updatable>();

	/**
	 * Add the specified object to the update schedule.
	 * 
	 * @param object - the Updatable to add to the update schedule
	 * @return true if the object was successfully added to the update schedule
	 */
	public static boolean registerForUpdate(Updatable object) {
		if (object != null && !updatableObjects.contains(object)) {
			return updatableObjects.add(object);
		}
		return false;
	}
	
	/**
	 * Remove the specified object from the update schedule.
	 * 
	 * @param object - the Updatable to remove from the update schedule
	 * @return true if the object was successfully removed from the update schedule
	 */
	public static boolean unregisterForUpdate(Updatable object) {
		if (object != null) {
			return updatableObjects.remove(object);
		}
		return false;
	}
	
	/**
	 * Schedule a update for all registered objects.
	 * Thereby the update() function of each object is called.
	 */
	public static void scheduleUpdate() {
		for(Updatable u : updatableObjects) {
			u.update();
		}
	}

}
