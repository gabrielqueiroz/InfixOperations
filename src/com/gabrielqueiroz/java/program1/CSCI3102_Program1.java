package com.gabrielqueiroz.java.program1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

import javax.swing.JOptionPane; 

public class CSCI3102_Program1 {
	
	/**
	 * The program accepts all the operators, any lenght of number and negative numbers.
	 * @author Gabriel Queiroz 
	 */
	
	/**	Priority Table #1
	*	Precedence Level	||	Operator(s)
	*	1 Lowest Priority	||	-,+
	*	2 					||	*,/,^
	*	3 Highest Priority 	||	(,)
	*/
	
	public static String fileValue = "Program 1";
	
	/**
	 * Main method used to run the program
	 * @param args
	 */
	
	public static void main(String[] args) {
		//Receive scan from the console
		String infix = JOptionPane.showInputDialog("Insert the Infix Expression\n"
													+"Obs: Insert one space for each number or operator\n"
													+"Example: 5 + 4; -10 + ( 3 * 5 )")+" $ ";		
		//Write in the String of the File
  		writeFile("Infix Expression: "+infix);
  		writeFile("Result: "+evaluatePostfix(infixToPostfix(infix)).pop());
  		//Try to write the file
  		try {
	        File file = new File("program1.txt");
	        BufferedWriter output = new BufferedWriter(new FileWriter(file));	     			
  			JOptionPane.showMessageDialog(null, fileValue);
	        output.write(fileValue);
  			output.close();  			
        } catch ( IOException e ) {
        	e.printStackTrace();
        }	
	}
	
	/**
	 * Compute the values and create the postfix-output expression.
	 * @param infix
	 * @return stack
	 */
	
	public static Stack<String> infixToPostfix(String infix){
		//Stack used to keep the stack values
		Stack<String> stack = new Stack<String>();
		//Stack used to keep the Postfix values
		Stack<String> postfix = new Stack<String>();
		//Stack used to keep the work with the infix
		Stack<String> aux = loadStack(infix);
		//String used to work with the current value;
		String current;						
		//Run at each substring of the string loading the stacks
		for (int i = 0; i < aux.size(); i++){				
			//Load the current value with the respective substring
			current = aux.get(i);
			//Verifiy if it's an operator
			if (current.equals("+") || current.equals("-") || current.equals("^") ||
				current.equals("*") || current.equals("/") || current.equals("(") ||
				current.equals(")")){	
				//If the stacks already has one operator, evaluate if it's priority
				if(stack.size()>0){
					if(current.equals("(")){
						stack.push(current);							
						} else if(current.equals(")")) {
							while(!stack.peek().equals("("))
								postfix.push(stack.pop());
							stack.pop();							
						}	
						//If it's a priority 2 operator, verify if the stack already has a priority 2 operator
							else if(current.equals("*") || current.equals("/") || current.equals("^")) {
								//If it's already has a priority operator at the stack, 
								//pop it to the postfix stack and push the new to the stack
									if(stack.peek().equals("*") || stack.peek().equals("/") || stack.equals("^")){
										postfix.push(stack.pop()); 
										stack.push(current);
								//If the top of the stack is not an priority operator push it at the Stack
								} else 
									stack.push(current);						
						} else {
							//If it's not a priority operator, pop the last one and push the new one
							if(stack.peek().equals("(")){
								stack.push(current);	
							} else { 
								postfix.push(stack.pop()); 
								stack.push(current);
							}
						}
					} else					
						//If it's the stack size is 0, just push it at the stack
						stack.push(current);
			} else {				
				//If it's the final of the string, pop everything from the stack and push it to the postfix
				if (current.equals("$")){	
					while(stack.size()>0)
						postfix.push(stack.pop());
				//If it's not an operator, push it to the postfix
				} else				
					postfix.push(current); 
			}		
			//Write in the String of the File
			writeFile("Postfix: "+stackToString(postfix));
			writeFile("Stack: "+stackToString(stack));
		}		
		//Write in the String of the File
		writeFile("Postfix expression: "+stackToString(postfix));
		return postfix;
	}
	
