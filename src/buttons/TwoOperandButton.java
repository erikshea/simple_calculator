package buttons;


public class TwoOperandButton extends CalculatorButton {
	private static final long serialVersionUID = 1307603184797728542L;
	
	
	public TwoOperandButton(String text, Calculator c) {
		super(text, c);
	}
	
	public void onClick() {
	if (!this.calculator.errorIsDisplayed()) {
			if (this.calculator.getCurrentOperatorButton() == null){ 
				// If no previous operator, both operands are displayed number
				this.calculator.setFirstOperand(this.getDisplayNumber());
			} else {
				this.calculator.getCurrentOperatorButton().processOperator();
			}
			
			this.calculator.setCurrentOperatorButton(this);
			super.onClick();
		}
	}

	public void processOperator() {
		if (!this.calculator.errorIsDisplayed()) {
			Double displayedNumber=this.getDisplayNumber();
			
			switch (this.getText()) {
			case "÷":
				 if (displayedNumber == 0) {
					this.calculator.setDisplayText("Cannot divide by zero");
					displayedNumber=null;
				 } else {
					 displayedNumber = this.calculator.getFirstOperand()  / displayedNumber;
				 }
				 break;
				 
			case "×":
				displayedNumber = this.calculator.getFirstOperand()  * displayedNumber;
			break;
			case "-":
				displayedNumber = this.calculator.getFirstOperand()  - displayedNumber;
			break;
			case "+":
				displayedNumber = this.calculator.getFirstOperand()  + displayedNumber;
			break; 
			}	

			this.setDisplayedNumber(displayedNumber);
			this.calculator.setFirstOperand(displayedNumber);
		}
	}
	
	public void processCurrentOperator() {
		if ( this.calculator.getCurrentOperatorButton() != null ) {
			this.calculator.getCurrentOperatorButton().processOperator();
		}
	}
}
