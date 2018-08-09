package asgard.engine.asgardengine.game.handler;

import java.util.ArrayList;
import java.util.HashSet;

import asgardengine.game.classes.world.Place;
import asgardengine.game.classes.world.Placeable;
import asgardengine.game.classes.world.Position;
import asgardengine.game.classes.world.placetree.PlaceTreeCell;
import asgardengine.game.entities.world.PlaceEntity;
import asgardengine.rendering.RenderPanel;
import asgardengine.utility.quadtree.Quadtree;
import asgardengine.utility.quadtree.RectangularBound;

/**
 * The PlaceHandler class manages the Place the player actor is currently in and all surrounding places loaded. <br>
 * It also handles, which Placeables are drawn by the specified RenderWindow.
 * 
 * @author Planters
 *
 */
public class PlaceHandler {

	public static final int QUADTREE_MAX_DEPTH = 100;
	public static final int QUADTREE_MAX_OBJECTS = 50;
	public static final int PLACETREE_SURROUNDING_CELLS = 1; // how many layers of surrounding cells should be fetched
	
	private PlaceEntity loadedPlace = null;
	private Quadtree objectTree = new Quadtree(PlaceHandler.QUADTREE_MAX_DEPTH, PlaceHandler.QUADTREE_MAX_OBJECTS, new Position(), Place.CELL_SIZE_X * (2*PLACETREE_SURROUNDING_CELLS+1), Place.CELL_SIZE_Y * (2*PLACETREE_SURROUNDING_CELLS+1));
	private Placeable pov = null; // the entity to represent the point of view and thereby the center of cells
			
	public PlaceHandler(Placeable pointOfView) {
		if (pointOfView != null) {
			this.pov = pointOfView;
			objectTree = new Quadtree(PlaceHandler.QUADTREE_MAX_DEPTH, PlaceHandler.QUADTREE_MAX_OBJECTS, this.pov.getPosition(), Place.CELL_SIZE_X * (2*PLACETREE_SURROUNDING_CELLS+1), Place.CELL_SIZE_Y * (2*PLACETREE_SURROUNDING_CELLS+1));
		}
	}
	
	/**
	 * Get the currently loaded PlaceEntity.
	 * 
	 * @return the PlaceEntity, which is currently loaded by the PlaceHandler
	 */
	public PlaceEntity getLoadedPlace() {
		return this.loadedPlace;
	}

	/**
	 * Load the specified PlaceEntity into this PlaceHandler.
	 * This entity will be used for all further functionality of 
	 * the PlaceHandler.
	 * 
	 * @param loadedPlace - the PlaceEntity to load into the PlaceHandler
	 */
	public void loadPlace(PlaceEntity loadedPlace) {
		this.loadedPlace = loadedPlace;
	}
	
	/**
	 * Get a number of cells at the specified Position vector.
	 * The position vector is used to determine the central cell 
	 * and the layers of surrounding cells are determined by the 
	 * PLACETREE_SURROUNDING_CELLS constant.
	 * The resulting two dimensional array will contain null values for 
	 * non existing cells and is arranged in the form Cells[relative X-coordinate][relative Y-coordinate]
	 * with the central cell as at the centre of the array.
	 * 
	 * @param position - the Position vector to define the central PlaceTreeCell
	 * @return a List of PlaceTreeCells surrounding the specified Position vector as ArrayList
	 */
	public PlaceTreeCell[][] getCells(Position position) {
		if (this.loadedPlace != null && this.loadedPlace.getSource() != null) {
			return this.loadedPlace.getSource().getCellTree().get2D(position, PlaceHandler.PLACETREE_SURROUNDING_CELLS);			
		} else {
			return null;
		}
	}
	
	// get all entities from the loaded cells and put them into the quadtree
	public void populateQuadtree() {
//		this.objectTree.reset(); // reset the quadtree first
		if (this.pov != null) {
			HashSet<Placeable> objects = null; // order does not matter
			ArrayList<PlaceTreeCell> cellList = this.getCells(this.pov.getPosition());
			// TODO: improve center
			Position center = this.getLoadedPlace().getSource().getCellTree().getCellCenter(this.getLoadedPlace().getSource().getCellTree().get(this.pov.getPosition()));
			this.objectTree = new Quadtree(PlaceHandler.QUADTREE_MAX_DEPTH, PlaceHandler.QUADTREE_MAX_OBJECTS, center, Place.CELL_SIZE_X * (2*PLACETREE_SURROUNDING_CELLS+1), Place.CELL_SIZE_Y * (2*PLACETREE_SURROUNDING_CELLS+1));;
			for (PlaceTreeCell cell : cellList) { // get all objects // cell cannot be null
				long bef = System.nanoTime();
				objects = cell.getEntities();
				for (Placeable object : objects) { // add all objects of the cell to a tree
					this.objectTree.add(object);
				}
				RenderPanel.measureIndex++;
				RenderPanel.measure += System.nanoTime() - bef;
			}
		}
	}
	
	/**
	 * Get all entities, which are close to the specified entity.
	 * 
	 * @param entity - the Placeable t
	 * @return
	 */
	public ArrayList<Placeable> getCloseEntities(Placeable entity) {
		if (entity != null && this.getLoadedPlace() != null && entity.getPlace() == this.getLoadedPlace().getSource()) { // only proceed if the place this entity is registered in is actually loaded by the handler
//			return this.objectTree.get(entity.getBounds());
			ArrayList<Placeable> p = this.objectTree.get(entity.getBounds());
			System.out.println(p.size());
			return p;
		} else {
			// TODO: finish
			return null;
		}
	}
	
	public ArrayList<Placeable> getEntities(RectangularBound bound) {
		if (bound != null && this.getLoadedPlace() != null) { // only proceed if the place this entity is registered in is actually loaded by the handler
//			System.out.println(this.objectTree.getRoot().getObjects().size());
			return this.objectTree.get(bound);
		} else {
			return null;
		}
	}
	
}
