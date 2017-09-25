package asgardengine.game.classes;

import asgardengine.utility.BaseID;
import asgardengine.utility.binary.ByteUtilities;

public class ClassID extends BaseID{
	// a class representing a hexadecimal unique identifier for each GameClass record

	public static final int ID_LENGTH = 4; // length of the id array
	public static final int INDEX_LENGTH = 2; // length of the index array
	private byte[] index = new byte[INDEX_LENGTH];
	
	public ClassID(byte[] iD, byte[] index) {
		super(ID_LENGTH, iD);
		this.index = BaseID.fill(INDEX_LENGTH, index);		
	}
	
	public byte[] getIndex() {
		return this.index.clone();
	}

	@Override
	public byte[] toByte() {
		return ByteUtilities.join(this.index, this.getID());
	}	
	
}
