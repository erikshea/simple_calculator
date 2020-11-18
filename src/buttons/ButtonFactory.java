package buttons;

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
		switch(text) {
			case "%": case "√": case "±":
				return new OneOperandButton(text, calc);
			case "×": case "÷": case "+": case "-":
				return new TwoOperandButton(text, calc);
			case "MRC": case "M+": case "M-": case "CE": case "ON/C": case "=":
				return new FunctionButton(text, calc);
			default:
				return new DigitButton(text,calc);
		}
	}
	
}
