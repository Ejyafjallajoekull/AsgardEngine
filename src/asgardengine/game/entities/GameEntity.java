package asgardengine.game.entities;

import asgardengine.game.classes.GameClass;

public abstract class GameEntity {
	// a instance of a GameClass
	
	private EntityID entityID = null;

	public GameEntity(EntityID entityID) {
		this.setEntityID(entityID);
	}

	public GameEntity(byte[] bytes) {
		
	}

	public abstract byte[] toBytes();
	public abstract void createFromBytes(byte[] bytes) throws IllegalArgumentException;
	/**
	 * Get the GameClass source object of this GameEntity.
	 * 
	 * @return the GameClass object corresponding to this entity
	 */
	public abstract GameClass getSource();
	
	public EntityID getEntityID() {
		return this.entityID;
	}

	public void setEntityID(EntityID entityID) {
		this.entityID = entityID;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof GameEntity) {
			return ((GameEntity) obj).getEntityID().equals(this.getEntityID());
		}
		return false;
	}
	
}
