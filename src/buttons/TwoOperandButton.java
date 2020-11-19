package buttons;

/**
 * Button for operations that involve two operands, and an operator
 *
 */
public class TwoOperandButton extends CalculatorButton {
	private static final long serialVersionUID = 1307603184797728542L;
	private Double firstOperand;
	
	public TwoOperandButton(String text, Calculator c) {
		super(text, c);
	}
	
	public void onClick() {
	if (!this.calculator.errorIsDisplayed()) {
			if (this.calculator.getCurrentOperatorButton() == null) {
				this.calculator.setCurrentOperatorButton(this);
			} else 	// If last button clicked was another TwoOperandButton, don't process
				 	//  so that new operator clicked becomes current operator 
			if (!this.getClass().isInstance(this.calculator.getLastButtonClicked())){
				this.calculator.getCurrentOperatorButton().processOperator();
			}

			this.firstOperand = this.getNumberOnScreen();
			this.calculator.setCurrentOperatorButton(this);
			super.onClick();
		}
	}
	
	
	public void processOperator() {
		if (!this.calculator.errorIsDisplayed()) {
			Double result=null;

			switch (this.getText()) {
			case "÷":
				 if (this.getNumberOnScreen() == 0) { // Show error on division by 0
					this.calculator.setTextOnScreen("Cannot divide by zero");
					result=null;
				 } else {
					 result = this.firstOperand  / this.getNumberOnScreen();
				 }
				 break;
				 
			case "×":
				result = this.firstOperand  * this.getNumberOnScreen();
			break;
			case "-":
				result = this.firstOperand  - this.getNumberOnScreen();
			break;
			case "+":
				result = this.firstOperand  + this.getNumberOnScreen();
			break; 
			}	

			this.firstOperand = this.getNumberOnScreen();
			
			this.setNumberOnScreen(result);
			this.calculator.setCurrentOperatorButton(null);
		}
	}
	
	public Double getFirstOperand() {
		return this.firstOperand;
	}
	
	public void setFirstOperand(Double n) {
		this.firstOperand = n;
	}
}
