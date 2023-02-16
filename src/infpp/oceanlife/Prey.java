package infpp.oceanlife;

/**
 * This class creates the Prey objects.
 * @author Julia Kobus & Jubin Lirawi
 * @serial Serial-Version-UID is 21.
 * @since JDK 1.8
 */
public final class Prey extends OceanObject {
	
	/**
	 * This is the Serialization UID.
	 */
	private static final long serialVersionUID = 21L;
	
	/**
	 * This boolean indicates the state of appearance. It  points out whether one image file or the other should be used.
	 */
	private boolean appearance;

	/**
	 * This integer array indicates the position of the predator to which this prey is attracted.
	 */
	private int[] positionOfAttractingPredator = new int[2];
    
    /**
	 * Constructor to generate the objects. The super constructor is executed, the variable "positionOfAttractingPredator" is initialized with zeros and the variable "appearance" is set randomly.
	 * @param x Gets the parameter "x" to save the width-position in the super class.
	 * @param y Gets the parameter "y" to save the depth-position in the super class.
	 */
	public Prey(int x, int y) { // Constructor
		super(x, y);
		positionOfAttractingPredator[0] = 0;
		positionOfAttractingPredator[1] = 0;
		if ((int) (Math.random() * 2) == 1) {
			appearance = true;
		} else {
			appearance = false;
		}
	}

	/**
	 * This getter method returns the boolean appearance. It overrides the infpp.oceanlife.OceanObject.getappearance method.
	 * @return Returns the boolean appearance.
	 * @see PaintOcean See also the paint method in the PaintOcean class.
	 */
	public boolean getAppearance() {
		return appearance;
	}
	
	/**
	 * Getter method to get the saved position of the attracting predator.
	 * @return Returns the positionOfAttractingPredator array.
	 */
	public int[] getPositionOfAttractingPredator() {
		return positionOfAttractingPredator;
	}
	
	/**
	 * Setter method to set the position of the attracting predator.
	 * @param positionOfAttractingPredator Gets the parameter "positionOfAttractingPredator" and sets it.
	 */
	public void setPositionOfAttractingPredator(int[] positionOfAttractingPredator) {
		this.positionOfAttractingPredator = positionOfAttractingPredator;
	}
	
    /**
     * This method moves the objects through the ocean by giving them a new position, but they can't move backwards. It overrides the infpp.oceanlife.OceanObject.move method.
     * First it is checked whether the objects are at the borders. If so, they just turn around. If not, a direction is randomly choosen. The chance to move straight across is 50%. Finally, if the new coordinates are valid (i.e. within the ocean) they are set.
     * @param oceanWidth This is the width of the ocean.
     * @param oceanDepth This is the depth of the ocean.
     **/
    public void move(int oceanWidth, int oceanDepth) {           
        int x = getPosition()[0];
        int y = getPosition()[1];
        int[] newPosition = new int[2];
        int dx = 0;  // position change in x and y coordinates
        int dy = 0;

        // change viewingDirection if object is at ocean border
        if ( ((x <= 0) && !getViewingDirection())					// false = left 
          || ((x >= oceanWidth - 96) && getViewingDirection()) )	// true = right 
        {
            setViewingDirection(!getViewingDirection());
        } else {
        	if (positionOfAttractingPredator[1] == 0) { // test if the fish is attracted by checking the position of the attracting predator (true if not attracted)   
	            // randomly choose a move until a valid move is found
	            boolean invalidMove = true;
	            do {
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
	                // if the object is facing left, then make dx negative
	                if (!getViewingDirection()) { // remember, false = left
	                    dx = -dx;
	                }
	                // compute new coordinates
	                //int newX = x + dx;
	                int newY = y + dy;
	                if ( //(newX >= 0) && (newX <= oceanWidth) && // check if new location is valid //diese Zeile ist unnötig
	                    (newY >= 0) && (newY <= oceanDepth - 150) ) {
	                      invalidMove = false;
	                }
	            } while (invalidMove);
	        
        	} else {
        		double deltax = positionOfAttractingPredator[0] - getPosition()[0]; // change in x and y coordinates between prey and predator
        		double deltay = positionOfAttractingPredator[1] - getPosition()[1];
        		if (deltax == 0) { // catch a possible error
        			//dx = 0;
        			dy = 1;
        		} else {
        			double tanphi = deltay / deltax;
        			if (tanphi <= 0.414 && tanphi >= -0.414) { // 0.382 = 22.5°
        				dx = 1;
        				//dy = 0;
        				
        			} else {
        				if ((tanphi > 0.414 && tanphi < 2.414) || (tanphi < -0.414 && tanphi > -2.414)) { // 0.707 = 45°
        					dx = 1;
        					dy = 1;
        				} else {
        					if (tanphi >= 2.414 || tanphi <= -2.414) {
        						//dx = 0;
        						dy = 1;
        					}
        				}
        			}
        		}
        		if (deltax > 0) { setViewingDirection(true); }
        		if (deltax < 0) {
        			dx = -dx;
        			setViewingDirection(false);
        			
        		}
				if (deltay < 0) { dy = -dy; }
        	}
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
		return ("Fish: " + super.toString());
	}
}