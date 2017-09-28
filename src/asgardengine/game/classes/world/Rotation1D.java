package asgardengine.game.classes.world;

/**
 * The Rotation1D class represents a rotation around the X-axis.
 * 
 * @author Planters
 *
 */
public class Rotation1D {
	
	private double rotation = 0.0; // the rotation in degree
	
	/**
	 * Create a new rotation with an initial value of 0 degree.
	 */
	public Rotation1D() {
		
	}
	
	/**
	 * Create a rotation of the specified degree.
	 * 
	 * @param degrees - the degrees of the rotation as double
	 */
	public Rotation1D(double degrees) {
		this.rotation = degrees;
	}
	
	/**
	 * Create a rotation of the specified value.
	 * 
	 * @param rotation - the value of the rotation
	 * @param rad - true to process the value as radians, false to process them as degrees
	 */
	public Rotation1D(double rotation, boolean rad) {
		if (rad) {
			this.rotation = Math.toDegrees(rotation);
		} else {
			this.rotation = rotation;
		}
	}
	
	public double asDegrees() {
		return this.rotation;
	}
	
	public double asRadians() {
		return Math.toRadians(this.rotation);
	}
	
}
