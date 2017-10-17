package asgardengine.utility.quadtree;

import java.awt.Rectangle;
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
	private Rectangle space = null;
	
	public Quadtree(int maxDepth, int threshold, Position center, Position topLeft) {
		this.maxDepth = maxDepth;
		this.nodeThreshold = threshold;
		Position diff = Position.subtract(center, topLeft);
		this.space = new Rectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) (diff.getX()*2.0d), (int) (diff.getY()*2.0d));
		this.root = new QuadNode(0, this, space);
	}
	
	/**
	 * Reset the Quadtree for repopulation.
	 */
	public void reset() {
		this.root = new QuadNode(0, this, space);
	}
	
	public ArrayList<Placeable> get(Rectangle targetSpace) {
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
