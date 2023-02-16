package infpp.oceanlife;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * This is the class which draws the oceanarea.
 * @author Julia Kobus & Jubin Lirawi
 * @serial Serial-Version-UID is 42.
 * @since JDK 1.8
 */
public class PaintOcean extends JPanel {
	
	/**
	 * This is the Serialization UID.
	 */
	private static final long serialVersionUID = 42L;

	/**
	 * This is the buffered Image to display an object.
	 */
	private BufferedImage displayedFish;
	
	/**
	 * This is the buffered Image to load the preyfish number one.
	 */
    private BufferedImage preyFish1;
    
    /**
     * This is the buffered Image to load the preyfish number two.
     */
    private BufferedImage preyFish2;
    
    /**
     * This is the buffered Image to load the frogfish in it's normal state.
     */
    private BufferedImage predatorFish;
    
    /**
     * This is the buffered Image to load the frogfish with it's lightbulb illuminated.
     */
    private BufferedImage predatorFishIlluminated;
    
    /**
     * Our ocean object.
     */
    private Ocean ocean;
    
    /**
     * Constructor to generate the ocean area.
     * @param ocean Takes the ocean for further use.
     * @exception NullPointerException Throws an exception if the size of the ocean is null.
     * @exception IOException Throws an exception if an error occurs during reading of the image file.
     * @exception IllegalArgumentException Throws an exception if input is null.
     */
    public PaintOcean(Ocean ocean) { // Constructor
    	super();
    	
    	this.ocean = ocean;
    	
    	this.setPreferredSize(new Dimension(this.ocean.getWidth(), this.ocean.getDepth()));
    	
    	try {
        	this.preyFish1 = ImageIO.read(new File("prey1.png"));
        	this.preyFish2 = ImageIO.read(new File("prey2.png"));
        	this.predatorFish = ImageIO.read(new File("predator1.png"));
        	this.predatorFishIlluminated = ImageIO.read(new File("predator2.png"));
        } catch (IOException e) {
			e.printStackTrace();
        } catch (IllegalArgumentException e) {
			e.printStackTrace();
        }
    }

    /**
     * Makes the ocean blue and draws the objects in the ocean. It overrides the javax.swing.JComponent.paint method.
     * @param g Graphical object to be drawn.
     */
    public final void paint(Graphics g) {
    	Dimension dim = new Dimension(this.ocean.getWidth(), this.ocean.getDepth());
    	this.setPreferredSize(dim);
    	
    	g.setColor(Color.blue);
    	g.fillRect(0, 0, dim.width, dim.height);
    	
    	Graphics2D g2 = (Graphics2D) g;
    	for (OceanObject i : ocean.getOceanObjects()) {
    		if (i instanceof PredatorInterface) {
    			if (i.getLightIsIlluminated()) {
    				displayedFish = predatorFishIlluminated;
    			} else {
    				displayedFish = predatorFish;
    			}
	    	} else {
		    	if (i.getAppearance()) {
		    		displayedFish = preyFish1;
		    	} else {
		    		displayedFish = preyFish2;
		    	}
    		}
    		if (i.getViewingDirection()) {
    			g2.drawImage(displayedFish, i.getPosition()[0], i.getPosition()[1], this);
    		} else {
    			g2.drawImage(displayedFish, i.getPosition()[0] + 80, i.getPosition()[1], -80, 50, this);
    		}	
    	}
    }
}