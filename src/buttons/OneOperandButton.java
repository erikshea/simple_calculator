package buttons;

import java.awt.event.ActionEvent;

import application.Calculator;

public class OneOperandButton extends CalculatorButton {
	private static final long serialVersionUID = 1L;

	public OneOperandButton(String text, Calculator c) {
		super(text, c);
	}

	public void onClick() {
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
			case "%":
				displayedNumber = displayedNumber / 100;
				break;
			case "±":
					displayedNumber =  - displayedNumber;
				break;
			}
			
			this.calculator.setDisplayedNumber(displayedNumber);
			super.onClick();
		}
		
	}
    protected void repaintEvent() {
		super.repaintEvent();
		this.setFont( this.getFont().deriveFont( (float) this.imageDimensions.getWidth()*1/2 ) );
    }
}
