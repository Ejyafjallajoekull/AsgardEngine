package asgardengine.game.classes.graphics;

import java.util.ArrayList;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;
import asgardengine.utility.binary.ByteUtilities;
import asgardengine.utility.logging.LoggingHandler;

public class Animation extends GameClass {
	// just a blueprint, the actual playing methods are implemented via an instance class

	public static final byte[] TYPE = {0, 2};
	
	private ArrayList<AnimSprite> sprites = new ArrayList<AnimSprite>(); // all sprites making the animation in the correct order
	private boolean isLoop = false; // does the animation loop indefinitely or does it play just once
	private long playbackLength = 0; // the length of this animation
	private boolean isUniform = false; // does every animation sprite have the same playing length?
	private boolean isRandom = false; // are the sprites played randomly?
	
	public Animation(ClassID classID) {
		super(classID);
	}
	
	public Animation(byte[] bytes) {
		super(bytes);
	}
	
	/**
	 * 
	 * Add a sprite with a specific playing length at the given index.
	 * 
	 * @param sprite - the sprite to add to the animation
	 * @param time - the time the sprite should be visible during the animation
	 * @param index - index at which the specified element is to be inserted
	 * @return whether the insertion was successful
	 */
	public boolean addAnimationSpirte(Sprite sprite, long time, int index) {
		if (index >= 0) {
			if (index < this.sprites.size()) {
				this.sprites.add(index, new AnimSprite(sprite, time));
				this.updatePlaybackTime();
				return true;
			} else { // add as last element if index is out of range
				boolean b = this.addAnimationSpirte(sprite, time);
				this.updatePlaybackTime();
				return b;
			}		
		}
		return false;
	}
	
	/**
	 * Add a sprite with a specific playing length to the end of the animation.
	 * 
	 * @param sprite - the sprite to add to the animation
	 * @param time - the time the sprite should be visible during the animation
	 * @return whether the insertion was successful
	 */
	public boolean addAnimationSpirte(Sprite sprite, long time) {
		 boolean b = this.sprites.add(new AnimSprite(sprite, time));
		 this.updatePlaybackTime();
		 return b;
	}
	
	/**
	 * Removes the first occurrence of the given sprite.
	 * 
	 * @param sprite - the sprite to remove from the animation
	 * @return whether the deletion was successful
	 */
	public boolean removeAnimationSprite(Sprite sprite) {
		for (AnimSprite animSprite : this.sprites) {
			if (animSprite.getSprite().equals(sprite)) {
				boolean b = this.sprites.remove(animSprite);
				this.updatePlaybackTime();
				return b;
			}
		}
		return false;
	}
	
	/**
	 * Removes the sprite at the given index from the animation.
	 * 
	 * @param index - the index of the sprite to be removed
	 * @return the sprite that was removed from the animation
	 */
	public Sprite removeAnimationSprite(int index) {
		Sprite s = this.sprites.remove(index).getSprite();
		this.updatePlaybackTime();
		return s;
	}
	
	
	/**
	 * Removes all sprites from the animation.
	 */
	public void clear() {
		this.sprites.clear();
		this.updatePlaybackTime();
	}
	
	/**
	 * Get the number of sprites of this animation.
	 * 
	 * @return the number of animation sprites
	 */
	public int getNumberSprites() {
		return this.sprites.size();
	}
	
	/**
	 * Remove all occurrences of the specified sprite from the animation.
	 * 
	 * @param sprite - the sprite to remove from the animation
	 */
	public void removeAllAnimationSprites(Sprite sprite) {
//		boolean oneRemoved = false;
//		while (this.removeAnimationSprite(sprite)) {
//			if (!oneRemoved) {
//				oneRemoved = true;
//			}
//		}
//		return oneRemoved; // does break if remove function fails
		
//		boolean worked = true; //  also return true if there is nothing to remove
		for (int i = 0; i < this.sprites.size(); i++) {
			if (sprite == null) {
				if (this.sprites.get(i) == null) {
					this.sprites.remove(i);
					i--; // iterate over the same index again so nothing gets skipped
				}
			} else {
				if (sprite.equals(this.getAt(i))) {
					this.sprites.remove(i);
				}
			}
		}
	}
	
	// must be called after each alteration of the animation // to enhance performance not part of toBufferedImage
	/**
	 * Updates the playback time of the animation.
	 */
	public void updatePlaybackTime() {
		long time = 0;
		long track = 0; // tracks the length of the first
		boolean doOnce = false;
		boolean uniform = true;
		for (AnimSprite as : this.sprites) {
			if (as != null) {
				if (!doOnce) { // only do this the first time
					track = as.getLength();
					doOnce = true;
				} else if (track != as.getLength()) {
					uniform = false;
				}
				time += as.getLength();
			} else { // if null is present, a animation can never be uniform
				uniform = false;
			}
		}
		this.playbackLength = time;
		this.isUniform = uniform;
	}
	
