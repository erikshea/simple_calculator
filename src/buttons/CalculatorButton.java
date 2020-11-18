package buttons;

/**
 * Base calculator button class, inherited by all 
 * @author Erik Shea
 *
 */
public abstract class CalculatorButton extends ImageButton{
	private static final long serialVersionUID = -6193737946831385074L;
	protected Calculator calculator;
	
	public CalculatorButton(String text, Calculator calc) {
		super(text, "button_" + text); // name is based on as text 
		this.baseFileName = "button"; // all calculator buttons share the same image by default
		
		this.calculator = calc;
		this.setFont( this.calculator.getBaseFont() );
		
		this.addActionListener( e->{
			this.onClick();
		} );
	}
	
	/**
	 * Called on every button click
	 */
	public void onClick() {
		calculator.setLastButtonClicked(this); // Store last button clicked in calculator, for calculation logic purposes
	};
	
	/**
	 * Redefine repaintEvent to resize font according to current button width
	 */
	@Override
    protected void repaintEvent() {
		super.repaintEvent();
		
		this.setFont( this.getFont().deriveFont( (float) this.getWidth()*3/5 ) );
    }
    

	/**
	 * Get number displayed on calculator screen, as a double
	 * @return double equivalent of number on calculator screen
	 */
	public Double getDisplayNumber()	{
		Double result;
		
		if (calculator.getDisplayText().equals("")) {
			result = 0.;
		} else {
			result = Double.parseDouble( calculator.getDisplayText() );
		}
		
		return result;
	}
	
	/**
	 * Set number displayed on calculator screen to a double
	 * @param d double to display
	 */
	public void setDisplayedNumber(Double d){
		if (d != null) {
			if ( d  == d.intValue() ) {
				// if double is X.0, remove superfluous decimal fraction
				calculator.setDisplayText( Integer.toString(d.intValue()) );
			} else {
				calculator.setDisplayText(Double.toString(d));
			}
		}
	}
}
