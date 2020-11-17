package buttons;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import util.FileUtils;

public class ImageButton extends JButton {
	private static final long serialVersionUID = -2735237343299882848L;
	protected Dimension imageDimensions; // Dimensions of button images
	protected String baseFileName;
	
	public ImageButton(String text, String name) {
		this.baseFileName = name;
        this.setName(name);
		this.setText(text);
        this.setContentAreaFilled(false);
        this.setBorder(null);
		this.setHorizontalTextPosition(JButton.CENTER);
		this.setVerticalTextPosition(JButton.CENTER);
	}

    @Override                                   
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);                
		if ( !this.getSize().equals(this.imageDimensions) ) {
			this.imageDimensions = this.getSize();
			
			this.repaintEvent();
		}                        
    }   
    
    protected void repaintEvent() {
		this.setIcon(this.getImageIcon(""));
		this.setPressedIcon(this.getImageIcon("_pressed"));
		this.setRolloverIcon(this.getImageIcon("_rollover"));
    }
    
	protected ImageIcon getImageIcon(String suffix) {
		ImageIcon icon = null;
		
		String imagePath = "assets/images/buttons/" + this.baseFileName + suffix + ".png";
		int imageWidth = (int) this.imageDimensions.getWidth();
		int imageHeight = (int) this.imageDimensions.getHeight();

		Image image = FileUtils.getImage(imagePath).getScaledInstance(imageWidth , imageHeight, java.awt.Image.SCALE_SMOOTH );
		icon = new ImageIcon(image);
		
		return icon;
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










