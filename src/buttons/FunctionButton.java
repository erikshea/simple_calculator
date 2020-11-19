package buttons;

import java.awt.Color;

import java.awt.Font;

/**
 * Button for functions (on, memory, equals..)
 */
public class FunctionButton extends CalculatorButton {
	private static final long serialVersionUID = 1269194353540007205L;
	private Double currentOperandForEquals=null;
	private TwoOperandButton currentOperatorForEquals=null;
	
	public FunctionButton(String text, Calculator c) {
		super(text, c);
		if (this.getText() == "ON/C") { // Special case for ON/C button
			this.baseFileName = "button_on"; // Red icons
			this.setForeground(Color.white); // White text
		}
	}
	
	public void onClick() {
		switch (this.getText()) {
		case "=":
			if (!this.calculator.errorIsDisplayed()) {
				if (this.equals(this.calculator.getLastButtonClicked()) && this.currentOperatorForEquals != null) {
					this.currentOperatorForEquals.firstOperand = this.getDisplayNumber();
					this.setDisplayNumber(currentOperandForEquals);
					this.currentOperatorForEquals.processOperator();
					return;
				}
				currentOperandForEquals = this.getDisplayNumber();
	
				if (this.calculator.getCurrentOperatorButton() != null) { // If we already entered an operator
					this.currentOperatorForEquals = this.calculator.getCurrentOperatorButton();
					this.calculator.getCurrentOperatorButton().processOperator();
				}
			}
			
			break;
		case "ON/C":
			calculator.setMemorizedNumber(null); // Erase memory
			this.calculator.setCurrentOperatorButton(null); // Reset current operator
		case "CE": // case applies to both CE and ON/C
			this.calculator.setDisplayText("");
			break;
		case "MRC": // Recall memory to screen
			if (calculator.getMemorizedNumber() != null) {
				this.setDisplayNumber( calculator.getMemorizedNumber() );
			}
			break;
		}
		
		if (!this.calculator.errorIsDisplayed()) { // M+ and M- have no effect if error displayed
			switch (this.getText()) {
			case "M+":
				if (calculator.getMemorizedNumber() == null) { // If no number in memory, add current displayed number
					calculator.setMemorizedNumber( this.getDisplayNumber() );
				} else { // Else, add current number to memory
					calculator.setMemorizedNumber( calculator.getMemorizedNumber() + this.getDisplayNumber() );
				}
				break;
			case "M-":
				// If last button clicked was "MRC", erase memory
				if ( this.calculator.getLastButtonClicked() != null && this.calculator.getLastButtonClicked().getText().equals("MRC") ) {
					calculator.setMemorizedNumber(null);
				}
				
				if (calculator.getMemorizedNumber() != null) {// Else, substract current number to memory
					calculator.setMemorizedNumber( calculator.getMemorizedNumber() - this.getDisplayNumber() );
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
