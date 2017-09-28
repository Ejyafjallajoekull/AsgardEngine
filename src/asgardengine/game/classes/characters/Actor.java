package asgardengine.game.classes.characters;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.graphics.Animation;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.graphics.Sprite;

public class Actor extends GameClass implements Drawable {

	public static final byte[] TYPE = {0, 0};
	
	private Sprite actorSprite = null; // the basic sprite of this character
	private ArrayList<Animation> animations = new ArrayList<Animation>(); // all animations this actor can perform
	
	
	public Actor(ClassID classID) {
		super(classID);
	}

	public Actor(byte[] bytes) {
		super(bytes);
		this.createFromBytes(bytes);
	}
	
	/**
	 * Add an animation to the actor.
	 * 
	 * @param animation - the animation to add
	 * @return true if the animation has successfully been added
	 */
	public boolean addAnimation(Animation animation) {
		return this.animations.add(animation);
	}
	
	public Animation getAnimation(int index) {
		return this.animations.get(index);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage toBufferedImage() {
		if (this.actorSprite != null) {
			return this.actorSprite.toBufferedImage();
		} else {
			return null;
		}
	}

	public Sprite getActorSprite() {
		return this.actorSprite;
	}

	public void setActorSprite(Sprite actorSprite) {
		this.actorSprite = actorSprite;
	}

}
