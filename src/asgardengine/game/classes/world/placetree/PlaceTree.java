package asgardengine.game.classes.world.placetree;

import java.util.HashMap;

import asgardengine.game.classes.world.Placeable;

/**
 * The PlaceTree class represents an dynamic, three dimensional tree, which is organised
 * in cells of a defined space holding all GameEntities added to the tree.
 * 
 * @author Planters
 *
 */
public class PlaceTree {
	
	private double cellX = 1000.0d; // size of a single cell in X-dimension
	private double cellY = 1000.0d; // size of a single cell in Y-dimension
	private double cellZ = 1000.0d; // size of a single cell in Z-dimension
	
	HashMap<PlaceTreeCellKey, PlaceTreeCell> cellMap = new HashMap<PlaceTreeCellKey, PlaceTreeCell>(); // the map holding all cells
	
	/**
	 * Create a PlaceTree with the specified cell dimensions.
	 * The specified dimensions must be positive.
	 * 
	 * @param cellSizeX - the cell size in X-dimension
	 * @param cellSizeY - the cell size in Y-dimension
	 * @param cellSizeZ - the cell size in Z-dimension
	 */
	public PlaceTree(double cellSizeX, double cellSizeY, double cellSizeZ) {
		if (cellSizeX > 0.0d)  { // do not allow zero or negative values
			this.cellX = cellSizeX;			
		}
		if (cellSizeY > 0.0d)  {
			this.cellY = cellSizeY;
		}
		if (cellSizeZ > 0.0d)  {
			this.cellZ = cellSizeZ;
		}
	}
	
	public void add(Placeable entity) {
		if () {
			
		}
	}
	
	/**
	 * Get the cell at the specified position.
	 * 
	 * @param x - 
	 * @param y
	 * @param z
	 * @return
	 */
	public PlaceTreeCell get(int x, int y, int z) {
		return this.cellMap.get(new PlaceTreeCellKey(x, y, z));
	}
	
	// get the hashmap index for a given X-coordinate
	private Integer computeIndexX(double x) {
		double exactIndex = x/this.cellX;
		return new Integer((int) Math.floor(exactIndex));
	}
	
	// get the hashmap index for a given Y-coordinate
	private Integer computeIndexY(double y) {
		double exactIndex = y/this.cellY;
		return new Integer((int) Math.floor(exactIndex));
	}

	// get the hashmap index for a given Z-coordinate
	private Integer computeIndexZ(double z) {
		double exactIndex = z/this.cellZ;
		return new Integer((int) Math.floor(exactIndex));
	}
}
