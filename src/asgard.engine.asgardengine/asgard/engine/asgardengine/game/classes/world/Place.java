package asgard.engine.asgardengine.game.classes.world;

import asgard.engine.asgardengine.game.classes.ClassID;
import asgard.engine.asgardengine.game.classes.GameClass;
import asgard.engine.asgardengine.game.classes.graphics.Sprite;
import asgard.engine.asgardengine.game.classes.world.placetree.PlaceTree;

/**
 * The Place class contains Placeables to display by the engine.
 * 
 * @author Planters
 *
 */
public class Place extends GameClass {

	public static final byte[] TYPE = {0, 4};
	public static final double CELL_SIZE_X = 3000.0d;
	public static final double CELL_SIZE_Y = 2000.0d;
	public static final double CELL_SIZE_Z = 500.0d;
	public static final int SUPERTILING_SIZE = 256; // TODO: supertiling
	
	private PlaceTree cellTree = new PlaceTree(Place.CELL_SIZE_X, Place.CELL_SIZE_Y, Place.CELL_SIZE_Z);
	
	private Sprite background = null; // the background of the place

	
	/**
	 * Create a Place to hold different Placeables, which are GameEntities most of the time.
	 * 
	 * @param classID - the unique identifier for this Place
	 */
	public Place(ClassID classID) {
		super(classID);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Recreate a previously saved Place from an array of bytes.
	 * 
	 * @param bytes - the byte array to recreate this Place from
	 */
	public Place(byte[] bytes) {
		super(bytes);
		this.createFromBytes(bytes);
	}

	/**
	 * Add the specific Placeable, which should be a GameEntity in most cases, to the Place.
	 * 
	 * @param entity - the Placeable to add to the Place
	 * @return true if the addition has been successful
	 */
	public boolean add(Placeable entity) {
		if (entity != null) {
			boolean success = false;
			if (entity.getPlace() != null) {
				if (entity.getPlace() != this) {
					success = this.cellTree.add(entity);
					if (success) { // remove the entity if successful
						entity.removeFromPlace();
					}
					entity.setPlace(this);
				}
			} else {
				success = this.cellTree.add(entity);
				if (success) { // if the addition was successful set this as the new Place for this object
					entity.setPlace(this);
				}
			}
			return success;
		}
		return false;
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
			return this.add(entity);
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
			if (pos == null) {
				pos = new Position(x, y, z);
			} else {
				pos.setX(x);
				pos.setY(y);
				pos.setZ(z);	
			}
			entity.setPosition(pos);
			return this.add(entity);
		} else {
			return false;
		}
	}
	
	/**
	 * Remove the Placeable from this Place.
	 * 
	 * @param entity - the Placeable to remove from this Place
	 * @return true if the removal was successful
	 */
	public boolean remove(Placeable entity) {
		if (entity != null && entity.getPlace() == this) { // only do this if the entity is registered in this place
			return entity.removeFromPlace();
		}
		return false;
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

	/**
	 * Get the background Sprite for this Place.
	 * 
	 * @return the background of this Place as Sprite
	 */
	public Sprite getBackground() {
		return this.background;
	}

	/**
	 * Set the background Sprite for this Place.
	 * 
	 * @param background - the background Sprite to set
	 */
	public void setBackground(Sprite background) {
		this.background = background;
	}

	/**
	 * Get the cell tree organising all objects of a Place spatially.
	 * 
	 * @return the Places' organising cell tree as PlaceTreeCell
	 */
	public PlaceTree getCellTree() {
		return cellTree;
	}

}
