package application;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
public abstract class CalculatorButton extends JButton{
	protected Calculator calculator;
	
	public CalculatorButton(String text, Calculator c) {
		this.calculator = c;
		
		this.setText(text);
		this.setFont( new Font("Times", Font.PLAIN, 14) );
		this.setPreferredSize(new Dimension(70, 40));
		this.addActionListener( e->{
			this.onClick(e);
			this.calculator.setPreviousButtonClicked(this);
		} );
	}
	
	protected abstract void onClick(ActionEvent e);
}
