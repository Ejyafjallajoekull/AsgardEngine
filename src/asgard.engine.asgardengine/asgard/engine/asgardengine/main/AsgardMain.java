package asgard.engine.asgardengine.main;

import asgard.engine.asgardengine.rendering.DisplayFrame;

public class AsgardMain implements Runnable {
	
	private boolean isRunning = false;
	private Thread mainThreat = null;
	private int desiredFPS = 120;
	private long updateThreshold = 1000000000l / this.desiredFPS; // the threshold to update everything 
	private DisplayFrame frame = null;
	private boolean shouldRender = true;
	private float currentFPS = 0.0f;
	
	public static AsgardMain main = null;
	
	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true"); // use OpenGL
		main = new AsgardMain();
		main.setFrame(new DisplayFrame());
		main.start();
	}
	
	public boolean start() {
		if (!this.isRunning) {
			this.isRunning  = true;
			this.mainThreat = new Thread(this);
			this.mainThreat.run();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean stop() {
		if (isRunning) {
			isRunning = false;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void run() {
		
		long startTime = System.nanoTime();
		long timePassed = 0;
		this.currentFPS = 0;
		double fpsTime = 0;
		
		while (isRunning) {
			timePassed += System.nanoTime() - startTime;
			fpsTime += System.nanoTime() - startTime;
			startTime = System.nanoTime();
			while (timePassed >= this.updateThreshold) {
				timePassed -= this.updateThreshold;
				if (this.shouldRender) {
					frame.draw();
				}
				this.currentFPS++;
				if (this.currentFPS >= 60) {
//					System.out.println("FPS: " + this.currentFPS/(fpsTime/1000000000d));
					this.currentFPS = 0;
					fpsTime = 0;
				}
//				this.currentFPS = 1000000000l/fpsTime;
//				fpsTime = 0;
//				System.out.println("FPS: " + this.currentFPS);
			}
//			if (fpsTime > 1000000000l) {
//				System.out.println("FPS: " + this.currentFPS/(fpsTime/1000000000l));
//				fpsTime = 0;
//				this.currentFPS = 0;
//			}
		}
//		try {
//			Thread.sleep(1);
//		} catch (Exception e) {
//			LoggingHandler.getLog().log(Level.WARNING, "The main threat skipped sleeping.", e);
//			e.printStackTrace();
//		}		
	}

	public DisplayFrame getFrame() {
		return frame;
	}

	public void setFrame(DisplayFrame frame) {
		this.frame = frame;
	}

}
