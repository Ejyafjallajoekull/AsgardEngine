package asgard.engine.asgardengine.rendering;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import asgard.engine.asgardengine.game.classes.actors.Actor;
import asgard.engine.asgardengine.game.classes.graphics.Animation;
import asgard.engine.asgardengine.game.classes.graphics.DirectionalAnimation;
import asgard.engine.asgardengine.game.classes.graphics.DirectionalSprite;
import asgard.engine.asgardengine.game.classes.graphics.Sprite;
import asgard.engine.asgardengine.game.classes.world.Place;
import asgard.engine.asgardengine.game.classes.world.Placeable;
import asgard.engine.asgardengine.game.classes.world.Position;
import asgard.engine.asgardengine.game.classes.world.Tile;
import asgard.engine.asgardengine.game.entities.actors.ActorEntity;
import asgard.engine.asgardengine.game.entities.world.PlaceEntity;
import asgard.engine.asgardengine.game.entities.world.TileEntity;
import asgard.engine.asgardengine.game.handler.ClassHandler;
import asgard.engine.asgardengine.game.handler.EntityHandler;
import asgard.engine.asgardengine.game.handler.PlaceHandler;
import asgard.engine.asgardengine.game.handler.PlayerControlHandler;
import asgard.engine.asgardengine.utility.quadtree.RectangularBound;
import central.logging.functionality.LoggingFailureException;
import central.logging.functionality.LoggingHandler;

public class RenderPanel extends JPanel {

	private Place testPlace = null;
	private ActorEntity golbatFRef = null;
	public int w = 0;
	public int h = 0;
	public Random random = new Random();
//	public Timer timer = new Timer(1000/240, l -> {this.repaint();});
	public Position screenCenter = new Position(300d, 300d, 0.0d);
	public Position relativePosition = new Position();
	public ArrayList<TileEntity> rocks = new ArrayList<TileEntity>(); // testing
	public boolean breakExec = false; // testing
	public static PlaceHandler placeHandler = null; // testing
	public static long measure = 0l;
	public static int measureIndex = 0;
	
	
	private Placeable pov = null;
	
	public RenderPanel() {
		this.test();
	}
	
