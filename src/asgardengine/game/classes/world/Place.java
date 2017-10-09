package asgardengine.game.classes.world;

import java.util.ArrayList;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.graphics.Sprite;
import asgardengine.game.entities.actors.ActorEntity;
import asgardengine.game.entities.world.TileEntity;

/**
 * The Place class contains Placeables to display by the engine.
 * 
 * @author Planters
 *
 */
public class Place extends GameClass {

	public static final byte[] TYPE = {0, 4};
	public static final int PLACE_HEIGTH = 2000;
	public static final int PLACE_WIDTH = 4000;
	public static final int SUPERTILING_SIZE = 256;
	
	private ArrayList<Placeable> placeables = new ArrayList<Placeable>(); // all placeable entities contained by the map
	private ArrayList<Drawable> drawables = new ArrayList<Drawable>(); // all drawable entities
	private Place north = null; // the place to the north of this one
	private Place south = null; // the place to the south of this one
	private Place west = null; // the place to the west of this one
	private Place east = null; // the place to the east of this one
	private Sprite background = null; // the background of the place

//	private ArrayList<TileEntity> superTiles = new ArrayList<TileEntity>(); // all static tiles
	private ArrayList<TileEntity> statics = new ArrayList<TileEntity>(); // all static tiles
	private ArrayList<TileEntity> tiles = new ArrayList<TileEntity>(); // all non static tiles
	private ArrayList<ActorEntity> actors = new ArrayList<ActorEntity>(); // all actors
//	private ArrayList<Item> items = new ArrayList<Item>(); // all drawable entities

	
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
	 * Add the specific TileEntity to the Place. <br>
	 * Static and non-static Tiles will be treated separately.
	 * 
	 * @param tile - the TileEntity to add to the Place
	 * @return true if the addition has been successful
	 */
	public boolean add(TileEntity tile) {
		if (tile != null) { // do not allow nulls
			if (tile.getSource().isStatic()) {
				return this.statics.add(tile);
			} else {
				return this.tiles.add(tile);
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Add the specific ActorEntity to the Place. <br>
	 * Static and non-static Tiles will be treated separately.
	 * 
	 * @param actor - the ActorEntity to add to the Place
	 * @return true if the addition has been successful
	 */
	public boolean add(ActorEntity actor) {
		if (actor != null) { // do not allow nulls
				return this.actors.add(actor);
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
	
	private void supertile() {
		//TODO: implement supertiling
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

	public Sprite getBackground() {
		return this.background;
	}

	public void setBackground(Sprite background) {
		this.background = background;
	}

	public ArrayList<TileEntity> getStatics() {
		return statics;
	}

	public ArrayList<TileEntity> getTiles() {
		return tiles;
	}

	public ArrayList<ActorEntity> getActors() {
		return actors;
	}

}
