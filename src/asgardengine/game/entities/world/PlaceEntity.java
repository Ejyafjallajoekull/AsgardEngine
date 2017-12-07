package asgardengine.game.entities.world;

import java.util.HashSet;

import asgardengine.game.classes.world.Place;
import asgardengine.game.classes.world.Placeable;
import asgardengine.game.entities.EntityID;
import asgardengine.game.entities.GameEntity;
import asgardengine.utility.quadtree.Quadtree;

/**
 * The PlaceEntity class is an instance class for Places. It keeps track of Placeables, 
 * which got added and removed from the template Place and decouples the Place template 
 * from changes occurring during gameplay.
 * 
 * @author Planters
 *
 */
public class PlaceEntity extends GameEntity {
	
	private Place place = null;
	private HashSet<Placeable> additions = new HashSet<Placeable>(); // objects to add on load
	private HashSet<Placeable> removals = new HashSet<Placeable>(); // objects to remove on load	
	
	public PlaceEntity(EntityID entityID, Place place) {
		super(entityID);
		this.place = place;
//		if (this.place != null) {
//			this.cellTree = 
//		}
	}
	
	/**
	 * Add a Placeable to this Place instance. 
	 * The object can only be added if it is not already part of the Place template.
	 * 
	 * @param entity - the Placeable to add to this PlaceEntity
	 * @return true if the addition was successful
	 */
	public boolean add(Placeable entity) {
		if (entity != null && this.getSource() != null && entity.getPlace() != this.getSource()) { // do not allow Placeables, which are already part of the place
			this.additions.add(entity);
			this.getSource().add(entity);
			// registering this object is done by the corresponding template place // an object can only be part of one place at a time
		}
		return false;
	}
	
	/**
	 * Remove the Placeable from this Place instance.
	 * 
	 * @param entity - the Placeable to remove from this PlaceEntity
	 * @return - true if the removal was successful
	 */
	public boolean remove(Placeable entity) {
		boolean removal = false;
		if (entity != null && this.getSource() != null && entity.getPlace() == this.getSource()) {
			if (this.additions.contains(entity)) { // remove this object from the set of additions if not originally part of the map
				removal = this.additions.remove(entity);
			} else { // add this object to the list of objects to remove from the original map
				removal = this.removals.add(entity);
			}
			if (removal) {
				entity.removeFromPlace(); // remove the object from the template place
			}
		}
		return removal;
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
	public void createFromBytes(byte[] bytes) {
		// TODO Auto-generated method stub

	}

	@Override
	public Place getSource() {
		return this.place;
	}

}
