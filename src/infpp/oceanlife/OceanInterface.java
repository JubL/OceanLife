package infpp.oceanlife;

import java.util.LinkedList;

/**
 * This is the interface for the ocean class.
 * @see Ocean See the Ocean class.
 * @author Julia Kobus & Jubin Lirawi
 * @since JDK 1.8
 */
public interface OceanInterface {
	
	/**
	 * Getter method to get the width of the ocean.
	 * @return Returns the width of the ocean.
	 * @see Ocean See the Ocean class.
	 */
	public int getWidth();
	
	/**
	 * Setter method to set the width of the ocean.
	 * @param width Gets the parameter "width" to set the width.
	 * @see Ocean See the Ocean class.
	 */
	public void setWidth(int width);
	
	/**
	 * Getter method to get the depth of the ocean.
	 * @return Returns the depth of the ocean.
	 * @see Ocean See the Ocean class.
	 */
	public int getDepth();
	
	/**
	 * Setter method to set the depth of the ocean.
	 * @param depth Gets the parameter "depth" to set the depth.
	 * @see Ocean See the Ocean class.
	 */
	public void setDepth(int depth);
	
	/**
	 * Getter method to get the LinkedList with the objects of the ocean.
	 * @return Returns the LinkedList with the objects of the ocean.
	 * @see Ocean See the Ocean class.
	 */
	public LinkedList<OceanObject> getOceanObjects();
	
	/**
	 * Setter method to set the LinkedList with the objects of the ocean.
	 * @param oceanObjects Gets the parameter "oceanObjects" to set the LinkedList with the objects.
	 * @exception IllegalArgumentException Throws an exception if the objects are not within the dimensions of the ocean.
	 * @see Ocean See the Ocean class.
	 */
	public void setOceanObjects(LinkedList<OceanObject> oceanObjects);
	
	/**
	 * Method to add objects to the LinkedList with the objects.
	 * @param newObject Gets the parameter "newObject" to test and add it to the LinkedList.
	 * @exception IllegalArgumentException Throws an exception if the newObject is not within the dimensions of the ocean.
	 * @see Ocean See the Ocean class.
	 */
	public void addOceanObject(OceanObject newObject);
	
	/**
	 * Method to remove an object from the LinkedList and thus from the ocean.
	 * @param index Gets the parameter "index" to remove a specific object from the LinkedList.
	 * @return Returns a boolean whether the removing of an object was successful (true) or not (false).
	 * @see Ocean See the Ocean class.
	 */
	public boolean removeOceanObject(int index);
	
	/**
	 * Method to return the length of the LinkedList.
	 * @return Returns the length of the LinkedList.
	 * @see Ocean See the Ocean class.
	 */
	public int numberOfOceanObjects();
		
	/**
	 * Method to return the index in the LinkedList of the nearest object near the mouse click. Works similar to the method "indexOfNearestObject(PaintOcean paintOcean)". See also the method "indexOfNearestObject(PaintOcean paintOcean)".
	 * @param paintOcean The PaintOcean (extends JPanel) paintOcean has to be given to this method to calculate the correct mouse position even in the case the window / JFrame has been moved.
	 * @param minimumDistance This parameter indicates the minimum distance for an object to have in order to return it's index.
	 * @return Returns the index in the LinkedList as an integer.
	 */
	public int indexOfNearestObject(PaintOcean paintOcean, int minimumDistance);
	
	/**
	 * This method is necessary to implement the interaction between the objects. For each prey all predators are tested how far away they are and the index (in the LinkedList) of the neares predator (for the tested prey) is saved in the variable "indexOfNearestPredator". Furthermore if the distance of the nearest predator to the tested prey is below an specific distance (e.g. 100 Pixel) then the current position of the predator is set in the variable "positionOfAttractingPredator" in the Prey class.   
	 */
	public void positionOfAttractingPredator();
	
	/**
	 * This method implements the interaction between the objects. If a prey is to near to a predator (e.g. 10 Pixel) then the prey is eaten, i.e. it is to be deleted.
	 */
	public void preyGotEaten();
	
	/**
	 * This method is to ensure that the light of the predators turns of whenever there is no prey within the 200 pixel distance of attraction.
	 */
	public void predatorLightCheck();
	
	/**
	 * This method performs the load operation by means of deserialization from this position and writes the new attributes to the ocean.
	 */
	public void loadTheOcean();
	
	/**
	 * Method to print specific information to the screen.
	 * @return Returns a string with the dimensions of the ocean, the objects and their position.
	 * @see Ocean See the Ocean class.
	 */
	public String toString();
}