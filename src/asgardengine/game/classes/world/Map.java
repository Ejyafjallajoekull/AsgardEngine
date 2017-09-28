package asgardengine.game.classes.world;

import java.awt.Color;
import java.util.ArrayList;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.graphics.Drawable;

/**
 * The Map class contains Placeables to display by the engine.
 * 
 * @author Planters
 *
 */
public class Map extends GameClass {

	public static final byte[] TYPE = {0, 4};
	
	private ArrayList<Placeable> placeables = new ArrayList<Placeable>(); // all placeable entities contained by the map
	private Position center = new Position(); // the current center of display
	private Color background = Color.BLACK; // the background of the map
	
	
	
	public Map(ClassID classID) {
		super(classID);
		// TODO Auto-generated constructor stub
	}

	public Map(byte[] bytes) {
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
		return TYPE;
	}

}
