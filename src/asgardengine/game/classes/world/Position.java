package asgardengine.game.classes.world;

import java.util.Arrays;

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
	
	public Position add(Position addend) {
		return add(this, addend);
	}
	
	public Position subtract(Position subtrahend) {
		return subtract(this, subtrahend);
	}
	
	public Position multiply(double multiplier) {
		return multiply(this, multiplier);
	}
	
	public Position divide(double divisor) {
		return divide(this, divisor);
	}
	
	public double distance(Position vector) {
		return distance(this, vector);
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
	 * This is equal to: amount(subtract(vector1, vector2))
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
	
	@Override
	public Position clone() {
		return new Position(this.getX(), this.getY(), this.getZ());
	}
	
	@Override
	public String toString() {
		return ("Position(X = " + this.getX() + " ; Y = " + this.getY() + " ; Z = " + this.getZ() + ")");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Position) {
				Position pos = (Position) obj;
				if (this.getX() == pos.getX() && this.getY() == pos.getY() && this.getZ() == pos.getZ()) {
					return true;
				}
			} else if (obj instanceof double[]) {
				return Arrays.equals((double[]) obj, this.toArray());
			}
		}
		return false;
	}

}
