package asgardengine.utility.quadtree;

import java.util.Dictionary;

import asgardengine.game.classes.world.placetree.PlaceTreeCell;

/**
 * The AsgardRootNode class holds cells, manages cell updates and passes objects 
 * to all the downstream children.
 * 
 * @author Planters
 *
 */
public class AsgardRootNode {
	
	private Dictionary<PlaceTreeCell, QuadNode> dict = null;
	private int numberOfSourroundingCells = 0;
	private PlaceTreeCell[][] cellArray = null;
	private AsgardQuadtree source = null;

	public AsgardRootNode(int numberOfSourroundingCells, AsgardQuadtree source) {
		this.source = source;
		if (numberOfSourroundingCells >= 0) {
			this.numberOfSourroundingCells = numberOfSourroundingCells;			
		}
		/* The array is quadratic by definition and the length of its' sides must be odd.
		 * The number of surrounding cells defines the layers of cells surrounding 
		 * the central cell.
		 * Additionally a single, padding layer of null objects is inserted as last layer 
		 * to assure correct insertion of Placeables intersecting unloaded cells, 
		 * which could be loaded at a later point of time, into the root node instead of 
		 * a child node.
		 */
		int arrayLength = 1 + (numberOfSourroundingCells + 1) * 2;
		this.cellArray = new PlaceTreeCell[arrayLength][arrayLength];
	}

}
