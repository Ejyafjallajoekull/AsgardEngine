package asgardengine.game.classes.world.placetree;

/**
 * The PlaceTreeCellKey class represents a three dimensional key representing the 
 * placement of the cell in the world.
 * 
 * @author Planters
 *
 */
public class PlaceTreeCellKey {

	private int x = 0;
	private int y = 0;
	private int z = 0;
	
	/**
	 * Create a new three dimensional cell key.
	 * 
	 * @param x - the X-index of the cell
	 * @param y - the Y-index of the cell
	 * @param z - the Z-index of the cell
	 */
	public PlaceTreeCellKey(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Get the X-index of the cell key.
	 * 
	 * @return the X-index of the key as int
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the Y-index of the cell key.
	 * 
	 * @return the Y-index of the key as int
	 */
	public int getY() {
		return y;
	}

	/**
	 * Get the Z-index of the cell key.
	 * 
	 * @return the Z-index of the key as int
	 */
	public int getZ() {
		return z;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof PlaceTreeCellKey) {
			PlaceTreeCellKey ptck = (PlaceTreeCellKey) obj;
			if (ptck.getX() == this.getX() && ptck.getY() == this.getY() && ptck.getZ() == this.getZ()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "PlaceTreeCellKey [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}
