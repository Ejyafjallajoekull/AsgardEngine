package asgardengine.game.entities.graphics;

import java.awt.image.BufferedImage;

import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.graphics.Animation;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.graphics.Sprite;
import asgardengine.game.classes.scripts.AnimationScript;
import asgardengine.game.entities.EntityID;
import asgardengine.game.entities.GameEntity;

public class AnimationEntity extends GameEntity implements Drawable {
	
	private Animation animation = null; // the animation this instance plays
	private long start = 0; // the starting time of playing the animation
	private boolean isPlayed = false; // is the animation currently playing?

	public AnimationEntity(EntityID entityID, Animation animation) {
		super(entityID);
		this.setAnimation(animation);
		if (this.getScript() != null) {
			this.getScript().onInit(); // send init event
		}
	}

	public AnimationEntity(byte[] bytes) {
		super(bytes);
		this.createFromBytes(bytes);
		if (this.getScript() != null) {
			this.getScript().onInit(); // send init event
		}
	}
	
	public boolean play() {
		if (!this.isPlayed) {
			this.isPlayed = true;
			this.start = System.nanoTime();
			if (this.getScript() != null) {
				this.getScript().onPlay(); // send play event
			}
			return true;
		}
		return false;
	}
	
	public boolean stop() {
		if (this.isPlayed) {
			this.isPlayed = false;
			if (this.getScript() != null) {
				this.getScript().onStop(); // send stop event
			}
			return true;
		}
		return false;
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

	public Animation getAnimation() {
		return this.animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	
	/**
	 * Get the script attached to the corresponding animation. <br>
	 * This is just a convenience method and basically: <br>
	 * <br>
	 * this.getAnimation().getScript();
	 * 
	 * @return the AnimationScript attached to the corresponding animation
	 */
	public AnimationScript getScript() {
		if (this.getAnimation() != null) {
			return this.getAnimation().getScript();
		} else {
			return null;
		}
	}

	@Override
	public BufferedImage toBufferedImage() {
		if (this.animation != null) {
			Sprite s = null;
			if (!this.animation.isLoop() && (System.nanoTime() - this.start) > this.animation.getPlaybackLength()) { // if the animation is not supposed to loop stop it at the appropriate time
				this.stop();
			}
			if (this.isPlayed) { // only do this if the animation gets currently played
				s = this.animation.get(System.nanoTime() - this.start); // get the currently played sprite
			} else if (this.animation.hasSprites()) { // only take the first if it really exists
				s = this.animation.getAt(0);
			}
			if (s != null) {
				return s.toBufferedImage();
			}
		}
		return null;
	}

//	@Override
//	public byte[] getType() {
//		return Animation.TYPE; // same as the corresponding class
//	}

	@Override
	public GameClass getSource() {
		return animation;
	}

//	@Override
//	public boolean didDrawingChange() {
//		return true;
//	}

}
