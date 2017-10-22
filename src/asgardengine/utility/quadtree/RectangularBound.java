package asgardengine.utility.quadtree;

import asgardengine.game.classes.world.Position;
import asgardengine.game.classes.world.Rotation1D;

/**
 * The RectangularBound class represents a rectangle with 
 * a three-dimensional world orientation, which can be used 
 * for collision detection.
 * 
 * @author Planters
 *
 */
public class RectangularBound {
	
	private double width = 0.0d;
	private double height = 0.0d;
	private Rotation1D rotation = new Rotation1D();
	private Position center = new Position();

	public RectangularBound(Position center, double width, double height) {
		this.setCenter(center);
		this.setWidth(width);
		this.setHeight(height);
	}
	
	/**
	 * Set the center of this RectangularBound to the specified Position vector.
	 * 
	 * @param center - the Position vector to set as center
	 */
	public void setCenter(Position center) {
		if (center != null) {
			this.center.setCoordinates(center.getX(), center.getY(), center.getZ());
		}
	}
	
	/**
	 * Set the center of this RectangularBound to the specified coordinates.
	 * 
	 * @param x - the X-coordinate of the center
	 * @param y - the Y-coordinate of the center
	 * @param z - the Z-coordinate of the center
	 */
	public void setCenter(double x, double y, double z) {
		this.center.setCoordinates(x, y, z);
	}
	
	
	/**
	 * Get a Position vector representing the position of this RectangularBound.
	 * 
	 * @return a Position vector, which is a copy of the internal one
	 */
	public Position getCenter() {
		return this.center.clone();
	}
	
	/**
	 * Check if there is intersection between this RectangularBoundn and the specified one.
	 * 
	 * @param bound - the RectangularBound to check intersection with
	 * @return true if the two RectangularBounds intersect each other
	 */
	public boolean intersects(RectangularBound bound) {
		// TODO: everything
		return false;
	}
	
	/**
	 * Get the width of this RectangularBound.
	 * The width represents its length in X-dimension with an rotation of 
	 * zero degrees.
	 * It will not change depending on the rotation.
	 * 
	 * @return - the width of this object as double
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Get the height of this RectangularBound.
	 * The height represents its length in Y-dimension with an rotation of 
	 * zero degrees.
	 * It will not change depending on the rotation.
	 * 
	 * @return - the height of this object as double
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Get the Rotation1D object of this RectangularBound for manipulation.
	 * 
	 * @return the rotation of this object as its' internal Rotation1D representation
	 */
	public Rotation1D getRotation() {
		return this.rotation;
	}

	/**
	 * Set the rotation of this object either in degrees or radians.
	 * 
	 * @param rotation - the rotation of this object
	 * @param degrees - true to process the passed rotation value as degrees, false to process it as radians
	 */
	public void setRotation(double rotation, boolean degrees) {
		this.rotation.setRotation(rotation, !degrees);
	}
	
	/**
	 * Set the height of this RectangularBound.
	 * 
	 * @param height - the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	
	/**
	 * Set the width of this RectangularBond.
	 * 
	 * @param width - the width to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}
	
	public Position getTopLeft() {
		// TODO: do this right
		double x = this.center.getX() + this.width*Math.cos(this.rotation.asRadians());
		return new Position( , , this.center.getZ());
	}
	
}
