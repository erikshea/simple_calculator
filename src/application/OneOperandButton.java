package application;

import java.awt.event.ActionEvent;

public class OneOperandButton extends CalculatorButton {

	public OneOperandButton(String text, Calculator c) {
		super(text, c);
	}

	public void onClick(ActionEvent e) {
		if ( !this.calculator.errorIsDisplayed() ) {
			Double displayedNumber=this.calculator.getDisplayedNumber();
		
			switch (this.getText()) {
			case "√":
				if ( displayedNumber < 0) {
					this.calculator.displayError("Cannot find square root of a negative number.");
					displayedNumber=null;
				} else {
					displayedNumber = Math.sqrt(displayedNumber);
				}
				break;
			case "¹/ₓ": 
					if ( displayedNumber == 0) {
						this.calculator.displayError("Cannot divide by zero");
						displayedNumber=null;
					} else {
						displayedNumber = 1 / displayedNumber;
					}
				break;
			case "%":
				displayedNumber = displayedNumber / 100;
				break;
			case "±":
					displayedNumber =  - displayedNumber;
				break;
				
			case "M+":
				if (this.calculator.getMemorizedNumber() == null) {
					this.calculator.setMemorizedNumber(displayedNumber);
				} else {
					displayedNumber += this.calculator.getMemorizedNumber();
				}
				break;
				
			case "M-":
				if (this.calculator.getMemorizedNumber() != null) {
					displayedNumber -= this.calculator.getMemorizedNumber();
				}
				break;
			
			case "MRC":
				if (this.calculator.getMemorizedNumber() != null) {
					displayedNumber = this.calculator.getMemorizedNumber();
				}
				break;
			}
			
			this.calculator.setDisplayedNumber(displayedNumber);
		}
		
	}

}
