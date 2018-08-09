package asgard.engine.asgardengine.game.classes.world.placetree;

import java.util.HashSet;

import asgard.engine.asgardengine.game.classes.world.Placeable;
import asgard.engine.asgardengine.utility.quadtree.AsgardRootNode;

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
	private AsgardRootNode root = null;
	
	// coordinate indices
	private int x = 0;
	private int y = 0;
	private int z = 0;
	
	
	/**
	 * Create a cell with the specified coordinates.
	 * 
	 * @param x - the X-index of this PlaceTreeCell
	 * @param y - the Y-index of this PlaceTreeCell
	 * @param z - the Z-index of this PlaceTreeCell
	 */
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
	
	/**
	 * Get whether this cell has a QuadNode attached to it or not.
	 * A QuadNode grants access to advanced object sorting and is 
	 * normally attached when a cell is loaded.
	 * 
	 * @return true if this cell has a QuadNode attached
	 */
	public boolean hasQuadNode() {
		if (this.root == null) {
			return false;
		} else {
			return true;
		}
	}
	
	// TODO: figure out whether cells should become quadtrees or not
	public void initialiseQuadNode() {
		this.root = new AsgardRootNode(1, , );
	}
	
	/**
	 * Destroy the QuadNode.
	 * This will simply remove the reference to the root, so the garbage cleaner 
	 * can handle object removal.
	 */
	public void destroyQuadNode() {
		// TODO: handle unloading
		this.root = null;
	}
}
