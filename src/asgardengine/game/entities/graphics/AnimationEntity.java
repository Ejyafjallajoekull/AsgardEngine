package asgardengine.game.entities.graphics;

import java.awt.image.BufferedImage;

import asgardengine.game.classes.graphics.Animation;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.graphics.Sprite;
import asgardengine.game.entities.EntityID;
import asgardengine.game.entities.GameEntity;

public class AnimationEntity extends GameEntity implements Drawable{
	
	private Animation animation = null; // the animation this instance plays
	private long start = 0; // the starting time of playing the animation
	private boolean isPlayed = false; // is the animation currently playing?

	public AnimationEntity(EntityID entityID, Animation animation) {
		super(entityID, null);
		if (animation != null) { // workaround to set the ClassID as the constructor call has to come first, so no check for null possible
			this.setClassID(animation.getClassID());
		}
		this.setAnimation(animation);
	}

	public AnimationEntity(byte[] bytes) {
		super(bytes);
		this.createFromBytes(bytes);
	}
	
	public boolean play() {
		if (!this.isPlayed) {
			this.isPlayed = true;
			this.start = System.nanoTime();
			return true;
		}
		return false;
	}
	
	public boolean stop() {
		if (this.isPlayed) {
			this.isPlayed = false;
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
		return false;
	}

	public Animation getAnimation() {
		return this.animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
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

}
