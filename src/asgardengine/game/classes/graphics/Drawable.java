package asgardengine.game.classes.graphics;

import java.awt.image.BufferedImage;

/**
 * The Drawable interface provides graphical access to data.
 * 
 * @author Planters
 *
 */
public interface Drawable {
	// an interface which marks a class as holding a buffered image

	/**
	 * Convert the calling objects current state to a BufferedImage.
	 * 
	 * @return A BufferedImage representation of the given object.
	 */
	public BufferedImage toBufferedImage();
	
}
