package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class CalculatorMain {

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		calc.setUndecorated(true);
		calc.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		
		calc.setLocation(	(Toolkit.getDefaultToolkit().getScreenSize().width - calc.getWidth() )/2,
							(Toolkit.getDefaultToolkit().getScreenSize().height - calc.getHeight() )/2);

		calc.pack(); 
		calc.setVisible(true);
		calc.setResizable(false);
	}
	
}
