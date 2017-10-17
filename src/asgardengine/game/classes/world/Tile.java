package asgardengine.game.classes.world;

import java.awt.image.BufferedImage;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.graphics.Animation;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.graphics.Sprite;

/**
 * The Tile class represents basic objects, which form a world map.
 * 
 * @author Planters
 *
 */
public class Tile extends GameClass implements Drawable {
	
	private static final byte[] TYPE = {0, 3};
	private Animation animation = null;
	private Sprite basicSprite = null;
	
	private boolean isStatic = false; // will the appearance of this tile change at runtime
	

	public Tile(ClassID classID, Sprite sprite) {
		super(classID);
		this.basicSprite = sprite;
	}

	public Tile(byte[] bytes) {
		super(bytes);
		this.createFromBytes(bytes);
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
		return this.basicSprite.toBufferedImage();
	}

	/**
	 * Get if the position of this Tile will change at runtime.
	 * 
	 * @return true if this Tile is static
	 */
	public boolean isStatic() {
		return this.isStatic;
	}

	/**
	 * Set if the position of this Tile will change at runtime.
	 * 
	 * @param isStatic - true if this Tile is static
	 */
	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	/**
	 * Get if this Tile supports supertiling, which means it does not change at runtime.
	 * 
	 * @return true if this Tile supports supertiling
	 */
	public boolean supportsSupertiling() {
		if (this.isStatic && this.animation == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if this Tile is animated.
	 * 
	 * @return true if this Tile has an Animation assigned
	 */
	public boolean isAnimated() {
		if (this.animation != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Get the Animation assigned to this Tile.
	 * 
	 * @return the Animation assigned to this Tile
	 */
	public Animation getAnimation() {
		return this.animation;
	}
	
	/**
	 * Get the basic defined Sprite for this Tile. This will not consider animations.
	 * 
	 * @return the basic Sprite attached to this Tile
	 */
	public Sprite getSprite() {
		return this.basicSprite;
	}

}
