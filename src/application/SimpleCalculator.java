package application;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import buttons.ButtonFactory;
import buttons.CalculatorButton;
import buttons.ImageButton;
import buttons.TwoOperandButton;
import buttons.Calculator;
import util.FileUtils;

/**
 * SimpleCalculator class creates interface elements and deals with presentation logic.
 * @author Erik Shea
 *
 */
public class SimpleCalculator extends JFrame implements Calculator{
	private static final long serialVersionUID = 4380693977371341395L;
	private final int maxDigitsOnScreen = 14; // For input: max numbers on screen allowed at once
	private final Font baseFont = new Font(	// Default font for UI
		// Choose first installed font in a list of font names, by order of preference.
		FileUtils.getFirstValidFontName(new String[] {"Yu Gothic UI","Segoe UI","Arial","SansSerif"})
		, Font.PLAIN
		, 0 ); // All font sizes will be set in resizeElements()
	private final double heightToWidthRatio = 1.63; // Calculator size ratio stays constant during resizing
	private JLabel screen;	// Calculator screen
	private JPanel keyboard; // Calculator keyboard contains all instances of CalculatorButton
	ImageButton closeButton, resizeButton; // Buttons to replace those in default window decoration
	
	// The following are needed for calculator logic
    private CalculatorButton lastButtonClicked = null; 
	private TwoOperandButton currentOperatorButton = null;
    private Double memorizedNumber = null;
	
	// Keep track of buttons to simulate clicks in unit tests
    private HashMap<String,CalculatorButton> buttonsByName = new HashMap<>();
    
    /**
     * Set up main pane, call methods to create interface elements.
     */
	public SimpleCalculator() 
	{
		this.setMinimumSize(new Dimension(200,0));
		
		// Don't draw window frame, or bar on top
		this.setUndecorated(true);
		// transparent background (no white corners behind calculator)
		this.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		
		// Main content pane: redefine paintComponent in anonymous class to draw background image on resize
		JPanel contentPane = new JPanel() {  
			private static final long serialVersionUID = 2099812517613916407L;
			@Override
			public void paintComponent(Graphics g) { 
				super.paintComponent(g);
				this.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
				g.drawImage(FileUtils.getImage("assets/images/background.png"), 0, 0, this.getWidth(), this.getHeight(), this);  
			}  
        };  
        
        contentPane.setLayout(new GridBagLayout());
		this.setContentPane(contentPane);
		
		// Set up buttons and display
        this.setUpInterfaceElements();
        
        // Listeners for window move and resize
        this.setUpMouseListeners();
	}
	
	/**
	 *  Create CalculatorButtons and screen
	 */
	private void setUpInterfaceElements() {
		// Set up calculator display
		
		SimpleCalculator me = this;
		// Screen: redefine paintComponent in anonymous class to adjust font size on error
		this.screen = new JLabel("", SwingConstants.RIGHT) {
			private static final long serialVersionUID = 4628608167887578237L;

			@Override
			protected void paintComponent(Graphics g) {
				if (me.errorIsDisplayed()) { // On error, resize font
					g.setFont(this.getFont().deriveFont(this.getFont().getSize() * 0.37f));
				}
				
				super.paintComponent(g);
			}
		};
		this.screen.setFont(this.getBaseFont());
		
		GridBagConstraints screenConstraints = new GridBagConstraints();
		screenConstraints.anchor=GridBagConstraints.NORTH;
		// Specify weights to keep interface from changing on resize
		screenConstraints.weightx = 1;
		screenConstraints.weighty = 1;
		screenConstraints.gridy=1; 
        this.add(screen,screenConstraints);
        
        keyboard = new JPanel();
        // DOn't show keyboard background between keys
        keyboard.setOpaque(false);
        
		keyboard.setLayout( new GridBagLayout() );
        GridBagConstraints keyboardConstraints = new GridBagConstraints();
        keyboardConstraints.anchor=GridBagConstraints.SOUTH;
        keyboardConstraints.gridy=2; 
        this.add(keyboard,keyboardConstraints);
        
        setUpInterfaceButtons();
	}
	
