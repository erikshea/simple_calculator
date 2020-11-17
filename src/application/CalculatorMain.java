package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class CalculatorMain {

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		
		calc.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width/8,0));

		int centeredWindowX = (int) (Toolkit.getDefaultToolkit().getScreenSize().width - calc.getPreferredSize().getWidth() )/2;
		int centeredWindowY = (int) (Toolkit.getDefaultToolkit().getScreenSize().height - calc.getPreferredSize().getHeight() )/2;
		calc.setLocation(centeredWindowX,centeredWindowY);
		
		
		calc.pack(); 
		calc.setVisible(true);
		calc.setResizable(true);
	}
	
}
