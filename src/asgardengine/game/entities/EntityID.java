package asgardengine.game.entities;

import asgardengine.utility.BaseID;

public class EntityID extends BaseID {

	public static final int iDLength = 8; // length of the id array
	
	public EntityID(byte[] iD) {
		super(iDLength, iD);
	}
	
	@Override
	public byte[] toByte() {
		return this.getID();
	}

}
