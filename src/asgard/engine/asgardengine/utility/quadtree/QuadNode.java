package asgard.engine.asgardengine.utility.quadtree;

import java.util.ArrayList;
import java.util.Arrays;

import asgardengine.game.classes.world.Placeable;
import asgardengine.game.classes.world.Position;

/**
 * The QuadNode class represents a node of a QuadTree.
 * 
 * @author Planters
 *
 */
public class QuadNode {
	
	private QuadNode[] subNodes = null;
	private ArrayList<Placeable> objects = null;
	private int depth = 0;
	private Quadtree source = null;
	private RectangularBound bounds = null;

	
	/** 
	 * Create a QuadNode of the given depth in a source Quadtree with a defined bound.
	 * 
	 * @param depth - the tree depth
	 * @param source - the source QuadTree this QuadNode belongs to
	 * @param space - the boundaries of this node
	 */
	public QuadNode(int depth, Quadtree source, RectangularBound space) {
		this.depth = depth;
		this.source = source;
		this.bounds = space;
		this.objects = new ArrayList<Placeable>(source.getNodeThreshold() + 1);
	}
	
	/**
	 * Split this QuadNode into four child nodes.
	 */
	public void split() {
		if (!this.hasChildren()) {
			subNodes = new QuadNode[4];
			int depth = this.depth + 1;
			double width = this.bounds.getWidth() * 0.5d;
			double height = this.bounds.getHeight() * 0.5d;
			subNodes[0] = new QuadNode(depth, this.source, new RectangularBound(new Position(this.bounds.getCenter().getX() - width*0.5d, this.bounds.getCenter().getY() + height*0.5d, this.bounds.getCenter().getZ()), width, height));
			subNodes[1] = new QuadNode(depth, this.source, new RectangularBound(new Position(this.bounds.getCenter().getX() + width*0.5d, this.bounds.getCenter().getY() + height*0.5d, this.bounds.getCenter().getZ()), width, height));
			subNodes[2] = new QuadNode(depth, this.source, new RectangularBound(new Position(this.bounds.getCenter().getX() + width*0.5d, this.bounds.getCenter().getY() - height*0.5d, this.bounds.getCenter().getZ()), width, height));
			subNodes[3] = new QuadNode(depth, this.source, new RectangularBound(new Position(this.bounds.getCenter().getX() - width*0.5d, this.bounds.getCenter().getY() - height*0.5d, this.bounds.getCenter().getZ()), width, height));
//			ArrayList<Placeable> cache = this.objects; // cache the old list
//			this.objects = new ArrayList<Placeable>(source.getNodeThreshold() + 1); // create a new one
//			for (int i = 0; i < cache.size() ;i++) { // add all old elements to the new list and subnodes
//				this.add(cache.get(i));
//			}
		}
	}
	
	/**
	 * Add the specified Placeable to this node and split it up if the nodes' threshold is reached.
	 * 
	 * @param placeable - the Placeable to add to this node
	 */
	public void add(Placeable placeable) {
		if (placeable != null) {
			if (this.hasChildren()) {
				int index = this.getIndex(placeable.getBounds());
				if (index < 0) { // add the object to this node if it belongs to more than one sub node
					this.objects.add(placeable);
				} else { // hand over to the specific sub node
					this.subNodes[index].add(placeable);
				}
			} else {
				this.objects.add(placeable);
				if (this.objects.size() > this.source.getNodeThreshold() && this.source.getMaxDepth() > this.depth) {
					this.split();
				}
			}
		}
	}
	
	/** 
	 * Get all objects currently intersecting the target space.
	 * 
	 * @param targetSpace - the space to check for object intersection
	 * @return all objects intersecting the target space as an ArrayList of Placeables
	 */
	public ArrayList<Placeable> get(RectangularBound targetSpace) {
		if (targetSpace != null) {
			ArrayList<Placeable> querry = new ArrayList<Placeable>();
			querry.addAll(this.objects);
			if (this.hasChildren()) {
				int index = this.getIndex(targetSpace);
				if (index >= 0) { // intersection with a single sub node
					// pass the querry list to be filled -> do not create unnecessary copies of the same task
					querry.addAll(this.subNodes[index].get(targetSpace, querry));
				} else if (index == -1) { // intersection with more than one sub node
					for (QuadNode node : this.subNodes) {
						// pass the querry list to be filled -> do not create unnecessary copies of the same task
						querry.addAll(node.get(targetSpace, querry));
					}
				}
			}
			return querry;
		} else {
			return null;
		}
	}
	
	// helper function to minimise redundant list copies by recursive get calls
	private ArrayList<Placeable> get(RectangularBound targetSpace, ArrayList<Placeable> targetList) {
		if (targetSpace != null) {
			targetList.addAll(this.objects);
			if (this.hasChildren()) {
				int index = this.getIndex(targetSpace);
				if (index >= 0) { // intersection with a single sub node
					targetList.addAll(this.subNodes[index].get(targetSpace, targetList));
				} else if (index == -1) { // intersection with more than one sub node
					for (QuadNode node : this.subNodes) {
						targetList.addAll(node.get(targetSpace, targetList));
					}
				}
			}
		}
		return targetList;
	}
	
	/**
	 * Check if this is the node contains sub nodes.
	 * 
	 * @return true if this QuadNode has sub nodes
	 */
	public boolean hasChildren() {
		if (this.subNodes != null) {
			return true;
		}
		return false;
	}
	
	// get the sub node index of the specified rectangle
	private int getIndex(RectangularBound r) {
		int index = -2;
		if (this.hasChildren()) {
			for (int i = 0; i < this.subNodes.length; i++) {
				if (this.subNodes[i].getBounds().intersects(r)) {
					if (index < 0) {
						index = i;
					} else { // intersects more than one sub node
						index = -1;
						break;
					}
				}
			}
		}
		return index;
	}

	/**
	 * Get all sub nodes of this QuadNode.
	 * 
	 * @return all four sub nodes of this node as array of QuadNodes
	 */
	public QuadNode[] getSubNodes() {
		return subNodes;
	}

	/**
	 * Get the objects held by this QuadNode. 
	 * Objects contained by its' child nodes are not returned.
	 * 
	 * @return the objects held by this QuadNode as ArrayList of Placeables
	 */
	public ArrayList<Placeable> getObjects() {
		return objects;
	}
	
	/**
	 * Get the objects held by this QuadNode and all its' child nodes. 
	 * 
	 * @return the objects held by this QuadNode and its' children as ArrayList of Placeables
	 */
	public ArrayList<Placeable> getObjectsDeep() {
		return this.get(this.getBounds());
	} 

	/**
	 * Get the current depth in the source Quadtree this node is residing at.
	 * 
	 * @return the current depth of this node
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Get the source Quadtree of this QuadNode
	 * 
	 * @return the source Quadtree of this node
	 */
	public Quadtree getSource() {
		return source;
	}

	/**
	 * Get the boundaries of this QuadNode.
	 * 
	 * @return the boundaries of this QuadNode as RectangularBound
	 */
	public RectangularBound getBounds() {
		return this.bounds;
	}
	
	@Override
	// does not depend on the source tree or the depth in the tree
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof QuadNode) {
			QuadNode node = (QuadNode) obj;
			if (node.getBounds().equals(this.getBounds()) && node.getObjects().equals(this.objects) && Arrays.equals(node.getSubNodes(), this.getSubNodes())) {
				return true;
			}
		}
		return false;
	}

}
