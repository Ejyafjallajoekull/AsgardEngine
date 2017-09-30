package asgardengine.rendering;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import asgardengine.game.classes.characters.Actor;
import asgardengine.game.classes.graphics.Animation;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.graphics.Sprite;
import asgardengine.game.classes.world.Place;
import asgardengine.game.classes.world.Placeable;
import asgardengine.game.entities.actors.ActorEntity;
import asgardengine.game.handler.ClassHandler;
import asgardengine.game.handler.EntityHandler;

public class RenderPanel extends JPanel {

	private Place testPlace = null;
	private ActorEntity golbatFRef = null;
	public int w = 0;
	public int h = 0;
//	public Timer timer = new Timer(1000/240, l -> {this.repaint();});
	
	public RenderPanel() {
		byte[] index = {0,0};
		byte[] ggf = null;
		try {
//			gf = new Sprite(Files.readAllBytes((new File("Test//a.sp")).toPath()));
			ggf = Files.readAllBytes((new File("Test//a.sp")).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sprite gf = new Sprite(ggf);
		System.out.println(gf.getSpriteFile());
		
	//	Sprite s = new Sprite(new ClassID(b, a), new File("Test//flygon.gif"));
//		Sprite gf = new Sprite(new ClassID(b, a), new File("Test//golbat.png"));
		Sprite gb = new Sprite(ClassHandler.nextID(index), new File("Test//golbat_b.png"));
//		try {
//			FileOutputStream fos = new FileOutputStream(new File("Test//a.sp"));
//			try {
//				fos.write(gf.toBytes());
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			try {
//				fos.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Animation anim = new Animation(ClassHandler.nextID(index));
		anim.addAnimationSpirte(gf, 1000000000l);
		anim.addAnimationSpirte(gb, 1000000000l);
		anim.loop(true);
//		anim.addAnimationSpirte(gf, 200000000l);
//		anim.addAnimationSpirte(gb, 500000000l);
//		anim.addAnimationSpirte(gf, 100000000l);
//		anim.addAnimationSpirte(gb, 500000000l);
	//	AnimationEntity ae = new AnimationEntity(new EntityID(b), anim);
		Actor golbat = new Actor(ClassHandler.nextID(index));
		gf.preload();
		gb.preload();
		golbat.addAnimation(anim);
		golbat.setActorSprite(gf);
		ActorEntity golbatRef = new ActorEntity(EntityHandler.nextID(), golbat);
		golbatRef.playAnimation(0);
		Sprite black = new Sprite(ClassHandler.nextID(index), new File("Test//black.png"));
		testPlace = new Place(ClassHandler.nextID(index));
		testPlace.setBackground(black);
		testPlace.getBackground().setScale(1920, 1080);
		Random rr = new Random();
		for (int i = 0; i < 10000; i++) {
			golbatRef = new ActorEntity(EntityHandler.nextID(), golbat);
			golbatRef.playAnimation(0);
			testPlace.add(golbatRef, rr.nextInt(1920), rr.nextInt(1080), 0);
		}
		golbatFRef = golbatRef;
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
//				System.out.println(e.getKeyCode());
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
//				System.out.println(e.getKeyCode());
//				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e != null) {
//					System.out.println(e.getKeyCode());
					if (e.getKeyCode() == KeyEvent.VK_LEFT + KeyEvent.VK_UP) {
//						System.out.println("do");
						moveLeft();
						moveUp();
					}
					if (e.getKeyCode() == KeyEvent.VK_UP) {
//						System.out.println("do");
						moveUp();
					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//						System.out.println("do");
						moveDown();
					}
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//						System.out.println("do");
						moveRight();
					}
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//						System.out.println("do");
						moveLeft();
					}
				}
				
			}
		});
//		timer.start();
	}
	
	// serialisation
	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.render((Graphics2D) g);
	}
	
	private void render(Graphics2D g){
		((Graphics2D) g).drawImage(testPlace.getBackground().toBufferedImage(), 0, 0, null);
		for (Drawable d : testPlace.getDrawables()) {
			((Graphics2D) g).drawImage(d.toBufferedImage(),(int) ((Placeable) d).getPosition().getX(), (int) ((Placeable) d).getPosition().getY(), null);
		}
		g.dispose();
	}
	
	public void moveUp() {
		h--;
		golbatFRef.getPosition().setY(h);
	}
	public void moveDown() {
		h++;
		golbatFRef.getPosition().setY(h);
	}
	public void moveRight() {
		w++;
		golbatFRef.getPosition().setX(w);
	}
	public void moveLeft() {
		w--;
		golbatFRef.getPosition().setX(w);
	}
	
}
