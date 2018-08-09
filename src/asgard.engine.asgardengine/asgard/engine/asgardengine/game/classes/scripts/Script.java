package asgard.engine.asgardengine.game.classes.scripts;

import asgard.engine.asgardengine.game.entities.GameEntity;

/**
 * The Script class provides support for different events performed by different GameEntitys.
 * Scripts are attached to the GameClasses corresponding to the GameEntity.
 * 
 * @author Planters
 *
 */
public abstract class Script {
	
	/**
	 * This event is fired on the first initialisation of the object.
	 */
	public abstract void onInit();
	
	/**
	 * Get the GameEntity source of this script.
	 * 
	 * @return the GameEntity that this script is attached to
	 */
	public abstract GameEntity getSource();
	
}
