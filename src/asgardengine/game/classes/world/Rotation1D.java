package asgardengine.game.classes.world;

/**
 * The Rotation1D class represents a rotation around the X-axis.
 * 
 * @author Planters
 *
 */
public class Rotation1D {
	
//	private double rotationDegrees = 0.0d; // the rotation in degree
	/**
	 * Northern orientation in radians.<br>
	 * This constant equals: <b>0.0 * pi</b> 
	 */
	public final static double RADIANS_NORTH = 0.0d;
	/**
	 * North eastern orientation in radians.<br>
	 * This constant equals: <b>0.25 * pi</b> 
	 */
	public final static double RADIANS_NORTHEAST = 0.25d * Math.PI;
	/**
	 * Eastern orientation in radians.<br>
	 * This constant equals: <b>0.5 * pi</b> 
	 */
	public final static double RADIANS_EAST = 0.5d * Math.PI;
	/**
	 * South eastern orientation in radians.<br>
	 * This constant equals: <b>0.75 * pi</b> 
	 */
	public final static double RADIANS_SOUTHEAST = 0.75 * Math.PI;
	/**
	 * Southern orientation in radians.<br>
	 * This constant equals: <b>1.0 * pi</b> 
	 */
	public final static double RADIANS_SOUTH = Math.PI;
	/**
	 * South western orientation in radians.<br>
	 * This constant equals: <b>1.25 * pi</b> 
	 */
	public final static double RADIANS_SOUTHWEST = 1.25 * Math.PI;
	/**
	 * Western orientation in radians.<br>
	 * This constant equals: <b>1.5 * pi</b> 
	 */
	public final static double RADIANS_WEST = 1.5d * Math.PI;
	/**
	 * North western orientation in radians.<br>
	 * This constant equals: <b>1.75 * pi</b> 
	 */
	public final static double RADIANS_NORTHWEST = 1.75 * Math.PI;
	
	private double rotationRadians = 0.0d; // the rotation in radians
	
	
	
	/**
	 * Create a new rotation with an initial value of 0 radians.
	 */
	public Rotation1D() {
		
	}
	
	/**
	 * Create a rotation of the specified radians.
	 * 
	 * @param radians - the radians of the rotation as double
	 */
	public Rotation1D(double radians) {
		this.setRotation(radians);
	}
	
//	/**
//	 * Create a rotation of the specified value.
//	 * 
//	 * @param rotation - the value of the rotation
//	 * @param rad - true to process the value as radians, false to process them as degrees
//	 */
//	public Rotation1D(double rotation, boolean rad) {
//		if (rad) {
//			this.rotationDegrees = Math.toRadians(rotation);
//		} else {
//			this.rotationDegrees = rotation;
//		}
//	}
	
	/**
	 * Get this objects rotation in degrees.
	 * 
	 * @return this objects rotation value in degrees
	 */
	public double asDegrees() {
		return Math.toDegrees(this.rotationRadians);
	}
	
	/**
	 * Get this objects rotation in radians.
	 * 
	 * @return this objects rotation value in radians
	 */
	public double asRadians() {
		return this.rotationRadians;
	}
	
	/**
	 * Set the rotation of this object in radians.
	 * 
	 * @param radians - the radians to set the rotation to
	 */
	public void setRotation(double radians) {
		this.rotationRadians = radians;
	}
	
	/**
	 * Set the rotation of this object either in degrees or radians.
	 * 
	 * @param rotation - the value of the rotation
	 * @param rad - true to process the value as radians, false to process them as degrees
	 */
	public void setRotation(double rotation, boolean rad) {
		if (rad) {
			this.rotationRadians = rotation;
		} else {
			this.rotationRadians = Math.toRadians(rotation);
		}
	}
	
	/**
	 * Normalise an arbitrary degrees value to a positive scale ranging from 0° to 360°, excluding the latter.
	 * 
	 * @param rotation - the rotation value to normalise, either radians or degrees
	 * @param rad - true to process the rotation parameter as radians, false to process as degrees
	 * @return a normalised rotation value as double
	 */
	public static double normalise(double rotation, boolean rad) {
		double transform = 0.0d;
		if (rad) {
			transform = rotation%(2 * Math.PI);
			if (rotation < 0) {
				transform = 2 * Math.PI + transform;
			}
		} else {
			transform = rotation%360.0d;
			if (rotation < 0) {
				transform = 360.0d + transform;
			}
		}
		return transform;
	}
	
	@Override
	public String toString() {
		return new Double(this.rotationRadians).toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rotation1D) {
			if (((Rotation1D) obj).asRadians() == this.rotationRadians) {
				
			}
		} else if (obj instanceof Double) {
			double d = ((Double) obj).doubleValue();
			if (d == this.rotationRadians) { // check if the double represents 
				return true;
			}
		}
		return false;
	}
	
}
