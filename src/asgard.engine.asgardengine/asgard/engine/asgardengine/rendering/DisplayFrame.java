package asgard.engine.asgardengine.rendering;

import javax.swing.JFrame;

public class DisplayFrame extends JFrame {

	// serialisation
	private static final long serialVersionUID = 1L;

	private RenderPanel renderer = new RenderPanel();
	
	public DisplayFrame() {
		this.setIgnoreRepaint(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.add(renderer);
		renderer.setFocusable(true);
		this.setVisible(true);
	}
	
	public void draw() {
		renderer.screenCenter.setCoordinates(this.getWidth()/2, this.getHeight()/2, 0);
		renderer.repaint();
	}

	public RenderPanel getRenderer() {
		return renderer;
	}
	
}
