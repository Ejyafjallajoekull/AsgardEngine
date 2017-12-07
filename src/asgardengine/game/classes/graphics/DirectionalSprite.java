package asgardengine.game.classes.graphics;

import java.awt.image.BufferedImage;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.world.Rotation1D;
import asgardengine.utility.binary.ByteUtilities;
import asgardengine.utility.logging.LoggingHandler;

/**
 * The DirectionalSprite class holds four different sprite variants of the same entity. <br>
 * A front view, a back view, and two different side views.
 * 
 * @author Planters
 *
 */
public class DirectionalSprite extends GameClass implements Drawable {

	private static final byte[] TYPE = {0, 5};
	
	private Sprite[] sprites = new Sprite[4];
	private Rotation1D rotation = new Rotation1D();
	
	
	/**
	 * Create a DirectionalSprite.
	 * 
	 * @param classID - the unique ClassID of this DirectionalSprite
	 * @param sprites - an array of sprites with a length of 4 containing sprites for all four directions
	 */
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
	
	/**
	 * Create a DirectionalSprite.
	 * 
	 * @param classID - the unique ClassID of this DirectionalSprite
	 * @param front - the front sprite
	 * @param back - the back sprite
	 * @param left - the left sprite
	 * @param right - the right sprite
	 */
	public DirectionalSprite(ClassID classID, Sprite front, Sprite back, Sprite left, Sprite right) {
		super(classID);
		this.sprites[0] = front;
		this.sprites[1] = back;
		this.sprites[2] = left;
		this.sprites[3] = right;
	}

	/**
	 * Recreate a DirectionalSprite from a previously saved array of bytes.
	 * 
	 * @param bytes - the byte array to recreate the DirectionalSprite from
	 */
	public DirectionalSprite(byte[] bytes) {
		super(bytes);
		this.createFromBytes(bytes);
	}

	/**
	 * Get the Sprite assigned to the currently set rotation.
	 * 
	 * @return the Sprite assigned to the currently set rotation
	 */
	public Sprite getSprite() {
		if (this.rotation.asRadians() >= Rotation1D.RADIANS_SOUTHWEST && this.rotation.asRadians() < Rotation1D.RADIANS_NORTHWEST) {
			return this.sprites[2]; // left
		} else if (this.rotation.asRadians() >= Rotation1D.RADIANS_NORTHEAST && this.rotation.asRadians() < Rotation1D.RADIANS_SOUTHEAST) {
			return this.sprites[3]; // right
		} else if (this.rotation.asRadians() >= Rotation1D.RADIANS_SOUTHEAST && this.rotation.asRadians() < Rotation1D.RADIANS_SOUTHWEST) {
			return this.sprites[0]; // front
		} else {
			return this.sprites[1]; // back
		}
	}
	
	/**
	 * Get the Sprite assigned to the specified rotation.
	 * 
	 * @param radians - the rotation in radians to get the Sprite for
	 * @return the Sprite assigned to the specified rotation
	 */
	public Sprite getSprite(double radians) {
		this.setRotation(radians);
		return this.getSprite();
	}
	
	/**
	 * Get the Sprite assigned to the specified rotation.
	 * 
	 * @param rotation - the rotation to get the Sprite for
	 * @return the Sprite assigned to the specified rotation
	 */
	public Sprite getSprite(Rotation1D rotation) {
		this.setRotation(rotation);
		return this.getSprite();
	}
	
	/**
	 * Set the rotation.
	 * 
	 * @param radians - the radians of rotation as double
	 */
	public void setRotation(double radians) {
		this.rotation.setRotation(radians);
	}

	/**
	 * Set the rotation.
	 * 
	 * @param rotation - the rotation as Rotation1D
	 */
	public void setRotation(Rotation1D rotation) {
		if (rotation != null) {
			this.rotation.setRotation(rotation.asRadians());	
		}
	}
	
	@Override
	public byte[] toBytes() {
		byte[][] bytes = new byte[14][];
		bytes[0] = ByteUtilities.integerToByte(this.getType().length); // first: length of the type array
		bytes[1] = this.getType(); // second: type array
		if (this.getClassID() != null) {
			bytes[3] = this.getClassID().toByte(); // fourth: class ID
			bytes[2] = ByteUtilities.integerToByte(bytes[3].length); // third: length of the class ID
		} else { // set the length to a negative value if the file is null
			bytes[2] = ByteUtilities.integerToByte(-1);
			LoggingHandler.getLog().warning("The ClassID of " + this + " is invalid.");
		}
		if (this.getDescriptiveName() != null) {
			bytes[5] = this.getDescriptiveName().getBytes(); // sixth: descriptive
			bytes[4] = ByteUtilities.integerToByte(bytes[5].length); // fifth: length of the descriptive
		} else { // set the length to a negative value if the descriptive is null
			bytes[4] = ByteUtilities.integerToByte(-1);
		}
		for (int i = 0; i < this.sprites.length; i++) {
			int addIndex = (i+1)*2; // helper variable
			if (this.sprites[i] != null && this.sprites[i].getClassID() != null) {
				bytes[5+addIndex] = this.sprites[i].getClassID().toByte(); // class ID of the sprite
				bytes[4+addIndex] = ByteUtilities.integerToByte(bytes[5+addIndex].length); // length of the sprite class ID
			} else { // set the length to a negative value if the file is null
				bytes[4+addIndex] = ByteUtilities.integerToByte(-1);
			}
		}
		return ByteUtilities.join(bytes);
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof DirectionalSprite) {
			Sprite s1 = this.getSprite(); // only compare the current state, as a directional sprite also just represents a sprite
			Sprite s2 = ((DirectionalSprite) obj).getSprite();
			if (s1 != null && s2 != null) {
				return s1.equals(s2);
			} else if (s1 == null && s2 == null) {
				return true;
			}
		}
		return false;
	}

}
