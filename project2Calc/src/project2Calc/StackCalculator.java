package project2Calc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class StackCalculator {

	Stack<Character> ops;//holds the operators
	Queue<Character> exp;//expression is read into this queue
	
	StringTokenizer st;//tokenizes the strings from the expression queue
	Queue<Object> out;//holds the postfix expression
	ArrayList<String> vars;//holds the declared variables
	Precedence p;
	
	public StackCalculator() {//constructor
		ops = new Stack<Character>();
		exp = new LinkedList<Character>();
		out = new LinkedList<>();
		vars = new ArrayList<>();
		p = new Precedence();
		
	}
	
	public void processInput(String ex) {//calculates the value of a postfix expression
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
		        r = b^a;
		        calc.push(r);
		        break;
		     
		     default:
		    	 System.out.println("Error at processInput switch");
		    	 break;
		    }
		}
		}
	int ans = calc.pop();//the final element in the stack is the answer
	
	System.out.print(ans);
		
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
		ArrayList<String> postfix = new ArrayList<String>();//the converted postfix expression
		char c;//current character
		
		//Scanner in  = new Scanner(s);
		s = s.replaceAll("\\s","");//taking all whitespace out of the string
		char [] characters = s.toCharArray();//holds all the characters in the expression
		
			
		for(char ch : characters) {//adding all characters to the initial queue
			exp.add(ch);
		}
			while(!exp.isEmpty()) {//runs until the x
				c = exp.remove();
			
			if( c >= '0' && c <= '9' ) {//adding all numbers to out queue
				String ch = String.valueOf(c);
			//	int i = Character.getNumericValue(c);
				char j;
				while(exp.peek() >= '0' && exp.peek() <= '9' ) {
					  j = exp.remove();
					  //i = (i*10) + j;
					  
					  ch += String.valueOf(j);
				}
				
				out.add(ch);
				
			}else if(isOperator(c)) {
				
				if(ops.isEmpty() || c == '(') {//auto push if ops stack is null or c is a left parentheses
					ops.push(c);
					
				}else if(c == ')'){
					while(ops.peek() != '(') {//pops and pushes to output queue until finding a left parentheses
						out.add(ops.pop());
					}
					ops.pop();//popping the left parentheses from the ops stack
				}
					else {
				
					char top = ops.peek();
					
					if (top == '(') {
						ops.push(c);
					}else {
					
					
						while(run(c, top)) {
					
						out.add(ops.pop());
					
					if(!ops.isEmpty()) {//if the stacks not empty set a new top
						top = ops.peek();
					}
						}
						
					
					ops.push(c);//push c after running stops
				}
					
					}
			}
		}
			while(!ops.isEmpty()) {//adding rest of operator stack to output queue
				out.add(ops.pop());
				
			}
		
			while(!out.isEmpty()) {
				String e = String.valueOf(out.remove());
				postfix.add(e);
			}
		return postfix;
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
	
	/*
	private int toInteger(Object object) {//converts an object to an integer
		int i;
		if(isInteger(object)) {
			String string = object.toString();
    		
    		try {
    			i = Integer.parseInt(string);
    			return i;
    		} catch(Exception e) {
    			System.out.println("ERROR: not an integer");
    		}	
		}
			System.out.println("ERROR: not an integer");//if the object cannot be parsed for an integer
			return 0;
		
	}
	private boolean isInteger(Object object) {//checks if an object is an integer
   
    	if(object instanceof Integer) {
    		return true;
    	} else {
    	   		
    		String string = object.toString();
    		
    		try {
    			Integer.parseInt(string);
    		} catch(Exception e) {
    			return false;
    		}	
    	}
      
        return false;
    }
    */
	private void lettervar(String s) {//adds a variable with correct syntax to the list vars
		String cur;//current character
		String add;//new variable to be added
		st = new StringTokenizer(s);
		cur = st.nextToken();
		
		if(Character.isLetter(cur.charAt(0))) {//checking that first character is a valid variable name
			add = cur;
			cur = st.nextToken();
			if(cur.equals("=")) {
				add = s.substring(2);//the expression after the = sign is the value of the new variable
			//	vars.add(Integer.toString(processInput(add)));//adding the value of the expression as a new variable to vars
					
				
			}else {
				//not a valid variable initialization
			}
					
					
		}else {
			//not a valid variable name
		}
			
		
	}
	
	
}