	/**
	 *  Create interface buttons (close, resize) to replace those in default window decoration
	 */
	private void setUpInterfaceButtons() {
		closeButton = new ImageButton("", "button_close");
		GridBagConstraints closeButtonConstraints = new GridBagConstraints();
		closeButtonConstraints.anchor=GridBagConstraints.NORTHEAST;
		this.add(closeButton, closeButtonConstraints);
		// Exit on click.
        closeButton.addActionListener(e->{
        	System.exit(0);
        });
        
		resizeButton = new ImageButton("", "button_resize");
		GridBagConstraints resizeButtonConstraints = new GridBagConstraints();
		resizeButtonConstraints.anchor=GridBagConstraints.SOUTHEAST;
		resizeButtonConstraints.gridy=3; 
		this.add(resizeButton, resizeButtonConstraints);
        
		// Labels and positions in grid for all calculator buttons
        String[][] buttonsLayout = {
			{ 	"MRC",	"M+", 	"M-", 	"CE", 	"ON/C"  },
			{ 	"7", 	"8",	"9", 	"%", 	"√" 	},
			{ 	"4" , 	"5", 	"6", 	"×", 	"÷" 	},
			{ 	"1" , 	"2", 	"3", 	"+", 	"-" 	},
			{ 	"0" , 	".", 	"±", 	null,	"=" 	},
        };

        this.addKeyboardButtons(buttonsLayout, keyboard);
	}
	
	/**
	 * Create and add CalculatorButtons to keyboard
	 * @param textMatrix text matrix containing button labels
	 * @param keyboard Pane containing all buttons
	 */
	private void addKeyboardButtons(String[][] textMatrix, JPanel keyboard) {
		GridBagConstraints c;
		CalculatorButton button;
		
        for (int gridRow = 0;gridRow<textMatrix.length;gridRow++) {
        	for (int gridCol = 0;gridCol<textMatrix[gridRow].length;gridCol++) {
        		if (textMatrix[gridRow][gridCol] != null ) {
        			c = new GridBagConstraints();
        			
        			// Button position in grid is the same as position in matrix
        			c.gridx=gridCol;
        			c.gridy=gridRow; 
        			c.fill = GridBagConstraints.BOTH;
        			
        			if (textMatrix[gridRow][gridCol].equals("+")) {
        				// Special case for plus button: double height
        				c.gridheight = 2;
        			}
        			
        			// ButtonFactory will create appropriate button type (digit, one operand...) according to label
        			button = ButtonFactory.create(textMatrix[gridRow][gridCol], this);
        			keyboard.add( button, c);
        			
        			// Add button to HashMap, for easy access in unit tests.
        			this.buttonsByName.put(button.getName(), button);
        		}
        	}
        }
	}
	
	/**
	 * Mouse listeners for moving and resizing window
	 */
	private void setUpMouseListeners() {
		SimpleCalculator me = this;
    	Dimension firstClickLocation = new Dimension();

		this.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	// On click, save initial location and show move cursor
		    	firstClickLocation.setSize(e.getX(), e.getY());
		    	me.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		    }
		    
