package buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import application.Calculator;

public class FunctionButton extends CalculatorButton {
	private static final long serialVersionUID = 1L;
	private static Double memorizedNumber = null;
	
	public FunctionButton(String text, Calculator c) {
		super(text, c);
		if (this.getText() == "ON-C") {
			this.baseFileName = "button_on";
			this.setForeground(Color.white);
		}
	}
	
	public void onClick() {
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
		super.onClick();
	}
	
    protected void repaintEvent() {
		super.repaintEvent();
		this.setFont( new Font(this.getFont().getFamily(), Font.BOLD, (int) this.imageDimensions.getWidth()*1/3 ) );
    }

}
