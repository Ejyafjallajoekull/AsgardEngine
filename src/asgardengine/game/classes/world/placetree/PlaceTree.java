package asgardengine.game.classes.world.placetree;

import java.util.ArrayList;
import java.util.HashMap;

import asgardengine.game.classes.world.Placeable;
import asgardengine.game.classes.world.Position;

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
	
	public boolean add(Placeable entity) {
		if (entity != null) {
			PlaceTreeCell cell = this.cellMap.get(this.placeableToKey(entity));
			PlaceTreeCellKey key = this.positionToKey(entity.getPosition());
			if (key != null) {
				if (cell == null) { // add the cell if it's not in the map
					cell = new PlaceTreeCell(key.getX(), key.getY(), key.getZ());
					this.cellMap.put(key, cell);
				}
				return cell.getEntities().add(entity);
			}
		}
		return false;
	}
	
	/**
	 * Get the cell at the specified index position.
	 * 
	 * @param x - the X-index of the cell
	 * @param y - the Y-index of the cell
	 * @param z - the Z-index of the cell
	 * @return the PlaceTreeCell at the specified position
	 */
//	private PlaceTreeCell get(int x, int y, int z) { // this should not be used
//		return this.cellMap.get(new PlaceTreeCellKey(x, y, z));
//	}
	
	/**
	 * Get the cell at the specified position.
	 * 
	 * @param x - the X-coordinate of the cell
	 * @param y - the Y-coordinate of the cell
	 * @param z - the Z-coordinate of the cell
	 * @return the PlaceTreeCell at the specified position
	 */
	public PlaceTreeCell get(double x, double y, double z) {
		return this.cellMap.get(new PlaceTreeCellKey(this.computeIndexX(x), this.computeIndexY(y), this.computeIndexZ(z)));
	}
	
	/**
	 * Get the cell at the specified position.
	 * 
	 * @param position - the Position to search for
	 * @return the PlaceTreeCell at the specified position
	 */
	public PlaceTreeCell get(Position position) {
		if (position != null) {
		return this.cellMap.get(this.positionToKey(position));
		} else {
			return null;
		}
	}
	
	/**
	 * Get the cell at the specified position and the specified amount of surrounding cells.
	 * 
	 * @param position - the Position to search for
	 * @return a list of PlaceTreeCell surrounding the specified position as ArrayList
	 */
	public ArrayList<PlaceTreeCell> get2D(Position position, int surroundingCells) {
		if (position != null && surroundingCells >= 0) {
			PlaceTreeCell cell = null;
			PlaceTreeCellKey cellKey = this.positionToKey(position); // center
			int minCellLengthX = cellKey.getX() - surroundingCells;
			int minCellLengthY = cellKey.getY() - surroundingCells;
			int maxCellLengthX = cellKey.getX() + surroundingCells;
			int maxCellLengthY = cellKey.getY() + surroundingCells;
			int CellLentgthZ = cellKey.getZ(); // the z-plane to get
			ArrayList<PlaceTreeCell> cells = new ArrayList<PlaceTreeCell>();
			for (int i = minCellLengthX; i <= maxCellLengthX; i++) {
				for (int n = minCellLengthY; n <= maxCellLengthY; n++) {
					cell = this.cellMap.get(new PlaceTreeCellKey(i, n, CellLentgthZ));
					if (cell != null) {
						cells.add(cell);
					}
				}
			}
			return cells;
		} else {
			return null;
		}
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

	public Position getCellCenter(PlaceTreeCell cell) {
		if (cell != null) {
			double x = (cell.getIndexX() + 1) * this.cellX - this.cellX*0.5d;
			double y = (cell.getIndexY() + 1) * this.cellY - this.cellY*0.5d;
			double z = (cell.getIndexZ() + 1) * this.cellZ - this.cellZ*0.5d;
			return new Position(x, y, z);
		} else {
			return null;
		}
	}
	
	/**
	 * Get the cell key representing the specified Position.
	 * Cell keys are used for access of an internal HashMap.
	 * 
	 * @param position - the Position vector to represent as cell key
	 * @return the Position vector as PlaceTreeCellKey
	 */
	public PlaceTreeCellKey positionToKey(Position position) {
		if (position != null) {
			return new PlaceTreeCellKey(this.computeIndexX(position.getX()), this.computeIndexY(position.getY()), this.computeIndexZ(position.getZ()));
		} else {
			return null;
		}
	}
	
	/**
	 * Get the cell key representing the position of the specified Placeable.
	 * Cell keys are used for access of an internal HashMap.
	 * 
	 * @param position - the Position vector to represent as cell key
	 * @return the Placeables' Position vector as PlaceTreeCellKey
	 */
	public PlaceTreeCellKey placeableToKey(Placeable entity) {
		if (entity != null) {
			return this.positionToKey(entity.getPosition());
		} else {
			return null;
		}
	}
	
}
