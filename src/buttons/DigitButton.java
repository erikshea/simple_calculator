package buttons;

/**
 * Button for digits and decimal sign
 */
public class DigitButton extends CalculatorButton {
	private static final long serialVersionUID = 1325608133794032548L;

	public DigitButton(String s, Calculator c) {
		super(s, c);
	}

	@Override
	public void onClick() {
		String displayString ="0";
		
		if ( this.getClass().isInstance(this.calculator.getLastButtonClicked()) ) { 
			// if previous button clicked was digit, keep entering
			displayString = calculator.getTextOnScreen();
		}


		if ( this.getText() != "."){ // if digit (not decimal sign) is entered
			displayString += this.getText(); // append to displayed number
		}  else if (!displayString.contains(".")) { // else, decimal sign is entered. If display string doesn't already contain a decimal sign 
			displayString = displayString + "."; // append decimal sign to displayed number
		}

		if ( displayString.startsWith("0") && !displayString.contains(".")) { // If leading 0 and no decimal sign
			displayString = displayString.substring(1);	// remove superfluous leading 0
		}
		
		calculator.setTextOnScreen( displayString );

		super.onClick();
	}
}
