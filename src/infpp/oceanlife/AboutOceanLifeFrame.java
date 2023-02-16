package infpp.oceanlife;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * This is the class which provides the JFrame for the Information Window (About OceanLife).
 * @author Julia Kobus & Jubin Lirawi
 * @serial Serial-Version-UID is 44.
 * @since JDK 1.8
 */
public class AboutOceanLifeFrame extends JFrame {

	/**
	 * This is the Serialization UID.
	 */
	private static final long serialVersionUID = 44L;
	
	/**
	 * This is the logo of OceanLife.
	 */
	private ImageIcon logo;
	
	/**
	 * Constructor
	 */
	public AboutOceanLifeFrame() {
		super("About OceanLife");

        try {
        	UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch(Exception e) {
        	System.out.println(e);
        }
        
        logo = new ImageIcon("logo.png");
        JLabel logoLabel = new JLabel(logo);

        JLabel textLabel = new JLabel();
        textLabel.setBackground(Color.white);
        textLabel.setForeground(Color.black);
        textLabel.setText("<html><body>OceanLife<br>V0.1<br><br>Author: Julia Kobus & Jubin Lirawi<br><br>What is OceanLife?<br>OceanLife is an simple ocean simulation<br>with different types of fishes which can<br>interact.<br><br>Why is OceanLife?<br>This java program was developed due to<br>a university course in which a group of two<br>students had to create such a project and<br>present it afterwards.</body></html>");

        JPanel overallpanel = new JPanel();
        overallpanel.add(logoLabel);
        overallpanel.add(textLabel);
        overallpanel.setLayout(new BoxLayout(overallpanel, BoxLayout.X_AXIS));
        
        add(overallpanel);
	}
}