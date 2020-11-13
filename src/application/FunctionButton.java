package application;

import java.awt.event.ActionEvent;

public class FunctionButton extends CalculatorButton {

	public FunctionButton(String text, Calculator c) {
		super(text, c);
	}
	
	public void onClick(ActionEvent e) {
		switch (this.getText()) {
		case "=":
			if ( this.calculator.getLastOperatorClicked() != null) {
				this.calculator.getLastOperatorClicked().processOperator();
			}
			break;
		case "ON-C":
			this.calculator.setMemorizedNumber(null);
			this.calculator.setLastNumber(null);
		case "CE":
			this.calculator.setDisplayedNumber(0.);
			break;
		}

		this.calculator.setLastOperatorClicked(null);
	}

}
