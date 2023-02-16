package infpp.oceanlife;

/**
 * This class creates the predator objects.
 * @author Julia Kobus & Jubin Lirawi
 * @serial Serial-Version-UID is 21.
 * @since JDK 1.8
 */
public final class Predator extends OceanObject implements PredatorInterface{
	
	/**
	 * This is the Serialization UID.
	 */
	private static final long serialVersionUID = 22L;
	
	/**
	 * This boolean indicates if the lamp of the predator (frogfish) is illuminated or not.
	 */
	private boolean lightIsIlluminated;
	
	/**
	 * Constructor to generate the objects.
	 * @param x Gets the parameter "x" to save the width-position in the super class.
	 * @param y Gets the parameter "y" to save the depth-position in the super class.
	 */
	public Predator(int x, int y) { // Constructor
		super(x, y);
		lightIsIlluminated = false;
	}
	
	/**
	 * Getter method to get the value of the variable "lightIsIlluminated". Overwrites the method infpp.OceanLife.OceanObject.getLightIsIlluminated.
	 * @return Returns the boolean "lightIsIlluminated".
	 */
	public boolean getLightIsIlluminated() {
		return lightIsIlluminated;
	}
	
	/**
	 * setter mehod toset the value of the boolean "lightIsIlluminated.
	 * @param lightIsIlluminated Set this parameter to the variable "lightIsIlluminated".
	 */
	public void setLightIsIlluminated(boolean lightIsIlluminated) {
		this.lightIsIlluminated = lightIsIlluminated;
	}
	
    /**
     * This method moves the objects through the ocean by giving them a new position, but they can't move backwards. It overrides the infpp.oceanlife.OceanObject.move method.
     * First it is checked whether the objects are at the borders. If so, they just turn around. If not, a second check is performed to determine whether or not the object is in the upper half of the ocean. If so it will swim downwards. If not a direction is randomly chosen. The chance to move straight across is 50%. Finally, if the new coordinates are valid (i.e. within the lower half of the ocean) they are set.
     * @param oceanWidth This is the width of the ocean.
     * @param oceanDepth This is the depth of the ocean.
     **/
	public void move(int oceanWidth, int oceanDepth) {           
	        int x = getPosition()[0];
	        int y = getPosition()[1];
	        int[] newPosition = new int[2];

	        // change viewingDirection if object is at ocean border
	        if ( (x <= 0) && (!getViewingDirection()) // getViewingDirecton == false = left 
	          || (x >= oceanWidth - 96) && (getViewingDirection()) ) // getViewingDirecton == true = right 
	        {
	            setViewingDirection(!getViewingDirection());
	        } else {  
	            // randomly choose a move until a valid move is found
	            boolean invalidMove = true;
	            int dx, dy; //position change in x and y coordinates

	            do {
	            	if (y < oceanDepth / 2) {
	            		dx = (int) (Math.random() * 2);
	            		dy = 1;
	            	} else {
		                // randomly choose a change of location
		                switch ((int) (Math.random() * 8)) {
		                    case 0:  dx = 0; dy =  1; break; // straight down
		                    case 1:  dx = 1; dy =  1; break; // diagonal down 
		                    case 2:  dx = 1; dy =  0; break; // straight across
		                    case 3:  dx = 1; dy =  0; break; // straight across
		                    case 4:  dx = 1; dy =  0; break; // straight across
		                    case 5:  dx = 1; dy =  0; break; // straight across
		                    case 6:  dx = 1; dy = -1; break; // diagonal up
		                    default: dx = 0; dy = -1; break; // straight up
		                }
	            	}
	    
	                // if the object is facing left, then make dx negative
	                if (!(getViewingDirection())) { // getViewingDirection() == false // remember, false = left
	                    dx = -dx;
	                }
	                
	                // compute new coordinates
	                int newX = x + dx;
	                int newY = y + dy;
	                
	                // check if new location is valid
	                if ((newX >= 0) && (newX <= oceanWidth) && (newY >= 0) && (newY <= oceanDepth)) {
	                      invalidMove = false;
	                }
	            } while(invalidMove);
	        
	            // move Fish
	            newPosition[0] = x + dx;
	            newPosition[1] = y + dy;
	            setPosition(newPosition);
	        }
	    }
		
		/**
		 * Method to print specific information to the screen. It overrides the java.lang.Object.toString method.
		 * @return Returns a string with the objects name and the position data from the super class.
		 */
		public String toString() {
			return ("Frogfish: " + super.toString());
		}
}


