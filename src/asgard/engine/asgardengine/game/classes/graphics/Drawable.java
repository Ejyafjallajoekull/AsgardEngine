package asgard.engine.asgardengine.game.classes.graphics;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
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
	
	/**
	 * Returns a resized version of the input image.
	 * 
	 * @param source - the input image
	 * @param width - the output width
	 * @param heigth - the output height
	 * @return a resized version of the input
	 */
	public static BufferedImage resize(BufferedImage source, int width, int heigth) {
		if (source != null) {
			BufferedImage resized = new BufferedImage(width, heigth, source.getType());
			Graphics2D g = resized.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(source, 0, 0, width, heigth, 0, 0, source.getWidth(), source.getHeight(), null);
			g.dispose();					
			return resized;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns a rotated version of the input image.
	 * 
	 * @param source - the input image
	 * @param radians - the output angle
	 * @return a rotated version of the input
	 */
	public static BufferedImage rotate(BufferedImage source, double radians) {
		if (source != null) {
			int max = (int) Math.ceil(Math.sqrt(Math.pow(source.getWidth(), 2.0d) + Math.pow(source.getHeight(), 2.0d)));
			BufferedImage rotated = new BufferedImage(max, max, source.getType());
			AffineTransform at = new AffineTransform();
			at.translate(max/2, 0);
			at.rotate(radians);
			Graphics2D g = rotated.createGraphics();
			g.drawImage(source, at, null);
			g.dispose();					
			return rotated;
		} else {
			return null;
		}
	}
	
}
