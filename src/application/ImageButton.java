package application;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {
	private static final long serialVersionUID = 1L;
	Dimension imageDimensions; // Dimensions of button images
	
	public ImageButton(String name) {
        this.setName(name);
		this.setText("");
        this.setContentAreaFilled(false);
        this.setBorder(null);
        
	}
	@Override
	public void paint(Graphics g) {
		if ( !this.getSize().equals(this.imageDimensions) ) {
			this.imageDimensions = this.getSize();
			this.setIcon(this.getImageIcon(""));
			this.setPressedIcon(this.getImageIcon("_pressed"));
			this.setRolloverIcon(this.getImageIcon("_rollover"));
		}
		
	    super.paint(g);
	}

	private ImageIcon getImageIcon(String suffix) {
		ImageIcon icon = null;
		
		String imagePath = "assets/images/buttons/" + this.getName() + suffix + ".png";
		int imageWidth = (int) this.imageDimensions.getWidth();
		int imageHeight = (int) this.imageDimensions.getHeight();
		
		try {
			Image image =	 Toolkit.getDefaultToolkit().getImage((new java.io.File(imagePath)).toURI().toURL())
							.getScaledInstance(imageWidth , imageHeight, java.awt.Image.SCALE_SMOOTH );
		
			icon = new ImageIcon(image);
		} catch (MalformedURLException e) {
		}
		
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










