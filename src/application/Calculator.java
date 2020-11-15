package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class Calculator extends JFrame{
	private static final long serialVersionUID = 1L;
	final int maxDisplaySize = 20;
	private JLabel screen;
	private JPanel keyboard;
	
    int clickedX, clickedY; // For window drag event: need to keep track of original click location
	

	public Calculator() 
	{
		GridBagConstraints c;
		this.setPreferredSize(new Dimension(700,1000));
		JPanel contentPane = new JPanel() {  
           public void paintComponent(Graphics g) {  
              Image img = Toolkit.getDefaultToolkit().getImage(Calculator.class.getResource("b2.png"));  
              g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
           }  
        };  

    		
        contentPane.setLayout(new GridBagLayout());
        
        
		this.setContentPane(contentPane);

		Dimension closeButtonSize = new Dimension(	this.getPercentWidth(5),
				this.getPercentWidth(5));
		ImageButton closeButton = new ImageButton("close");
		closeButton.setPreferredSize(closeButtonSize);
		
		c = new GridBagConstraints();
		c.weightx = 0;
		c.weighty = 0;
		c.insets=new Insets(getPercentHeight(3),0,0,this.getPercentHeight(3));
		c.anchor=GridBagConstraints.EAST;
		this.add(closeButton,c);
        closeButton.addActionListener(e->{
        	System.exit(0);
        });
        

		
		
		screen = new JLabel("0", SwingConstants.RIGHT);
		screen.setPreferredSize(new Dimension(	this.getPercentWidth(84),
													this.getPercentHeight(14.6)));


		c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.gridy=1; 
		c.insets=new Insets(this.getPercentHeight(8.1),0,this.getPercentHeight(8),0);
        this.add(screen,c);
        
        keyboard = new JPanel();
        keyboard.setPreferredSize(new Dimension(this.getPercentWidth(95),
        										this.getPercentHeight(47)));
		c = new GridBagConstraints();
		c.gridy=2; 
		c.insets=new Insets(0,0,this.getPercentHeight(6),0);
        this.add(keyboard,c);
        

		keyboard.setLayout( new GridBagLayout() );
		
		

        String[][] buttonsLayout = {
			{ 	"MRC",	"M+", 	"M-", 	"CE", 	"ON-C" },
			{ 	"7", 	"8",	"9", 	"%", 	"√" 	},
			{ 	"4" , 	"5", 	"6", 	"✕", 	"÷" 	},
			{ 	"1" , 	"2", 	"3", 	"+", 	"-" 	},
			{ 	"0" , 	".", 	"±", 	null,	"=" 	},
        };

        this.addKeyboardButtons(buttonsLayout, keyboard);
        this.setUpListeners();
	}
	
	private void setUpListeners() {
		Calculator me = this;
		addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        // Get x,y and store them
		    	clickedX = e.getX();
		    	clickedY = e.getY();
		    }
		});
		addMouseMotionListener(new MouseMotionAdapter() {
		    public void mouseDragged(MouseEvent e) {
		    	me.setLocation(me.getLocation().x + e.getX() - clickedX, 
		        		me.getLocation().y + e.getY() - clickedY);
		    }
		});
	}
	

	private int getPercentWidth(double percentX) {
		return (int) (percentX*this.getPreferredSize().getWidth()/100);
	}
	
	private int getPercentHeight(double percentY) {
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
        			c.weightx = 1;
        			c.weighty = 1;
        			c.insets=new Insets(5,5,5,5);
        			c.fill = GridBagConstraints.BOTH;
        			
        			if (textMatrix[gridRow][gridCol].equals("+")) {
        				c.gridheight = 2;
        			}
        			
        			keyboard.add( ButtonFactory.create(textMatrix[gridRow][gridCol], this), c);
        		}
        	}
        }
	}

	void setDisplayedNumber(Double d){
		if (d != null) {
			if ( d  == d.intValue() ) {
				screen.setText( Integer.toString(d.intValue()) );
			} else {
				screen.setText(Double.toString(d));
			}
		}
	}

	void displayError(String errorMessage){
		screen.setText(errorMessage); 
	}
	
	Double getDisplayedNumber()	{
		/*if (this.errorIsDisplayed()) {
			return null;
		}*/
		
		return Double.parseDouble( screen.getText() );
	}
	
	boolean errorIsDisplayed() {
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
	public void setPreferredSize(Dimension size) {
	    super.setPreferredSize(size);
	    System.out.println("prefCCC");
	}
}


