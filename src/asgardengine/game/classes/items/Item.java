package asgardengine.game.classes.items;

import asgardengine.game.classes.ClassID;
import asgardengine.game.classes.GameClass;

/**
 * The Item class provides some abstract properties for items.
 * 
 * @author Planters
 *
 */
public abstract class Item extends GameClass {

	private String name = null; // the name of the item
	private String description = null; // the description for this item
	
	public Item(ClassID classID) {
		super(classID);
	}

	public Item(byte[] bytes) {
		super(bytes);
	}

	@Override
	public abstract byte[] toBytes();
	@Override
	public abstract void createFromBytes(byte[] bytes) throws IllegalArgumentException;
	@Override
	public abstract byte[] getType();

	/**
	 * Get the name of this item.
	 * 
	 * @return the name of this item as a String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name of this item.
	 * 
	 * @param name - a String to name this item
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the description of this item.
	 * 
	 * @return the description of this item as a String
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Set the description of this item.
	 * 
	 * @param description - a String to name this item
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
