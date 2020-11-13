package application;

import java.awt.event.ActionEvent;

public class TwoOperandButton extends CalculatorButton {

	public TwoOperandButton(String text, Calculator c) {
		super(text, c);
	}
	
	public void onClick(ActionEvent e) {
		if (this.calculator.getLastOperatorClicked() == null){ // If no previous operator, both members of operation are displayed number (ie multiply with self)
			this.calculator.setLastNumber(this.calculator.getDisplayedNumber());
		} else {
			this.processOperator();
		}
		this.calculator.setLastOperatorClicked(this);
	}

	public void processOperator() {
		if ( !this.calculator.errorIsDisplayed() ) {
			Double displayedNumber=this.calculator.getDisplayedNumber();
			
			if (this.getText().equals("÷") && displayedNumber == 0) {
				this.calculator.displayError("Cannot divide by zero");
				displayedNumber=null;
			} else {
				if (this.getText().equals("÷")) {
					displayedNumber = this.calculator.getLastNumber() / displayedNumber;
				} else if (this.getText().equals("✕")) {
					displayedNumber = this.calculator.getLastNumber() * displayedNumber;
				} else if (this.getText().equals("-")) {
					displayedNumber = this.calculator.getLastNumber() - displayedNumber;
				} else if (this.getText().equals("+")) {
					displayedNumber = this.calculator.getLastNumber() + displayedNumber;
				}
			}
			
			this.calculator.setLastNumber(displayedNumber);
			this.calculator.setDisplayedNumber(displayedNumber);
		}
	}
}
