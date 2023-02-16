package infpp.oceanlife;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/**
 * This is the class which provides the GUI.
 * @author Julia Kobus & Jubin Lirawi
 * @serial Serial-Version-UID is 43.
 * @since JDK 1.8
 */
public final class OceanLifeGUI extends JFrame {

	/**
	 * This is the Serialization UID.
	 */
	private static final long serialVersionUID = 43L;
    
    /**
     * Object for the graphic output of the ocean. 
     */
    private PaintOcean paintOcean;

    /**
     * Combobox to choose objects to be placed in the ocean.
     */
    private JComboBox<String> typeChooser = new JComboBox<>();
    
    /**
     * This is the 'load' button.
     */
    private JButton load;
    /**
     * This is the 'save' button.
     */
    private JButton save;
    /**
     * This is the 'playpause' button.
     */
    private JButton playPause;
    /**
     * This is the 'step' button.
     */
    private JButton step;
    /**
     * This is the 'quit' button.
     */
    private JButton quit;

    /**
     * Here comes the outputlabel for the textmessages.
     */
    private JLabel messageLabel;
    
    /**
     * Create the 'add' radiobutton.
     */
    private JRadioButton rb1;
    /**
     * Create the 'delete' radiobutton.
     */
    private JRadioButton rb2;

    /**
     * Constructor to generate the GUI.
     * @param title Gets the parameter "title" to set the title of the window (i.e. the JFrame).
     * @param ocean Gets the parameter "ocean" to give it to further methods.
     * @exception Exception Throws an exception if the LookAndFeel class could not be found, if a new instance of the class couldn't be created, if the class or initializer isn't accessible, if lnf.isSupportedLookAndFeel() is false or if the given parameter does not identify a class that extends LookAndFeel.
     */
    public OceanLifeGUI(String title, final Ocean ocean) { // Constructor
        super(title);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch(Exception e) {
        	System.out.println(e);
        }

        /**
         * Generate the ocean area in which the objects will swim.
         */
        this.paintOcean = new PaintOcean(ocean);
        
        /**
         * Add the items to the combobox.
         */
        typeChooser.addItem("Add an Object...");
        typeChooser.addItem("Prey");
        typeChooser.addItem("Predator");

        /**
         * Initialize the Buttons
         */
        load = new JButton("Load");
        save = new JButton("Save");
        playPause = new JButton("Play / Pause");
        step = new JButton("Step");
        quit = new JButton("Quit");     
        
        /**
         * Initialization and configuration of the radiobuttons
         */
        rb1 = new JRadioButton("Add Object");
        rb1.setBackground(Color.white);
        rb1.setSelected(true);
        rb2 = new JRadioButton("Delete Object");
        rb2.setBackground(Color.white);
        rb2.setSelected(false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);

        /**
         * With the following code the buttons get their functionality. Implements java.awt.event.ActionListener.actionPerformed.
         */
        playPause.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (OceanTest.runOrNot()) {
        			messageLabel.setText("Play");
        		} else {
        			messageLabel.setText("Pause");
        		}
        	}
        });
        
        save.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Serialization.serialize(ocean);
        		messageLabel.setText("Saving completed.");
        	}
        });
        
        load.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ocean.loadTheOcean();
        		messageLabel.setText("Loading completed.");
        		repaint();
        	}
        });
        
        quit.addActionListener(new ActionListenerQuit());
        
        step.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LinkedList<OceanObject> oceanObjects = ocean.getOceanObjects();
        		for (OceanObject i : oceanObjects) {
        			i.move(ocean.getWidth(), ocean.getDepth());
        			messageLabel.setText("One step performed.");
        			repaint();
        		}
        	}
        });
        
        Action exitAction = new AbstractAction("Quit") {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        };
        
        JMenuItem loadItem = new JMenuItem(new AbstractAction("Open...") {
        	private static final long serialVersionUID = 1L;
            public void actionPerformed(ActionEvent e) {
            	ocean.loadTheOcean();
            	messageLabel.setText("Loading completed.");
            	repaint();
            }
        });
        
        JMenuItem saveItem = new JMenuItem(new AbstractAction("Save...") {
        	private static final long serialVersionUID = 1L;
            public void actionPerformed(ActionEvent e) {
            	Serialization.serialize(ocean);
            	messageLabel.setText("Saving completed.");
            }
        });
        
        JMenuItem playPauseItem = new JMenuItem(new AbstractAction("Play / Pause") {
        	private static final long serialVersionUID = 1L;
            public void actionPerformed(ActionEvent e) {
            	if (OceanTest.runOrNot()) {
        			messageLabel.setText("Play");
        		} else {
        			messageLabel.setText("Pause");
        		}
            }
        });
        
        JMenuItem aboutItem = new JMenuItem(new AbstractAction("About OceanLife") {
        	private static final long serialVersionUID = 1L;
            public void actionPerformed(ActionEvent e) {
            	AboutOceanLifeFrame aboutFrame = new AboutOceanLifeFrame();
            	aboutFrame.pack();
            	aboutFrame.setLocation(300, 300);
            	aboutFrame.setVisible(true);
            }
        });
        
        /**
         * Add an object to the ocean or delete an object from it when the left mouse button is clicked. It overrides the java.awt.event.MouseAdapter.* methods.
         * If the RadioButton "Add" is chosen the mouse position is saved and a new Object is constructed according to what is chosen in the ComboBox. Last, the object is added to the list with the ocean objects.
         */
        paintOcean.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent m) {
            	if (rb1.isSelected()) {
            		OceanObject newObject = null; // declare an OceanObject
            		PointerInfo mouse = MouseInfo.getPointerInfo(); // get the mouse position
            		switch (typeChooser.getSelectedItem().toString()) { // find out what is clicked in the typeChooser JComboBox
            			case "Add an Object...": System.out.println("Choose an object to be added."); break;
            			case "Prey": newObject = new Prey((int) (mouse.getLocation().getX() - 8 - getLocation().getX()), (int) (mouse.getLocation().getY() - 115 - getLocation().getY())); break;	// create an prey object at the current mouse position
            			case "Predator": newObject = new Predator((int) (mouse.getLocation().getX() - 8 - getLocation().getX()), (int) (mouse.getLocation().getY() - 115 - getLocation().getY())); break; // create an predator object at the current mouse position
            			default: newObject = null; break;
            		}
            		try {
            			if (newObject == null) {
            				throw new NullPointerException("You have to choose an object from the combobox to be put in the ocean.");
            			}
	            		ocean.addOceanObject(newObject); // add the created object to the oceanObjects List
	            		System.out.println("Number of objects in the ocean: " + ocean.numberOfOceanObjects()); //print the number of objects in the ocean
	            		messageLabel.setText("Object added.");
            		} catch (NullPointerException e) {
            			messageLabel.setText("You have to choose an object from the combobox to be put in the ocean.");
        				e.printStackTrace();
        			}
            	} else {
            		if (ocean.removeOceanObject(ocean.indexOfNearestObject(paintOcean, 100))) {
           			messageLabel.setText("Object deleted.");
           			System.out.println("Number of objects in list: " + ocean.numberOfOceanObjects());
            		} else {
            				System.out.println("There is no object near your position to be deleted, there is no object in the ocean to be deleted or an index out of boundary exception occured.");
            				messageLabel.setText("Delete opeation not possible.");
            		}
            	}
            }

        	public void mouseEntered(MouseEvent e) {}

        	public void mouseExited(MouseEvent e) {}

        	public void mousePressed(MouseEvent e) {}

        	public void mouseReleased(MouseEvent e) {}
        });
        
        /**
         * Assembly of the menubarpanel.
         */
        JPanel menubarpanel = new JPanel();
        menubarpanel.setBackground(Color.blue);
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.white);
        menubarpanel.add(menuBar);
        this.setJMenuBar(menuBar);
        
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitAction);
        
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);
        editMenu.add(playPauseItem);
        
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        helpMenu.add(aboutItem);
        
        /**
         * Implementing the Keyboard shortcuts (Accelerators).
         */
        playPauseItem.setAccelerator(KeyStroke.getKeyStroke('p'));
        loadItem.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        aboutItem.setAccelerator(KeyStroke.getKeyStroke("F1"));
        
        /**
         * Assembly of the menupanel.
         */
        JPanel menupanel = new JPanel();
        menupanel.add(load);
        menupanel.add(save);
        menupanel.add(playPause);
        menupanel.add(step);
        menupanel.add(rb1);
        menupanel.add(rb2);
        menupanel.add(typeChooser);
        menupanel.add(quit);
        menupanel.setLayout(new GridLayout(2, 1));
        menupanel.setBackground(Color.blue);

        /**
         * Generation of the messagelabel
         */
        messageLabel = new JLabel();
        messageLabel.setForeground(Color.white);
        messageLabel.setText("Hello World! The ocean size is " + ocean.getWidth() + " x " + ocean.getDepth());
        
        /**
         * Generation of the messagepanel
         */
        JPanel messagepanel = new JPanel();
        messagepanel.add(messageLabel);
        messagepanel.setBackground(Color.blue);
        
        /**
         * Assembly of the Overallpanel (consisting of the menubarpanel, the menupanel, the oceanarea and the messagepanel)
         */
        JPanel overallpanel = new JPanel();
        overallpanel.add(menubarpanel);
        overallpanel.add(menupanel);
        overallpanel.add(paintOcean);
        overallpanel.add(messagepanel);
        overallpanel.setLayout(new BoxLayout(overallpanel, BoxLayout.Y_AXIS));

        /**
         * Assembly to the JFrame
         */
        add(overallpanel);
        
        /**
         * Defining of the close-operations
         */
        addWindowListener(new WindowListenerExit());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        /**
         * This component listener resizes the ocean area whenever the window size is changed. It overrides java.awt.event.ComponentAdapter.coponentResized method.
         */
        addComponentListener(new ComponentAdapter() {
        	public void componentResized(ComponentEvent event) {
        		Component component = (Component) event.getSource();
        		ocean.setWidth(component.getWidth());
        		ocean.setDepth(component.getHeight());
        	}
        });
    }
}