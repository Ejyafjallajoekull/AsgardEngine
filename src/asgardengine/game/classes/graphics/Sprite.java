package asgardengine.game.classes.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;
import asgardengine.utility.binary.ByteUtilities;
import asgardengine.utility.logging.LoggingHandler;

/**
 * A Sprite class represents one distinct image file and provides graphical access to it by
 * implementing the Drawable interface.
 * 
 * @author Planters
 *
 */
public class Sprite extends GameClass implements Drawable {
	
	private static final byte[] TYPE = {0, 1}; // an identifier for this class type
	public static final int cacheTime = 120000; // the time after which the preload image is discarded
	
	private File sprite = null; // store the sprite as path to save memory
	private BufferedImage preload = null; // a preloaded version to enhance performance
	private int scaleY = 0; // scaling disabled by default
	private int scaleX = 0; // scaling disabled by default
	private boolean scaling = false; // is the image scaled?
	private int scaleDefaultY = 0; // default height
	private int scaleDefaultX = 0; // default width
	private Timer preloadTimer = new Timer(cacheTime, a -> this.unload()); // discard the preloaded image if not currently in use
	
	/**
	 * Create a Sprite representing an image file.
	 * 
	 * @param classID - the unique ClassID of this Sprite
	 * @param image - the image file of this Sprite
	 */
	public Sprite(ClassID classID, File image) {
		super(classID);
		this.setSprite(image);
		this.init();
	}
	
	/**
	 * Create a Sprite representing an image file.
	 * 
	 * @param classID - the unique ClassID of this class
	 * @param imagePath - the path to the image file of this Sprite
	 */
	public Sprite(ClassID classID, Path imagePath) {
		super(classID);
		this.setSprite(imagePath);
		this.init();
	}
	
	/**
	 * Recreate a Sprite representing an image file from a previously saved array of bytes.
	 * 
	 * @param bytes - the byte array to recreate the Sprite from
	 */
	public Sprite(byte[] bytes) {
		super(bytes);
		this.createFromBytes(bytes);
		this.init();
	}
	
	// do all this on construction
	private void init() {
		this.preloadTimer.setRepeats(false); // does not loop by default
//		this.setDefaultImageSize(); // part of setSprite()
	}
	
