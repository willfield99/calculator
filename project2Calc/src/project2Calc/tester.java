package project2Calc;

public class tester {
public static void main (String[] args) {
	
	StackCalculator test = new StackCalculator();
	System.out.println("Error Test 1");
	System.out.println("Expected output: Unbalanced Parentheses Error, Too Many Left Parentheses");
	test.processInput("((())())(((()))");
	System.out.println("Expected output: Unbalanced Parentheses Error, Mismatched Parentheses");
	test.processInput(")(");
	System.out.println("Expected output: Unbalanced Parentheses Error, Mismatched Parentheses");
	test.processInput("([)]");
	System.out.println("");
	
	}
	
	
			
}

