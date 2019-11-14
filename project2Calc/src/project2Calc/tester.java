package project2Calc;

public class tester {
public static void main (String[] args) {
	
	StackCalculator test = new StackCalculator();
	//String s = "((15 / (7 - (1 + 1))) * 3) - (2 + (1 + 1))";
	//System.out.println(calc.toPostFix(s));
	test.processInput("x = 5");
	test.processInput("x * 6");
	test.processInput("x = 1    2%x * 3 + 12 - 4 + 2**5");
	test.processInput("33 * x + 4");
	test.processInput("y=  8 + 4 - x *2");
	test.processInput("12 * y");
	}
	
	
			
}

