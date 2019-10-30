package fractioncalculator;

import java.util.Scanner;

public class FractionCalculator {

	public static Scanner input = new Scanner(System.in); 
	
	String enterFraction = "Please enter an operation (+, -, /, *, = or Q to quit: ";
	
	public static String getOperation(Scanner input) {
		String numbers = input.nextLine();
		int inputnumber = 0;
		while (inputnumber == 0) {
			if(numbers.equals("+") || numbers.equals("-") || numbers.equals("/") || numbers.equals("*") || numbers.equals("q") || numbers.equals("Q")) {
				inputnumber++;
			} else {
				System.out.println("Invalid input, please enter +, -, /, *, = or q or Q to exit the program");
				numbers = input.nextLine();
			}
		}
		return numbers;
	}
	public static boolean isNumber(String input) {
		boolean result;
		if(input.matches("[0-9]+")) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	public static boolean validFraction(String input) {
		boolean result;
		if(input.startsWith("-")) {
			input = input.substring(1, input.length());
		}
		
		if(input.contains(" ") || input.contains("-") || (input.charAt(input.indexOf("/")+1) == ('0'))) {
			result = false;
		} else if (input.contains("/")) {
			input = input.replace("/", "");
		}
		
		if(isNumber(input)) {
			result = true;
		}
		
		return result = true;
	}
	
	public static Fraction getFraction(Scanner input) {
		System.out.println("Please enter a fraction (a/b) or integer (a): ");
		String inputnumber = input.nextLine();
		
		while(!validFraction(inputnumber)) {
			System.out.println("Invalid input, please enter (a/b) or (a), where a and b are integers and b is not zero: ");
			inputnumber = input.nextLine();
		}
		
		int numeral = 0;
        int dennominador = 0;
        if (inputnumber.contains("/")){
        	numeral=Integer.parseInt(inputnumber.substring(0,inputnumber.indexOf("/") ));
        	dennominador=Integer.parseInt(inputnumber.substring(inputnumber.indexOf("/")+1,inputnumber.length() ));
        } else {
        	numeral=Integer.parseInt(inputnumber);
        	dennominador=1;
        }
		
		Fraction fractionInput = new Fraction(numeral, dennominador);
		
		return fractionInput;
	}
	
	public static void main(String[] args) {

		String s1 = "This program is a fraction calculator.";
		String s2 = "It will add, substract, multiply and divide fractions until you type q or Q to quit.";
		String s3 = "Please enter your fractions in the form a/b , where a and b are integers.";
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println("------------------------------------------------------------");
		
		while(true) {
			String op = getOperation(input);
			Fraction fraction1 = getFraction(input);
			Fraction fraction2 = getFraction(input);
			
			Fraction result = new Fraction(1,1);
			String outputRs = "";
			
			if(op.equals("+")) {
				result = fraction1.add(fraction2);
			} else if(op.equals("-")) {
				result = fraction1.substract(fraction2);
			} else if(op.equals("*")) {
				if(fraction2.getNumerator() == 0) {
					outputRs = "0";
				} else {
					result = fraction1.multiply(fraction2);
				}
			} else if(op.equals("/")) {
				if(fraction2.getNumerator() == 0) {
					outputRs = "This cannot be zero, undefined";
				} else {
					result = fraction1.divide(fraction2); 
				}
			} else if (op.equals("=")) {
				System.out.println(fraction1 + " " + op + " " + fraction2 + " " + fraction1.equals(fraction2));
			} 
			
			if(result.getNumerator() % result.getDenominator() == 0) {
				System.out.println(fraction1 + " " + op + " " + fraction2 + " = " + (result.getNumerator() / result.getDenominator()));
			} else if (outputRs != "") {
				System.out.println(fraction1 + " " + op + " " + "0" + " = " + outputRs);
			} else {
				System.out.println(fraction1 + " "+ op + " " + fraction2 + " = " + result.toString());
			}
		}
		
	}
	
}
