package asgardengine.game.classes.world;

import asgardengine.utility.quadtree.RectangularBound;

/**
 * The Placeable interface provides access to basic 2D(/3D) world placement behaviour.
 * 
 * @author Planters
 *
 */
public interface Placeable {
	
	/**
	 * Get the position of the object.
	 * 
	 * @return the Position of this object.
	 */
	public Position getPosition();
	
	/**
	 * Get the rotation around the X-axis of the object.
	 * 
	 * @return the Rotation1D of this object.
	 */	
	public Rotation1D getRotation();
	
	/**
	 * Set the position of the object.
	 * 
	 * @param position - the Position to set
	 */
	public void setPosition(Position position);
	
	/**
	 * Set the rotation around the X-axis of the object.
	 * 
	 * @param rotation - the Rotation1D to set
	 */
	public void setRotation(Rotation1D rotation);
	
	/**
	 * Get a rectangular bound of this object.
	 * 
	 * @return a Rectangle representing the boundaries of this object
	 */
	public RectangularBound getBounds();
	
	/**
	 * Get the width of the objects boundary.
	 * This equals the X-scale.
	 * 
	 * @return the width of the object as double
	 */
	public double getWidth();
	
	/**
	 * Get the height of the objects boundary.
	 * This equals the Y-scale.
	 * 
	 * @return the height of the object as double
	 */
	public double getHeight();
	
	/**
	 * Get the Z-height of the object.
	 * This equals the Z-scale.
	 * 
	 * @return the Z-height of the object as double
	 */
	public double getZHeight();

}
