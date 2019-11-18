package project2Calc;

import java.util.Comparator;

/*William Field and Abby Mattingly
 * 11/18/19
 * 
 * Precedence is a comparator class that is used to compare operators precedence in PEMDAS order. '%' is the same precedence As muliplication and division.
 * This class is used by the toPostFix method of StackCalculator
 */

public class Precedence implements Comparator<Character>{
	
	/*Pre Condition-c1 and c2 are characters being compared in the toPostFix method to be pushed to the operator stack
	 * 
	 * Post Condition- returns a positive integer if c1 has greater precedence in order of operations, negative integer if
	 * c2 has greater precedence, and 0 if the precedence of the 2 operators is equal.
	 */
	
	@Override
	public int compare(Character c1, Character c2) {//compares 2 characters precedence
		int i1, i2;
		i1 = intassign(c1);
		i2 = intassign(c2);
		
		return i1 - i2;
	}
	
	/*Pre Condition- char c is an operator that has passed isOperator in StackCalculator
	 * 
	 * Post Condition- an int used to represent the operators precedence is returned to the compare function
	 */
	
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
				//default is val is 0
		}
		
		return val;
	}
	
}
