package asgardengine.game.entities.actors;

import java.awt.image.BufferedImage;

import asgardengine.game.classes.characters.Actor;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.world.Placeable;
import asgardengine.game.entities.EntityID;
import asgardengine.game.entities.GameEntity;
import asgardengine.game.entities.graphics.AnimationEntity;

public class ActorEntity extends GameEntity implements Drawable, Placeable {
	
	private Actor actor = null;
	private AnimationEntity currentAnim = null; // the animation currently played

	public ActorEntity(EntityID entityID, Actor actor) {
		super(entityID);
		this.actor = actor;
	}

	public ActorEntity(byte[] bytes) {
		super(bytes);
		// TODO Auto-generated constructor stub
	}
	
	//TODO: Handler!!!
	public boolean playAnimation(int index) {
		this.currentAnim = new AnimationEntity(new EntityID(new byte[]{1}), this.getSource().getAnimation(index));
		return this.currentAnim.play();
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
	public Actor getSource() {
		return this.actor;
	}

	@Override
	public BufferedImage toBufferedImage() {
		if (this.currentAnim != null) {
			return this.currentAnim.toBufferedImage();
		} else {
			return this.actor.toBufferedImage();
		}
	}

}