	/**
	 * Get the playback length of the animation.
	 * 
	 * @return the playback length of the animation
	 */
	public long getPlaybackLength() {
		return this.playbackLength;
	}
	
	/**
	 * Whether the animation contains animation sprites or not.
	 * 
	 * @return true if this animation contains animation sprites
	 */
	public boolean hasSprites() {
		return !this.sprites.isEmpty();
	}
	
	/**
	 * Get the sprite at the specified index.
	 * 
	 * @param index - index of the sprite to return
	 * @return the sprite at the specified index
	 */
	public Sprite getAt(int index) {
		return this.sprites.get(index).getSprite();
	}
	
	//TODO: optimise
	//get the sprite at the given time point of the animation
	public Sprite get(long time) {
		if (!this.sprites.isEmpty()) {
			if (time > 0 && this.getPlaybackLength() > 0) {
				long diff = time%this.getPlaybackLength();
				int index = 0;
				if (this.isUniform()) {
					index = (int) Math.floorDiv(diff*this.getNumberSprites(), this.getPlaybackLength());
				} else {
					long length = 0;
					AnimSprite as = null;
					for (index = 0; index < this.sprites.size(); index++) {
						as = this.sprites.get(index);
						if (as != null && as.getLength() > 0) {
							length += as.getLength();
							if (diff <= length) {
								break;
							}
						}
					}	
				}
				return this.getAt(index);
			} else {
				return this.getAt(0);
			}
		} else {
			return null;
		}
	}
	
	@Override
	public byte[] toBytes() {
		byte[][] bytes = new byte[9+this.sprites.size()][];
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
		byte[] bool = new byte[1];
		if (this.isLoop) {
			bool[0] = (byte) 1;
		} else {
			bool[0] = (byte) 0;
		}
		bytes[6] = bool;
		if (this.isRandom) {
			bool[0] = (byte) 1;
		} else {
			bool[0] = (byte) 0;
		}
		bytes[7] = bool;
		bytes[8] = ByteUtilities.integerToByte(this.sprites.size()); // number of animation sprites to come
		if (this.sprite != null) {
			bytes[7] = this.sprite.getPath().getBytes(); // eighth: file path as string
			bytes[6] = ByteUtilities.integerToByte(bytes[7].length); // seventh: length of the file path
		} else { // set the length to a negative value if the file is null
			bytes[6] = ByteUtilities.integerToByte(-1);
		}
		return ByteUtilities.join(bytes);	
	}

	@Override
	public void createFromBytes(byte[] bytes) {
		
	}
	
	
	/**
	 * Whether this animation is played as a loop.
	 * 
	 * @return true if this animation is looping
	 */
	public boolean isLoop() {
		return this.isLoop;
	}
	
	/**
	 * Set the looping behaviour of this animation.
	 * 
	 * @param loop - true for a looping animation
	 */
	public void loop(boolean loop) {
		this.isLoop = loop;
	}
	
	
	// TODO: implement random behaviour
	/**
	 * Whether this animation is uniformly played.
	 * 
	 * @return true if this animation only consists of animation sprites of the same playback length
	 */
	public boolean isUniform() {
		return this.isUniform;
	}
	
	/**
	 * Whether the sprites of this animation are played randomly.
	 * 
	 * @return true if the sprites of this animation are played randomly
	 */
	public boolean isRandom() {
		return this.isRandom;
	}
	
	/**
	 * Set the random behaviour of this animation.
	 * 
	 * @param loop - true for a randomly played sprites
	 */
	public void random(boolean random) {
		this.isRandom = random;
	}
	
	private class AnimSprite {
		
		private Sprite sprite = null;
		private long length = -1;
		
		public AnimSprite(Sprite sprite, long animationLength) {
			this.sprite = sprite;
			this.length = animationLength;
		}
		
		private Sprite getSprite() {
			return this.sprite;
		}
		
		private long getLength() {
			return this.length;
		}
		
		private byte[] toByte() {
			byte[][] bytes = new byte[3][];
			if (this.sprite.getClassID() != null) { // only ID needed since it's a reference
				bytes[1] = this.sprite.getClassID().toByte(); // second: class ID
				bytes[0] = ByteUtilities.integerToByte(bytes[1].length); // first: length of the class ID
			} else { // set the length to a negative value if the file is null
				bytes[0] = ByteUtilities.integerToByte(-1);
				LoggingHandler.getLog().warning("The ClassID of " + this.sprite + " is invalid.");
			}
			bytes[2] = ByteUtilities.longToByte(this.length);
			return ByteUtilities.join(bytes);
		}
		
	}

	@Override
	public byte[] getType() {
		return TYPE;
	}

}
