package asgard.engine.asgardengine.game.classes.actors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.graphics.Animation;
import asgardengine.game.classes.graphics.DirectionalAnimation;
import asgardengine.game.classes.graphics.DirectionalSprite;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.graphics.Sprite;

public class Actor extends GameClass implements Drawable {

	private static final byte[] TYPE = {0, 0};
	
	private Sprite actorSprite = null; // the current sprite of this character // must be set for correct higth determination
	//TODO: implement an AnimationList class which is a tree containing some default values like walking and stuff
	private ArrayList<Animation> animations = new ArrayList<Animation>(); // all animations this actor can perform
	private DirectionalSprite idleSprite = null;
	private DirectionalAnimation idleAnimation = null; // the standard idle animation // preferred over the idle sprite
	private DirectionalAnimation jumpAnimation = null; // the standard jump animation
	private DirectionalAnimation fallingAnimation = null; // the standard falling animation
	private DirectionalAnimation landingAnimation = null; // the animation to follow the falling one
	private DirectionalAnimation walkingAnimation = null; // the standard walk animation
	private DirectionalAnimation sprintingAnimation = null; // the fast movement animation
	private DirectionalAnimation swimmingAnimation = null; // the standard swimming animation
	
	// properties
	private double zHeight = 0.0d; // the Z-scale of this object in a 3D world
	private double steppingHeight = 0.0d; // the maximum height of a step // only objects higher than this can be stepped over
	
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
		return TYPE;
	}

	@Override
	public BufferedImage toBufferedImage() {
		if (this.actorSprite != null) {
			return this.actorSprite.toBufferedImage();
		} else {
			return null;
		}
	}

	/**
	 * Get the basic Sprite representing this Actor.
	 * 
	 * @return - the basic Sprite representing this Actor
	 */
	public Sprite getActorSprite() {
		return this.actorSprite;
	}

	/**
	 * Set the basic Sprite representing this Actor.
	 * This Sprite is used for size determination of an Actor, so it should be set
	 * even if it is not visible.
	 * 
	 * @param actorSprite - the Sprite to represent the Actor
	 */
	public void setActorSprite(Sprite actorSprite) {
		this.actorSprite = actorSprite;
	}

	/**
	 * Get the DirectionalSprite used for display of idle behaviour.
	 * 
	 * @return the directional idle sprite
	 */
	public DirectionalSprite getIdle() {
		return idleSprite;
	}

	/**
	 * Get the DirectionalSprite used for display of idle behaviour.
	 * 
	 * @param idle - the directional idle sprite to set
	 */
	public void setIdle(DirectionalSprite idle) {
		this.idleSprite = idle;
	}

	public DirectionalAnimation getJumpAnimation() {
		return jumpAnimation;
	}

	public void setJumpAnimation(DirectionalAnimation jumpAnimation) {
		this.jumpAnimation = jumpAnimation;
	}

	/**
	 * Get the Z-Height of this actor. This value represents its' scale
	 * in Z-dimension in a three dimensional environment.
	 * 
	 * @return the Z-height of this actor as double
	 */
	public double getZHeight() {
		return this.zHeight;
	}

	/**
	 * Set the Z-Height of this Actor. This value represents its' scale
	 * in Z-dimension in a three dimensional environment.
	 * 
	 * @param zHeight - the scale of this Actor in Z-dimension
	 */
	public void setZHeight(double zHeight) {
		this.zHeight = zHeight;
	}

	public double getSteppingHeight() {
		return this.steppingHeight;
	}

	public void setSteppingHeight(double steppingHeight) {
		this.steppingHeight = steppingHeight;
	}

}
