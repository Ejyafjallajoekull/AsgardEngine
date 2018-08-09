package asgard.engine.asgardengine.utility.quadtree;

import java.util.ArrayList;
import java.util.Arrays;

import asgard.engine.asgardengine.game.classes.world.Position;
import asgard.engine.asgardengine.game.classes.world.Rotation1D;

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
	private double radius = 0.0d; // convenience variable // radius of the outer circle of this rectangle
	private double offsetAngle = 0.0d; // the offset of the upper right corner
	private Position[] corners = new Position[4]; // the corners
	private Position[] normals = new Position[2]; // the normals
	
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
	 * Check if there is intersection between this RectangularBound and the specified one.
	 * 
	 * @param bound - the RectangularBound to check intersection with
	 * @return true if the two RectangularBounds intersect each other
	 */
	public boolean intersects(RectangularBound bound) {
		boolean intersects = false; // false as standard return
		if (bound != null) {
			intersects = true; // set it to true for following iteration -> check for separation
			Position[] corners1 = this.getCorners();
			Position[] corners2 = bound.getCorners();
			ArrayList<Position> normals = new ArrayList<Position>(Arrays.asList(this.getNormals()));
			normals.addAll(Arrays.asList(bound.getNormals()));
			Intersector intersector = null;
			for (Position normal : normals) {
				intersector = new Intersector(corners1, corners2, normal);
				if (intersector.isSeparated()) {
					intersects = false;
					break;
				}
			}
		}
		return intersects;
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
	 * @param radians - false to process the passed rotation value as degrees, true to process it as radians
	 */
	public void setRotation(double rotation, boolean radians) {
		this.rotation.setRotation(rotation, radians);
		this.calculateRadius();
	}
	
	/**
	 * Set the height of this RectangularBound.
	 * 
	 * @param height - the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
		this.calculateRadius();
	}
	
	/**
	 * Set the width of this RectangularBond.
	 * 
	 * @param width - the width to set
	 */
	public void setWidth(double width) {
		this.width = width;
		this.calculateRadius();
	}
	
	/**
	 * Get the two normals needed for collision detection.
	 * 
	 * @return two normals needed for collision
	 */
	private Position[] getNormals() {
		return this.normals;
	}
	
	public Position getTopLeft() {
		double x = this.center.getX() + this.radius * Math.sin(this.rotation.asRadians() - this.offsetAngle);
		double y = this.center.getY() + this.radius * Math.cos(this.rotation.asRadians() - this.offsetAngle);
		return new Position(x, y, this.center.getZ());
	}
	
	public Position getTopRight() {
		double x = this.center.getX() + this.radius * Math.sin(this.rotation.asRadians() + this.offsetAngle);
		double y = this.center.getY() + this.radius * Math.cos(this.rotation.asRadians() + this.offsetAngle);
		return new Position(x, y, this.center.getZ());
	}
	
	public Position getLesserLeft() {
		double x = this.center.getX() + this.radius * Math.sin(this.rotation.asRadians() + this.offsetAngle + Math.PI);
		double y = this.center.getY() + this.radius * Math.cos(this.rotation.asRadians() + this.offsetAngle + Math.PI);
		return new Position(x, y, this.center.getZ());
	}
	
	public Position getLesserRight() {
		double x = this.center.getX() + this.radius * Math.sin(this.rotation.asRadians() - this.offsetAngle + Math.PI);
		double y = this.center.getY() + this.radius * Math.cos(this.rotation.asRadians() - this.offsetAngle + Math.PI);
		return new Position(x, y, this.center.getZ());
	}
	
	public Position[] getCorners() {
		return this.corners;
	}
	
	// convenience method
	// calculate the radius of the outer circle surrounding this rectangle
	// and calculate the offset angle
	private void calculateRadius() {
		this.radius = (new Position(this.width, this.height, 0.0d)).amount() * 0.5d;
		this.offsetAngle = Math.atan(this.width/this.height);
		
		double x = this.radius * Math.sin(this.rotation.asRadians() + this.offsetAngle);
		double y = this.radius * Math.cos(this.rotation.asRadians() + this.offsetAngle);

//		double x = this.radius * Trigonometry.sinSimpleCalc((this.rotation.asRadians() + this.offsetAngle));
//		double y = this.radius * Trigonometry.cosSimpleCalc((this.rotation.asRadians() + this.offsetAngle));
		
//		double x = 1;
//		double y = 1;
		
		this.corners[0] = new Position(this.getCenter().getX() + x, this.getCenter().getY() + y, this.center.getZ());
		this.corners[2] = new Position(this.getCenter().getX() - x, this.getCenter().getY() - y, this.center.getZ());
		
		x = this.radius * Math.sin(this.rotation.asRadians() - this.offsetAngle + Math.PI);
		y = this.radius * Math.cos(this.rotation.asRadians() - this.offsetAngle + Math.PI);

//		x = this.radius * Trigonometry.sinSimpleCalc((this.rotation.asRadians() - this.offsetAngle + Math.PI));
//		y = this.radius * Trigonometry.cosSimpleCalc((this.rotation.asRadians() - this.offsetAngle + Math.PI));
		
		this.corners[1] = new Position(this.getCenter().getX() + x, this.getCenter().getY() + y, this.center.getZ());
		this.corners[3] = new Position(this.getCenter().getX() - x, this.getCenter().getY() - y, this.center.getZ());
		
		Position q = Position.subtract(this.corners[0], this.corners[3]);
		Position r = Position.subtract(this.corners[1], this.corners[0]);
		this.normals[0] = q.normalVector(true);
		this.normals[1] = r.normalVector(true);
	}
	
	// a pair of min and max scalar products for a rectangle and a normal vector
	private class MinMaxPair {
		public double min = Double.MAX_VALUE; // start at largest possible value
		public double max = -Double.MAX_VALUE; // start at smallest possible value
		
		// throw null pointer exception if something's not right
		public MinMaxPair(Position[] corners, Position normal) {
			double value = 0.0d;
			for (Position corner : corners) {
				value = corner.scalarProduct(normal);
				if (value > max) {
					this.max = value;
				}
				if (value < min) {
					this.min = value;
				}
			}
		}
		
	}
	
	private class Intersector {
		
		private boolean separation = false;
		
		public Intersector(Position[] corners1, Position corners2[], Position normal) {
			if (corners1 != null && corners2 != null && normal != null) {
				MinMaxPair pair1 = new MinMaxPair(corners1, normal);
				MinMaxPair pair2 = new MinMaxPair(corners2, normal);
				if (pair1.max < pair2.min || pair2.max < pair1.min) {
					this.separation = true;
				}
			}
		}
		
		public boolean isSeparated() {
			return this.separation;
		}
	}

	@Override
	public String toString() {
		return "RectangularBound [width=" + width + ", height=" + height + ", rotation=" + rotation + ", center="
				+ center + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof RectangularBound) {
			RectangularBound r = (RectangularBound) obj;
			if (this.getWidth() == r.getWidth() && this.getHeight() == r.getHeight() &&
					this.getCenter().equals(r.getCenter()) && this.getRotation().equals(r.getRotation())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public RectangularBound clone() {
		RectangularBound clone = new RectangularBound(this.getCenter(), this.getWidth(), this.getHeight());
		clone.setRotation(this.getRotation().asRadians(), true);
		return clone;
	}
	
}
