package buttons;

import application.Calculator;

public class ButtonFactory {
	public static CalculatorButton create(String t, Calculator calc) {
		switch(t) {
			case "%": case "√": case "±":
				return new OneOperandButton(t, calc);
			case "×": case "÷": case "+": case "-":
				return new TwoOperandButton(t, calc);
			case "MRC": case "M+": case "M-": case "CE": case "ON-C": case "=":
				return new FunctionButton(t, calc);
			default:
				return new DigitButton(t,calc);
		}
	}
	
}
