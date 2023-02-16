package infpp.oceanlife;

import java.io.Serializable;

/**
 * This abstract class serves as a super class for creatable objects.
 * @author Julia Kobus & Jubin Lirawi
 * @serial Serial-Version-UID is 20.
 * @since JDK 1.8
 */
public abstract class OceanObject implements Serializable {

	/**
	 * This is the Serialization UID.
	 */
	private static final long serialVersionUID = 20L;
    
    /**
	 * Array to save the objects position, measured in pixels.
	 */
	private int[] position = new int[2];
	
	/**
	 * This is the direction the object is facing. True means it's facing right, i.e. moving towards the rightside of the ocean.
	 */
	private boolean viewingDirection;

	/**
	 * Constructor to generate the objects. They parameters "x" and "y" are set as position and the viewing direction is set randomly.
	 * @param x Gets the parameter "x" to save the width-position.
	 * @param y Gets the parameter "y" to save the depth-position.
	 */
	public OceanObject(int x, int y) { // Constructor
		checkPosition(x, y);
		this.position[0] = x;
		this.position[1] = y;
		if ((int) (Math.random() * 2) < 1) {
			this.viewingDirection = true;
		} else {
			this.viewingDirection = false;
		}
	}
	
	/**
	 * Getter method to get the position of an object.
	 * @return Returns the position array.
	 */
	public final int[] getPosition() {
		return position;
	}
	
	/**
	 * Setter method to set the position of an object.
	 * @param position Gets the parameter "position" to set the position.
	 */
	public final void setPosition(int[] position) {
		this.position = position;
	}
	
	/**
	 * Setter method to set the position of an attracting object (i.e. the position of an predator).
	 * @param positionOfAttractingPredator This parameter contains the position of an othe object.
	 */
	public void setPositionOfAttractingPredator(int[] positionOfAttractingPredator) {}

	/**
	 * This method is to be overwritten in the Predator class. Here it only returns "false".
	 * @return Always returns "false".
	 */
	public boolean getLightIsIlluminated() {
		return false;
	}
	
	/**
	 * This method is to be overwritten in the Predator class. Here it dose nothing.
	 * @param lightIsIlluminated This is the parameter iven to this method.
	 */
	public void setLightIsIlluminated(boolean lightIsIlluminated) {}
	
	/**
	 * Getter method to get the boolean witch indicates the state of appearance. In this case it is always "true". This method exists due to the fact that it has to be overwritten in an extended class.
	 * @return true
	 */
	public boolean getAppearance() {
		return true;
	}
	
	/**
     * Get the direction of this object.
     * @return this fish's direction
     **/
    public final boolean getViewingDirection() {           
        return viewingDirection;
    }
    
    /**
     * Setter method to set the viewingDirection of an object.
     * @param viewingDirection This parameter is the one to be set as new "viewingDirection".
     */
    public final void setViewingDirection(boolean viewingDirection) {
    	this.viewingDirection = viewingDirection;
    }
	
	/**
	 * Method to check that the position data is valid.
	 * @param x Gets the parameter "x" to check it.
	 * @param y Gets the parameter "y" to check it.
	 * @exception IllegalArgumentException Throws an exception if the objects to create have a negative position.
	 */
	private void checkPosition(int x, int y) {
		try {
			if (x < 0 || y < 0) {
				throw new IllegalArgumentException("Objekt befindet sich außerhalb des Aquariums.");
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
		
	/**
     * This method moves the objects through the ocean by giving them a new position, but they can't move backwards.
     * @param oceanWidth This is the width of the ocean.
     * @param oceanDepth This is the depth of the ocean.
     **/
    public void move(int oceanWidth, int oceanDepth) {}
	
	/**
	 * Method to print specific information to the screen. It overrides the java.lang.Object.toString method.
	 * @return Returns a string with the position and direction data.
	 */
	public String toString() {
		return ("position: x: " + position[0] + "; y: " + position[1] + "; direction: " + viewingDirection);
	}
}