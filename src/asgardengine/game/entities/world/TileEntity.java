package asgardengine.game.entities.world;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.graphics.Sprite;
import asgardengine.game.classes.world.Place;
import asgardengine.game.classes.world.Placeable;
import asgardengine.game.classes.world.Position;
import asgardengine.game.classes.world.Rotation1D;
import asgardengine.game.classes.world.Tile;
import asgardengine.game.classes.world.placetree.PlaceTreeCell;
import asgardengine.game.entities.EntityID;
import asgardengine.game.entities.GameEntity;
import asgardengine.game.entities.graphics.AnimationEntity;
import asgardengine.game.handler.EntityHandler;
import asgardengine.utility.logging.LoggingHandler;
import asgardengine.utility.quadtree.RectangularBound;

public class TileEntity extends GameEntity implements Drawable, Placeable {

	private Tile tile = null; // the base tile
	private Position position = new Position();
	private Rotation1D rotation = new Rotation1D();
	public static final int cacheTime = 120000; // the time after which the preload image is discarded
	private Timer cacheTimer = new Timer(cacheTime, a -> this.unload()); // discard the preloaded image if not currently in use
	private BufferedImage cache = null; // a preloaded version to enhance performance
	private AnimationEntity currentAnimation = null;
	private Place currentPlace = null;
	
	public TileEntity(EntityID entityID, Tile tile) {
		super(entityID);
		this.tile = tile;
		this.initAnimation();
	}

	public TileEntity(byte[] bytes) {
		super(bytes);
		// TODO Auto-generated constructor stub
	}
	
	// create an Entity for the animation and play it
	private void initAnimation() {
		if (this.tile != null && this.tile.getAnimation() != null) {
			this.currentAnimation = new AnimationEntity(EntityHandler.nextID(), this.tile.getAnimation());
			this.currentAnimation.play();
		}
	}
	
	public boolean cache() {
		if (this.tile != null) {
			Sprite s = this.getSprite();
			if (s != null) {
				if (this.getRotation().asDegrees() != 0.0d) {
					this.cache = Drawable.rotate(s.toBufferedImage(), this.getRotation().asRadians());
				} else {
					this.cache = s.toBufferedImage();
				}
				this.cacheTimer.restart();
				return true;	
			}
		}
		return false;
	}
	
	public boolean unload() {
		if (isCached()) {
			this.cache = null;
			LoggingHandler.getLog().fine("Unloaded " + this);
			return true;
		}
		return false;
	}
	
	public boolean isCached() {
		if (this.cache != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Get the current state of this Tile as a Sprite.
	 * 
	 * @return the current state of this Tile as a Sprite
	 */
	public Sprite getSprite() {
		if (this.currentAnimation != null && (this.currentAnimation.isPlayed() || this.getSprite() == null)) {
			return this.currentAnimation.getSprite();
		} else {
			return this.tile.getSprite();
		}
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
	public Tile getSource() {
		return this.tile;
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
		if (this.isCached()) { // cache the altered image
			this.cache();
		}
	}

	@Override
	public BufferedImage toBufferedImage() {
		if (this.tile != null) {
			if (!this.isCached()) {
				this.cache();
			}
			return this.cache;
		} else {
			return null;
		}
	}

	@Override
	public RectangularBound getBounds() {
		return new RectangularBound(this.getPosition(), this.getWidth(), this.getHeight());
	}

	@Override
	public double getWidth() {
		Sprite s = this.getSprite();
		if (s != null) {
			return this.getSprite().getWidth();
		} else {
			return 0.0d;
		}
	}

	@Override
	public double getHeight() {
		Sprite s = this.getSprite();
		if (s != null) {
			return this.getSprite().getHeight();
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

	@Override
	public PlaceTreeCell getCell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCell(PlaceTreeCell cell) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlace(Place place) {
		this.currentPlace = place;
	}

	@Override
	public Place getPlace() {
		return this.currentPlace;
	}

	@Override
	public boolean removeFromPlace() {
		// TODO Auto-generated method stub
		return false;
	}

}
