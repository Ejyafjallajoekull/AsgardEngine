package asgard.engine.asgardengine.game.classes.graphics;

import java.util.Arrays;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.world.Rotation1D;

/**
 * The DirectionalAnimation class holds four different animation variants of the same entity. <br>
 * A front view, a back view, and two different side views.
 * 
 * @author Planters
 *
 */
public class DirectionalAnimation extends GameClass {

	/**
	 * The unique type identifier of this class.
	 */
	public static final byte[] TYPE = {0, 6};
	
	private static final int INDEX_FRONT = 0; // the animation array index for front animations
	private static final int INDEX_BACK = 1; // the animation array index for back animations
	private static final int INDEX_LEFT = 2; // the animation array index for left animations
	private static final int INDEX_RIGHT = 3; // the animation array index for right animations
	
	private Animation[] animations = new Animation[4];
	
	/**
	 * Create a directional animation from an array of animations.
	 * The input animation array must have the following organisation:
	 * [front, back, left, right]
	 * 
	 * @param classID - the unique identifier of this GameClass
	 * @param animations - the array of animations to 
	 */
	public DirectionalAnimation(ClassID classID, Animation[] animations) {
		super(classID);
		for (int i = 0; i < this.animations.length; i++) {
			if ((animations.length - i) > 0) {
				this.animations[i] = animations[i];
			} else {
				this.animations[i] = null;
			}
		}
	}
	
	/**
	 * Create a directional animation from various animations.
	 * 
	 * @param classID - the unique identifier of this GameClass
	 * @param front - the front animation
	 * @param back - the back animation
	 * @param left - the animation, when turned left
	 * @param right - the animation, when turned right
	 */
	public DirectionalAnimation(ClassID classID, Animation front, Animation back, Animation left, Animation right) {
		super(classID);
		this.animations[0] = front;
		this.animations[1] = back;
		this.animations[2] = left;
		this.animations[3] = right;
	}


	public DirectionalAnimation(byte[] bytes) {
		super(bytes);
		this.createFromBytes(bytes);
	}

	/**
	 * Get the animation corresponding to the input rotation.
	 * 
	 * @param radians - the rotation in radians
	 * @return the animation corresponding to the input rotation as Animation
	 */
	public Animation getAnimation(double radians) {
		if (radians >= Rotation1D.RADIANS_SOUTHWEST && radians < Rotation1D.RADIANS_NORTHWEST) {
			return this.animations[INDEX_LEFT]; // left
		} else if (radians >= Rotation1D.RADIANS_NORTHEAST && radians < Rotation1D.RADIANS_SOUTHEAST) {
			return this.animations[INDEX_RIGHT]; // right
		} else if (radians >= Rotation1D.RADIANS_SOUTHEAST && radians < Rotation1D.RADIANS_SOUTHWEST) {
			return this.animations[INDEX_FRONT]; // front
		} else {
			return this.animations[INDEX_BACK]; // back
		}
	}
	
	/**
	 * Get the animation corresponding to the input rotation.
	 * 
	 * @param rotation - the input rotation
	 * @return the animation corresponding to the input rotation as Animation
	 */
	public Animation getAnimation(Rotation1D rotation) {
		if (rotation.asRadians() >= Rotation1D.RADIANS_SOUTHWEST && rotation.asRadians() < Rotation1D.RADIANS_NORTHWEST) {
			return this.animations[INDEX_LEFT]; // left
		} else if (rotation.asRadians() >= Rotation1D.RADIANS_NORTHEAST && rotation.asRadians() < Rotation1D.RADIANS_SOUTHEAST) {
			return this.animations[INDEX_RIGHT]; // right
		} else if (rotation.asRadians() >= Rotation1D.RADIANS_SOUTHEAST && rotation.asRadians() < Rotation1D.RADIANS_SOUTHWEST) {
			return this.animations[INDEX_FRONT]; // front
		} else {
			return this.animations[INDEX_BACK]; // back
		}
	}
	
	@Override
	public byte[] toBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createFromBytes(byte[] bytes) {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] getType() {
		return TYPE;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof DirectionalAnimation) {
			return Arrays.equals(this.animations, ((DirectionalAnimation) obj).animations);
		}
		return false;
	}

}
