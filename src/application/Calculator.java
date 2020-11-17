package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import buttons.ButtonFactory;
import buttons.ImageButton;
import util.FileUtils;

public class Calculator extends JFrame{
	private static final long serialVersionUID = 1L;
	private final int maxDisplaySize = 20;
	private final Font baseFont = new Font("Yu Gothic UI", Font.PLAIN, 0 );
	private JLabel screen;
	private JPanel keyboard;
	ImageButton closeButton, resizeButton;
	final double heightToWidthRatio = 1.63;
    int clickedX, clickedY; // For window drag event: need to keep track of original click location

	public Calculator() 
	{
		this.setMinimumSize(new Dimension(200,0));
		this.setUndecorated(true);
		this.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		
		JPanel contentPane = new JPanel() {  
			// draw background image on resize
			public void paintComponent(Graphics g) { 
				g.drawImage(FileUtils.getImage("assets/images/background.png"), 0, 0, this.getWidth(), this.getHeight(), this);  
			}  
        };  
        
        contentPane.setLayout(new GridBagLayout());
        
		this.setContentPane(contentPane);
		
		closeButton = new ImageButton("", "button_close");
		GridBagConstraints closeButtonConstraints = new GridBagConstraints();
		closeButtonConstraints.anchor=GridBagConstraints.NORTHEAST;
		this.add(closeButton, closeButtonConstraints);
        closeButton.addActionListener(e->{
        	System.exit(0);
        });
        
		screen = new JLabel("0", SwingConstants.RIGHT);
		screen.setFont(this.getBaseFont());
		GridBagConstraints screenConstraints = new GridBagConstraints();
		screenConstraints.anchor=GridBagConstraints.NORTH;
		screenConstraints.weightx = 1;
		screenConstraints.weighty = 1;
		screenConstraints.gridy=1; 
        this.add(screen,screenConstraints);
        
        keyboard = new JPanel();
        keyboard.setOpaque(false);
		keyboard.setLayout( new GridBagLayout() );
        GridBagConstraints keyboardConstraints = new GridBagConstraints();
        keyboardConstraints.anchor=GridBagConstraints.SOUTH;
        keyboardConstraints.gridy=2; 
        this.add(keyboard,keyboardConstraints);
		
        String[][] buttonsLayout = {
			{ 	"MRC",	"M+", 	"M-", 	"CE", 	"ON-C" },
			{ 	"7", 	"8",	"9", 	"%", 	"√" 	},
			{ 	"4" , 	"5", 	"6", 	"×", 	"÷" 	},
			{ 	"1" , 	"2", 	"3", 	"+", 	"-" 	},
			{ 	"0" , 	".", 	"±", 	null,	"=" 	},
        };

        this.addKeyboardButtons(buttonsLayout, keyboard);
        
		resizeButton = new ImageButton("", "button_resize");
		GridBagConstraints resizeButtonConstraints = new GridBagConstraints();
		resizeButtonConstraints.anchor=GridBagConstraints.SOUTHEAST;
		resizeButtonConstraints.gridy=3; 
		this.add(resizeButton, resizeButtonConstraints);
        this.setUpListeners();
	}
	
	private void setUpListeners() {
		Calculator me = this;
		this.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	clickedX = e.getX();
		    	clickedY = e.getY();
		    	me.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		    }
		    
