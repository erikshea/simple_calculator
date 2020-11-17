package buttons;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.JButton;

import application.Calculator;
public abstract class CalculatorButton extends ImageButton{
	private static final long serialVersionUID = 1L;
	protected Calculator calculator;
	protected static CalculatorButton lastButtonClicked = null;
	protected static HashMap<String,CalculatorButton> buttonsByName = new HashMap<>();
	
	public CalculatorButton(String text, Calculator calc) {
		super(text, "button_" + text); // name is same as text (for image filename purposes)
		this.baseFileName = "button";
		
		this.calculator = calc;
		this.setFont( this.calculator.getBaseFont() );
		CalculatorButton.buttonsByName.put(this.getName(), this);
		
		this.addActionListener( e->{
			this.onClick();
		} );
	}
	
	protected void onClick() {
		CalculatorButton.lastButtonClicked = this;
	};
	
	protected static CalculatorButton getLastButtonClicked() {
		return CalculatorButton.lastButtonClicked;
	}
	
    protected void repaintEvent() {
		super.repaintEvent();
		this.setFont( this.getFont().deriveFont( (float) this.imageDimensions.getWidth()*3/5 ) );
    }
    
    public static void activateButtons(String[] buttonText) {
    	for (String buttonName : buttonText) {
    		CalculatorButton.buttonsByName.get("button_" + buttonName).onClick();
    	}
    }
}
