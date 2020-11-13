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

public class Calculator extends JFrame implements ActionListener{
	final int maxDisplaySize = 20;

	boolean clearDisplayOnInput = true;
	Double lastNumber = null;
	String lastOperator = null;
	
	private JLabel calcDisplay;
	private HashMap<String,JButton> calcButtons = new HashMap<>();
	
	public Calculator() 
	{
		calcDisplay = new JLabel();
		calcDisplay.setOpaque(true);
		calcDisplay.setBackground(Color.WHITE);
		calcDisplay.setPreferredSize(new Dimension(0, 50));
		
		this.getContentPane().add(calcDisplay, BorderLayout.NORTH);
		
		JPanel calcKeyboard = new JPanel();
		calcKeyboard.setLayout(new GridLayout(6, 4, 2, 2));
		
		this.addCalculatorButton("%", calcKeyboard);
		this.addCalculatorButton("√", calcKeyboard);
		this.addCalculatorButton("x²", calcKeyboard);
		this.addCalculatorButton("¹/ₓ", calcKeyboard);
		
		this.addCalculatorButton("CE", calcKeyboard);
		this.addCalculatorButton("C", calcKeyboard);
		this.addCalculatorButton("⌫", calcKeyboard);
		this.addCalculatorButton("/", calcKeyboard);
		
		this.addCalculatorButton("7", calcKeyboard);
		this.addCalculatorButton("8", calcKeyboard);
		this.addCalculatorButton("9", calcKeyboard);
		this.addCalculatorButton("*", calcKeyboard);
		

		this.addCalculatorButton("4", calcKeyboard);
		this.addCalculatorButton("5", calcKeyboard);
		this.addCalculatorButton("6", calcKeyboard);
		this.addCalculatorButton("-", calcKeyboard);
		

		this.addCalculatorButton("1", calcKeyboard);
		this.addCalculatorButton("2", calcKeyboard);
		this.addCalculatorButton("3", calcKeyboard);
		this.addCalculatorButton("+", calcKeyboard);

		this.addCalculatorButton("±", calcKeyboard);
		this.addCalculatorButton("0", calcKeyboard);
		this.addCalculatorButton(".", calcKeyboard);
		this.addCalculatorButton("=", calcKeyboard);

		this.getContentPane().add(calcKeyboard, BorderLayout.SOUTH);
		
		
		calcDisplay.setText("0");
	}
	
	 void addCalculatorButton(String name, JPanel keyboard) {
		JButton button = new JButton( name );
		button.setFont( new Font("Times", Font.PLAIN, 14) );
		button.setPreferredSize(new Dimension(60, 40));
		this.calcButtons.put(name, button);
		
		keyboard.add(button);
		button.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		String buttonName = ( (JButton) e.getSource() ).getText();
		
		switch(buttonName)
		{
			case "0": case "1": case "2": case "3": case "4": case "5": case "6": case "7": case "8": case "9":
				enterDigit(buttonName);
				break;

			case ".":
				if (!calcDisplay.getText().contains(".")) {
					calcDisplay.setText(calcDisplay.getText() + ".");
				}
				break;

			case "/": case "*": case "-": case "+":
				processOperator(buttonName);
				break;
				
			case "√": case "¹/ₓ": case "%": case "±":
				processFunction(buttonName);
				break;

			case "=":
				if ( !this.errorIsDisplayed() && this.lastOperator != null) {
					processLastOperator();
					this.lastOperator = null;
				}
				break;

			case "⌫":
				if ( !this.errorIsDisplayed() ){
					calcDisplay.setText(calcDisplay.getText().substring(0,
								calcDisplay.getText().length() - 1));
					
					if (calcDisplay.getText().length() < 1)
						calcDisplay.setText("0");
				}
				break;

				
			case "C":
				lastOperator = null;
				lastNumber = null;
			case "CE":
				clearDisplayOnInput = true;
				calcDisplay.setText("0");
				break;
		}
	}

	void enterDigit(String digit){
		if (clearDisplayOnInput) {
			calcDisplay.setText("");
		}

		String displayString = calcDisplay.getText();
		
		if (displayString.startsWith("0")){
			displayString = displayString.substring(1); // If we entered "0" first, remove leading 0
		}

		if ( displayString.length() < maxDisplaySize ){
			calcDisplay.setText(displayString + digit);
		}
		
		clearDisplayOnInput = false;
	}


	void processFunction(String f) {
		if ( !this.errorIsDisplayed() ) {
			clearDisplayOnInput = true;
			Double result=null;
		
			switch (f) {
			case "√":
				if ( this.getDisplayedNumber() < 0) {
					displayError("Cannot find square root of a negative number.");
				} else {
					result = Math.sqrt(getDisplayedNumber());
				}
				break;
			case "¹/ₓ": 
					if ( this.getDisplayedNumber() == 0) {
						displayError("Cannot divide by zero");
					} else {
						result = 1 / this.getDisplayedNumber();
					}
				break;
			case "%":
				result = this.getDisplayedNumber() / 100;
				break;
			case "±":
				if ( this.getDisplayedNumber() != 0 ) {
					result = -this.getDisplayedNumber();
				}
				break;
			}
			
			this.displayResult(result);
		}
		
	}

	void processOperator(String op) {
		clearDisplayOnInput = true;
		
		if ( !this.errorIsDisplayed() ) {
			if (lastOperator == null){ // If no previous operator, both members of operation are displayed number (ie multiply with self)
				lastNumber = getDisplayedNumber();
			} else {
				processLastOperator();
			}
			
			lastOperator = op;
		}
	}


	void processLastOperator() {
		Double result = null;
		double numberInDisplay = getDisplayedNumber();

		if (lastOperator.equals("/") && numberInDisplay == 0) {
				displayError("Cannot divide by zero");
		} else {
			if (lastOperator.equals("/")) {
				result = lastNumber / numberInDisplay;
			} else if (lastOperator.equals("*")) {
				result = lastNumber * numberInDisplay;
			} else if (lastOperator.equals("-")) {
				result = lastNumber - numberInDisplay;
			} else if (lastOperator.equals("+")) {
				result = lastNumber + numberInDisplay;
			}
		}

		lastNumber = result;
		displayResult(result);
	}

	void displayResult(Double result){
		lastNumber = result;
		clearDisplayOnInput = true;
		
		if (result != null) {
			if ( result  == result.intValue() ) {
				calcDisplay.setText( Integer.toString(result.intValue()) );
			} else {

				calcDisplay.setText(Double.toString(result));
			}
		}
	}

	void displayError(String errorMessage){
		calcDisplay.setText(errorMessage);
		lastNumber = null;
		clearDisplayOnInput = true;
	}
	
	Double getDisplayedNumber()	{
		return Double.parseDouble( calcDisplay.getText() );
	}
	
	boolean errorIsDisplayed() {
		return calcDisplay.getText().startsWith("Cannot");
	}

	
	public static void main(String[] args) {
		Calculator calc = new Calculator();
		calc.setTitle("Calculator");
		calc.setLocation(100, 100);
		calc.pack(); // size according to content
		calc.setVisible(true);
		calc.setResizable(false);
	}
}


