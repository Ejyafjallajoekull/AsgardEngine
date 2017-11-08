package asgardengine.utility.quadtree;

import java.util.ArrayList;

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
	
	public ArrayList<Placeable> get(RectangularBound targetSpace) {
		if (targetSpace != null) {
			ArrayList<Placeable> querry = new ArrayList<Placeable>();
			querry.addAll(this.objects);
			if (this.hasChildren()) {
				int index = this.getIndex(targetSpace);
				if (index >= 0) {
					querry.addAll(this.subNodes[index].get(targetSpace));
				} else if (index == -1) {
					for (QuadNode node : this.subNodes) {
						querry.addAll(node.get(targetSpace));
					}
				}
			}
			return querry;
		} else {
			return null;
		}
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
	public int getIndex(RectangularBound r) {
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

	public QuadNode[] getSubNodes() {
		return subNodes;
	}

	public ArrayList<Placeable> getObjects() {
		return objects;
	}

	public int getDepth() {
		return depth;
	}

	public Quadtree getSource() {
		return source;
	}

	public RectangularBound getBounds() {
		return this.bounds;
	}

}
