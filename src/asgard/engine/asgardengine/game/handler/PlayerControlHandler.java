package asgard.engine.asgardengine.game.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import asgardengine.game.classes.world.Rotation1D;
import asgardengine.game.entities.actors.ActorEntity;
import asgardengine.rendering.RenderPanel;

public class PlayerControlHandler implements KeyListener{

	private ActorEntity player = null;
	
	//states
	private boolean isMovingUp = false;
	private boolean isMovingDown = false;
	private boolean isMovingRight = false;
	private boolean isMovingLeft = false;
	
	//Key controls
	public static final int MOVE_UP = KeyEvent.VK_W;
	public static final int MOVE_DOWN = KeyEvent.VK_S;
	public static final int MOVE_RIGHT = KeyEvent.VK_D;
	public static final int MOVE_LEFT = KeyEvent.VK_A;
	public static final int JUMP = KeyEvent.VK_SPACE;
	public static final int RUN = KeyEvent.VK_SHIFT;
	public static final int TESTING_MEASURE = KeyEvent.VK_L;
	
	public PlayerControlHandler(ActorEntity player) {
		this.player = player;
	}
	
	// is the player currently moving?
	private boolean isPlayerMoving() {
		if (this.isMovingDown || this.isMovingLeft || this.isMovingUp || this.isMovingRight) {
			return true;
		} else {
			return false;
		}
	}
	
	private void measure() {
		System.out.println("Measured value: " + RenderPanel.measure / RenderPanel.measureIndex);
	}
	
	private void adjustPlayerRotation() {
		if (this.isMovingUp) {
			if (this.isMovingLeft) {
				player.getRotation().setRotation(Rotation1D.RADIANS_NORTHWEST);
			} else if (this.isMovingRight) {
				player.getRotation().setRotation(Rotation1D.RADIANS_NORTHEAST);
			} else {
				player.getRotation().setRotation(Rotation1D.RADIANS_NORTH);
			}
		} else if (this.isMovingDown) {
			if (this.isMovingLeft) {
				player.getRotation().setRotation(Rotation1D.RADIANS_SOUTHWEST);
			} else if (this.isMovingRight) {
				player.getRotation().setRotation(Rotation1D.RADIANS_SOUTHEAST);
			} else {
				player.getRotation().setRotation(Rotation1D.RADIANS_SOUTH);
			}
		} else if (this.isMovingLeft) {
			player.getRotation().setRotation(Rotation1D.RADIANS_WEST);
		} else if (this.isMovingRight) {
			player.getRotation().setRotation(Rotation1D.RADIANS_EAST);
		}
	}
	
	public void playerMoving() {
		this.adjustPlayerRotation();
		if (this.isPlayerMoving()) {
			player.walk(true);
		} else {
			player.walk(false);
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == RUN) {
			player.setRunning(true);
			this.playerMoving();
		} else if (ke.getKeyCode() == MOVE_UP) {
			this.isMovingUp = true;
			this.playerMoving();
		}  else if (ke.getKeyCode() == MOVE_DOWN) {
			this.isMovingDown = true;
			this.playerMoving();
		} else if (ke.getKeyCode() == MOVE_RIGHT) {
			this.isMovingRight = true;
			this.playerMoving();
		} else if (ke.getKeyCode() == MOVE_LEFT) {
			this.isMovingLeft = true;
			this.playerMoving();
		}
		
		if (ke.getKeyCode() == JUMP) {
			player.jump();
		}
		
		if (ke.getKeyCode() == TESTING_MEASURE) {
			this.measure();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		if (ke.getKeyCode() == RUN) {
			player.setRunning(false);
			this.playerMoving();
		} else if (ke.getKeyCode() == MOVE_UP) {
			this.isMovingUp = false;
			this.playerMoving();
		}  else if (ke.getKeyCode() == MOVE_DOWN) {
			this.isMovingDown = false;
			this.playerMoving();
		} else if (ke.getKeyCode() == MOVE_RIGHT) {
			this.isMovingRight = false;
			this.playerMoving();
		} else if (ke.getKeyCode() == MOVE_LEFT) {
			this.isMovingLeft = false;
			this.playerMoving();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

}
