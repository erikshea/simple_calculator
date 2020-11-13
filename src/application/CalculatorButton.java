package application;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
public class CalculatorButton extends JButton implements ActionListener {
	public CalculatorButton(String text) {
		this.setText(text);
		this.setFont( new Font("Times", Font.PLAIN, 14) );
		this.setPreferredSize(new Dimension(60, 40));
	}
	
	
	public void actionPerformed(ActionEvent e){
		
	}
}
