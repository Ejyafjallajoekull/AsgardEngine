package asgardengine.game.classes.graphics;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.Level;

import javax.imageio.ImageIO;

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
	
	public static final byte[] TYPE = {0, 1}; // an identifier for this class type
	
	private File sprite = null; // store the sprite as path to save memory
	private BufferedImage preload = null; // a preloaded version to enhance performance
	private int scaleY = -1; // scaling disabled by default
	private int scaleX = -1; // scaling disabled by default
//	private File change = null; // monitor changes
	
	public Sprite(ClassID classID, File image) {
		super(classID);
		this.sprite = image;
	}
	
	public Sprite(ClassID classID, Path imagePath) {
		super(classID);
		this.setSprite(imagePath);
		
	}
	
	public Sprite(byte[] bytes) {
		super(bytes);
		this.createFromBytes(bytes);
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
				this.preload = ImageIO.read(this.sprite);
				if (this.scaleY >= 0 || this.scaleX >= 0) {
					int x = this.preload.getWidth();
					int y = this.preload.getHeight();
					if (this.scaleY >= 0) {
						y = this.scaleY;
					}
					if (this.scaleX >= 0) {
						x = this.scaleX;
					}
//					GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
//					BufferedImage resized = gc.createCompatibleImage(x, y);
					BufferedImage resized = new BufferedImage(x, y, this.preload.getType());
					Graphics2D g = resized.createGraphics();
					g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
					g.drawImage(this.preload, 0, 0, x, y, 0, 0, this.preload.getWidth(), this.preload.getHeight(), null);
					g.dispose();					
					this.preload = resized;
				}
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
	
	public File getSpriteFile() {
		return this.sprite;
	}

	
	// setters
	
	public void setSprite(Path sprite) {
		if (sprite != null) {
			this.sprite = sprite.toFile();
		} else {
			this.sprite = null;
		}
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
	
	public void setScale(int width, int height) {
		this.scaleY = height;
		this.scaleX = width;
		if (this.preload != null) {
			this.preload();
		}
	}

	@Override
	public byte[] getType() {
		return TYPE;
	}

//	@Override
//	public boolean didDrawingChange() {
//		if (this.sprite != this.change) {
//			this.change = this.sprite;
//			return true;
//		}
//		return false;
//	}

}
