package buttons;

import java.util.ArrayList;
import java.util.HashMap;

import application.Calculator;
public abstract class CalculatorButton extends ImageButton{
	private static final long serialVersionUID = -6193737946831385074L;
	protected Calculator calculator;
	
	public CalculatorButton(String text, Calculator calc) {
		super(text, "button_" + text); // name is same as text (for image filename purposes)
		this.baseFileName = "button";
		
		this.calculator = calc;
		this.setFont( this.calculator.getBaseFont() );
		
		this.addActionListener( e->{
			this.onClick();
		} );
	}
	
	public void onClick() {
		calculator.setLastButtonClicked(this);
	};
	
	
    protected void repaintEvent() {
		super.repaintEvent();
		this.setFont( this.getFont().deriveFont( (float) this.imageDimensions.getWidth()*3/5 ) );
    }
}
