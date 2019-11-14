package project2Calc;

public class tester {
public static void main (String[] args) {
	
	StackCalculator test = new StackCalculator();
	
	 System.out.println("Expected output:");
		System.out.println("\t 0");
		System.out.println("\t 0");
		System.out.println("\t 4");
		System.out.println("\t 4");
		test.processInput("");
		test.processInput("((()()())())");
		test.processInput("4");
		test.processInput("((4))");
	}
	
	
			
}

