package asgardengine.game.classes.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;
import asgardengine.utility.logging.LoggingHandler;

/**
 * A Sprite class represents one distinct image file and provides graphical access to it by
 * implementing the Drawable interface.
 * 
 * @author Planters
 *
 */
public class Sprite extends GameClass implements Drawable {
	
	public static final byte[] TYPE = {0, 1};
	
	private File sprite = null; // store the sprite as path to save memory
	private BufferedImage preload = null; // a preloaded version to enhance performance
	
	public Sprite(ClassID classID, File image) {
		super(classID);
		this.sprite = image;
	}
	
	public Sprite(ClassID classID, Path imagePath) {
		super(classID);
		this.sprite = imagePath.toFile();
	}
	
	public Sprite(byte[] bytes) {
		super(bytes);
	}
	
//	public Sprite clone() {
//		Sprite clone = new Sprite(new File(this.sprite.getPath())); // create an independent file object
//		if (preload != null) {
//			clone.preload();
//		}
//		return clone;
//	}

	public boolean preload() {
		if (this.sprite != null && this.sprite.isFile()) {
			try {
				preload = ImageIO.read(this.sprite);
				return true;
			} catch (Exception e) {
				LoggingHandler.getLog().log(Level.SEVERE, "Sprite " + this.sprite + " could not be preloaded.", e);
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public byte[] toBytes() {
		byte[] bytes = null;
		return bytes;
	}

	@Override
	public void createFromBytes(byte[] bytes) {
		if () {
			
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Sprite && obj != null) {
			return this.sprite.equals(((Sprite) obj).getSpriteFile());
			// preload does not matter for equality
		}
		return false;
	}
	
	@ Override
	public BufferedImage toBufferedImage() {
		if (preload != null) {
			return this.preload;
		} else {
			if (this.sprite != null && this.sprite.isFile()) {
				try {
					return ImageIO.read(this.sprite);
				} catch (Exception e) {
					LoggingHandler.getLog().log(Level.SEVERE, "Sprite " + this.sprite + " could not be read.", e);
					e.printStackTrace();
				}
			}
			return null;
		}
	}
	
	// getters
	
	public File getSpriteFile() {
		return this.sprite;
	}

	
	// setters
	
	public void setSprite(Path sprite) {
		this.sprite = sprite.toFile();
		if (preload != null) {
			this.preload();
		}
	}
	
	public void setSprite(File sprite) {
		this.sprite = sprite;
		if (preload != null) {
			this.preload();
		}
	}
	
	public void setSprite(String sprite) {
		this.sprite = new File(sprite);
		if (preload != null) {
			this.preload();
		}
	}

	@Override
	public byte[] getType() {
		return TYPE;
	}

}
