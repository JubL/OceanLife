package infpp.oceanlife;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This is the class which extends from the WindowAdapter which itself implements the functionality of the window-close-button.
 * @author Julia Kobus & Jubin Lirawi
 * @since JDK 1.8
 */
public final class WindowListenerExit extends WindowAdapter {
	
	/**
	 * WindowListener to perform the quit functionality. It overrides the java.awt.event.WindowAdapter.windowClosing method.
	 * @param e Takes the WindowEvent "e" as a parameter.
	 */
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}