	/**
	 * Compute the Postfix expression in the answer of the expression.
	 * Recursive method that receive a postfix and return a postfix until the lenght of postfix be 1, 
	 * which representes the answer. 
	 * @param postfix
	 * @return postfix
	 */
	
	public static Stack<String> evaluatePostfix(Stack<String> postfix){
		//Stack used to keep the stack values
		Stack<String> newPostfix = new Stack<String>();
		//Boolean value used to control if it's already evalutead this current operation
		boolean evaluated=false;
		//Int value used to storage the result of the current operation
		int value;
		//If postfix size is 1, so it's the final result and it's returned
		if(postfix.size()==1)			
			return postfix;
		//Run at each value of the stack evaluating the operation
		for(int i = 0 ; i<postfix.size() ; i++){	
			//If it's already evalutead this current operation, load the newPostfix Stack
			if (evaluated==false){
				//Switch case to each operation
				switch (postfix.get(i)){
					case "+":					
						value = Integer.parseInt(postfix.get(i-2))+
								Integer.parseInt(postfix.get(i-1));
							//Load the new stack to recursive method
							for(int j=0;j<i-2;j++)
								newPostfix.push(postfix.get(j));	
						newPostfix.push(String.valueOf(value));
						evaluated=true;
						break;
					case "-":
						value = Integer.parseInt(postfix.get(i-2))-
								Integer.parseInt(postfix.get(i-1));
							//Load the new stack to recursive method
							for(int j=0;j<i-2;j++)
								newPostfix.push(postfix.get(j));	
						newPostfix.push(String.valueOf(value));
						evaluated=true;
						break;
					case "*":
						value = Integer.parseInt(postfix.get(i-2))*
								Integer.parseInt(postfix.get(i-1));
							//Load the new stack to recursive method
							for(int j=0;j<i-2;j++)
								newPostfix.push(postfix.get(j));							
						newPostfix.push(String.valueOf(value));
						evaluated=true;
						break;
					case "/":
						value = Integer.parseInt(postfix.get(i-2))/
								Integer.parseInt(postfix.get(i-1));
							//Load the new stack to recursive method	
							for(int j=0;j<i-2;j++)
								newPostfix.push(postfix.get(j));							
						newPostfix.push(String.valueOf(value));
						evaluated=true;
						break;
					case "^":
						value = (int) Math.pow(Integer.parseInt(postfix.get(i-2)), 
								Integer.parseInt(postfix.get(i-1)));	
							//Load the new stack to recursive method	
							for(int j=0;j<i-2;j++)
								newPostfix.push(postfix.get(j));							
						newPostfix.push(String.valueOf(value));
						evaluated=true;
						break;
					default:
						break;
				}			
			} else 
				//Load the new stack to recursive method
				newPostfix.push(postfix.get(i));			
		}
		//Write in the String of the File
		writeFile("Evaluating Postfix: "+stackToString(newPostfix));
		//Recursive method to work with the result
		return evaluatePostfix(newPostfix);		
	}
	
	/**
	 * Transform a stack in a String to be showed at screen
	 * @param Stack<String>
	 * @return String
	 */
			
	public static String stackToString(Stack<String> stack){
		String stackToString="";
		for(int i=0;i<stack.size();i++)
			stackToString=stackToString+stack.get(i)+" ";
		return stackToString;
	}
	
	/**
	 * Put each number or operator of a String infix into an aux Stack 
	 * @param infix
	 * @return Stack<String> aux
	 */
	
	public static Stack<String> loadStack(String infix){
		Stack<String> aux = new Stack<String>();
		int point=0;
		for(int i=0;i<infix.length();i++){
			if(infix.substring(i,i+1).equals(" ")){				
				aux.push(infix.substring(point, i));
				point=i+1;
			}
		}
		return aux;
	}
	
	/**
	 * Write in the String of the File 
	 * @param line
	 */
	
	public static void writeFile(String line){
		fileValue = fileValue+"\n"+line;
	}
}
