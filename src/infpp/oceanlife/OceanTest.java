package infpp.oceanlife;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * This is the testclass with the "maim" method to run the OceanLife project.
 * @author Julia Kobus & Jubin Lirawi
 * @since JDK 1.8
 */
public class OceanTest {
	
	/**
	 * This boolean indicates whether or not the program is running.
	 */
	private static boolean running = false;
	
	/**
	 * This method changes he value of the boolean "running".
	 * @return Returns the current state of the parameter "running".
	 */
	protected static boolean runOrNot() {
		running = !running;
		return running;
	}
	
	/**
	 * This is the main method to run the project.
	 * @param args Command line argument
	 */
	public static void main(String[] args) {

		int width = 1024;
		int depth = 640;
		LinkedList<OceanObject> oceanObjects = new LinkedList<OceanObject>();
		
		Prey goldFish1 = new Prey(width - 100, 319);
		Prey goldFish2 = new Prey(70, 120);
		Predator frogfish1 = new Predator(500, 500);
		
		oceanObjects.add(goldFish1);
		oceanObjects.add(goldFish2);
		oceanObjects.add(frogfish1);
		
		Ocean ocean = new Ocean(width, depth, oceanObjects);
		
		System.out.print(ocean);
		System.out.println("Number of objects in list: " + ocean.numberOfOceanObjects());
		
		OceanLifeGUI gui = new OceanLifeGUI("OceanLife", ocean);
		gui.pack();
		gui.setLocation(0, 0);
        gui.setVisible(true);
        
        while(true) {
        	while(running) {
        		ocean.positionOfAttractingPredator();
        		ocean.preyGotEaten();
        		for (OceanObject i : ocean.getOceanObjects()) { 
        			i.move(ocean.getWidth(), ocean.getDepth());
        		}
        		ocean.predatorLightCheck();
    			gui.repaint();
    			
    			try {
    			    TimeUnit.MILLISECONDS.sleep(30);
    			} catch (InterruptedException e) {
    			    System.out.println("Error occured while sleeping.");
    			} catch (Exception e) {
    				System.out.println("Error!");
    				e.printStackTrace();
    			}
        	}
	    	try {
			    TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
			    System.out.println("Error occured while sleeping.");
			}
        }
	}
}