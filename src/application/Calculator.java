package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator extends JFrame{
	final int maxDisplaySize = 20;

	private Double lastNumber = null;
	private Double memorizedNumber = null;
	private TwoOperandButton lastOperatorClicked = null;
	private CalculatorButton previousButtonClicked = null;
	
	private JLabel calcDisplay;
	//private HashMap<String,JButton> calcButtons = new HashMap<>();
	
	public Calculator() 
	{
		calcDisplay = new JLabel();
		calcDisplay.setOpaque(true);
		calcDisplay.setBackground(Color.WHITE);
		calcDisplay.setPreferredSize(new Dimension(0, 50));
		
		this.getContentPane().add(calcDisplay, BorderLayout.NORTH);
		
		JPanel calcKeyboard = new JPanel();
		calcKeyboard.setLayout(new GridLayout(5, 5, 2, 2));
		
		
		calcKeyboard.add( new OneOperandButton("MRC", this));
		calcKeyboard.add( new OneOperandButton("M+", this));
		calcKeyboard.add( new OneOperandButton("M-", this));
		calcKeyboard.add( new FunctionButton("CE", this));
		calcKeyboard.add( new FunctionButton("ON-C", this));
		
		calcKeyboard.add( new DigitButton("7", this));
		calcKeyboard.add( new DigitButton("8", this));
		calcKeyboard.add( new DigitButton("9", this));
		calcKeyboard.add( new OneOperandButton("%", this));
		calcKeyboard.add( new OneOperandButton("√", this));
		
		calcKeyboard.add( new DigitButton("4", this));
		calcKeyboard.add( new DigitButton("5", this));
		calcKeyboard.add( new DigitButton("6", this));
		calcKeyboard.add( new TwoOperandButton("✕", this));
		calcKeyboard.add( new TwoOperandButton("÷", this));
		
		calcKeyboard.add( new DigitButton("1", this));
		calcKeyboard.add( new DigitButton("2", this));
		calcKeyboard.add( new DigitButton("3", this));
		calcKeyboard.add( new TwoOperandButton("+", this));
		calcKeyboard.add( new TwoOperandButton("-", this));
		

		calcKeyboard.add( new DigitButton("0", this));
		calcKeyboard.add( new DigitButton(".", this));
		calcKeyboard.add( new OneOperandButton("±", this));
		calcKeyboard.add( new TwoOperandButton("+", this));
		calcKeyboard.add( new FunctionButton("=", this));
		
		this.getContentPane().add(calcKeyboard, BorderLayout.SOUTH);

		this.setDisplayedNumber(0.0);
	}
	
	

	void setDisplayedNumber(Double d){
		if (d != null) {
			if ( d  == d.intValue() ) {
				calcDisplay.setText( Integer.toString(d.intValue()) );
			} else {
				calcDisplay.setText(Double.toString(d));
			}
		}
	}

	void displayError(String errorMessage){
		calcDisplay.setText(errorMessage);
		lastNumber = null;
	}
	
	Double getDisplayedNumber()	{
		return Double.parseDouble( calcDisplay.getText() );
	}
	
	boolean errorIsDisplayed() {
		return calcDisplay.getText().startsWith("Cannot");
	}

	Double getLastNumber() {
		return this.lastNumber;
	}
	
	void setLastNumber(Double d) {
		this.lastNumber = d;
	}
	
	void setMemorizedNumber(Double d) {
		this.memorizedNumber = d;
	}
	
	Double getMemorizedNumber() {
		return this.memorizedNumber;
	}
	
	
	public void setDisplayText(String s) {
		this.calcDisplay.setText(s);
	}
	

	public String getDisplayText() {
		return this.calcDisplay.getText();
	}
	
	public int getMaxDisplaySize() {
		return this.maxDisplaySize;
	}
	
	public void setPreviousButtonClicked(CalculatorButton b) {
		this.previousButtonClicked = b;
	}
	

	public CalculatorButton getPreviousButtonClicked() {
		return this.previousButtonClicked;
	}
	
	public void setLastOperatorClicked(TwoOperandButton b) {
		this.lastOperatorClicked = b;
	}
	

	public TwoOperandButton getLastOperatorClicked() {
		return this.lastOperatorClicked;
	}
}


