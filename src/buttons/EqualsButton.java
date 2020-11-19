package buttons;

/**
 * "Equals" button processes current operator, and deals with the special case of repeated clicks on it.
 */
public class EqualsButton extends CalculatorButton {
	private static final long serialVersionUID = 1269194353540007205L;
	private Double currentOperandForRepeatedClicks=null;
	private TwoOperandButton currentOperatorForRepeatedClicks=null;
	
	public EqualsButton(String text, Calculator c) {
		super(text, c);
	}
	
	public void onClick() {
		if (!this.calculator.errorIsDisplayed()) {
			// Special case: if repeated clicks on same (equal) button
			if (this.equals(this.calculator.getLastButtonClicked()) && this.currentOperatorForRepeatedClicks != null) {
				// current result becomes first operand
				this.currentOperatorForRepeatedClicks.firstOperand = this.getNumberOnScreen();
				// Result from previous operation becomes second operand 
				this.setNumberOnScreen(currentOperandForRepeatedClicks);
				
				this.currentOperatorForRepeatedClicks.processOperator(); // Process last current operator
			} else if (this.calculator.getCurrentOperatorButton() != null) { // Else, if we already entered an operator
				currentOperandForRepeatedClicks = this.getNumberOnScreen(); // Current operation's left operand is saved in case we click again on equal
				// Current operation is saved in case we click again on equal (this.calculator.getCurrentOperatorButton() is reset when processed)
				this.currentOperatorForRepeatedClicks = this.calculator.getCurrentOperatorButton();

				this.calculator.getCurrentOperatorButton().processOperator();
			}
		}
		super.onClick();
	}
}
