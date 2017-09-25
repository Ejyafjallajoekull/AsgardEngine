package asgardengine.game.entities;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;

public abstract class GameEntity extends GameClass {
	// a instance of a GameClass
	
	private EntityID entityID = null;

	public GameEntity(EntityID entityID, ClassID classID) {
		super(classID); // force setting the ClassID
		this.setEntityID(entityID);
	}

	public GameEntity(byte[] bytes) {
		super(bytes);
	}

	public abstract byte[] toBytes();
	public abstract void createFromBytes(byte[] bytes) throws IllegalArgumentException;
	
	public EntityID getEntityID() {
		return this.entityID;
	}

	public void setEntityID(EntityID entityID) {
		this.entityID = entityID;
	}

}
