package project2Calc;

import java.util.Comparator;

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
		/*case '(' :
		case ')':
			val = 4;
			*/
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
				//not a correct operand
		}
		
		return val;
	}
	
}
