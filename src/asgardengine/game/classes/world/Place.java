package asgardengine.game.classes.world;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.graphics.Sprite;

/**
 * The Place class contains Placeables to display by the engine.
 * 
 * @author Planters
 *
 */
public class Place extends GameClass {

	public static final byte[] TYPE = {0, 4};
	
	private ArrayList<Placeable> placeables = new ArrayList<Placeable>(); // all placeable entities contained by the map
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>(); // all drawable entities
	private Position center = new Position(); // the current center of display
	private Sprite background = null; // the background of the place
	
	
	
	public Place(ClassID classID) {
		super(classID);
		// TODO Auto-generated constructor stub
	}

	public Place(byte[] bytes) {
		super(bytes);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Add the specific Placeable, which should be a GameEntity in most cases, to the Place.
	 * 
	 * @param entity - the Placeable to add to the Place
	 * @return true if the addition has been successful
	 */
	public boolean add(Placeable entity) {
		if (entity != null) { // do not allow nulls
			if (entity instanceof Drawable) {
				this.drawables.add((Drawable) entity);
			}
			return this.placeables.add(entity);
		} else {
			return false;
		}
	}
	
	/**
	 * Add the specific Placeable, which should be a GameEntity in most cases, to the Place at the given position.
	 * 
	 * @param entity - the Placeable to add to the Place
	 * @param pos - the Position of the Placeable
	 * @return true if the addition has been successful
	 */
	public boolean add(Placeable entity, Position pos) {
		if (entity != null && pos != null) {
			entity.setPosition(pos);
			if (entity instanceof Drawable) {
				this.drawables.add((Drawable) entity);
			}
			return this.placeables.add(entity);
		} else {
			return false;
		}
	}
	
	/**
	 * Add the specific Placeable, which should be a GameEntity in most cases, to the Place at the given coordinates.
	 * 
	 * @param entity - the Placeable to add to the Place
	 * @param x - the X coordinate as double
	 * @param y - the Y coordinate as double
	 * @param z - the Z coordinate as double
	 * @return true if the addition has been successful
	 */
	public boolean add(Placeable entity, double x, double y, double z) {
		if (entity != null) {
			Position pos = entity.getPosition();
			if (pos != null) {
				pos = new Position(x, y, z);
			} else {
				pos.setX(x);
				pos.setY(y);
				pos.setZ(z);	
			}
			entity.setPosition(pos);
			if (entity instanceof Drawable) {
				this.drawables.add((Drawable) entity);
			}
			return this.placeables.add(entity);
		} else {
			return false;
		}
	}
	
	public ArrayList<Drawable> getDrawables() {
//		ArrayList<Drawable> drawables = new ArrayList<Drawable>();
//		for (Placeable d: this.placeables) {
//			if (d instanceof Drawable) {
//				drawables.add((Drawable) d);
//			}
//		}
		return drawables;
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

	public Position getCenter() {
		return this.center;
	}

	public void setCenter(Position center) {
		this.center = center;
	}

	public Sprite getBackground() {
		return this.background;
	}

	public void setBackground(Sprite background) {
		this.background = background;
	}

}
