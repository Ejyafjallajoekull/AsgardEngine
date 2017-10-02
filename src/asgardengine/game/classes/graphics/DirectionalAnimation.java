package asgardengine.game.classes.graphics;

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

	public static final byte[] TYPE = {0, 6};
	
	private Animation[] animations = new Animation[4];
	private Rotation1D rotation = new Rotation1D();
	
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
	
	public Animation getAnimation() {
		if (this.rotation.asDegrees() >= 225.0d && this.rotation.asDegrees() < 315.0d) {
			return this.animations[2]; // left
		} else if (this.rotation.asDegrees() >= 45.0d && this.rotation.asDegrees() < 135.0d) {
			return this.animations[3]; // right
		} else if (this.rotation.asDegrees() >= 135.0d && this.rotation.asDegrees() < 225.0d) {
			return this.animations[0]; // front
		} else {
			return this.animations[1]; // back
		}
	}
	
	public Animation getAnimation(double degrees) {
		this.setRotation(degrees);
		return this.getAnimation();
	}
	
	public Animation getAnimation(Rotation1D rotation) {
		this.setRotation(rotation);
		return this.getAnimation();
	}
	
	/**
	 * Set the rotation.
	 * 
	 * @param degrees - the degrees of rotation as double
	 */
	public void setRotation(double degrees) {
		this.rotation.setRotation(degrees);
	}

	/**
	 * Set the rotation.
	 * 
	 * @param rotation - the rotation as Rotation1D
	 */
	public void setRotation(Rotation1D rotation) {
		if (rotation != null) {
			this.rotation.setRotation(rotation.asDegrees());	
		}
	}

	@Override
	public byte[] toBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createFromBytes(byte[] bytes) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] getType() {
		return TYPE;
	}

}
