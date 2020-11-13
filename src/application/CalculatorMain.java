package application;

public class CalculatorMain {

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		calc.setTitle("Calculator");
		calc.setLocation(100, 100);
		calc.pack(); 
		calc.setVisible(true);
		calc.setResizable(false);
	}
	
}
