package project2Calc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*William Field and Abby Mattingly
 * 11/18/19
 * 
 * The StackCalculator is used to create a StackCalculator Object, which is a fully functioning integer expression calculator. StackCalculator handles variable declarations, parentheses, modulus, exponents, etc...
 * and returns an error if the expression is not properly formatted. The expression is converted to post fix notation using the toPostFix function, which implements the 
 * Shunting-Yard algorithm. The answer of the expression is then calculated in the processInput method. This calculator does not handle decimal input.
 */
public class StackCalculator {

	Stack<Character> ops;//holds the operators
	Queue<Character> exp;//expression is read into this queue
	Queue<Object> out;//holds the postfix expression
	HashMap<Character, String> var;//holds the variables
	Precedence p;//used to check operator precedence during conversion to post fix
	boolean run;//tells the calculator to run
	
	public StackCalculator() {//constructor
		ops = new Stack<Character>();
		exp = new LinkedList<Character>();
		out = new LinkedList<>();
		var = new HashMap<Character, String>(52);
		p = new Precedence();
		run = true;//run defaults to true
	}
	
	/*Pre Condition- String ex contains a mathematical expression or variable declaration.
	 * 
	 * Post Condition- The calculated answer is returned, or the variable declaration is confirmed. If the input was improperly formatted an error is returned.
	*/
	public void processInput(String ex) {//calculates the value of a postfix expression
		run = true;
		
		ex = ex.replaceAll("\\s","");//taking out whitespace
	
		if (ex != null && !ex.isEmpty()) { //runs if the expression string is not empty
		
		if(Character.isLetter(ex.charAt(0)) && ex.charAt(1) == '=') {//assigning variables and inserting them into the map var
		
			char key = ex.charAt(0);//the key of the value
			String val = processNewVariable(ex.substring(2));//the value to be stored
			var.put(key, val);
			System.out.println(key + " is set to " + val);
		}
		
		else {//otherwise process the input as an expression
		
			Stack<Integer> calc = new Stack<Integer>();//the stack that holds the integers
				ArrayList<String> input = toPostFix(ex);//input holds the postfix expression
			if(run == true) {	
				
				int a = 0;//the first integer in the operation
			    int b = 0;//the second integer in the operation
			    int r = 0;//the result of the operation
		for(int i = 0; i < input.size(); i++){
			 a = 0;
			 b = 0;
			 r = 0;
			 String c = input.get(i);//reading the next character of the expression
		   
		    if(c.matches("^[+-]?\\d+$")){//checking if c is an integer, all integers get pushed to the stack
		    try {
		    	Integer t = Integer.parseInt(c);
		    	calc.push(t);//all integers get pushed to the stack
		       
		    } catch (NumberFormatException e) {
		      // not an integer!
		    }		        
		    }
		   
		    else{//each case handles a different operation, the result of the operation is pushed back to the stack
		    	char x = c.charAt(0);
		    	switch(x) {
		    	
		    case'+':
		        a = calc.pop();
		        b = calc.pop();
		        r = b+a;
		        calc.push(r);
		        break;
		    case'-':
		        a = calc.pop();
		        b = calc.pop();
		        r = b - a;
		        calc.push(r);
		        break;
		    case'*':
		        a = calc.pop();
		        b = calc.pop();
		        r = b*a;
		        calc.push(r);
		        break;
		    case '/':
		        a = calc.pop();
		        b = calc.pop();
		        r = b / a;
		        calc.push(r);
		        break;
		    case'%':
		        a = calc.pop();
		        b = calc.pop();
		        r = b % a;
		        calc.push(r);
		        break;
		    case'^':
		        a = calc.pop();
		        b = calc.pop();
		        r = (int) Math.pow(b, a);
		        calc.push(r);
		        break;
		     
		     default:
		    	 System.out.println("Error at processInput switch");//in case somehow an incorrect operator slips by...(i dont think it ever happens but you never know)
		    	 break;
		    }
		}
		}
		if(!calc.isEmpty()) {
			int ans = calc.pop();//the final element in the stack is the answer
			System.out.println(ans);
		}
		else {//If the stack is empty print 0
			System.out.println("0");
		}
		}
		}
		}else {//prints 0 if the expression string is empty
			System.out.println("0");
		}
	}
	
