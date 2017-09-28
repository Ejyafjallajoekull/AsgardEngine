package asgardengine.game.classes;

/**
 * The abstract GameClass class specifies the basic behaviour of all entity types
 * in the engine.
 * 
 * @author Planters
 *
 */
public abstract class GameClass {

	private ClassID classID = null; // a unique identifier
	private String descriptiveName = null; // a name displayed by editors
	
	public GameClass(ClassID classID) {
		this.setClassID(classID);
	}
	
	// constructor for recreation of saved objects // no ID necessary as it's encoded in the byte array
	public GameClass(byte[] bytes) {
//		this.createFromBytes(bytes); // not part of the super constructor to banish the need for uninitialised variables as super constructors get executed before initialisation of subclass variables, so init overwrites set values
	}

	/**
	 * Export the state of this object to an array of bytes.
	 * 
	 * @return an array of bytes containing all information to rebuild the object
	 */
	public abstract byte[] toBytes(); // export all relevant data to an array of bytes
	/**
	 * Restore the status of a GameClass object from an array of bytes.
	 * 
	 * @param bytes - the byte array containing all information to instantiate this class.
	 * @return true if the instantiation was successful
	 */
	public abstract void createFromBytes(byte[] bytes) throws IllegalArgumentException; // recreate the object from saved data
	/**
	 * Get the type of the GameClass subclass. Basically this is just a binary subclass identifier.
	 * 
	 * @return an array of bytes containing a unique identifier for this GameClass subclass
	 */
	public abstract byte[] getType();
	
	/**
	 * Get the unique identifier for this GameClass Object.
	 * 
	 * @return the unique ClassID of this object
	 */
	public ClassID getClassID() {
		return this.classID;
	}

	/**
	 * Set the unique identifier for this GameClass Object to be the specified ClassID.
	 * Warning: If a consistent relation of object and ID should be established, it's
	 * not advised to use this function uncoutiously.
	 * 
	 * @param classID - a unique ClassID for this object 
	 */
	public void setClassID(ClassID classID) {
		this.classID = classID;
	}

	/**
	 * Get a short descriptive Name for this class.
	 * 
	 * @return a short, descriptive String for this class
	 */
	public String getDescriptiveName() {
		return this.descriptiveName;
	}

	/**
	 * Set a short, descriptive String for this class.
	 * 
	 * @param descriptiveName - the String to be set
	 */
	public void setDescriptiveName(String descriptiveName) {
		this.descriptiveName = descriptiveName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof GameClass) {
			return ((GameClass) obj).getClassID().equals(this.getClassID());
		}
		return false;
	}
	
}
