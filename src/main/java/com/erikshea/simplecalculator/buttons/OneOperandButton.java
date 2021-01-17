package com.erikshea.simplecalculator.buttons;

/**
 * Button for operations that involve one operand only
 */
public class OneOperandButton extends CalculatorButton {
	private static final long serialVersionUID = -6671161224512114057L;
	public OneOperandButton(String text, Calculator c) {
		super(text, c);
	}

	public void onClick() {
		if ( !this.calculator.errorIsDisplayed() ) {
			Double displayedNumber=this.getNumberOnScreen();
		
			switch (this.getText()) {
			case "√":
				if ( displayedNumber < 0) {
					// Attempting to find root of negative number displays error
					this.calculator.setTextOnScreen("Cannot find square root of a negative number.");
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
			
			this.setNumberOnScreen(displayedNumber);
			super.onClick();
		}
		
	}
	
	/**
	 * One operand buttons have their own font size
	 */
    protected void resizeIcons() {
		super.resizeIcons();
		this.setFont( this.getFont().deriveFont( (float) this.getWidth()*1/2 ) );
    }
}
