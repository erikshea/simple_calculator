package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Calculator;
import buttons.CalculatorButton;

class CalculatorTest extends Calculator {
	private static final long serialVersionUID = 7060761027810301604L;

	@Test
	void Storyboard_1() {
		Calculator calc = new Calculator();
		assert(calc.getDisplayText().equals(""));
		
		calc.activateButtons(new String[]{"2"});
		assert(calc.getDisplayText().equals("2"));
		
		calc.activateButtons(new String[]{"1"});
		assert(calc.getDisplayText().equals("21"));

		calc.activateButtons(new String[]{"%"});
		assert(calc.getDisplayText().equals("0.21"));
	}
	
	@Test
	void Storyboard_2() {
		Calculator calc = new Calculator();
		assert(calc.getDisplayText().equals(""));
		
		calc.activateButtons(new String[]{"2"});
		assert(calc.getDisplayText().equals("2"));
		
		calc.activateButtons(new String[]{"1"});
		assert(calc.getDisplayText().equals("21"));

		calc.activateButtons(new String[]{"%"});
		assert(calc.getDisplayText().equals("0.21"));
	}

	@Test
	void Storyboard_3() {
		Calculator calc = new Calculator();
		assert(calc.getDisplayText().equals(""));
		
		calc.activateButtons(new String[]{"2"});
		assert(calc.getDisplayText().equals("2"));

		calc.activateButtons(new String[]{"+"});
		assert(calc.getDisplayText().equals("2"));

		calc.activateButtons(new String[]{"1"});
		assert(calc.getDisplayText().equals("1"));
		
		calc.activateButtons(new String[]{"×"});
		assert(calc.getDisplayText().equals("3"));
		
		calc.activateButtons(new String[]{"5"});
		assert(calc.getDisplayText().equals("5"));
		
		calc.activateButtons(new String[]{"="});
		assert(calc.getDisplayText().equals("15"));
	}

	@Test
	void Storyboard_4a() {
		Calculator calc = new Calculator();
		assert(calc.getDisplayText().equals(""));
		
		calc.activateButtons(new String[]{"2"});
		assert(calc.getDisplayText().equals("2"));

		calc.activateButtons(new String[]{"+"});
		assert(calc.getDisplayText().equals("2"));

		calc.activateButtons(new String[]{"4"});
		assert(calc.getDisplayText().equals("4"));
		
		calc.activateButtons(new String[]{"CE"});
		assert(calc.getDisplayText().equals(""));
		
		calc.activateButtons(new String[]{"1"});
		assert(calc.getDisplayText().equals("1"));
		
		calc.activateButtons(new String[]{"="});
		assert(calc.getDisplayText().equals("3"));
	}
	
	
	@Test
	void Storyboard_4b() {
		Calculator calc = new Calculator();
		assert(calc.getDisplayText().equals(""));
		
		calc.activateButtons(new String[]{"2"});
		assert(calc.getDisplayText().equals("2"));

		calc.activateButtons(new String[]{"+"});
		assert(calc.getDisplayText().equals("2"));

		calc.activateButtons(new String[]{"4"});
		assert(calc.getDisplayText().equals("4"));
		
		calc.activateButtons(new String[]{"ON/C"});
		assert(calc.getDisplayText().equals(""));
		
		calc.activateButtons(new String[]{"1"});
		assert(calc.getDisplayText().equals("1"));
		
		calc.activateButtons(new String[]{"="});
		assert(calc.getDisplayText().equals("1"));
	}
	
	@Test
	void Memory_Example_1() {
		Calculator calc = new Calculator();
		calc.activateButtons(new String[]{"5", "×", "2", "7", "="});
		assert(calc.getDisplayText().equals("135"));

		calc.activateButtons(new String[]{"M+", "4", "1", "2", "÷", "4", "="});
		assert(calc.getDisplayText().equals("103"));

		calc.activateButtons(new String[]{"M-"});
		assert(calc.getDisplayText().equals("103"));
		
		calc.activateButtons(new String[]{"MRC"});
		assert(calc.getDisplayText().equals("32"));
		
	}
	
