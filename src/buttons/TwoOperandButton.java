package buttons;

/**
 * Button for operations that involve two operands, and an operator
 *
 */
public class TwoOperandButton extends CalculatorButton {
	private static final long serialVersionUID = 1307603184797728542L;
	public Double firstOperand;
	
	public TwoOperandButton(String text, Calculator c) {
		super(text, c);
	}
	
	public void onClick() {
	if (!this.calculator.errorIsDisplayed()) {
			if (this.calculator.getCurrentOperatorButton() == null) {
				this.calculator.setCurrentOperatorButton(this);
			} else {
				this.calculator.getCurrentOperatorButton().processOperator();
			}

			this.firstOperand = this.getDisplayNumber();
			this.calculator.setCurrentOperatorButton(this);
			super.onClick();
		}
	}
	
	
	public void processOperator() {
		if (!this.calculator.errorIsDisplayed()) {
			Double result=null;

			switch (this.getText()) {
			case "÷":
				 if (this.getDisplayNumber() == 0) { // Show error on division by 0
					this.calculator.setDisplayText("Cannot divide by zero");
					result=null;
				 } else {
					 result = this.firstOperand  / this.getDisplayNumber();
				 }
				 break;
				 
			case "×":
				result = this.firstOperand  * this.getDisplayNumber();
			break;
			case "-":
				result = this.firstOperand  - this.getDisplayNumber();
			break;
			case "+":
				result = this.firstOperand  + this.getDisplayNumber();
			break; 
			}	

			this.firstOperand = this.getDisplayNumber();
			
			this.setDisplayNumber(result);
			this.calculator.setCurrentOperatorButton(null);
		}
	}
}
