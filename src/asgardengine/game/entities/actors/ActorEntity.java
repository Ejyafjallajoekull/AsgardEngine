package asgardengine.game.entities.actors;

import java.awt.image.BufferedImage;

import asgardengine.game.classes.characters.Actor;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.world.Placeable;
import asgardengine.game.classes.world.Position;
import asgardengine.game.classes.world.Rotation1D;
import asgardengine.game.entities.EntityID;
import asgardengine.game.entities.GameEntity;
import asgardengine.game.entities.graphics.AnimationEntity;

public class ActorEntity extends GameEntity implements Drawable, Placeable {
	
	private Actor actor = null;
	private AnimationEntity currentAnim = null; // the animation currently played
	
	private Position position = new Position();
	private Rotation1D rotation = new Rotation1D();

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
			this.actor.getIdle().setRotation(this.rotation);
			return this.actor.getIdle().toBufferedImage();
		}
	}

	@Override
	public Position getPosition() {
		return this.position;
	}

	@Override
	public Rotation1D getRotation() {
		return this.rotation;
	}

	@Override
	public void setPosition(Position position) {
		this.position = position;	
	}

	@Override
	public void setRotation(Rotation1D rotation) {
		this.rotation = rotation;	
	}

//	@Override
//	public boolean didPlaceChange() {
//		if () {
//			
//		}
//		return false;
//	}

//	@Override
//	public boolean didDrawingChange() {
//		// TODO Auto-generated method stub
//		return true;
//	}

}
