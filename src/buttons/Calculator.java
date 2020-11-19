package buttons;

import java.awt.Font;

/**
 * CalculatorButton constructor accepts an object that conforms to this interface
 *
 */
public interface Calculator {
	
	/**
	 * Get calculator font
	 * @return base font
	 */
	public Font getBaseFont();

	/**
	 * Get max digits allowed on screen
	 * @return max digits
	 */
	public int getMaxDisplaySize();
	
	/**
	 * Make calculator display a string
	 * @param s string to set calculator screen to
	 */
	public void setDisplayText(String s);

	/**
	 * Get text displayed on screen
	 * @return string displayed
	 */
	public String getDisplayText();
	
	/**
	 * Is calculator showing an error?
	 * @return yes if error, false otherwise
	 */
	public boolean errorIsDisplayed();
	
	/**
	 * Get last button clicked on calculator interface.
	 * @return last button clicked
	 */
    public CalculatorButton getLastButtonClicked();
    
    /**
     * Set last button clicked on calculator interface.
     */
    public void setLastButtonClicked(CalculatorButton b);
    
    /**
     * Get number in calculator memory
     * @return number in memory
     */
    public Double getMemorizedNumber();
    
    /**
     * Set number in calculator memory to Double parameter
     * @param n number to set memory to
     */
    public void setMemorizedNumber(Double n);
    
    /**
     * Get current operation button 
     * @return reference to operation button
     */
    public TwoOperandButton getCurrentOperatorButton();

    /**
     * Set current operation button to passed reference
     */
    public void setCurrentOperatorButton(TwoOperandButton b);
}