	/*Pre Condition- A new variable has been declared, String ex is the expression that the variable has been set to.
	 * 
	 * Post Condition- The value of the expression and thus the variable is returned. (assuming the expression was properly formatted)
	*/
	
	private String processNewVariable(String ex) {//calculates the value of an expression when creating a new variable 
		//this method returns a string instead of printing out the answer
		
		Stack<Integer> calc = new Stack<Integer>();//the stack that holds the integers
				ArrayList<String> input = toPostFix(ex);
				int a = 0;//the first integer in the operation
			    int b = 0;//the second integer in the operation
			    int r = 0;//the result of the operation
		for(int i = 0; i < input.size(); i++){
			 a = 0;
			 b = 0;
			 r = 0;
			 String c = input.get(i);//reading the next character of the expression
		   
		    if(c.matches("^[+-]?\\d+$")){//checking if c is an integer
		    try {
		    	Integer t = Integer.parseInt(c);
		    	calc.push(t);
		       
		    } catch (NumberFormatException e) {
		      // not an integer!
		    }		        
		    }
		   
		    else{//each case handles a different operation, the result of the operation is pushed back to the stack
		    	char x = c.charAt(0);
		    	switch(x) {
		    	
		    case'+':
		        a = calc.pop();
		        b = calc.pop();
		        r = b+a;
		        calc.push(r);
		        break;
		    case'-':
		        a = calc.pop();
		        b = calc.pop();
		        r = b - a;
		        calc.push(r);
		        break;
		    case'*':
		        a = calc.pop();
		        b = calc.pop();
		        r = b*a;
		        calc.push(r);
		        break;
		    case '/':
		        a = calc.pop();
		        b = calc.pop();
		        r = b / a;
		        calc.push(r);
		        break;
		    case'%':
		        a = calc.pop();
		        b = calc.pop();
		        r = b % a;
		        calc.push(r);
		        break;
		    case'^':
		        a = calc.pop();
		        b = calc.pop();
		        r = (int)Math.pow(b, a);
		        calc.push(r);
		        break;
		     
		     default:
		    	 System.out.println("Error at processInput switch");//in case somehow an incorrect operator slips by...(i dont think it ever happens but you never know)
		    	 break;
		    }
		}
		}
	String ans = String.valueOf(calc.pop());//the final element in the stack is the answer
	
	return ans;//this answer is the value of the new variable
		
	}
	
	/*Pre Condition- char i is the current token from the expression queue, char s is the operator at the top of the stack. This method determines whether
	 * the operator stack used in toPostFix should pop before pushing the current character.
	 * 
	 * Post Condition- returns true if the operator stack should pop before pushing the current character.
	*/

	private boolean run(char i, char s) {//see below
		/*	runs while stacks not empty, top is greater precedence than s,
		 *  or top is equal precedence and left associative, and s is not a left parenthesis 
		 */ 
		if(!ops.isEmpty()) {					
		if((0 > p.compare(i, s) || (0 == p.compare(i, s) && isLeftAssociative(i))) && s != '(') {
			return true;
		}else {
			return false;
		}
		}
		return false;
	}
	
	/*Pre Condition-An expression in string form with white space removed is entered
	 * 
	 *Post Condition-returns a true boolean and allows the toPostFix method to run if proper variable names are used, defined variables are used, proper symbols are used,
	 *and parentheses are balanced/equal.
	 */
	
