package infpp.oceanlife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the class which implements the ActionListener which itself implements the functionality of the quit button.
 * @author Julia Kobus & Jubin Lirawi
 * @since JDK 1.8
 */
public final class ActionListenerQuit implements ActionListener {
	
	/**
	 * ActionListener to perform the quit functionality.
	 * @param e Takes the ActionEvent "e" as a parameter.
	 */
    public void actionPerformed(ActionEvent e) {
    	System.exit(0);
    	}
}