package project2Calc;

public class tester {
public static void main (String[] args) {
	
	StackCalculator calc = new StackCalculator();
	String s = "((15 / (7 - (1 + 1))) * 3) - (2 + (1 + 1))";
	System.out.println(calc.toPostFix(s));
	System.out.println(calc.processInput(s));
	}
	
	
			
}

