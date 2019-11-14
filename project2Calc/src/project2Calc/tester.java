package project2Calc;

public class tester {
public static void main (String[] args) {
	
	StackCalculator test = new StackCalculator();
	//String s = "((15 / (7 - (1 + 1))) * 3) - (2 + (1 + 1))";
	//System.out.println(calc.toPostFix(s));
	System.out.println("Expected output:");
	System.out.println("\t 40");
	System.out.println("\t -12");
	System.out.println("\t 10");
	System.out.println("\t 5");
	test.processInput("--40");
	test.processInput("---12");
	test.processInput("10");
	test.processInput("10 + ---5");
	}
	
	
			
}

