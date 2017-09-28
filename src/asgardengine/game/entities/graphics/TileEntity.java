package asgardengine.game.entities.graphics;

import java.awt.image.BufferedImage;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.world.Placeable;
import asgardengine.game.classes.world.Tile;
import asgardengine.game.entities.EntityID;
import asgardengine.game.entities.GameEntity;

public class TileEntity extends GameEntity implements Drawable, Placeable {

	private Tile tile = null;
	
	public TileEntity(EntityID entityID, ClassID classID) {
		super(entityID, classID);
		// TODO Auto-generated constructor stub
	}

	public TileEntity(byte[] bytes) {
		super(bytes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] toBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createFromBytes(byte[] bytes) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage toBufferedImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
