package asgardengine.game.classes.graphics;

import java.awt.image.BufferedImage;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.world.Rotation1D;

/**
 * The DirectionalSprite class holds four different sprite variants of the same entity. <br>
 * A front view, a back view, and two different side views.
 * 
 * @author Planters
 *
 */
public class DirectionalSprite extends GameClass implements Drawable {

	public static final byte[] TYPE = {0, 5};
	
	private Sprite[] sprites = new Sprite[4];
	private Rotation1D rotation = new Rotation1D();
	
	
	public DirectionalSprite(ClassID classID, Sprite[] sprites) {
		super(classID);
		for (int i = 0; i < this.sprites.length; i++) {
			if ((sprites.length - i) > 0) {
				this.sprites[i] = sprites[i];
			} else {
				this.sprites[i] = null;
			}
		}
	}
	
	public DirectionalSprite(ClassID classID, Sprite front, Sprite back, Sprite left, Sprite right) {
		super(classID);
		this.sprites[0] = front;
		this.sprites[1] = back;
		this.sprites[2] = left;
		this.sprites[3] = right;
	}

	public DirectionalSprite(byte[] bytes) {
		super(bytes);
		this.createFromBytes(bytes);
	}

	public Sprite getSprite() {
		if (this.rotation.asDegrees() >= 225.0d && this.rotation.asDegrees() < 315.0d) {
			return this.sprites[2]; // left
		} else if (this.rotation.asDegrees() >= 45.0d && this.rotation.asDegrees() < 135.0d) {
			return this.sprites[3]; // right
		} else if (this.rotation.asDegrees() >= 135.0d && this.rotation.asDegrees() < 225.0d) {
			return this.sprites[0]; // front
		} else {
			return this.sprites[1]; // back
		}
	}
	
	public Sprite getSprite(double degrees) {
		this.setRotation(degrees);
		if (this.rotation.asDegrees() >= 225.0d && this.rotation.asDegrees() < 315.0d) {
			return this.sprites[2]; // left
		} else if (this.rotation.asDegrees() >= 45.0d && this.rotation.asDegrees() < 135.0d) {
			return this.sprites[3]; // right
		} else if (this.rotation.asDegrees() >= 135.0d && this.rotation.asDegrees() < 225.0d) {
			return this.sprites[0]; // front
			
		} else {
			return this.sprites[1]; // back
		}
	}
	
	public Sprite getSprite(Rotation1D rotation) {
		this.setRotation(rotation);
		if (this.rotation.asDegrees() >= 225.0d && this.rotation.asDegrees() < 315.0d) {
			return this.sprites[0]; // left
		} else if (this.rotation.asDegrees() >= 45.0d && this.rotation.asDegrees() < 135.0d) {
			return this.sprites[3]; // right
		} else if (this.rotation.asDegrees() >= 135.0d && this.rotation.asDegrees() < 225.0d) {
			return this.sprites[1]; // back
		} else {
			return this.sprites[0]; // front
		}
	}
	
	public void setRotation(double degrees) {
		this.rotation.setRotation(degrees);
	}

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

	@Override
	public BufferedImage toBufferedImage() {
		Sprite s = this.getSprite();
		if (s != null) {
			return s.toBufferedImage();
		} else {
			return null;
		}
	}

}