	// determine the default image size
	private void setDefaultImageSize() {
		if (this.sprite != null && this.sprite.isFile()) {
			try {
				BufferedImage bi = ImageIO.read(this.sprite);
				this.scaleDefaultX = bi.getWidth();
				this.scaleDefaultY = bi.getHeight();
			} catch (Exception e) {
				LoggingHandler.getLog().log(Level.SEVERE, "Sprite " + this.sprite + " could not be loaded.", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Cache the BufferedImage of this Sprite.
	 * 
	 * @return - true if the Sprite was successfully cached as BufferedImage
	 */
	public boolean preload() {
		if (this.sprite != null && this.sprite.isFile()) {
			try {
				this.preload = ImageIO.read(this.sprite);
				if (scaling) {					
					this.preload = Drawable.resize(this.preload, this.scaleX, this.scaleY);
				}
				this.preloadTimer.restart();
				return true;
			} catch (Exception e) {
				LoggingHandler.getLog().log(Level.SEVERE, "Sprite " + this.sprite + " could not be preloaded.", e);
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Clear the cached BufferedImage.
	 * 
	 * @return - true if the cached BufferedImage was successfully cleared
	 */
	public boolean unload() {
		if (this.preload != null) {
			this.preload = null;
			LoggingHandler.getLog().fine("Unloaded " + this);
			return true;
		}
		return false;
	}
	
	@Override
	public byte[] toBytes() {
		byte[][] bytes = new byte[8][];
		bytes[0] = ByteUtilities.integerToByte(this.getType().length); // first: length of the type array
		bytes[1] = this.getType(); // second: type array
		if (this.getClassID() != null) {
			bytes[3] = this.getClassID().toByte(); // fourth: class ID
			bytes[2] = ByteUtilities.integerToByte(bytes[3].length); // third: length of the class ID
		} else { // set the length to a negative value if the file is null
			bytes[2] = ByteUtilities.integerToByte(-1);
			LoggingHandler.getLog().warning("The ClassID of " + this + " is invalid.");
		}
		if (this.getDescriptiveName() != null) {
			bytes[5] = this.getDescriptiveName().getBytes(); // sixth: descriptive
			bytes[4] = ByteUtilities.integerToByte(bytes[5].length); // fifth: length of the descriptive
		} else { // set the length to a negative value if the descriptive is null
			bytes[4] = ByteUtilities.integerToByte(-1);
		}
		if (this.sprite != null) {
			bytes[7] = this.sprite.getPath().getBytes(); // eighth: file path as string
			bytes[6] = ByteUtilities.integerToByte(bytes[7].length); // seventh: length of the file path
		} else { // set the length to a negative value if the file is null
			bytes[6] = ByteUtilities.integerToByte(-1);
		}
		return ByteUtilities.join(bytes);	
	}

	@Override
	public void createFromBytes(byte[] bytes) {
		if (bytes != null) {
			ByteBuffer bb = ByteBuffer.wrap(bytes); // create a BuyteBuffer to ease reading
			int length = bb.getInt(); // a helper variable to determine byte lengths
			byte[] value = new byte[length]; // a helper variable to read bytes
			bb.get(value);
			if (Arrays.equals(value, this.getType())) { // the byte array must have the same type identifier
				length = bb.getInt(); // get the length of the ClassID
				if (length >= 0) {
					value = new byte[length]; // get the ClassID
					bb.get(value);
					this.setClassID(new ClassID(value));
				} else {
					this.setClassID(null);
				}
				length = bb.getInt(); // get the length of the descriptive
				if (length >= 0) {
					value = new byte[length];
					bb.get(value); // get the descriptive
					this.setDescriptiveName(new String(value));
				} else {
					this.setDescriptiveName(null);
				}
				length = bb.getInt(); // get the length of the file path
				if (length >= 0) {
					value = new byte[length];
					bb.get(value); // get the file path
					this.setSprite(new String(value));
				} else {
					this.setSprite((File) null);
				}
			} else {
				throw new IllegalArgumentException();
			}
		} else {
			LoggingHandler.getLog().warning(this + " cannot be reacreated from null.");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Sprite && obj != null) {
			Sprite dummy = (Sprite) obj;
			if (this.getHeight() == dummy.getHeight() && this.getWidth() == dummy.getWidth()) {
				return this.sprite.equals(dummy.getSpriteFile());
				// preload does not matter for equality
			}
		}
		return false;
	}
	
	@ Override
	public BufferedImage toBufferedImage() {
		this.preloadTimer.restart();
		if (preload != null) {
			return this.preload;
		} else {
			if (this.sprite != null && this.sprite.isFile()) {
				try {
					this.preload();
					return this.preload;
				} catch (Exception e) {
					LoggingHandler.getLog().log(Level.SEVERE, "Sprite " + this.sprite + " could not be read.", e);
					e.printStackTrace();
				}
			}
			return null;
		}
	}
	
	// getters
	
	/**
	 * Get the file that contains this sprite.
	 * 
	 * @return the image file of this sprite
	 */
	public File getSpriteFile() {
		return this.sprite;
	}

	
	// setters
	
	/**
	 * Set the image file of this Sprite.
	 * 
	 * @param sprite - the Path to the Sprite image
	 */
	public void setSprite(Path sprite) {
		if (sprite != null) {
			this.sprite = sprite.toFile();
		} else {
			this.sprite = null;
		}
		this.setDefaultImageSize(); // determine the new default image size
		if (preload != null) {
			this.preload();
		}
	}
	
	/**
	 * Set the image file of this Sprite.
	 * 
	 * @param sprite - the File of this Sprite
	 */
	public void setSprite(File sprite) {
		this.sprite = sprite;
		this.setDefaultImageSize(); // determine the new default image size
		if (preload != null) {
			this.preload();
		}
	}
	
	/**
	 * Set the image file of this Sprite.
	 * 
	 * @param sprite - the path to the file of this Sprite as String
	 */
	public void setSprite(String sprite) {
		this.sprite = new File(sprite);
		this.setDefaultImageSize(); // determine the new default image size
		if (preload != null) {
			this.preload();
		}
	}
	
	/**
	 * Set the scale of this sprite. If negative values are passed, the default scale is used.
	 * 
	 * @param width - the width to size this sprite to
	 * @param height - the height to size this sprite to
	 */
	public void setScale(int width, int height) {
		if (width >= 0 && height >= 0) {
			if (width == this.scaleDefaultX && height == this.scaleDefaultY) {
				this.scaling = false;
			} else {
				this.scaling = true;
			}
			this.scaleY = height;
			this.scaleX = width;
			if (this.preload != null) {
				this.preload();
			}
		} else { // turn of scaling if negative values are passed
			this.scaling = false;
		}
	}
	
	/**
	 * Scale the image to the specified factor. If negative values are passed, the default scale is used.
	 * 
	 * @param factor - the factor to multiply the image height and width with
	 */
	public void setScale(double factor) {
		if (factor != 1.0d && factor >= 0.0d) {
			this.scaling = true;
		} else {
			this.scaling = false;
		}
		this.scaleY = (int) (this.scaleDefaultY * factor);
		this.scaleX = (int) (this.scaleDefaultX * factor);
		if (this.preload != null) {
			this.preload();
		}
	}
	
	/**
	 * Get the width of this sprite.
	 * 
	 * @return the width of this sprite as int
	 */
	public int getWidth() {
		if (this.scaling) {
			return this.scaleX;
		} else {
			return this.scaleDefaultX;
		}
	}
	
	/**
	 * Get the height of this sprite.
	 * 
	 * @return the height of this sprite as int
	 */
	public int getHeight() {
		if (this.scaling) {
			return this.scaleY;
		} else {
			return this.scaleDefaultY;
		}
	}

	@Override
	public byte[] getType() {
		return TYPE;
	}

}
