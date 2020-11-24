package buttons;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Automates the fetching of background image files, and sets them according to button state (mouse over, click..)
 *
 */
public class ImageButton extends JButton {
	private static final long serialVersionUID = -2735237343299882848L;
	protected Dimension imageDimensions; // Current size of button images
	protected String baseFileName; // Base file name (without path, suffix or file extension)
	protected HashMap<String,Image> baseIconImages=new HashMap<>(); // Cached full-size images
	
	public ImageButton(String text, String name) {
		this.baseFileName = name; // File name is name by default
        this.setName(name);
		this.setText(text);
        this.setContentAreaFilled(false); // No background other than icon
        this.setBorder(null); // No border
        
        // Center text
		this.setHorizontalTextPosition(JButton.CENTER); 
		this.setVerticalTextPosition(JButton.CENTER);
	}

	/**
	 * Redefine paintComponent to resize background images if button size was changed
	 */
    @Override                                   
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);                
		if ( !this.getSize().equals(this.imageDimensions) ) { // If button size is different than when we last resized the images
			this.imageDimensions = this.getSize(); // Get new size for icons
			
			this.resizeIcons();
		}  
    }   
    
    
    protected void resizeIcons() {
		this.setIcon(this.getImageIcon("")); // Default icon
		this.setPressedIcon(this.getImageIcon("_pressed")); // Icon on mouse pressed
		this.setRolloverIcon(this.getImageIcon("_rollover")); // Icon on mouse over
    }
    
	protected ImageIcon getImageIcon(String suffix) {
		if (!baseIconImages.containsKey(suffix)) { // If image doesn't exist in cache

			URL imageUrl = ImageButton.class.getResource("/images/buttons/" + this.baseFileName + suffix + ".png");
			baseIconImages.put(suffix, Toolkit.getDefaultToolkit().getImage(imageUrl));
		}
		
		// New image dimensions the same as button
		int imageWidth = (int) this.getSize().getWidth();
		int imageHeight = (int) this.getSize().getHeight();

		// Scale image
		Image scaledImage = baseIconImages.get(suffix).getScaledInstance(imageWidth , imageHeight, java.awt.Image.SCALE_SMOOTH );
		
		return new ImageIcon(scaledImage); // Return icon set to scaled image
	}
	
	
}









/*
@Override
public void setPreferredSize(Dimension size) {
    super.setPreferredSize(size);
    System.out.println("pref");

}

@Override
public void setMinimumSize(Dimension size) {
    super.setMinimumSize(size);
    System.out.println("min");
}

@Override
public void setSize(Dimension size) {
    super.setSize(size);
    System.out.println("s");
}

@Override
public void resize(Dimension size) {
    super.resize(size);
    System.out.println("res");
}
@Override
public void resize(int w, int h) {
    super.resize(w,h);
    System.out.println("res2");
}*/










