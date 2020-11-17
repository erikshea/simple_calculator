package buttons;

import java.awt.event.ActionEvent;

import application.Calculator;

public class TwoOperandButton extends CalculatorButton {
	private static final long serialVersionUID = 1L;
	private static Double firstOperand = null;
	private static TwoOperandButton currentOperatorButton = null;
	
	
	public TwoOperandButton(String text, Calculator c) {
		super(text, c);
	}
	
	public void onClick() {
		if (TwoOperandButton.currentOperatorButton == null){ 
			// If no previous operator, both operands are displayed number
			TwoOperandButton.firstOperand = this.calculator.getDisplayedNumber();
			TwoOperandButton.currentOperatorButton = this;
		} else {
			this.processOperator();
		}
		super.onClick();
	}

	public void processOperator() {
		if (!this.calculator.errorIsDisplayed()) {
			Double displayedNumber=this.calculator.getDisplayedNumber();
			
			switch (this.getText()) {
			case "÷":
				 if (displayedNumber == 0) {
					this.calculator.displayError("Cannot divide by zero");
					displayedNumber=null;
				 } else {
					 displayedNumber = TwoOperandButton.firstOperand  / displayedNumber;
				 }
				 break;
				 
			case "×":
				displayedNumber = TwoOperandButton.firstOperand  * displayedNumber;
			break;
			case "-":
				displayedNumber = TwoOperandButton.firstOperand  - displayedNumber;
			break;
			case "+":
				displayedNumber = TwoOperandButton.firstOperand  + displayedNumber;
			break; 
			}	

			TwoOperandButton.firstOperand = displayedNumber;
			this.calculator.setDisplayedNumber(displayedNumber);
		}
	}
	
	public static void processCurrentOperator() {
		if ( TwoOperandButton.currentOperatorButton != null ) {
			TwoOperandButton.currentOperatorButton.processOperator();
		}
	}
	
	public static void resetCurrentOperator() {
		TwoOperandButton.currentOperatorButton = null;
	}
}
