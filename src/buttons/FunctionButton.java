package buttons;

import java.awt.Color;

import java.awt.Font;

/**
 * Button for functions (on, memory, equals..)
 */
public class FunctionButton extends CalculatorButton {
	private static final long serialVersionUID = 1269194353540007205L;
	
	public FunctionButton(String text, Calculator c) {
		super(text, c);
		if (this.getText() == "ON/C") { // Special case for ON/C button
			this.baseFileName = "button_on"; // Red icons
			this.setForeground(Color.white); // White text
		}
	}
	
	public void onClick() {
		switch (this.getText()) {
		case "ON/C":
			this.calculator.setCurrentOperatorButton(null); // Reset current operator
		case "CE": // case applies to both CE and ON/C
			this.calculator.setTextOnScreen("");
			break;
		case "MRC": // Show memory (if exists) on screen 
			if (calculator.getMemorizedNumber() != null) {
				this.setNumberOnScreen( calculator.getMemorizedNumber() );
			}
			break;
		}
		
		if (!this.calculator.errorIsDisplayed()) { // M+ and M- have no effect if error displayed
			switch (this.getText()) {
			case "M+":
				if (calculator.getMemorizedNumber() == null) { // If no number in memory, add current displayed number
					calculator.setMemorizedNumber( this.getNumberOnScreen() );
				} else { // Else, add current number to memory
					calculator.setMemorizedNumber( calculator.getMemorizedNumber() + this.getNumberOnScreen() );
				}
				break;
			case "M-":
				// If last button clicked was "MRC", erase memory
				if ( this.calculator.getLastButtonClicked() != null && this.calculator.getLastButtonClicked().getText().equals("MRC") ) {
					calculator.setMemorizedNumber(null);
				}
				
				if (calculator.getMemorizedNumber() != null) {// Else, substract current number to memory
					calculator.setMemorizedNumber( calculator.getMemorizedNumber() - this.getNumberOnScreen() );
				}
				break;
			}
		}
		super.onClick();
	}
	
	/**
	 * Function buttons have their own font size, and are bold
	 */
    protected void resizeIcons() {
		super.resizeIcons();
		this.setFont( new Font(this.getFont().getFamily(), Font.BOLD, (int) ( this.getWidth()*1/3 )) );
    }

}
