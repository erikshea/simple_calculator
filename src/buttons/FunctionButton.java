package buttons;

import java.awt.Color;
import java.awt.Font;

public class FunctionButton extends CalculatorButton {
	private static final long serialVersionUID = 1269194353540007205L;
	
	public FunctionButton(String text, Calculator c) {
		super(text, c);
		if (this.getText() == "ON/C") {
			this.baseFileName = "button_on";
			this.setForeground(Color.white);
		}
	}
	
	public void onClick() {
		switch (this.getText()) {
		case "=":
			if (this.calculator.getCurrentOperatorButton() != null) {
				this.calculator.getCurrentOperatorButton().processOperator();
			}
			
			this.calculator.setCurrentOperatorButton(null);
			break;
		case "ON/C":
			calculator.setMemorizedNumber(null);
			this.calculator.setCurrentOperatorButton(null);
		case "CE":
			this.calculator.setDisplayText("");
			break;
		case "MRC":
			if (calculator.getMemorizedNumber() != null) {
				this.setDisplayedNumber( calculator.getMemorizedNumber() );
			}
			break;
		}
		
		if (!this.calculator.errorIsDisplayed()) {
			switch (this.getText()) {
			case "M+":
				if (calculator.getMemorizedNumber() == null) {
					calculator.setMemorizedNumber( this.getDisplayNumber() );
				} else {
					calculator.setMemorizedNumber( calculator.getMemorizedNumber() + this.getDisplayNumber() );
				}
				break;
			case "M-":
				if ( this.calculator.getLastButtonClicked() != null && this.calculator.getLastButtonClicked().getText().equals("MRC") ) {
					calculator.setMemorizedNumber(null);
				}
				
				if (calculator.getMemorizedNumber() != null) {
					calculator.setMemorizedNumber( calculator.getMemorizedNumber() - this.getDisplayNumber() );
				}
				break;
			}
		}
		super.onClick();
	}
	
    protected void repaintEvent() {
		super.repaintEvent();
		this.setFont( new Font(this.getFont().getFamily(), Font.BOLD, (int) ( this.getWidth()*1/3 )) );
    }

}
