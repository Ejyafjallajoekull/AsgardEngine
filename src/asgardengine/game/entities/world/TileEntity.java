package asgardengine.game.entities.world;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.world.Placeable;
import asgardengine.game.classes.world.Position;
import asgardengine.game.classes.world.Rotation1D;
import asgardengine.game.classes.world.Tile;
import asgardengine.game.entities.EntityID;
import asgardengine.game.entities.GameEntity;
import asgardengine.utility.logging.LoggingHandler;

public class TileEntity extends GameEntity implements Drawable, Placeable {

	private Tile tile = null; // the base tile
	private Position position = new Position();
	private Rotation1D rotation = new Rotation1D();
	public static final int cacheTime = 120000; // the time after which the preload image is discarded
	private Timer cacheTimer = new Timer(cacheTime, a -> this.unload()); // discard the preloaded image if not currently in use
	private BufferedImage cache = null; // a preloaded version to enhance performance

	
	public TileEntity(EntityID entityID, Tile tile) {
		super(entityID);
		this.tile = tile;
	}

	public TileEntity(byte[] bytes) {
		super(bytes);
		// TODO Auto-generated constructor stub
	}
	
	public boolean cache() {
		if (this.tile != null) {
			if (this.getRotation().asDegrees() != 0.0d) {
				this.cache = Drawable.rotate(this.tile.toBufferedImage(), this.getRotation().asRadians());
			} else {
				this.cache = this.tile.toBufferedImage();
			}
			this.cacheTimer.restart();
			return true;
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

}