	public void test() {
		try {
			LoggingHandler.startLogWriting();
		} catch (LoggingFailureException e1) {
			System.out.println("Starting the logging process failed");
			e1.printStackTrace();
		}
		byte[] index = {0,0};
		Sprite grass01 = new Sprite(ClassHandler.nextID(index), new File("Test//green_01.png"));
		Tile grassTile01 = new Tile(ClassHandler.nextID(index), grass01);
//		Trigonometry.initialise();
//		double maxDiff = 0.0d;
//		double curDiff = 0.0d;
//		double math = 0.0d;
//		double trig = 0.0d;
//		double randD = 0.0d;
//		for (int i = 0; i < 100000; i++) {
//			randD = this.random.nextDouble();
//			math = Math.cos(randD);
//			trig = Trigonometry.cosNotch((float) randD);
//			curDiff = trig-math;
//			if (curDiff < 0.0d) {
//				curDiff *= -1.0d;
//			}
//			if (curDiff > maxDiff) {
//				maxDiff = curDiff;
//			}
//		}
//		System.out.println(maxDiff);
		if (breakExec) {
			throw new NullPointerException("Break!");
		}
		byte[] ggf = null;
		try {
//			gf = new Sprite(Files.readAllBytes((new File("Test//a.sp")).toPath()));
			ggf = Files.readAllBytes((new File("Test//a.sp")).toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sprite gf = new Sprite(ggf);
		System.out.println(gf);
		
	//	Sprite s = new Sprite(new ClassID(b, a), new File("Test//flygon.gif"));
//		Sprite gf = new Sprite(new ClassID(b, a), new File("Test//golbat.png"));
		Sprite gb = new Sprite(ClassHandler.nextID(index), new File("Test//golbat_b.png"));
		Sprite rock01 = new Sprite(ClassHandler.nextID(index), new File("Test//rock01.png"));
		Tile rockTile01 = new Tile(ClassHandler.nextID(index), rock01);
		Sprite pool01 = new Sprite(ClassHandler.nextID(index), new File("Test//pool.png"));
		Tile poolTile01 = new Tile(ClassHandler.nextID(index), pool01);
		Sprite heroFront = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//hero_front.png"));
		Sprite heroBack = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//hero_back.png"));
		Sprite heroRight = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//hero_right.png"));
		Sprite heroLeft = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//hero_left.png"));
		DirectionalSprite heroSprite = new DirectionalSprite(ClassHandler.nextID(index), heroFront, heroBack, heroLeft, heroRight);
		Sprite hero_anim_jump0 = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//Animation//Jump/jump_front_00.png"));
		Sprite hero_anim_jump1 = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//Animation//Jump/jump_front_01.png"));
		Sprite hero_anim_jump2 = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//Animation//Jump/jump_front_02.png"));
		Sprite hero_anim_jump3 = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//Animation//Jump/landing_front_00.png"));
		Sprite hero_anim_jump4 = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//Animation//Jump/landing_front_01.png"));
		Sprite hero_anim_jump5 = new Sprite(ClassHandler.nextID(index), new File("Test//Actor//Animation//Jump/landing_front_02.png"));
		Animation hero_jump_front = new Animation(ClassHandler.nextID(index));
		hero_jump_front.addAnimationSpirte(hero_anim_jump0, 100000000);
		hero_jump_front.addAnimationSpirte(hero_anim_jump1, 100000000);
		hero_jump_front.addAnimationSpirte(hero_anim_jump2, 100000000);
		hero_jump_front.addAnimationSpirte(hero_anim_jump0, 100000000);
		hero_jump_front.addAnimationSpirte(hero_anim_jump1, 100000000);
		hero_jump_front.addAnimationSpirte(hero_anim_jump2, 100000000);
		hero_jump_front.addAnimationSpirte(hero_anim_jump0, 100000000);
		hero_jump_front.addAnimationSpirte(hero_anim_jump1, 100000000);
		hero_jump_front.addAnimationSpirte(hero_anim_jump2, 100000000);
		hero_jump_front.addAnimationSpirte(hero_anim_jump3, 300000000);
		hero_jump_front.addAnimationSpirte(hero_anim_jump4, 150000000);
		hero_jump_front.addAnimationSpirte(hero_anim_jump5, 150000000);
		DirectionalAnimation hero_jump = new DirectionalAnimation(ClassHandler.nextID(index), hero_jump_front, null, null, null);
		Actor hero = new Actor(ClassHandler.nextID(index));
		hero.setJumpAnimation(hero_jump);
		hero.setActorSprite(heroFront);
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
		int x = 1;
		int y = 0;
		TileEntity grassE01 = null;
		rockTile01.setZHeight(5.0d);
//		rock01.setScale(5);
		hero.setSteppingHeight(0.5d);
		for (int i = 0; i < 8100; i++) {
			grassE01 = new TileEntity(EntityHandler.nextID(), grassTile01);
			testPlace.add(grassE01, x, y, 0);
			if (x >= 1920) {
				x = 1;
				y += 15;
			} else {
				x += 15;
			}
			if (rr.nextInt(1000) < 1) {
				this.rocks.add(new TileEntity(EntityHandler.nextID(), rockTile01));
				testPlace.add(this.rocks.get(this.rocks.size() - 1), rr.nextInt(x), rr.nextInt(y), 0);
			}
		}
		TileEntity poolE = new TileEntity(EntityHandler.nextID(), poolTile01);
//		poolE.getRotation().setRotation(45);
		testPlace.add(poolE, 500, 500, 0);
		testPlace.add(golbatRef, 300, 300, 0);
		placeHandler = new PlaceHandler(heroRef);
		placeHandler.loadPlace(new PlaceEntity(EntityHandler.nextID(), testPlace));
		golbatFRef = golbatRef;
		this.pov = heroRef;
		this.addKeyListener(new PlayerControlHandler(heroRef));
		relativePosition = Position.subtract(heroRef.getPosition(), screenCenter);
	}
	
	// serialisation
	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.render((Graphics2D) g);
	}
	
	private void render(Graphics2D g){
		if (this.getPOV() != null) {
			((Graphics2D) g).drawImage(testPlace.getBackground().toBufferedImage(), 0, 0, null);
			Position pos = null;
			relativePosition = Position.subtract(this.pov.getPosition(), screenCenter);


			ArrayList<Placeable> objects = placeHandler.getEntities(new RectangularBound(this.pov.getPosition(), 1920.0d, 1080.0d));
//			System.out.println(objects.size());
			placeHandler.populateQuadtree();
			for (Placeable d : objects) {
				pos = Position.subtract(d.getPosition(), this.relativePosition);
				((Graphics2D) g).drawImage((d).toBufferedImage(),(int) pos.getX(), (int) pos.getY(), null);
			}
		}
		g.dispose();
	}

	public Placeable getPOV() {
		return this.pov;
	}

	public void setPOV(Placeable pov) {
		this.pov = pov;
	}
	
}
