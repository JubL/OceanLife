package infpp.oceanlife;

import java.util.LinkedList;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.io.Serializable;

/**
 * This is the class which creates the ocean.
 * @author Julia Kobus & Jubin Lirawi
 * @serial Serial-Version-UID is 10.
 * @since JDK 1.8
 */
public class Ocean implements OceanInterface, Serializable {
	
	/**
	 * This is the Serialization UID.
	 */
	private static final long serialVersionUID = 10L;
    
    /**
	 * Variable to save the oceans width, measured in pixels.
	 */
	private int width;
	
	/**
	 * Variable to save the oceans depth, measured in pixels.
	 */
	private int depth;
	
	/**
	 * LinkedList to save the objects which swim in the ocean.
	 */
	private LinkedList<OceanObject> oceanObjects;
	
	/**
	 * Constructor to generate the ocean.
	 * @param width Gets the parameter "width" to set the width of the ocean.
	 * @param depth Gets the parameter "depth" to set the depth of the ocean.
	 * @param oceanObjects Gets the LinkedList "oceanObjects" to set it.
	 * @exception IllegalArgumentException Throws an exception if the width or the depth of the ocean are not positive.
	 */
	public Ocean(int width, int depth, LinkedList<OceanObject> oceanObjects) { // Constructor
		
		try {
			if (width <= 0 || depth <= 0) throw new IllegalArgumentException("Dimensionen des Aquariums m¸ssen positiv sein.");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
			
		this.width = width;
		this.depth = depth;
		this.oceanObjects = oceanObjects;
	}
	
	/**
	 * Getter method to get the width of the ocean.
	 * @return Returns the width of the ocean.
	 */
	public final int getWidth() {
		return width;
	}

	/**
	 * Setter method to set the width of the ocean.
	 * @param width Gets the parameter "width" to set the width.
	 */
	public final void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Getter method to get the depth of the ocean.
	 * @return Returns the depth of the ocean.
	 */
	public final int getDepth() {
		return depth;
	}

	/**
	 * Setter method to set the depth of the ocean.
	 * @param depth Gets the parameter "depth" to set the depth.
	 */
	public final void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * Getter method to get the LinkedList with the objects of the ocean.
	 * @return Returns the LinkedList with the objects of the ocean.
	 */
	public final LinkedList<OceanObject> getOceanObjects() {
		return oceanObjects;
	}

	/**
	 * Setter method to set the LinkedList with the objects of the ocean.
	 * @param oceanObjects Gets the parameter "oceanObjects" to set the LinkedList with the objects.
	 * @exception IllegalArgumentException Throws an exception if the objects are not within the dimensions of the ocean.
	 */
	public final void setOceanObjects(LinkedList<OceanObject> oceanObjects) {
		try {
			for (OceanObject i : oceanObjects) {
				if (i.getPosition()[0] < 0 || i.getPosition()[0] > width || i.getPosition()[1] < 0 || i.getPosition()[1] > depth) {
					throw new IllegalArgumentException("Mindestens eines der Objekte befindet sich auﬂerhalb des Aquariums.");
				}
				this.oceanObjects = oceanObjects;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to add objects to the LinkedList with the objects.
	 * @param newObject Gets the parameter "newObject" to test and add it to the LinkedList.
	 * @exception IllegalArgumentException Throws an exception if the newObject is not within the dimensions of the ocean.
	 */
	public final void addOceanObject(OceanObject newObject) {
		try {
			if (newObject == null) {
				throw new NullPointerException("NullPointerException aufgetreten.");
				}
			if (newObject.getPosition()[0] < 0 || newObject.getPosition()[0] > width || newObject.getPosition()[1] < 0 || newObject.getPosition()[1] > depth) {
				throw new IllegalArgumentException("Objekt befindet sich auﬂerhalb des Aquariums.");
			}
			if (newObject != null) {
				this.oceanObjects.add(newObject);
			}
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
	}	
	
	/**
	 * Method to remove an object from the LinkedList and thus from the ocean.
	 * @param index Gets the parameter "index" to remove a specific object from the LinkedList.
	 * @exception IllegalArgumentException Throws an exception if the index is greater than the length of the LinkedList or less than 0.
	 * @return Returns true if the removing operation was successful.
	 */
	public final boolean removeOceanObject(int index) { 
		if (index > (numberOfOceanObjects() - 1)) { return false; }
		if (index < 0) { return false; }
		oceanObjects.remove(index);
		System.out.println("Number of objects in list: " + numberOfOceanObjects());
		return true;
	}	
	
	/**
	 * This method is to return the length of the LinkedList in which the OceanObjects are in.
	 * @return Returns the length of the LinkedList.
	 */
	public final int numberOfOceanObjects() {
		return this.oceanObjects.size();
	}
	
	/**
	 * Method to return the index in the LinkedList of the nearest object near the mouse click. Works similar to the method "indexOfNearestObject(PaintOcean paintOcean)". See also the method "indexOfNearestObject(PaintOcean paintOcean)".
	 * @param paintOcean The PaintOcean (extends JPanel) paintOcean has to be given to this method to calculate the correct mouse position even in the case the window / JFrame has been moved.
	 * @param minimumDistance This parameter indicates the minimum distance for an object to have in order to return it's index.
	 * @return Returns the index in the LinkedList as an integer.
	 */
	public final int indexOfNearestObject(PaintOcean paintOcean, int minimumDistance) {
		if (numberOfOceanObjects() == 0) { return -1; }
		int indexOfNearestObject = -1;
		PointerInfo mouse = MouseInfo.getPointerInfo();
		for (OceanObject i : oceanObjects) {
			double distance = Math.sqrt(Math.pow(((double) i.getPosition()[0] - (mouse.getLocation().getX() - 8L - paintOcean.getLocation().getX())), 2) 
					+ Math.pow(((double) i.getPosition()[1] - (mouse.getLocation().getY() - 57L - paintOcean.getLocation().getY())), 2));
			if (distance < minimumDistance) {
				indexOfNearestObject = oceanObjects.indexOf(i);
				minimumDistance = (int) distance;
			}
		}
		return indexOfNearestObject;
	}
	
	/**
	 * This method returns the distance between two objects in Pixel. It is intendet to determine the distance between a prey and a predator.
	 * @param preyObject This is the prey object given to this method.
	 * @param predatorObject This is the predator object given to this method.
	 * @return Returns the distance in Pixel as an integer.
	 */
	private int distanceBetweenObjects(OceanObject preyObject, OceanObject predatorObject) {
			return ((int) Math.sqrt(Math.pow((preyObject.getPosition()[0] - predatorObject.getPosition()[0]), 2) 
					+ Math.pow((preyObject.getPosition()[1] - predatorObject.getPosition()[1]), 2)));
	}
	
	/**
	 * This method is necessary to implement the interaction between the objects. For each prey all predators are tested how far away they are and the index (in the LinkedList) of the neares predator (for the tested prey) is saved in the variable "indexOfNearestPredator". Furthermore if the distance of the nearest predator to the tested prey is below an specific distance (e.g. 100 Pixel) then the current position of the predator is set in the variable "positionOfAttractingPredator" in the Prey class.   
	 */
	public final void positionOfAttractingPredator() {
		for (OceanObject preyObject : oceanObjects) {
			if (!(preyObject instanceof PredatorInterface)) {
				double minimumDistance = 9999999999999L;
				int indexOfNearestPredator = -1;
				for (OceanObject predatorObject : oceanObjects) {
					if (predatorObject instanceof PredatorInterface) {
						int distance = distanceBetweenObjects(preyObject, predatorObject);
						if (distance < minimumDistance) {
							minimumDistance = distance;
							indexOfNearestPredator = oceanObjects.indexOf(predatorObject);
						}
					}
				}
				if (minimumDistance < 200) { // 200 is the distance in which a prey is attracted
					int[] position = new int[2];
					position[0] = oceanObjects.get(indexOfNearestPredator).getPosition()[0];
					position[1] = oceanObjects.get(indexOfNearestPredator).getPosition()[1];
					preyObject.setPositionOfAttractingPredator(position);
					oceanObjects.get(indexOfNearestPredator).setLightIsIlluminated(true);
				}
			}
		}
		
	}
		
	/**
	 * This method implements the interaction between the objects. If a prey is to near to a predator (e.g. 10 Pixel) then the prey is eaten, i.e. it is to be deleted.
	 */
	public final void preyGotEaten() {
		int[] indicesOfEatenPreys = new int[30];
		for (int i = 0; i < 30; i++) {
			indicesOfEatenPreys[i] = -1;
		}
		int[] indicesOfEatingPredators = new int[30];
		for (int i = 0; i < 30; i++) {
			indicesOfEatingPredators[i] = -1;
		}
		int indexOfPreyArray = 29;
		int indexOfPredatorArray = 29;
		for (OceanObject predatorObject : oceanObjects) {
			if (predatorObject instanceof PredatorInterface) {
				for (OceanObject preyObject : oceanObjects) {
					if (!(preyObject instanceof PredatorInterface)) {
						if (distanceBetweenObjects(preyObject, predatorObject) < 10) {
							indicesOfEatenPreys[indexOfPreyArray--] = oceanObjects.indexOf(preyObject);
							indicesOfEatingPredators[indexOfPredatorArray--] = oceanObjects.indexOf(predatorObject);
						}
					}
				}
			}
		}
		for (int i = 0; i < 30; i++) {
			if (indicesOfEatingPredators[i] != -1) {
				oceanObjects.get(indicesOfEatingPredators[i]).setLightIsIlluminated(false);
			}
		}
		for (int i = 0; i < 30; i++) {
			if (indicesOfEatenPreys[i] != -1) {
				removeOceanObject(indicesOfEatenPreys[i]);
			}
		}
	}
	
	/**
	 * This method is to ensure that the light of the predators turns of whenever there is no prey within the 200 pixel distance of attraction.
	 */
	public final void predatorLightCheck() {
		for (OceanObject predatorObject : oceanObjects) {
			if (predatorObject instanceof PredatorInterface) {
				double minimumDistance = 9999999999999L;
				for (OceanObject preyObject : oceanObjects) {
					if (!(preyObject instanceof PredatorInterface)) {
						int distance = distanceBetweenObjects(preyObject, predatorObject);
						if (distance < minimumDistance) {
							minimumDistance = distance;
						}
					}
				}
				if (minimumDistance > 200) {
					predatorObject.setLightIsIlluminated(false);
				}
			}
		}
	}
	
	/**
	 * This method performs the loading operation by means of deserialization from this position and writes the new attributes to the ocean.
	 */
	public final void loadTheOcean() {
		Ocean loadingOcean = Deserialization.deserialize(this.width, this.depth, this.oceanObjects);
		
		this.width = loadingOcean.getWidth();
		this.depth = loadingOcean.getDepth();
		this.oceanObjects = loadingOcean.getOceanObjects();
	}

	/**
	 * Method to print specific information to the screen. It overrides the java.lang.Object.toString method.
	 * @return Returns a string with the dimensions of the ocean, the objects and their position.
	 */
	public final String toString() {
		String s = "Ocean: width: " + width + " depth: " + depth + "\n" + "Objects:\n";
		for (OceanObject i : oceanObjects) {
			s = s + i.toString() + "\n";
		}
		return s;
	}
}