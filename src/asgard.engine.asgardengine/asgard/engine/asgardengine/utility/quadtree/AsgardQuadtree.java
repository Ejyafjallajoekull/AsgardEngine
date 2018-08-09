package asgard.engine.asgardengine.utility.quadtree;

import asgard.engine.asgardengine.game.classes.world.Placeable;
import asgard.engine.asgardengine.game.classes.world.placetree.PlaceTreeCell;
import asgard.engine.asgardengine.game.handler.Updatable;
import asgard.engine.asgardengine.game.handler.UpdateScheduler;

/**
 * The AsgardQuadtree class provides a Quadtree implementation optimised for dynamic 
 * loading of cells and updating of moving objects without the necessity to repopulate the
 * whole quadtree.
 * 
 * @author Planters
 *
 */
public class AsgardQuadtree implements Updatable{
	
	private int maxDepth = Integer.MAX_VALUE; // the maximal number of subnodes
	private int nodeThreshold = 256; // the threshold for creation of new nodes
	private AsgardRootNode root = null;
	private Placeable center = null;
//	private Position centralPos = null; // the previous position of the central object
	private int currentDepth = 0; // the maximum depth of its' Quadnode
	private int surroundingCells = 0;

	/**
	 * Create an AsgardQuadtree with cell specific 2D-boundaries.
	 * All objects of the cell containing the central object and all 
	 * layers of surrounding cells are loaded into this tree.
	 * 
	 * @param maxDepth - the maximum depth of AsgardQuadNodes
	 * @param threshold - the object threshold for each AsgardQuadNode to split into four smaller ones
	 * @param center - the central object, which position determines the central cell
	 * @param surroundingCells - layers of loaded cells surrounding the central cell
	 */
	public AsgardQuadtree(int maxDepth, int threshold, Placeable center, int surroundingCells) {
		this.maxDepth = maxDepth;
		this.nodeThreshold = threshold;
		this.center = center;
		this.surroundingCells = surroundingCells;
		UpdateScheduler.registerForUpdate(this); // update this event with every frame
	}
	
	

	@Override
	public void update() {
		if (this.center != null && this.center.getPlace() != null) {
			PlaceTreeCell[][] cells = this.center.getPlace().getCellTree().get2D(this.center.getPosition(), this.surroundingCells);
			for (int x = 0; x < cells.length; x++) {
				for (int y = 0; x < cells[x].length; y++) {
					
				}
			}
		}
		
	}
	

}
