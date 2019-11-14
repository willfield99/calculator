package project2Calc;
//TESTT
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class StackCalculator {

	Stack<Character> ops;//holds the operators
	Queue<Character> exp;//expression is read into this queue
	StringTokenizer st;//tokenizes the strings from the expression queue
	Queue<Object> out;//holds the postfix expression
	HashMap<Character, String> var;//holds the variables
	Precedence p;
	boolean run;//tells the calculator to run
	
	public StackCalculator() {//constructor
		ops = new Stack<Character>();
		exp = new LinkedList<Character>();
		out = new LinkedList<>();
		var = new HashMap<Character, String>(52);
		p = new Precedence();
		run = true;
	}
	
	public void processInput(String ex) {//calculates the value of a postfix expression
		run = true;
		
		ex = ex.replaceAll("\\s","");//taking out whitespacce
	
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
		    	 System.out.println("Error at processInput switch");
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
	
	public String processNewVariable(String ex) {//calculates the value of an expression when creating a new variable 
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
		    	 System.out.println("Error at processInput switch");
		    	 break;
		    }
		}
		}
	String ans = String.valueOf(calc.pop());//the final element in the stack is the answer
	
	return ans;
		
	}

	public boolean run(char i, char s) {//see below
		/*
		 * run is used to check if operator stack should pop before pushing the current character
		 * char i is the current token from the expression queue
		 * char s is the operator at the top of the stack
		 * runs while stacks not empty, top is greater precedence than s,
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
	
	
	
	public ArrayList<String> toPostFix(String s) {//converts a string expression from in fix to post fix
		int count = -1; //where the algorithm is at in the char array, starts at -1 one so that the 
						//first time count increments its at the 0 element of the characters array
		ArrayList<String> postfix = new ArrayList<String>();//the converted postfix expression
		char current;//current character
		s = s.replaceAll("\\s","");//taking all whitespace out of the string
		s = cleanString(s);
		
		char [] characters = s.toCharArray();//holds all the characters in the expression
		
		for(char ch : characters) {//adding all characters to the initial queue
			exp.add(ch);
		}
		
			while(!exp.isEmpty()) {//runs until the exp queue is empty
				current = exp.remove();
				count++;
				
			if(Character.isLetter(current)){//converting variables to their numeric values
				
				String value = var.get(current);
				if(value.equals(null)) {
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
				if(!e.equals("(") && !e.contentEquals(")")){
				postfix.add(e);
				}else if(e.equals(")")) {
					System.out.println("Unbalanced Parentheses Error, Mismatched Parentheses");
					run = false;
				}
				}
			for(String c: postfix) {
				if(c.equals("(")) {
					System.out.println("Unbalanced Parentheses Error, Too Many Left Parentheses");
					run = false;
				}
			}
		return postfix;
	}
	
	private String cleanString(String s) {//takes out double asterisks, double subtraction signs, '{', '[', ']', '}'

		for(int i = 0; i < s.length(); i++) {
			if(i > 0) {
				if(s.charAt(i - 1) == '-' && s.charAt(i) == '-') {
					String pre = s.substring(0, i - 1);
					String post = s.substring(i + 1);
					s = pre + post;				
				}else if(s.charAt(i - 1) == '*' && s.charAt(i) == '*') {
					String pre = s.substring(0, i - 1);
					String post = s.substring(i + 1);
					s = pre + "^" + post;
				}
				
			}
		}
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '{' || s.charAt(i) == '[') {
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
		
	private boolean isOperator(char c) {//used to check if the character is an operand
		Character [] goodops = {'(', ')', '^', '*', '/', '%', '+', '-'};
		for(int i = 0; i < goodops.length; i++) {
			if(c == goodops[i]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isLeftAssociative(char c) {//checks if an object is left associative
		switch(c) {
		//case '^':
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
	
	
