package asgard.engine.asgardengine.utility.quadtree;

import java.util.ArrayList;

import asgard.engine.asgardengine.game.classes.world.Placeable;
import asgard.engine.asgardengine.game.classes.world.Position;

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
//	private int currentDepth = 0; // the maximum depth of its' Quadnode
	
	/**
	 * Create a Quadtree with specific 2D-boundaries.
	 * 
	 * @param maxDepth - the maximum depth of QuadNodes
	 * @param threshold - the object threshold for each QuadNode to split into four smaller ones
	 * @param center - the center of the Quadtrees' boundaries
	 * @param width - the width of the Quadtrees' boundaries
	 * @param height - the height of the Quadtrees' boundaries
	 */
	public Quadtree(int maxDepth, int threshold, Position center, double width, double height) {
		this.maxDepth = maxDepth;
		this.nodeThreshold = threshold;
		this.space = new RectangularBound(center, width, height);
		this.root = new QuadNode(0, this, space);
	}
	
	/**
	 * Reset the Quadtree for repopulation.
	 */
	public void reset(RectangularBound bound) {
		if (bound != null) {
			this.space = bound.clone();
		}
		this.root = new QuadNode(0, this, space);
	}
	
	/**
	 * Get all objects intersecting the target space.
	 * 
	 * @param targetSpace - the space to check object intersection against
	 * @return all objects intersecting the target space as ArrayList of Placeables
	 */
	public ArrayList<Placeable> get(RectangularBound targetSpace) {
		return this.root.get(targetSpace);
	}
	
	/**
	 * Add a Placeable to the Quadtree.
	 * 
	 * @param object - the Placeable to add to the Quadtree
	 */
	public void add(Placeable object) {
		this.root.add(object);	
	}

	/**
	 * Get the maximum depth this Quadtree can reach.
	 * If a QuadNode of this depth is created it will not split up any further 
	 * into sub nodes.
	 * 
	 * @return the max depth as an integer
	 */
	public int getMaxDepth() {
		return maxDepth;
	}

	/**
	 * Get the threshold of objects in a QuadNode causing it to split 
	 * into four child nodes.
	 * 
	 * @return the splitting threshold for each QuadNode of this Quadtree
	 */
	public int getNodeThreshold() {
		return nodeThreshold;
	}

	/**
	 * Get the root QuadNode of this Quadtree.
	 * This node contains all other child nodes of this tree.
	 * 
	 * @return the root QuadNode of this QuadTree
	 */
	public QuadNode getRoot() {
		return root;
	}

}
