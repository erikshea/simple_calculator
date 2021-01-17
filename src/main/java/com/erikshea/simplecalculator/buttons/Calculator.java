package com.erikshea.simplecalculator.buttons;

/**
 * CalculatorButton constructor accepts an object that conforms to this interface
 */
public interface Calculator {
	/**
	 * Make calculator display a string
	 * @param s string to set calculator screen to
	 */
	public void setTextOnScreen(String s);
	
	/**
	 * Get text displayed on screen
	 * @return string displayed
	 */
	public String getTextOnScreen();
	
	/**
	 * Is calculator showing an error?
	 * @return true if error, false otherwise
	 */
	public boolean errorIsDisplayed();
	
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
	 * Get last button clicked on calculator interface.
	 * @return last button clicked
	 */
	public CalculatorButton getLastButtonClicked();
	
	/**
	 * Set last button clicked on calculator interface.
	 */
	public void setLastButtonClicked(CalculatorButton b);
	
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
