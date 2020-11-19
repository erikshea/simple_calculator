package test;

import org.junit.jupiter.api.Test;

import application.SimpleCalculator;

class CalculatorTest extends SimpleCalculator {
	private static final long serialVersionUID = 7060761027810301604L;

	@Test
	void TestCase_1() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{"1","1","+","2","=","+","3","÷","2","="});
		assert(calc.getTextOnScreen().equals("8"));
	}
	
	@Test
	void TestCase_2() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{"2","±","+","6","±","×","2","±","="});
		assert(calc.getTextOnScreen().equals("16"));
	}
	
	@Test
	void TestCase_3() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{"1","6","√","%","×","1","0","0","="});
		assert(calc.getTextOnScreen().equals("4"));
		
		calc.activateButtons(new String[]{"M+","3","×","MRC","="});
		assert(calc.getTextOnScreen().equals("12"));
		
		calc.activateButtons(new String[]{"ON/C","MRC"});
		assert(calc.getTextOnScreen().equals("4"));
		
		calc.activateButtons(new String[]{"MRC","M-","ON/C","MRC"});
		assert(calc.getTextOnScreen().equals(""));
	}
	
	@Test
	void TestCase_4() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{"1","+","2","="});
		assert(calc.getTextOnScreen().equals("3"));
		
		calc.activateButtons(new String[]{"="});
		assert(calc.getTextOnScreen().equals("5"));

		calc.activateButtons(new String[]{"="});
		assert(calc.getTextOnScreen().equals("7"));
		
		calc.activateButtons(new String[]{"="});
		assert(calc.getTextOnScreen().equals("9"));
		
		calc.activateButtons(new String[]{"="});
		assert(calc.getTextOnScreen().equals("11"));
	}
	
	@Test
	void TestCase_5() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{".","2"});
		assert(calc.getTextOnScreen().equals("0.2"));
	}
	
	@Test
	void TestCase_6() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{"0","1"});
		assert(calc.getTextOnScreen().equals("1"));
	}
	
	@Test
	void TestCase_7() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{"9","÷","3"});
		assert(calc.getTextOnScreen().equals("3"));
	}
	
	@Test
	void TestCase_8() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{"1","0","÷","3","="});
		assert(calc.getTextOnScreen().equals("3.333333333333")); // Max numbers on screen: 14
	}
	
	@Test
	void TestCase_9() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{"1","÷","0","="});
		assert(calc.errorIsDisplayed());
	}
	
	@Test
	void TestCase_9b() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{"1","±","√"});
		assert(calc.errorIsDisplayed());
	}
	
	@Test
	void TestCase_10() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{"5","+","1","±","="});
		assert(calc.getTextOnScreen().equals("4"));
	}
	
	@Test
	void TestCase_11() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{"5","+","-","×","2","="});
		assert(calc.getTextOnScreen().equals("10"));
	}
	
	@Test
	void TestCase_12() {
		SimpleCalculator calc = new SimpleCalculator();
		
		calc.activateButtons(new String[]{"5","+","1","-"});
		assert(calc.getTextOnScreen().equals("6"));
	}
	
	@Test
	void Storyboard_1() {
		SimpleCalculator calc = new SimpleCalculator();
		assert(calc.getTextOnScreen().equals(""));
		
		calc.activateButtons(new String[]{"2"});
		assert(calc.getTextOnScreen().equals("2"));
		
		calc.activateButtons(new String[]{"1"});
		assert(calc.getTextOnScreen().equals("21"));

		calc.activateButtons(new String[]{"%"});
		assert(calc.getTextOnScreen().equals("0.21"));
	}
	
	@Test
	void Storyboard_2() {
		SimpleCalculator calc = new SimpleCalculator();
		assert(calc.getTextOnScreen().equals(""));
		
		calc.activateButtons(new String[]{"2"});
		assert(calc.getTextOnScreen().equals("2"));
		
		calc.activateButtons(new String[]{"1"});
		assert(calc.getTextOnScreen().equals("21"));

		calc.activateButtons(new String[]{"%"});
		assert(calc.getTextOnScreen().equals("0.21"));
	}

	@Test
	void Storyboard_3() {
		SimpleCalculator calc = new SimpleCalculator();
		assert(calc.getTextOnScreen().equals(""));
		
		calc.activateButtons(new String[]{"2"});
		assert(calc.getTextOnScreen().equals("2"));

		calc.activateButtons(new String[]{"+"});
		assert(calc.getTextOnScreen().equals("2"));

		calc.activateButtons(new String[]{"1"});
		assert(calc.getTextOnScreen().equals("1"));
		
		calc.activateButtons(new String[]{"×"});
		assert(calc.getTextOnScreen().equals("3"));
		
		calc.activateButtons(new String[]{"5"});
		assert(calc.getTextOnScreen().equals("5"));
		
		calc.activateButtons(new String[]{"="});
		assert(calc.getTextOnScreen().equals("15"));
	}

	@Test
	void Storyboard_4a() {
		SimpleCalculator calc = new SimpleCalculator();
		assert(calc.getTextOnScreen().equals(""));
		
		calc.activateButtons(new String[]{"2"});
		assert(calc.getTextOnScreen().equals("2"));

		calc.activateButtons(new String[]{"+"});
		assert(calc.getTextOnScreen().equals("2"));

		calc.activateButtons(new String[]{"4"});
		assert(calc.getTextOnScreen().equals("4"));
		
		calc.activateButtons(new String[]{"CE"});
		assert(calc.getTextOnScreen().equals(""));
		
		calc.activateButtons(new String[]{"1"});
		assert(calc.getTextOnScreen().equals("1"));
		
		calc.activateButtons(new String[]{"="});
		assert(calc.getTextOnScreen().equals("3"));
	}
	
	
	@Test
	void Storyboard_4b() {
		SimpleCalculator calc = new SimpleCalculator();
		assert(calc.getTextOnScreen().equals(""));
		
		calc.activateButtons(new String[]{"2"});
		assert(calc.getTextOnScreen().equals("2"));

		calc.activateButtons(new String[]{"+"});
		assert(calc.getTextOnScreen().equals("2"));

		calc.activateButtons(new String[]{"4"});
		assert(calc.getTextOnScreen().equals("4"));
		
		calc.activateButtons(new String[]{"ON/C"});
		assert(calc.getTextOnScreen().equals(""));
		
		calc.activateButtons(new String[]{"1"});
		assert(calc.getTextOnScreen().equals("1"));
		
		calc.activateButtons(new String[]{"="});
		assert(calc.getTextOnScreen().equals("1"));
	}
	
	@Test
	void Memory_Example_1() {
		SimpleCalculator calc = new SimpleCalculator();
		calc.activateButtons(new String[]{"5", "×", "2", "7", "="});
		assert(calc.getTextOnScreen().equals("135"));

		calc.activateButtons(new String[]{"M+", "4", "1", "2", "÷", "4", "="});
		assert(calc.getTextOnScreen().equals("103"));

		calc.activateButtons(new String[]{"M-"});
		assert(calc.getTextOnScreen().equals("103"));
		
		calc.activateButtons(new String[]{"MRC"});
		assert(calc.getTextOnScreen().equals("32"));
		
	}
	
	@Test
	void Memory_Example_2() {
		SimpleCalculator calc = new SimpleCalculator();
		calc.activateButtons(new String[]{"4", "7", "8", "×", "2", "="});
		assert(calc.getTextOnScreen().equals("956"));

		calc.activateButtons(new String[]{"M+", "1", "5", "0", "-", "4", "9", "="});
		assert(calc.getTextOnScreen().equals("101"));
		
		calc.activateButtons(new String[]{"M+", "MRC"});
		assert(calc.getTextOnScreen().equals("1057"));
	}

	
	@Test
	void Memory_Example_3() {
		SimpleCalculator calc = new SimpleCalculator();
		calc.activateButtons(new String[]{"ON/C"});
		assert(calc.getTextOnScreen().equals(""));
		assert(calc.getMemorizedNumber() == null);

		calc.activateButtons(new String[]{"2"});
		assert(calc.getTextOnScreen().equals("2"));
		assert(calc.getMemorizedNumber() == null);
		
		calc.activateButtons(new String[]{"1"});
		assert(calc.getTextOnScreen().equals("21"));
		assert(calc.getMemorizedNumber() == null);
		
		calc.activateButtons(new String[]{"3"});
		assert(calc.getTextOnScreen().equals("213"));
		assert(calc.getMemorizedNumber() == null);
		
		calc.activateButtons(new String[]{"×"});
		assert(calc.getTextOnScreen().equals("213"));
		assert(calc.getMemorizedNumber() == null);

		calc.activateButtons(new String[]{"5"});
		assert(calc.getTextOnScreen().equals("5"));
		assert(calc.getMemorizedNumber() == null);

		calc.activateButtons(new String[]{"="});
		assert(calc.getTextOnScreen().equals("1065"));
		assert(calc.getMemorizedNumber() == null);

		calc.activateButtons(new String[]{"M+"});
		assert(calc.getTextOnScreen().equals("1065"));
		assert(calc.getMemorizedNumber() == 1065);

		calc.activateButtons(new String[]{"4"});
		assert(calc.getTextOnScreen().equals("4"));
		assert(calc.getMemorizedNumber() == 1065);

		calc.activateButtons(new String[]{"2"});
		assert(calc.getTextOnScreen().equals("42"));
		assert(calc.getMemorizedNumber() == 1065);
		
		calc.activateButtons(new String[]{"+"});
		assert(calc.getTextOnScreen().equals("42"));
		assert(calc.getMemorizedNumber() == 1065);
		
		calc.activateButtons(new String[]{"1"});
		assert(calc.getTextOnScreen().equals("1"));
		assert(calc.getMemorizedNumber() == 1065);
		
		calc.activateButtons(new String[]{"0"});
		assert(calc.getTextOnScreen().equals("10"));
		assert(calc.getMemorizedNumber() == 1065);
		
		calc.activateButtons(new String[]{"5"});
		assert(calc.getTextOnScreen().equals("105"));
		assert(calc.getMemorizedNumber() == 1065);
		
		calc.activateButtons(new String[]{"="});
		assert(calc.getTextOnScreen().equals("147"));
		assert(calc.getMemorizedNumber() == 1065);
		
		calc.activateButtons(new String[]{"M+"});
		assert(calc.getTextOnScreen().equals("147"));
		assert(calc.getMemorizedNumber() == 1212);

		calc.activateButtons(new String[]{"1"});
		assert(calc.getTextOnScreen().equals("1"));
		assert(calc.getMemorizedNumber() == 1212);

		calc.activateButtons(new String[]{"2"});
		assert(calc.getTextOnScreen().equals("12"));
		assert(calc.getMemorizedNumber() == 1212);

		calc.activateButtons(new String[]{"9"});
		assert(calc.getTextOnScreen().equals("129"));
		assert(calc.getMemorizedNumber() == 1212);
		
		calc.activateButtons(new String[]{"×"});
		assert(calc.getTextOnScreen().equals("129"));
		assert(calc.getMemorizedNumber() == 1212);

		calc.activateButtons(new String[]{"4"});
		assert(calc.getTextOnScreen().equals("4"));
		assert(calc.getMemorizedNumber() == 1212);

		calc.activateButtons(new String[]{"="});
		assert(calc.getTextOnScreen().equals("516"));
		assert(calc.getMemorizedNumber() == 1212);

		calc.activateButtons(new String[]{"M-"});
		assert(calc.getTextOnScreen().equals("516"));
		assert(calc.getMemorizedNumber() == 696);
		
		calc.activateButtons(new String[]{"MRC"});
		assert(calc.getTextOnScreen().equals("696"));
		assert(calc.getMemorizedNumber() == 696);
	}
}
