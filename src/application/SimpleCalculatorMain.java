package application;

import java.awt.Dimension;
import java.awt.Toolkit;
/*
____________________________________________________________________ 
FILE NAME : SimpleCalculatorMain.java
FILE LOCATION : /src/application
DESCRIPTION : Simple calculator with basic functions
AUTHOR : Erik Shea <hoopsnake@gmail.com>
CREATION DATE : 19/11/2020
_____________________________________________________________________
*/

public class SimpleCalculatorMain {

	public static void main(String[] args) {
		SimpleCalculator calc = new SimpleCalculator();
		
		// Set calculator size as a fraction of screen width.
		calc.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/8,0));

		// Calculator X and Y of top left corner so that calculator is centered on the screen
		int centeredWindowX = (int) (Toolkit.getDefaultToolkit().getScreenSize().width - calc.getPreferredSize().getWidth() )/2;
		int centeredWindowY = (int) (Toolkit.getDefaultToolkit().getScreenSize().height - calc.getPreferredSize().getHeight() )/2;
		calc.setLocation(centeredWindowX,centeredWindowY);
		
		calc.pack(); 
		calc.setVisible(true);
	}
	
}