		    public void mouseReleased(MouseEvent e) {
		    	// On release, restore initial cursor
		    	me.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		    }
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
		    public void mouseDragged(MouseEvent e) {
		    	// On move, calculate new location
		    	me.setLocation(	me.getLocation().x + e.getX() - (int) firstClickLocation.getWidth(), 
		        				me.getLocation().y + e.getY() - (int) firstClickLocation.getHeight());
		    }
		});
		
		resizeButton.addMouseListener(new MouseAdapter() {
			// Cursor to restore on mouse exit will not always be default cursor:
			// (eg. during a resize event, move cursor should be restored and not default)
			Cursor currentCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			
		    public void mousePressed(MouseEvent e) {
		    	keyboard.setVisible(false); // Hide keyboard to resize smoothly
		    	currentCursor = new Cursor(Cursor.NW_RESIZE_CURSOR); 
		    	me.setCursor(currentCursor);
		    	
		    }
		    public void mouseReleased(MouseEvent e) {
		    	keyboard.setVisible(true);// Show keyboard again
		    	currentCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		    	me.setCursor(currentCursor);
		    }
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	// Show resize cursor as hint on button mouse over.
		    	me.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
		    }
		    
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	// restore cursor on button mouse out.
		    	me.setCursor(currentCursor);
		    }
		    
		});
		
		resizeButton.addMouseMotionListener(new MouseMotionAdapter() {
		    public void mouseDragged(MouseEvent e) {
		    	// Calculate new calculator size according to cursor coordinates
		    	int newWidth =  e.getXOnScreen() - me.getLocation().x;
		    	int newHeight =  e.getYOnScreen() - me.getLocation().y;
		 
		    	me.setSize(new Dimension(newWidth,newHeight)); 	// Resize calculator
		    }
		});
		
	}
	
	/**
	 * Returns a width in pixels that corresponds to a given percentage of total calculator width
	 * Used to create interface elements that can adapt to a new calculator size.
	 * @param percentage of calculator width
	 * @return corresponding pixel width
	 */
	private int getPercentWidth(double percentage) {
		return (int) (percentage*this.getPreferredSize().getWidth()/100);
	}
	
	/**
	 * Returns a height in pixels that corresponds to a given percentage of total calculator height
	 * Used to create interface elements that can adapt to a new calculator size.
	 * @param percentage of calculator height
	 * @return corresponding pixel height
	 */
	private int getPercentHeight(double percentage) {
		return (int) (percentage*this.getPreferredSize().getHeight()/100);
	}
	
	/*
	 * Redefine setPreferredSize to resize interface components to new size.
	 */
	@Override
	public void setPreferredSize(Dimension size) {
		size = this.applyRatioToDimension(size); // Calculator needs to maintain a given height-to-width ratio
		
	    super.setPreferredSize(size);

	    if (size.width > this.getMinimumSize().width) { // Make sure new width isn't too small
	    	this.resizeElements();
	    }
	}
	
	/*
	 * Redefine setSize to resize interface components to new size.
	 */
	@Override
	public void setSize(Dimension size) {
	    this.setPreferredSize(size);
	    super.setSize(this.getPreferredSize());
	}
	
	/*
	 * Redefine setMinimum to maintain correct aspect ratio
	 */
	@Override
	public void setMinimumSize(Dimension size) {
		size = this.applyRatioToDimension(size);
	    super.setMinimumSize(size);
	}
	

	/**
	 * Adjust all GUI component sizes and paddings according to a percentage of calculator width and height
	 */
	private void resizeElements() {
		//Get gridbag layout of content pane, to access constraints
		GridBagLayout contentPaneLayout =  (GridBagLayout) this.getContentPane().getLayout();

		GridBagConstraints closeButtonConstraints = contentPaneLayout.getConstraints(this.closeButton);
		// Change close button padding.
		closeButtonConstraints.insets=new Insets(getPercentWidth(4),0,0,this.getPercentWidth(4));
		contentPaneLayout.setConstraints(this.closeButton, closeButtonConstraints);
		this.closeButton.setPreferredSize(new Dimension(	this.getPercentWidth(4),
															this.getPercentWidth(4)));

		GridBagConstraints screenConstraints = contentPaneLayout.getConstraints(this.screen);
		screenConstraints.insets=new Insets(this.getPercentHeight(14.4),0,0,0);
		contentPaneLayout.setConstraints(this.screen, screenConstraints);
		
		// Screen font depends on calculator size
		this.screen.setFont(this.screen.getFont().deriveFont( (float) this.getPercentHeight(6.3) ));
		this.screen.setBorder(new EmptyBorder(0,this.getPercentWidth(3),00,this.getPercentWidth(3)));
		this.screen.setPreferredSize(	new Dimension(	this.getPercentWidth(84),
										this.getPercentHeight(14.6)));

		GridBagConstraints keyboardConstraints = contentPaneLayout.getConstraints(this.keyboard);
        keyboardConstraints.insets=new Insets(0,0,this.getPercentHeight(3),0);
		contentPaneLayout.setConstraints(this.keyboard, keyboardConstraints);
		
		GridBagLayout keyboardLayout =  (GridBagLayout) this.keyboard.getLayout();
		GridBagConstraints buttonConstraints;
		int buttonSpacing = this.getPercentWidth(1.5);
		
		for (Component button : this.keyboard.getComponents() ) {
			buttonConstraints = keyboardLayout.getConstraints(button);
			buttonConstraints.insets=new Insets(buttonSpacing,buttonSpacing,buttonSpacing,buttonSpacing);
			keyboardLayout.setConstraints(button,buttonConstraints);
			button.setPreferredSize(new Dimension(	this.getPercentWidth(16.2),
													this.getPercentHeight(8)));
		}
		GridBagConstraints resizeButtonConstraints = contentPaneLayout.getConstraints(this.resizeButton);
		resizeButtonConstraints.insets=new Insets(0,0,getPercentWidth(5),this.getPercentWidth(3));
		contentPaneLayout.setConstraints(this.resizeButton, resizeButtonConstraints);
		this.resizeButton.setPreferredSize(new Dimension(	this.getPercentWidth(4),
															this.getPercentWidth(4)));
	}
	
	/**
	 * Adjusts a given width and height pair so they conform to calculator aspect ratio
	 * @param d original dimension
	 * @return d adjusted to aspect ratio
	 */
	private Dimension applyRatioToDimension(Dimension d) {
		if (d.height < d.width * this.heightToWidthRatio) {
			d.height = (int) (d.width * this.heightToWidthRatio);
		}
		
		if (d.width < (int) (d.height * 1/this.heightToWidthRatio) ) {
			d.width = (int) (d.height * 1/this.heightToWidthRatio);
		}
		
		return d;
	}


	/**
	 *  Simulate button clicks for unit tests.
	 *  
	 * @param buttonText sequence of button labels to activate
	 */
    public void activateButtons(String[] buttonText) {
    	for (String buttonName : buttonText) {
    		this.buttonsByName.get("button_" + buttonName).onClick();
    	}
    }

    /**
     * Sets display text to s, truncated to max screen size (if not error)
     */
	public void setTextOnScreen(String s) {
		if (!this.isErrorString(s) && s.length() > this.getMaxDigitsOnScreen()) {
			s = s.substring(0,this.getMaxDigitsOnScreen());
		}
		this.screen.setText(s);
	}
	
    /**
     * get display text to s
     */
	public String getTextOnScreen() {
		return this.screen.getText();
	}
	
	public boolean isErrorString(String s) {
		return s.startsWith("Cannot");
	}
	
	public boolean errorIsDisplayed() {
		return this.isErrorString(this.getTextOnScreen());
	}
	
	/** Generic getters and setters **/
	public int getMaxDigitsOnScreen() {
		return this.maxDigitsOnScreen;
	}
	
	public Font getBaseFont() {
		return this.baseFont;
	}
	
    public CalculatorButton getLastButtonClicked() {
    	return this.lastButtonClicked;
    }
    
    public void setLastButtonClicked(CalculatorButton b) {
    	this.lastButtonClicked = b;
    }
    
    public Double getMemorizedNumber() {
    	return this.memorizedNumber;
    }
    
    public void setMemorizedNumber(Double n) {
    	this.memorizedNumber = n;
    }
    
    public TwoOperandButton getCurrentOperatorButton() {
    	return this.currentOperatorButton;
    }
    
    public void setCurrentOperatorButton(TwoOperandButton b) {
    	this.currentOperatorButton = b;
    }
}


