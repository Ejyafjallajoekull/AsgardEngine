package asgardengine.game.classes.world.placetree;

import java.util.ArrayList;
import java.util.HashSet;

import asgardengine.game.classes.world.Placeable;

/**
 * The PlaceTreeCell class represents a single cell of a PlaceTree containing GameEntities.
 * 
 * @author Planters
 *
 */
public class PlaceTreeCell {
	
//	private HashMap<EntityID, Placeable> entityMap = new HashMap<EntityID, Placeable>(); // maybe a list is sufficient
	
//	private ArrayList<Placeable> entityList = new ArrayList<Placeable>();
	private HashSet<Placeable> entityList = new HashSet<Placeable>(100000);
//	private ArrayList<TileEntity> tileList = new ArrayList<TileEntity>();
//	private ArrayList<ActorEntity> actorList = new ArrayList<ActorEntity>();
	// coordinate indices
	private int x = 0;
	private int y = 0;
	private int z = 0;
	
	
	public PlaceTreeCell(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof PlaceTreeCell) {
			PlaceTreeCell cell = (PlaceTreeCell) obj;
			if (this.getIndexX() == cell.getIndexX() && this.getIndexY() == cell.getIndexY() && this.getIndexZ() == cell.getIndexZ()) {
				if (this.getEntities().equals(cell.getEntities())) {
					return true;
				}
			}
		}
		return false;
	}

//	public HashMap<EntityID, Placeable> getEntityMap() {
//		return entityMap;
//	}

	/**
	 * Get the X-index of this cell.
	 * 
	 * @return the X-index of this PlaceTreeCell as int
	 */
	public int getIndexX() {
		return this.x;
	}

	/**
	 * Get the Y-index of this cell.
	 * 
	 * @return the Y-index of this PlaceTreeCell as int
	 */
	public int getIndexY() {
		return this.y;
	}

	/**
	 * Get the Z-index of this cell.
	 * 
	 * @return the Z-index of this PlaceTreeCell as int
	 */
	public int getIndexZ() {
		return this.z;
	}

	/**
	 * Get a list of all objects in the defined cell.
	 * 
	 * @return all objects in the cell as ArrayList of Placeables
	 */
	public HashSet<Placeable> getEntities() {
		return this.entityList;
	}
}
