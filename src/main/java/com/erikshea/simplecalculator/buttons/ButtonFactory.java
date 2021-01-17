package com.erikshea.simplecalculator.buttons;

/**
 * Create button objects according to button text
 *
 */
public class ButtonFactory {
	/**
	 * Create and return correct button object. Subclass depends on text parameter.
	 * @param t button text
	 * @param calc object conforming to Calculator interface
	 * @return CalculatorButton subclass
	 */
	public static CalculatorButton create(String text, Calculator calc) {
		return switch(text) { 
			case "=" -> new EqualsButton(text, calc);
			case "%", "√", "±"  -> new OneOperandButton(text, calc);
			case "×", "÷", "+", "-" -> new TwoOperandButton(text, calc);
			case "MRC", "M+", "M-", "CE", "ON/C" -> new FunctionButton(text, calc);
			default -> new DigitButton(text,calc);
		};
	}
	
}
