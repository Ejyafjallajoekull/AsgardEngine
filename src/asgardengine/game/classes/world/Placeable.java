package asgardengine.game.classes.world;

/**
 * The Placeable interface provides access to basic 2D(/3D) world placement behaviour.
 * 
 * @author Planters
 *
 */
public interface Placeable {

	/**
	 * The position of the object.
	 */
	public Position position = new Position();
	
	/**
	 * The rotation around the X-axis of the object.
	 */
	public Rotation1D rotation = new Rotation1D();
	
	
}