	@Test
	void Memory_Example_2() {
		Calculator calc = new Calculator();
		calc.activateButtons(new String[]{"4", "7", "8", "×", "2", "="});
		assert(calc.getDisplayText().equals("956"));

		calc.activateButtons(new String[]{"M+", "1", "5", "0", "-", "4", "9", "="});
		assert(calc.getDisplayText().equals("101"));
		
		calc.activateButtons(new String[]{"M+", "MRC"});
		assert(calc.getDisplayText().equals("1057"));
	}
	
	
	@Test
	void Memory_Example_3() {
		Calculator calc = new Calculator();
		calc.activateButtons(new String[]{"ON/C"});
		assert(calc.getDisplayText().equals(""));
		assert(calc.getMemorizedNumber() == null);

		calc.activateButtons(new String[]{"2"});
		assert(calc.getDisplayText().equals("2"));
		assert(calc.getMemorizedNumber() == null);
		
		calc.activateButtons(new String[]{"1"});
		assert(calc.getDisplayText().equals("21"));
		assert(calc.getMemorizedNumber() == null);
		
		calc.activateButtons(new String[]{"3"});
		assert(calc.getDisplayText().equals("213"));
		assert(calc.getMemorizedNumber() == null);
		
		calc.activateButtons(new String[]{"×"});
		assert(calc.getDisplayText().equals("213"));
		assert(calc.getMemorizedNumber() == null);

		calc.activateButtons(new String[]{"5"});
		assert(calc.getDisplayText().equals("5"));
		assert(calc.getMemorizedNumber() == null);

		calc.activateButtons(new String[]{"="});
		assert(calc.getDisplayText().equals("1065"));
		assert(calc.getMemorizedNumber() == null);

		calc.activateButtons(new String[]{"M+"});
		assert(calc.getDisplayText().equals("1065"));
		assert(calc.getMemorizedNumber() == 1065);

		calc.activateButtons(new String[]{"4"});
		assert(calc.getDisplayText().equals("4"));
		assert(calc.getMemorizedNumber() == 1065);

		calc.activateButtons(new String[]{"2"});
		assert(calc.getDisplayText().equals("42"));
		assert(calc.getMemorizedNumber() == 1065);
		
		calc.activateButtons(new String[]{"+"});
		assert(calc.getDisplayText().equals("42"));
		assert(calc.getMemorizedNumber() == 1065);
		
		calc.activateButtons(new String[]{"1"});
		assert(calc.getDisplayText().equals("1"));
		assert(calc.getMemorizedNumber() == 1065);
		
		calc.activateButtons(new String[]{"0"});
		assert(calc.getDisplayText().equals("10"));
		assert(calc.getMemorizedNumber() == 1065);
		
		calc.activateButtons(new String[]{"5"});
		assert(calc.getDisplayText().equals("105"));
		assert(calc.getMemorizedNumber() == 1065);
		
		calc.activateButtons(new String[]{"="});
		assert(calc.getDisplayText().equals("147"));
		assert(calc.getMemorizedNumber() == 1065);
		
		calc.activateButtons(new String[]{"M+"});
		assert(calc.getDisplayText().equals("147"));
		assert(calc.getMemorizedNumber() == 1212);

		calc.activateButtons(new String[]{"1"});
		assert(calc.getDisplayText().equals("1"));
		assert(calc.getMemorizedNumber() == 1212);

		calc.activateButtons(new String[]{"2"});
		assert(calc.getDisplayText().equals("12"));
		assert(calc.getMemorizedNumber() == 1212);

		calc.activateButtons(new String[]{"9"});
		assert(calc.getDisplayText().equals("129"));
		assert(calc.getMemorizedNumber() == 1212);
		
		calc.activateButtons(new String[]{"×"});
		assert(calc.getDisplayText().equals("129"));
		assert(calc.getMemorizedNumber() == 1212);

		calc.activateButtons(new String[]{"4"});
		assert(calc.getDisplayText().equals("4"));
		assert(calc.getMemorizedNumber() == 1212);

		calc.activateButtons(new String[]{"="});
		assert(calc.getDisplayText().equals("516"));
		assert(calc.getMemorizedNumber() == 1212);

		calc.activateButtons(new String[]{"M-"});
		assert(calc.getDisplayText().equals("516"));
		assert(calc.getMemorizedNumber() == 696);
		
		calc.activateButtons(new String[]{"MRC"});
		assert(calc.getDisplayText().equals("696"));
		assert(calc.getMemorizedNumber() == 696);
	}
	
	
	
}
