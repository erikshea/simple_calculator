package application;

import java.awt.event.ActionEvent;

public class FunctionButton extends CalculatorButton {
	private static final long serialVersionUID = 1L;
	private static Double memorizedNumber = null;
	
	public FunctionButton(String text, Calculator c) {
		super(text, c);
	}
	
	public void onClick(ActionEvent e) {
		switch (this.getText()) {
		case "=":
			TwoOperandButton.processCurrentOperator();
			TwoOperandButton.resetCurrentOperator();
			break;
		case "ON-C":
			FunctionButton.memorizedNumber = null;
			TwoOperandButton.resetCurrentOperator();
		case "CE":
			this.calculator.setDisplayedNumber(0.);
			TwoOperandButton.resetCurrentOperator();
			break;
		}
		
		if (!this.calculator.errorIsDisplayed()) {
			switch (this.getText()) {
			case "M+":
				if (FunctionButton.memorizedNumber == null) {
					FunctionButton.memorizedNumber = this.calculator.getDisplayedNumber();
				} else {
					this.calculator.setDisplayedNumber( FunctionButton.memorizedNumber + this.calculator.getDisplayedNumber());
				}
				break;
			case "M-":
				if (FunctionButton.memorizedNumber != null) {
					this.calculator.setDisplayedNumber( FunctionButton.memorizedNumber - this.calculator.getDisplayedNumber());
				}
				break;
			case "MRC":
				if (FunctionButton.memorizedNumber != null) {
					this.calculator.setDisplayedNumber(FunctionButton.memorizedNumber);
				}
				break;
			}
		}
		
	}

}
