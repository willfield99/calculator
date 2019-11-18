package project2Calc;

import java.util.Comparator;

/*William Field and Abby Mattingly
 * 11/18/19
 * 
 * Precedence is a comparator class that is used to compare operators precedence in PEMDAS order. '%' is the same precedence As muliplication and division.
 * This class is used by the toPostFix method of StackCalculator
 * 
 */
public class Precedence implements Comparator<Character>{

	@Override
	public int compare(Character c1, Character c2) {//compares 2 characters precedence
		int i1, i2;
		i1 = intassign(c1);
		i2 = intassign(c2);
		
		return i1 - i2;
	}
	
	private int intassign(char c) {//assigns int values to characters precedence to compare them
		int val = 0;
		switch(c) {
		case '^':
			val = 3;
			break;
		
		case '*':
		case '%':
		case '/':
			val = 2;
			break;
		case '+':
		case '-':
			val = 1;
			break;
			
			default:
				System.out.println("Error: Invalid Operator");//not a correct operand
		}
		
		return val;
	}
	
}
