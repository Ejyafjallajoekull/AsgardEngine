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
	
	public GameClass(ClassID classID) {
		this.setClassID(classID);
	}
	
	// constructor for recreation of saved objects // no ID necessary as it's encoded in the byte array
	public GameClass(byte[] bytes) {
		this.createFromBytes(bytes);
	}

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
	
}
