package asgardengine.game.entities.actors;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Timer;

import asgardengine.game.classes.actors.Actor;
import asgardengine.game.classes.graphics.Animation;
import asgardengine.game.classes.graphics.DirectionalAnimation;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.world.Place;
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
	private Timer movingTimer = new Timer(100/6, a -> this.move());
	private double speed = 0.0d;
	private double cachedRotation = 0.0d;
		
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
	
	private void move() {
		if (!this.collides()) {
			this.cachedRotation = this.rotation.asRadians();
			this.getPosition().setMovementX(Math.sin(this.cachedRotation)*speed);
			this.getPosition().setMovementY((-1.0d)*Math.cos(this.cachedRotation)*speed);
		} else {
			this.getPosition().setMovementX((-1.0d)*Math.sin(this.cachedRotation)*speed);
			this.getPosition().setMovementY(Math.cos(this.cachedRotation)*speed);
		}
	}

	public void walk(boolean walking) {
		if (!isJumping) {
			double speed = this.baseSpeed;
			if (isRunning) {
				speed = speed * 2;
			}
			if (walking) {
				this.speed = speed;
				this.movingTimer.setRepeats(true);
				this.movingTimer.restart();
			} else {
				this.speed = 0;
				this.movingTimer.setRepeats(false);
			}
//			if (walking) {
//				this.isWalking = true;
//				if (!this.collides()) { // if there is no collision, move normally
//					this.getPosition().setMovementX(Math.sin(this.rotation.asRadians())*speed);
//					this.getPosition().setMovementY((-1.0d)*Math.cos(this.rotation.asRadians())*speed);
//				} else { // if there is collision, move back in reverse to the forward movement
//					this.getPosition().setMovementX((-1.0d)*Math.sin(this.rotation.asRadians())*speed);
//					this.getPosition().setMovementY(Math.cos(this.rotation.asRadians())*speed);
//				}
//			} else { // stop movement
//				this.isWalking = false;
//				this.getPosition().setMovementX(0.0d);
//				this.getPosition().setMovementY(0.0d);
//
//			}	
		}
	}
	
	/**
	 * Check if this ActorEntity collides with any other object.
	 * 
	 * @return true if there is collision
	 */
	public boolean collides() {
		ArrayList<Placeable> potentialColliders = Place.tree.get(this.getBounds());
//		System.out.println("Checking collision for " + potentialColliders.size() + " objects.");
		if (potentialColliders != null) {
			for (Placeable collider : potentialColliders) {
				if ((this.getBounds().intersects(collider.getBounds()) ||collider.getBounds().intersects(this.getBounds()) ) && (collider.getPosition().getZ() + collider.getZHeight()) > (this.getPosition().getZ() + this.getSteppingHeight()) && (collider.getPosition().getZ() <= (this.getPosition().getZ() + this.getZHeight()))) {
//				Rectangle intersection = this.getBounds().intersection(collider.getBounds());
//				if (intersection != null && intersection.getWidth() > 0 && intersection.getHeight() > 0 && (collider.getPosition().getZ() + collider.getZHeight()) > (this.getPosition().getZ() + this.getSteppingHeight()) && (collider.getPosition().getZ() <= (this.getPosition().getZ() + this.getZHeight()))) {

					System.out.println("Collision between " + this.getBounds() + " and " + collider.getBounds() + " in area " + this.getBounds().intersection(collider.getBounds()));
					return true;
				}
			}
		}
		// TODO: create PlaceLoader, fetch objects from Quadtree/Octree, compare intersection and Z-values
		return false;
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
	
	public double getSteppingHeight() {
		if (this.getSource() != null) {
			return this.getSource().getSteppingHeight();
		} else {
			return 0.0d;
		}
	}

	@Override
	public Rectangle getBounds() {
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

	@Override
	public double getZHeight() {
		if (this.getSource() != null) {
			return this.getSource().getZHeight();
		} else {
			return 0.0d;
		}
	}

}