	private boolean checkString(String s) {//checks the string for proper formatting before putting it into the postfix algorithm
		int p, b, c;//represent parentheses, brackets, and curly brackets
		p = b = c = 0;
		char [] characters = s.toCharArray();
		Stack<Character> balance = new Stack<Character>();//used to check if parentheses are mismatched
		int i = 0;//index of the for loop
				
		for(char character: characters) {
			if(Character.isLetter(character)) {//handling variable related errors
				String value = var.get(character);
				
				if(i < characters.length && value == null && Character.isLetter(characters[i + 1])) {//handles variable names longer than 1 letter in length
					int j = i + 1;
					
					while(j < characters.length && Character.isLetter(characters[j])) {
						j ++;
					}
					System.out.println("Invalid Variable Name " + s.substring(i, j));
					return false;
				}else if(value == null) {//handles undefined variables (character is not a key to the hashmap var)
					System.out.println("Undefined Variable " + character);
					return false;
				}
				}else if(!Character.isDigit(character) && !Character.isLetter(character)) {
					if(!isOperator(character)) {							//handles invalid symbols
						System.out.println("Invalid Symbol" + character);
						return false;
					}
					
				else if(isOperator(character)) {//handling mismatched parentheses, right parentheses should always pop left parentheses
						switch(character) {
						case '(':
							p ++;
							balance.push(character);
							break;
						case ')':
							p--;
							if(balance.isEmpty() || balance.pop() != '(') {
								System.out.println("Unbalanced Parentheses Error, Mismatched Parentheses");
								return false;
							}
							break;
						case '[':
							b ++;
							balance.push(character);
							break;
						case ']':
							b --;
							if(balance.isEmpty() || balance.pop() != '[') {
								System.out.println("Unbalanced Parentheses Error, Mismatched Parentheses");
								return false;
							}
							break;
						case '{':
							c ++;
							balance.push(character);
							break;
						case '}':
							c --;
							if(balance.isEmpty() || balance.pop() != '{') {
								System.out.println("Unbalanced Parentheses Error, Mismatched Parentheses");
								return false;
							}
								break;
							default:
		
							}
						
						}
					}
			i++;
				}
		if (p > 0 || b > 0 || c > 0) {//if there are more left than right parentheses at the end of the loop
			System.out.println("Unbalanced Parentheses Error, Too Many Left Parentheses");
			return false;
		}
		
		return true;
	}
	
	/*Pre Condition-String s is an expression that has had whitespace removed, and gone through checkString and cleanString.
	 * 
	 *Post Condition- Returns true and allows toPostFix to continue to run if the string does not contain any nonsensical input (ie: 1 +-* 3)
	 */
	
	private boolean checkNonsensical(String s) {//handling nonsensical input
		char [] characters = s.toCharArray();
		Queue<Character> expression = new LinkedList<Character>();
		char current;
		for(char ch : characters) {//adding all characters to the expression queue
			expression.add(ch);
		}
		int count = 0;//count should never be above 1
		while(!expression.isEmpty()) {//goes through the queue, looking for consecutive operators
			current = expression.remove();
			if(isOperator(current) && current != '(' && current != ')' && !(current == '-' && !isOperator(expression.peek()))) {//parentheses and minuses that 
				//signal negative numbers do not increase count
				count ++;
				if(count > 1) {
					System.out.println("Nonsensical Input " + s);//printing the error
					return false;
				}
			}else if(current == '(' || current == ')' || !isOperator(current)) {//count gets at each number or variable
				count = 0;
			}
		}
		
		return true;
	}
	
	/*Pre Condition-String s is being processed by processInput or is the the expression of a variable declaration
	 * 
	 *Post Condition- s is returned in post fix form if s has been properly formatted. Otherwise an error is returned.
	 */
	
