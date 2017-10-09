package asgardengine.game.entities.world;

import java.util.ArrayList;

import asgardengine.game.classes.GameClass;
import asgardengine.game.classes.world.Place;
import asgardengine.game.entities.EntityID;
import asgardengine.game.entities.GameEntity;
import asgardengine.game.entities.actors.ActorEntity;

public class PlaceEntity extends GameEntity {
	
	private Place place = null;
	
	private ArrayList<TileEntity> statics = new ArrayList<TileEntity>(); // all static tiles
	private ArrayList<TileEntity> tiles = new ArrayList<TileEntity>(); // all non static tiles
	private ArrayList<ActorEntity> actors = new ArrayList<ActorEntity>(); // all actors

	
	public PlaceEntity(EntityID entityID, Place place) {
		super(entityID);
		this.place = place;
		if (this.place != null) {
			this.statics = new ArrayList<TileEntity>(this.place.getStatics());
			this.tiles = new ArrayList<TileEntity>(this.place.getTiles());
			this.actors = new ArrayList<ActorEntity>(this.place.getActors());
		}
	}

	public PlaceEntity(byte[] bytes) {
		super(bytes);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] toBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createFromBytes(byte[] bytes) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public Place getSource() {
		return this.place;
	}

}
