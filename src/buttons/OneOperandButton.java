package buttons;

public class OneOperandButton extends CalculatorButton {
	private static final long serialVersionUID = -6671161224512114057L;
	public OneOperandButton(String text, Calculator c) {
		super(text, c);
	}

	public void onClick() {
		if ( !this.calculator.errorIsDisplayed() ) {
			Double displayedNumber=this.getDisplayNumber();
		
			switch (this.getText()) {
			case "√":
				if ( displayedNumber < 0) {
					this.calculator.setDisplayText("Cannot find square root of a negative number.");
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
			
			this.setDisplayedNumber(displayedNumber);
			super.onClick();
		}
		
	}
    protected void repaintEvent() {
		super.repaintEvent();
		this.setFont( this.getFont().deriveFont( (float) this.getWidth()*1/2 ) );
    }
}
