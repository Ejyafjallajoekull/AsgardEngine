package asgardengine.game.entities.actors;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import asgardengine.game.classes.characters.Actor;
import asgardengine.game.classes.graphics.Animation;
import asgardengine.game.classes.graphics.DirectionalAnimation;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.world.Placeable;
import asgardengine.game.classes.world.Position;
import asgardengine.game.classes.world.Rotation1D;
import asgardengine.game.entities.EntityID;
import asgardengine.game.entities.GameEntity;
import asgardengine.game.entities.graphics.AnimationEntity;
import asgardengine.game.handler.EntityHandler;

public class ActorEntity extends GameEntity implements Drawable, Placeable {
	
	private Actor actor = null;
	private AnimationEntity currentAnim = null; // the animation currently played
	
	//states
	private boolean isJumping = false;
	private Timer jumpTimer = new Timer(0, a -> this.isJumping = false); // timer to reset jumping
	private boolean isWalking = false;
	private boolean isRunning = false;
	
	//attributes
	private double baseSpeed = 40.0d;
	
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
		this.currentAnim = new AnimationEntity(EntityHandler.nextID(), this.getSource().getAnimation(index));
		return this.currentAnim.play();
	}

	public void walk(boolean walking) {
		if (!isJumping) {
			double speed = this.baseSpeed;
			if (isRunning) {
				speed = speed * 2;
			}
			
			if (walking) {
				this.isWalking = true;
				this.getPosition().setMovementX(Math.sin(this.rotation.asRadians())*speed);
				this.getPosition().setMovementY((-1.0d)*Math.cos(this.rotation.asRadians())*speed);
			} else {
				this.isWalking = false;
				this.getPosition().setMovementX(0.0d);
				this.getPosition().setMovementY(0.0d);

			}	
		}
	}
	
	public void move(Position position, double speed) {
		//TODO: everything
	}
	
	public boolean jump() {
		if (this.getSource() != null && !isJumping) {
			DirectionalAnimation jump = this.getSource().getJumpAnimation();
			if (jump != null ) {
				Animation jumpAnim = jump.getAnimation(this.getRotation());
				if (jumpAnim != null) {
					this.currentAnim = new AnimationEntity(EntityHandler.nextID(), jumpAnim);
					int lengthMillis = (int) (jumpAnim.getPlaybackLength() / 1000000);
					this.jumpTimer.setInitialDelay(lengthMillis);
					if (this.jumpTimer.isRepeats()) {
						this.jumpTimer.setRepeats(false); // just do it once
					}
					this.isJumping = true;
					this.jumpTimer.start();
					return this.currentAnim.play();					
				}
			}
		}
		return false;
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
		if (this.currentAnim != null && this.currentAnim.isPlayed()) {
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

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int) this.getPosition().getX(), (int) this.getPosition().getY(), (int) this.getWidth(), (int) this.getHeight());
	}

	@Override
	public double getWidth() {
		if (this.getSource() != null && this.getSource().getActorSprite() != null) {
			return this.actor.getActorSprite().getWidth();	
		} else {
			return 0.0d;
		}
	}

	@Override
	public double getHeight() {
		if (this.getSource() != null && this.getSource().getActorSprite() != null) {
			return this.actor.getActorSprite().getHeight();	
		} else {
			return 0.0d;
		}
	}

}
