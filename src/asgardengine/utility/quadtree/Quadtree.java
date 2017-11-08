package asgardengine.utility.quadtree;

import java.util.ArrayList;

import asgardengine.game.classes.world.Placeable;
import asgardengine.game.classes.world.Position;

/**
 * The Quadtree class creates a two-dimensional proximity tree for Placeable objects.
 * 
 * @author Planters
 *
 */
public class Quadtree {

	private int maxDepth = Integer.MAX_VALUE; // the maximal number of subnodes
	private int nodeThreshold = 256; // the threshold for creation of new nodes
	private QuadNode root = null;
	private RectangularBound space = null;
	
	public Quadtree(int maxDepth, int threshold, Position center, double width, double height) {
		this.maxDepth = maxDepth;
		this.nodeThreshold = threshold;
		this.space = new RectangularBound(center, width, height);
		this.root = new QuadNode(0, this, space);
	}
	
	/**
	 * Reset the Quadtree for repopulation.
	 */
	public void reset() {
		this.root = new QuadNode(0, this, space);
	}
	
	public ArrayList<Placeable> get(RectangularBound targetSpace) {
		return this.root.get(targetSpace);
	}
	
	public void add(Placeable object) {
		this.root.add(object);	
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public int getNodeThreshold() {
		return nodeThreshold;
	}

	public QuadNode getRoot() {
		return root;
	}

}
