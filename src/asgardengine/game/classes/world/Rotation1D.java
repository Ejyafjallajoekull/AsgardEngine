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
		this.setRotation(degrees);
	}
	
	/**
	 * Create a rotation of the specified value.
	 * 
	 * @param rotation - the value of the rotation
	 * @param rad - true to process the value as radians, false to process them as degrees
	 */
	public Rotation1D(double rotation, boolean rad) {
		if (rad) {
			this.rotation = Math.toRadians(rotation);
		} else {
			this.rotation = rotation;
		}
	}
	
	/**
	 * Get this objects rotation in degrees.
	 * 
	 * @return this objects rotation value in degrees
	 */
	public double asDegrees() {
		return this.rotation;
	}
	
	/**
	 * Get this objects rotation in radians.
	 * 
	 * @return this objects rotation value in radians
	 */
	public double asRadians() {
		return Math.toRadians(this.rotation);
	}
	
	/**
	 * Set the rotation of this object in degrees.
	 * 
	 * @param degrees - the degrees to set the rotation to
	 */
	public void setRotation(double degrees) {
		this.rotation = normalise(degrees);
	}
	
	/**
	 * Set the rotation of this object either in degrees or radians.
	 * 
	 * @param rotation - the value of the rotation
	 * @param rad - true to process the value as radians, false to process them as degrees
	 */
	public void setRotation(double rotation, boolean rad) {
		if (rad) {
			this.rotation = Math.toRadians(rotation);
		} else {
			this.rotation = rotation;
		}
	}
	
	/**
	 * Normalise an arbitrary degrees value to a positive scale ranging from 0° to 360°, excluding the latter.
	 * 
	 * @param degrees - the degrees value to normalise
	 * @return a normalised degrees value as double
	 */
	public static double normalise(double degrees) {
		double transform = degrees%360;
		if (degrees < 0) {
			transform = 360.0d + transform;
		}
		return transform;
	}
	
	@Override
	public String toString() {
		return new Double(this.rotation).toString();
	}
	
}
