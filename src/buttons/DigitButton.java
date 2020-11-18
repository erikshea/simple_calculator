package buttons;

public class DigitButton extends CalculatorButton {
	private static final long serialVersionUID = 1325608133794032548L;

	public DigitButton(String s, Calculator c) {
		super(s, c);
	}

	public void onClick() {
		String displayString ="0";
		
		if ( this.getClass().isInstance(this.calculator.getLastButtonClicked()) ) { 
			// if previous button clicked was digit, keep entering
			displayString = calculator.getDisplayText();
		}
		
		if ( this.getText() != "."){
			displayString += this.getText();
		}  else if (!displayString.contains(".")) {
			displayString = displayString + ".";
		}

		if ( displayString.startsWith("0") && !displayString.contains(".")) {
			displayString = displayString.substring(1);
		}
		
		if ( displayString.length() < calculator.getMaxDisplaySize() ){
			calculator.setDisplayText( displayString );
		}
		super.onClick();
	}
}
