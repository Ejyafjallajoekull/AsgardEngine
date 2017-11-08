package asgardengine.game.classes.world;

import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.world.placetree.PlaceTreeCell;
import asgardengine.game.entities.world.PlaceEntity;
import asgardengine.utility.quadtree.RectangularBound;

/**
 * The Placeable interface provides access to basic 2D(/3D) world placement behaviour.
 * 
 * @author Planters
 *
 */
public interface Placeable extends Drawable{
	
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
	
	/**
	 * Get the cell this object is currently registered in.
	 * The cell needs to be updated on a regular basis for moving objects.
	 * 
	 * @return the cell this object is registered in as PlaceTreeCell
	 */
	public PlaceTreeCell getCell();
	
	/**
	 * Set the cell this object is currently registered in.
	 * This needs to be updated on a regular basis for moving objects.
	 *
	 * @param cell - the PlaceTreeCell to register this object in
	 */
	public void setCell(PlaceTreeCell cell);
	
	/**
	 * Set the PlaceEntity this object is currently registered in.
	 *
	 * @param place - the PlaceEntity to register this object in
	 */
	public void setPlace(Place place);
	
	public Place getPlace();
	
	/**
	 * Removes the Placeable from the Place and cell it is currently registered in.
	 * 
	 * @return true if the removal was successful
	 */
	public boolean removeFromPlace();
	

}
