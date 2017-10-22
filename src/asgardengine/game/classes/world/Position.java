package asgardengine.game.classes.world;

import java.util.Arrays;

/**
 * The Position class represents a three dimensional vector. <br>
 * It also includes a movement vector.
 * 
 * @author Planters
 *
 */
public class Position {
	
	// TODO: add function such as degrees & movement & acceleration
	
	private double x = 0.0d;
	private double y = 0.0d;
	private double z = 0.0d;
	private double moveX = 0.0d;
	private double moveY = 0.0d;
	private double moveZ = 0.0d;
	private long movementStart = -1l;
//	private Position end = null;
	
	/**
	 * Create a new three dimensional vector with the coordinates {0, 0, 0}.
	 */
	public Position() {
		
	}
	
	/**
	 * Create a new three dimensional vector with the specified coordinates.
	 * 
	 * @param x - the X-coordinate as double
	 * @param y - the Y-coordinate as double
	 * @param z - the Z-coordinate as double
	 */
	public Position(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Convert this vector to its' double array representation. <br>
	 * Starting with the coordinates and followed by the movement vector.
	 * 
	 * @return a array of doubles with a length of 6
	 */
	public double[] toArray() {
		this.updateCoordinates();
		return new double[]{this.x, this.y, this.z, this.moveX, this.moveY, this.moveZ};
	}
	
	/**
	 * Get the X coordinate of this vector.
	 * 
	 * @return the X coordinate of this vector as a double
	 */
	public double getX() {
		this.updateCoordinates();
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
		this.updateCoordinates();
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
		this.updateCoordinates();
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
	
	/**
	 * Set the coordinates of this Position vector to the specified ones.
	 * 
	 * @param x - the X-coordinate as double
	 * @param y - the Y-coordinate as double
	 * @param z - the Z-coordinate as double
	 */
	public void setCoordinates(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Get the X-value of the movement vector of this Position.
	 * 
	 * @return the X-movement [value/second] as double
	 */
	public double getMovementX() {
		return this.moveX;
	}
	
	/**
	 * Get the Y-value of the movement vector of this Position.
	 * 
	 * @return the Y-movement [value/second] as double
	 */
	public double getMovementY() {
		return this.moveY;
	}
	
	/**
	 * Get the Z-value of the movement vector of this Position.
	 * 
	 * @return the Z-movement [value/second] as double
	 */
	public double getMovementZ() {
		return this.moveZ;
	}
	
	/**
	 * Set the X-value of this Positions' movement vector to the specified value.
	 * 
	 * @param x - the X-movement [value/second] as double
	 */
	public void setMovementX(double x) {
		this.updateCoordinates();
		this.moveX = x;
	}
	
	/**
	 * Set the Y-value of this Positions' movement vector to the specified value.
	 * 
	 * @param y - the Y-movement [value/second] as double
	 */
	public void setMovementY(double y) {
		this.updateCoordinates();
		this.moveY = y;
	}
	
	/**
	 * Set the Z-value of this Positions' movement vector to the specified value.
	 * 
	 * @param z - the Z-movement [value/second] as double
	 */
	public void setMovementZ(double z) {
		this.updateCoordinates();
		this.moveZ = z;
	}
	
	/**
	 * Set the movement values of this Position vector to the specified ones.
	 * 
	 * @param x - the X-value [value/second] as double
	 * @param y - the Y-value [value/second] as double
	 * @param z - the Z-value [value/second] as double
	 */
	public void setMovement(double x, double y, double z) {
		this.updateCoordinates();
		this.moveX = x;
		this.moveY = y;
		this.moveZ = z;
	}
	
	/**
	 * Checks if this position has a movement vector, which amount is different from zero.
	 * 
	 * @return true if the amount of the movement vector of this Position is different from zero
	 */
	public boolean isMoving() {
		if (this.moveX != 0.0d || this.moveY != 0.0d || this.moveZ != 0.0d) { // amount == 0.0d could also be checked
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Calculate the movement speed of this Position in value/second.
	 * 
	 * @return the movement speed in value/second as double
	 */
	public double getSpeed() {
		return amount(new Position(this.moveX, this.moveY, this.moveZ));
	}
	
	/**
	 * Calculate the vector product of this and the specified vector. <br>
	 * If the specified vectors is null, a copy of this vector is returned.
	 * 
	 * @param vector - the vector to calculate the vector product with
	 * @return a Position vector, which is the vector product of this and the specified vector
	 */
	public Position vectorProduct(Position vector) {
		return vectorProduct(this, vector);
	}
	
	/**
	 * Calculate the standard scalar product of this vector and the specified vector.
	 * 
	 * @param vector - the Position vector to calculate the scalar product with
	 * @return the scalar product of this vector and the specified one 
	 */
	public double scalarProduct(Position vector) {
		return scalarProduct(this, vector);
	}
	
	/**
	 * Calculate the amount of this vector.
	 * 
	 * @return the amount of this vector as double
	 */
	public double amount() {
		return amount(this);
	}
	
	/**
	 * Add the specified vector to this one.
	 * 
	 * @param addend - the Position vector to add to this vector
	 */
	public void add(Position addend) {
		 if (addend != null) {
				this.setX(this.getX() + addend.getX());
				this.setY(this.getY() + addend.getY());
				this.setZ(this.getZ() + addend.getZ());
		 }
	}
	
	/**
	 * Subtract the specified vector to this one.
	 * 
	 * @param subtrahend - the Position vector to subtract from this vector
	 */
	public void subtract(Position subtrahend) {
		 if (subtrahend != null) {
				this.setX(this.getX() - subtrahend.getX());
				this.setY(this.getY() - subtrahend.getY());
				this.setZ(this.getZ() - subtrahend.getZ());
		 }
	}
	
	/**
	 * Multiply this vector with the specified multiplier.
	 * 
	 * @param multiplier - the double to multiply this vector with
	 */
	public void multiply(double multiplier) {
		this.setX(this.getX() * multiplier);
		this.setY(this.getY() * multiplier);
		this.setZ(this.getZ() * multiplier);
	}
	
	/**
	 * Divide this vector by the specified divisor.
	 * 
	 * @param divisor - the double to divide this vector by
	 */
	public void divide(double divisor) {
		this.setX(this.getX() * divisor);
		this.setY(this.getY() * divisor);
		this.setZ(this.getZ() * divisor);	}
	
	/**
	 * Calculate the distance of this vector to the specified vector. <br>
	 * This is equal to: <b> amount(this.subtract(vector)) </b>
	 * 
	 * @param vector - the Position vector to calculate the distance to
	 * @return the distance between both Position vectors as double
	 */
	public double distance(Position vector) {
		return distance(this, vector);
	}
	
	/**
	 * Get the unit vector of this Position vector.
	 * 
	 * @return the unit vector of the input vector as Position 
	 */
	public Position unitVector() {
		return Position.unitVector(this);
	}
	
	/**
	 * Returns the angle theta between this and the specified Position vector 
	 * either in degrees or radians.
	 * 
	 * @param vector - the Position vector to calculate the angle against
	 * @param degrees - true to return the angle in degrees, false to return the angle in radians
	 * @return the angle between the Position vectors as double
	 */
	public double angle(Position vector, boolean degrees) {
		return Position.angle(this, vector, degrees);
	}
	
	// update the coordinates of this Position vector if it is currently moving
	private void updateCoordinates() {
		if (this.isMoving()) {
			if (this.movementStart >= 0l) { // for all preceding calls
				long diff = System.nanoTime() - this.movementStart;
				double diffSec = diff / 1000000000.0d; // the difference in seconds
				this.x += this.getMovementX() * diffSec;
				this.y += this.getMovementY() * diffSec;
				this.z += this.getMovementZ() * diffSec;
				this.movementStart += diff; // set for the next update interval
			} else { // for the first call
				this.movementStart = System.nanoTime();
			}
		} else { // set to do not track
			this.movementStart = -1l;
		}
	}
	
	/**
	 * Calculate the vector product of the two vectors. <br>
	 * If one of the vectors is null, a copy of the other vector is returned.
	 * 
	 * @param vector1 - the first Position vector
	 * @param vector2 - the second Position vector
	 * @return a Position vector, which is the vector product of the two input vectors
	 */
	public static Position vectorProduct(Position vector1, Position vector2) {
		if ( vector1 != null && vector2 != null) {
			double x = (vector1.getY()*vector2.getZ()) - (vector1.getZ()*vector2.getY());
			double y = (vector1.getZ()*vector2.getX()) - (vector1.getX()*vector2.getZ());
			double z = (vector1.getX()*vector2.getY()) - (vector1.getY()*vector2.getX());
			return new Position(x, y, z);
		} else {
			if (vector1 != null) {
				return vector1.clone();
			} else if (vector2 != null) {
				return vector2.clone();
			} else { // both vectors are null
				return null;
			}
		}
	}
	
	/**
	 * Calculate the standard scalar product of the specified vectors.
	 * 
	 * @param vector1 - the first Position vector
	 * @param vector2 - the second Position vector
	 * @return the standard scalar product of both vectors as double
	 */
	public static double scalarProduct(Position vector1, Position vector2) {
		if ( vector1 != null && vector2 != null) {
			double product = (vector1.getX()*vector2.getX()) + (vector1.getY()*vector2.getY()) + (vector1.getZ()*vector2.getZ());
			return product;
		} else {
			return 0.0d;
		}
	}
	
	/**
	 * Calculate the amount of the specified vector.
	 * 
	 * @param vector - the Position vector
	 * @return the amount of the specified Position
	 */
	public static double amount(Position vector) {
		return Math.sqrt(scalarProduct(vector, vector));
	}
	
	/**
	 * Calculate the sum of the two Position vectors. <br>
	 * If one of the vectors is null, a copy of the other vector is returned.
	 * 
	 * @param augend - the Position vector to be increased
	 * @param addend - the Position vector to added
	 * @return a Position vector, which is the sum of the specified input vectors
	 */
	public static Position add(Position augend, Position addend) {
		if ( augend != null && addend != null) {
			double x = augend.getX() + addend.getX();
			double y = augend.getY() + addend.getY();
			double z = augend.getZ() + addend.getZ();
			return new Position(x, y, z);
		} else {
			if (augend != null) {
				return augend.clone();
			} else if (addend != null) {
				return addend.clone();
			} else { // both vectors are null
				return null;
			}
		}
	}
	
	/**
	 * Calculates the product of a Position vector and a multiplier.
	 * 
	 * @param multiplicand - the Position vector to multiply
	 * @param multiplier - the double to multiply the vector with
	 * @return a Position vector, which is the product of the multiplication
	 */
	public static Position multiply(Position multiplicand, double multiplier) {
		if (multiplicand != null) {
			return new Position(multiplicand.getX()*multiplier, multiplicand.getY()*multiplier, multiplicand.getZ()*multiplier);
		} else {
			return null;
		}
	}
	
	/**
	 * Calculate the quotient of a Position vector and a divisor.
	 * 
	 * @param dividend - the Position vector to divide
	 * @param divisor - the double to divide the vector by
	 * @return a Position vector, which is the quotient of the division
	 */
	public static Position divide(Position dividend, double divisor) {
		if (dividend != null) { // double, so it can handle infinity
			return new Position(dividend.getX()/divisor, dividend.getY()/divisor, dividend.getZ()/divisor);
		} else {
			return null;
		}
	}
	
	/**
	 * Calculate the difference between the minuend and subtrahend vector. <br>
	 * If one of the vectors is null, a copy of the other vector is returned.
	 * 
	 * @param minuend - the Position vector to subtract from
	 * @param subtrahend - the Position vector to be subtracted
	 * @return a Position vector, which is the difference between minuend and subtrahend
	 */
	public static Position subtract(Position minuend, Position subtrahend) {
		if ( minuend != null && subtrahend != null) {
			double x = minuend.getX() - subtrahend.getX();
			double y = minuend.getY() - subtrahend.getY();
			double z = minuend.getZ() - subtrahend.getZ();
			return new Position(x, y, z);
		} else {
			if (minuend != null) {
				return minuend.clone();
			} else if (subtrahend != null) {
				return subtrahend.clone();
			} else { // both vectors are null
				return null;
			}
		}
	}
	
	/**
	 * Calculate the distance between the two specified vectors. <br>
	 * This is equal to: <b> amount(subtract(vector1, vector2)) </b>
	 * 
	 * @param vector1 - the first Position vector
	 * @param vector2 - the second Position vector
	 * @return the distance between both Position vectors as double
	 */
	public static double distance(Position vector1, Position vector2) {
		if ( vector1 != null && vector2 != null) {
			return amount(subtract(vector1, vector2));
		} else {
			if (vector1 != null) {
				return vector1.amount();
			} else if (vector2 != null) {
				return vector2.amount();
			} else { // both vectors are null
				return 0.0d;
			}
		}
	}
	
	/**
	 * Get the unit vector of the specified Position vector.
	 * 
	 * @param vector - the vector to get the unit vector from
	 * @return the unit vector of the input vector as Position 
	 */
	public static Position unitVector(Position vector) {
		if (vector != null) {
			return Position.divide(vector, vector.amount());
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the angle theta between the specified Position vectors either in degrees 
	 * or radians.
	 * 
	 * @param vector1 - the first Position vector
	 * @param vector2 - the second Position vector
	 * @param degrees - true to return the angle in degrees, false to return the angle in radians
	 * @return the angle between the specified Position vectors as double
	 */
	public static double angle(Position vector1, Position vector2, boolean degrees) {
		if (vector1 != null && vector2 != null) {
			double angle = Math.acos(vector1.scalarProduct(vector2)/(vector1.amount()*vector2.amount())); // radians
			if (degrees) { // return the angle in degrees
				return Rotation1D.normalise(Math.toDegrees(angle)); // normalise the angle to 0 - 360° before returning
			} else { // return the angle in radians
				return angle;
			}
		} else {
			return 0.0d;
		}	
	}
	
	@Override
	public Position clone() {
		Position pos = new Position(this.getX(), this.getY(), this.getZ());
		pos.setMovement(this.getMovementX(), this.getMovementY(), this.getMovementZ());
		return pos;
	}
	
	@Override
	public String toString() {
		return ("Position(X = " + this.getX() + " ; Y = " + this.getY() + " ; Z = " + this.getZ() + " / X-Movement = " + this.getMovementX() + " ; Y-Movement = " + this.getMovementY() + " ; Z-Movement = " + this.getMovementZ() + ")");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Position) {
				Position pos = (Position) obj;
				if (this.getX() == pos.getX() && this.getY() == pos.getY() && this.getZ() == pos.getZ() && this.getMovementX() == pos.getMovementX()  && this.getMovementY() == pos.getMovementY()  && this.getMovementZ() == pos.getMovementZ()) {
					return true;
				}
			} else if (obj instanceof double[]) {
				return Arrays.equals((double[]) obj, this.toArray());
			}
		}
		return false;
	}

}