		    public void mouseReleased(MouseEvent e) {
		    	me.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		    }
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
		    public void mouseDragged(MouseEvent e) {
		    	me.setLocation(me.getLocation().x + e.getX() - clickedX, 
		        		me.getLocation().y + e.getY() - clickedY);
		    }
		});
		
		resizeButton.addMouseListener(new MouseAdapter() {
			Cursor currentCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			
		    public void mousePressed(MouseEvent e) {
		    	keyboard.setVisible(false); // Hide keyboard to avoid flickering
		    	currentCursor = new Cursor(Cursor.NW_RESIZE_CURSOR);
		    	me.setCursor(currentCursor);
		    	
		    }
		    public void mouseReleased(MouseEvent e) {
		    	keyboard.setVisible(true);// Show keyboard again
		    	currentCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		    	me.setCursor(currentCursor);
		    }
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	me.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
		    }
		    
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	me.setCursor(currentCursor);
		    }
		    
		});
		
		resizeButton.addMouseMotionListener(new MouseMotionAdapter() {
		    public void mouseDragged(MouseEvent e) {
		    	int newWidth =  e.getXOnScreen() - me.getLocation().x;
		    	int newHeight =  e.getYOnScreen() - me.getLocation().y;
		 
		    	me.setSize(new Dimension(newWidth,newHeight));
		    }
		});
		
	}
	

	public int getPercentWidth(double percentX) {
		return (int) (percentX*this.getPreferredSize().getWidth()/100);
	}
	
	public int getPercentHeight(double percentY) {
		return (int) (percentY*this.getPreferredSize().getHeight()/100);
	}
	
	void addKeyboardButtons(String[][] textMatrix, JPanel keyboard) {
		GridBagConstraints c;
		
        for (int gridRow = 0;gridRow<textMatrix.length;gridRow++) {
        	for (int gridCol = 0;gridCol<textMatrix[gridRow].length;gridCol++) {
        		if (textMatrix[gridRow][gridCol] != null ) {
        			c = new GridBagConstraints();
        			c.gridx=gridCol;
        			c.gridy=gridRow; 
        			c.fill = GridBagConstraints.BOTH;
        			
        			if (textMatrix[gridRow][gridCol].equals("+")) {
        				c.gridheight = 2;
        			}
        			
        			keyboard.add( ButtonFactory.create(textMatrix[gridRow][gridCol], this), c);
        		}
        	}
        }
	}

	public void setDisplayedNumber(Double d){
		if (d != null) {
			if ( d  == d.intValue() ) {
				screen.setText( Integer.toString(d.intValue()) );
			} else {
				screen.setText(Double.toString(d));
			}
		}
	}

	public void displayError(String errorMessage){
		screen.setText(errorMessage); 
	}
	
	public Double getDisplayedNumber()	{
		return Double.parseDouble( screen.getText() );
	}
	
	public boolean errorIsDisplayed() {
		return screen.getText().startsWith("Cannot");
	}

	public void setDisplayText(String s) {
		this.screen.setText(s);
	}

	public String getDisplayText() {
		return this.screen.getText();
	}
	
	public int getMaxDisplaySize() {
		return this.maxDisplaySize;
	}
	@Override
	public void setSize(Dimension size) {
	    this.setPreferredSize(size);
	    super.setSize(this.getPreferredSize());
	}
	
	@Override
	public void setMinimumSize(Dimension size) {
		size = this.applyRatioToDimension(size);
	    super.setMinimumSize(size);
	}
	
	@Override
	public void setPreferredSize(Dimension size) {
		size = this.applyRatioToDimension(size);
		
	    super.setPreferredSize(size);

	    if (size.width > this.getMinimumSize().width) {
	    	this.resizeElements();
	    }
	}
	
	private void resizeElements() {
		GridBagLayout contentPaneLayout =  (GridBagLayout) this.getContentPane().getLayout();

		GridBagConstraints closeButtonConstraints = contentPaneLayout.getConstraints(this.closeButton);
		closeButtonConstraints.insets=new Insets(getPercentWidth(4),0,0,this.getPercentWidth(4));
		contentPaneLayout.setConstraints(this.closeButton, closeButtonConstraints);
		this.closeButton.setPreferredSize(new Dimension(	this.getPercentWidth(4),
				this.getPercentWidth(4)));


		GridBagConstraints screenConstraints = contentPaneLayout.getConstraints(this.screen);
		screenConstraints.insets=new Insets(this.getPercentHeight(14.4),0,0,0);
		contentPaneLayout.setConstraints(this.screen, screenConstraints);
		this.screen.setFont( this.screen.getFont().deriveFont( (float) this.getPercentHeight(7) ) );
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
	
	private Dimension applyRatioToDimension(Dimension d) {
		if (d.height < d.width * this.heightToWidthRatio) {
			d.height = (int) (d.width * this.heightToWidthRatio);
		}
		
		if (d.width < (int) (d.height * 1/this.heightToWidthRatio) ) {
			d.width = (int) (d.height * 1/this.heightToWidthRatio);
		}
		
		return d;
	}
	
	public Font getBaseFont() {
		return this.baseFont;
	}
}


