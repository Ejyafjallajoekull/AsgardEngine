package asgardengine.utility.quadtree;

import java.awt.Rectangle;
import java.util.ArrayList;

import asgardengine.game.classes.world.Placeable;

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
	private Rectangle bounds = null;

	
	public QuadNode(int depth, Quadtree source, Rectangle space) {
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
			int width = this.bounds.width / 2;
			int heigth = this.bounds.height / 2;
			subNodes[0] = new QuadNode(depth, this.source, new Rectangle(this.bounds.x, this.bounds.y, width, heigth));
			subNodes[1] = new QuadNode(depth, this.source, new Rectangle(this.bounds.x, (int) this.bounds.getCenterY(), width, heigth));
			subNodes[2] = new QuadNode(depth, this.source, new Rectangle((int) this.bounds.getCenterX(), this.bounds.y, width, heigth));
			subNodes[3] = new QuadNode(depth, this.source, new Rectangle((int) this.bounds.getCenterX(), (int) this.bounds.getCenterY(), width, heigth));
			ArrayList<Placeable> cache = this.objects; // cache the old list
			this.objects = new ArrayList<Placeable>(source.getNodeThreshold() + 1); // create a new one
			for (int i = 0; i < cache.size() ;i++) { // add all old elements to the new list and subnodes
				this.add(cache.get(i));
			}
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
	
	public ArrayList<Placeable> get(Rectangle targetSpace) {
		if (targetSpace != null) {
			ArrayList<Placeable> querry = new ArrayList<Placeable>();
			querry.addAll(this.objects);
			if (this.hasChildren()) {
				int index = this.getIndex(targetSpace);
				if (index >= 0) {
					querry.addAll(this.subNodes[index].get(targetSpace));
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
	private int getIndex(Rectangle r) {
		int index = -1;
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

	public Rectangle getBounds() {
		return bounds;
	}

}
