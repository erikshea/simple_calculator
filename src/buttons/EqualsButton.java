package buttons;

/**
 * "Equals" button processes current operator, and deals with the special case of repeated clicks on it.
 */
public class EqualsButton extends CalculatorButton {
	private static final long serialVersionUID = 1269194353540007205L;
	private TwoOperandButton currentOperatorForRepeatedClicks=null;
	
	public EqualsButton(String text, Calculator c) {
		super(text, c);
	}
	
	public void onClick() {
		if (!this.calculator.errorIsDisplayed()) {
			// If we already entered an operator, and we aren't repeating a click on "Equals" button
			if (this.calculator.getCurrentOperatorButton() != null && !this.equals(this.calculator.getLastButtonClicked())) {
				// Current operation is saved in case we click again on equal
				this.currentOperatorForRepeatedClicks = this.calculator.getCurrentOperatorButton();
				this.calculator.getCurrentOperatorButton().processOperator();
			} // Special case: if repeated clicks on "equal"
			else if (this.currentOperatorForRepeatedClicks != null) {
				// this is actually the second operand of previous operation (saved as first operand on operation completion)
				Double temp = this.currentOperatorForRepeatedClicks.getFirstOperand(); 
				
				// result from previous operation becomes first operand 
				this.currentOperatorForRepeatedClicks.setFirstOperand(this.getNumberOnScreen()); 
				this.setNumberOnScreen(temp); // second operand of previous operation becomes second operand of new operation  
				
				this.currentOperatorForRepeatedClicks.processOperator();
			} 
		}
		super.onClick();
	}
}