	private ArrayList<String> toPostFix(String s) {//converts a string expression from in fix to post fix
		int count = -1; //where the algorithm is at in the char array, starts at -1 one so that the 
						//first time count increments its at the 0 element of the characters array
		ArrayList<String> postfix = new ArrayList<String>();//the converted postfix expression
		char current;//current character
		s = s.replaceAll("\\s","");//taking all whitespace out of the string
		
		if(checkString(s)) {//checking the strings format
			s = cleanString(s);
			char [] characters = s.toCharArray();//holds all the characters in the expression
			
		if(checkNonsensical(s)) {
			
		for(char ch : characters) {//adding all characters to the initial queue
			exp.add(ch);
		}
		
			while(!exp.isEmpty()) {//runs until the exp queue is empty
				current = exp.remove();
				count++;
				
			if(Character.isLetter(current)){//converting variables to their numeric values
				
				String value = var.get(current);
				if(value == null) {
					System.out.println("Undefined variable " + current);
				}else {
					out.add(value);
				}
				
			}else if(Character.isDigit(current)) {//adding all numbers to out queue			 
				String ch = String.valueOf(current);				
				char j;	
				while(!exp.isEmpty() && Character.isDigit(exp.peek())) {//combining multi digit numbers into a string
					
					  j = exp.remove();
					  count++;
					  ch += String.valueOf(j);
				}				
				out.add(ch);//adding to out queue
				
			}
			else if(isOperator(current)) {
				if(count == 0 && current == '-' || (current == '-' && isOperator(characters[count - 1]))) {//creates a string to represent a negative integer when
																											//there is a negative sign at the start of expression or after another operator
						String ch = String.valueOf(current);				
						char j;	
						while(!exp.isEmpty() && Character.isDigit(exp.peek())) {
							
							  j = exp.remove();
							  count++;
							  ch += String.valueOf(j);
						}				
						out.add(ch);
						
					}
				
			else if(ops.isEmpty() || current == '(') {//auto push if ops stack is null or c is a left parentheses
					ops.push(current);
					
				}else if(current == ')'){
					while(ops.peek() != '(') {//pops and pushes to output queue until finding a left parentheses
						out.add(ops.pop());
					}
					ops.pop();//popping the left parentheses from the ops stack
				}
					else {
				
					char top = ops.peek();
					
					if (top == '(') {
						ops.push(current);
					}else {
					
					
						while(run(current, top)) {
					
						out.add(ops.pop());
					
					if(!ops.isEmpty()) {//if the stacks not empty set a new top
						top = ops.peek();
					}
						}
						
					
					ops.push(current);//push c after running stops
				}
					
					}
			}
		}
			while(!ops.isEmpty()) {//adding rest of operator stack to output queue
				String as = String.valueOf(ops.pop());
				out.add(as);
				
			}
		
			while(!out.isEmpty() && run == true) {
				String e = String.valueOf(out.remove());
				//if(!e.equals("(") && !e.contentEquals(")")){
				postfix.add(e);
				//}
			}
			return postfix;
		}
		run = false;
		return postfix;
		}
		run = false;
		return postfix;
	}
	
	/*Pre Condition-String s is an expression that has had whitespace removed and gone through checkString.
	 * 
	 *Post Condition-s is returned with out double asterisks, double subtraction signs, and '{', '[', ']', '}'. these symbols are replaced
	 *with mathematically equivalent symbols that simplify the expression resulting in a smoother conversion to post fix.
	 * 
	 */
	
	private String cleanString(String s) {//takes out double asterisks, double subtraction signs, and '{', '[', ']', '}'.

		for(int i = 0; i < s.length(); i++) {
			if(i > 0) {
				if(s.charAt(i - 1) == '-' && s.charAt(i) == '-') {//taking out double subtraction signs
					String pre = s.substring(0, i - 1);
					String post = s.substring(i + 1);
					s = pre + post;				
				}else if(s.charAt(i - 1) == '*' && s.charAt(i) == '*') {//taking out double multiplication signs and replacing with ^
					String pre = s.substring(0, i - 1);
					String post = s.substring(i + 1);
					s = pre + "^" + post;
				}
				
			}
		}
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '{' || s.charAt(i) == '[') {// replacing brackets and curly brackets with parentheses
				String pre = s.substring(0, i);
				String post = s.substring(i + 1);
				s = pre + "(" + post;
			}else if(s.charAt(i) == '}' || s.charAt(i) == ']') {
				String pre = s.substring(0, i);
				String post = s.substring(i + 1);
				s = pre + ")" + post;
			}
		}
		
		return s;
	}
	
	/*Pre Condition- char c is a symbol used in the expression
	 * 
	 *Post Condition-true is returned if the symbol is a valid operator for the calculator
	 */
		
	private boolean isOperator(char c) {//used to check if the character is a correct operator
		Character [] goodops = {'{', '}', '[', ']', '(', ')', '^', '*', '/', '%', '+', '-'};
		for(int i = 0; i < goodops.length; i++) {
			if(c == goodops[i]) {
				return true;
			}
		}
		return false;
	}
	
	/*Pre Condition-char c is a symbol that has returned true in isOperator
	 * 
	 *Post Condition-true is returned if the symbol is a left associative operator that is used by the calculator.
	 */
	
	private boolean isLeftAssociative(char c) {//checks if a character is left associative
		switch(c) {
		case '%':
		case '/':
		case '-':
		case '+':
		case '*':
			return true;			
		default:
			return false;
			}
	}			
	}