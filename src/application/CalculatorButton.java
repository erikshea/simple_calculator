package application;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
public abstract class CalculatorButton extends ImageButton{
	private static final long serialVersionUID = 1L;
	protected Calculator calculator;
	protected static CalculatorButton lastButtonClicked = null;
	
	public CalculatorButton(String name, Calculator calc) {
		super(name);
		this.calculator = calc;
		this.setText(name);
		this.setFont( new Font("Times", Font.PLAIN, 14) );
	
		this.addActionListener( e->{
			this.onClick(e);
			CalculatorButton.lastButtonClicked = this;
		} );
	}
	
	protected abstract void onClick(ActionEvent e);
	
	protected static CalculatorButton getLastButtonClicked() {
		return CalculatorButton.lastButtonClicked;
	}
	
	
}
