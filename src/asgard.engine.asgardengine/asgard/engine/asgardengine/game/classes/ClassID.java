package asgard.engine.asgardengine.game.classes;

import java.util.Arrays;

import asgard.engine.asgardengine.utility.BaseID;
import asgard.engine.asgardengine.utility.binary.ByteUtilities;

public class ClassID extends BaseID{
	// a class representing a hexadecimal unique identifier for each GameClass record

	public static final int ID_LENGTH = 4; // length of the id array
	public static final int INDEX_LENGTH = 2; // length of the index array
	public static final int TOTAL_LENGTH = ID_LENGTH + INDEX_LENGTH; // total id length
	private byte[] index = new byte[INDEX_LENGTH];
	
	public ClassID(byte[] iD, byte[] index) {
		super(ID_LENGTH, iD);
		this.index = BaseID.fill(INDEX_LENGTH, index);		
	}
	
	public ClassID(byte[] wholeID) {
		super(ID_LENGTH, Arrays.copyOfRange(BaseID.fill(INDEX_LENGTH + ID_LENGTH, wholeID), INDEX_LENGTH, INDEX_LENGTH + ID_LENGTH));
		this.index = Arrays.copyOfRange(BaseID.fill(INDEX_LENGTH + ID_LENGTH, wholeID), 0, INDEX_LENGTH);		
	}
	
	public byte[] getIndex() {
		return this.index.clone();
	}

	@Override
	public byte[] toByte() {
		return ByteUtilities.join(this.index, this.getID());
	}	
	
}
