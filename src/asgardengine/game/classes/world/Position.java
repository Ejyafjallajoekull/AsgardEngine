package asgardengine.game.classes.world;

/**
 * The Position class represents a three dimensional vector.
 * 
 * @author Planters
 *
 */
public class Position {
	
	// TODO: add function such as add, scalar product, distance,...
	
	private double x = 0.0;
	private double y = 0.0;
	private double z = 0.0;
	
	/**
	 * Create a new three dimensional vector with the coordinates {0, 0, 0}.
	 */
	public Position() {
		
	}
	
	/**
	 * Create a new three dimensional vector with the specified coordinates.
	 * 
	 * @param x - the X coordinate as double
	 * @param y - the Y coordinate as double
	 * @param z - the Z coordinate as double
	 */
	public Position(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Convert this vector to its' double array representation.
	 * 
	 * @return a array of doubles with a length of 3
	 */
	public double[] toArray() {
		return new double[]{this.x, this.y, this.z};
	}
	
	/**
	 * Get the X coordinate of this vector.
	 * 
	 * @return the X coordinate of this vector as a double
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Set the X coordinate.
	 * 
	 * @param x - the double to set as new X coordinate
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Get the Y coordinate of this vector.
	 * 
	 * @return the Y coordinate of this vector as a double
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Set the Y coordinate.
	 * 
	 * @param y - the double to set as new Y coordinate
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Get the Z coordinate of this vector.
	 * 
	 * @return the Z coordinate of this vector as a double
	 */
	public double getZ() {
		return this.z;
	}
	
	/**
	 * Set the Z coordinate.
	 * 
	 * @param z - the double to set as new Z coordinate
	 */
	public void setZ(double z) {
		this.z = z;
	}
	

}
