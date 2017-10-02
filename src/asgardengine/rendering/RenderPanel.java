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

import asgardengine.game.classes.characters.Actor;
import asgardengine.game.classes.graphics.Animation;
import asgardengine.game.classes.graphics.DirectionalSprite;
import asgardengine.game.classes.graphics.Drawable;
import asgardengine.game.classes.graphics.Sprite;
import asgardengine.game.classes.world.Place;
import asgardengine.game.classes.world.Placeable;
import asgardengine.game.classes.world.Rotation1D;
import asgardengine.game.classes.world.Tile;
import asgardengine.game.entities.EntityID;
import asgardengine.game.entities.actors.ActorEntity;
import asgardengine.game.entities.world.TileEntity;
import asgardengine.game.handler.ClassHandler;
import asgardengine.game.handler.EntityHandler;
import asgardengine.utility.logging.LoggingHandler;

public class RenderPanel extends JPanel {

	private Place testPlace = null;
	private ActorEntity golbatFRef = null;
	public int w = 0;
	public int h = 0;
//	public Timer timer = new Timer(1000/240, l -> {this.repaint();});
	
	public RenderPanel() {
		this.test();
	}
	
	public void test() {
		LoggingHandler.startLogWriting();
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
		Sprite grass01 = new Sprite(ClassHandler.nextID(index), new File("Test//green_01.png"));
		Tile grassTile01 = new Tile(ClassHandler.nextID(index), grass01);
		Sprite rock01 = new Sprite(ClassHandler.nextID(index), new File("Test//rock01.png"));
		Tile rockTile01 = new Tile(ClassHandler.nextID(index), rock01);
		Sprite pool01 = new Sprite(ClassHandler.nextID(index), new File("Test//pool.png"));
		Tile poolTile01 = new Tile(ClassHandler.nextID(index), pool01);
		Sprite heroFront = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//hero_front.png"));
		Sprite heroBack = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//hero_back.png"));
		Sprite heroRight = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//hero_right.png"));
		Sprite heroLeft = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//hero_left.png"));
		DirectionalSprite heroSprite = new DirectionalSprite(ClassHandler.nextID(index), heroFront, heroBack, heroLeft, heroRight);
		Actor hero = new Actor(ClassHandler.nextID(index));
		hero.setIdle(heroSprite);
		ActorEntity heroRef = new ActorEntity(EntityHandler.nextID(), hero);
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
//		golbatRef = new ActorEntity(EntityHandler.nextID(), golbat);
		golbatRef = heroRef;
		int x = 0;
		int y = 0;
		TileEntity grassE01 = null;
		for (int i = 0; i < 8100; i++) {
			grassE01 = new TileEntity(EntityHandler.nextID(), grassTile01);
			testPlace.add(grassE01, x, y, 0);
			if (x >= 1920) {
				x = 0;
				y += 15;
			} else {
				x += 15;
			}
			if (rr.nextInt(1000) < 1) {
				testPlace.add(new TileEntity(EntityHandler.nextID(), rockTile01), rr.nextInt(x), rr.nextInt(y), 0);
			}
		}
		TileEntity poolE = new TileEntity(EntityHandler.nextID(), poolTile01);
		poolE.getRotation().setRotation(45);
		testPlace.add(poolE, 500, 500, 0);
		testPlace.add(golbatRef, 300, 300, 0);
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
		h-= 1;
		golbatFRef.getPosition().setY(h);
		golbatFRef.getRotation().setRotation(0);
	}
	public void moveDown() {
		h++;
		golbatFRef.getPosition().setY(h);
		golbatFRef.getRotation().setRotation(180);
	}
	public void moveRight() {
		w++;
		golbatFRef.getPosition().setX(w);
		golbatFRef.getRotation().setRotation(90);
	}
	public void moveLeft() {
		w--;
		golbatFRef.getPosition().setX(w);
		golbatFRef.getRotation().setRotation(270);
	}
	
}
