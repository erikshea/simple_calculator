package buttons;

/**
 * Base calculator button class, inherited by all other buttons
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
	 * Resize font according to current button width
	 */
    protected void resizeIcons() {
		super.resizeIcons();
		
		this.setFont( this.getFont().deriveFont( (float) this.getWidth()*3/5 ) );
    }
    

	/**
	 * Get number displayed on calculator screen, as a double
	 * @return double equivalent of number on calculator screen
	 */
	public Double getNumberOnScreen()	{
		Double result = null;
		
		if ( calculator.getTextOnScreen().equals("")) {
			result = 0.;
		} else  if (!this.calculator.errorIsDisplayed()){
			result = Double.parseDouble( calculator.getTextOnScreen() );
		}
		
		return result;
	}
	
	/**
	 * Set number displayed on calculator screen to a double
	 * @param d double to display
	 */
	public void setNumberOnScreen(Double d){
		if (d != null) {
			if ( d  == d.intValue() ) {
				// if double is X.0, remove superfluous decimal fraction
				calculator.setTextOnScreen( Integer.toString(d.intValue()) );
			} else {
				calculator.setTextOnScreen(Double.toString(d));
			}
		}
	}
}
