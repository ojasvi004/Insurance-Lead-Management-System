package application;

import java.util.ArrayList;
import java.util.Scanner;

public class InputValidation {

	public static String getValidString(Scanner sc, String question) {
	    String input = "";
	    while (true) {
	        System.out.println(question);
	        input = sc.nextLine().trim();
	        if (!input.isEmpty() && isOnlyLetters(input)) {
	            break;
	        }
	        System.out.println("use letters only");
	    }
	    return input;
	}
	
	public static boolean isOnlyLetters(String str) {
	    for (char c : str.toCharArray()) {
	        if (!Character.isLetter(c)) {
	            return false; 
	        }
	    }
	    return true;
	}

    public static int getValidInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            if (!input.trim().isEmpty()) {
                try {
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format. Please enter a number");
                }
            } else {
                System.out.println("Please try again");
            }
        }
    }

    public static String getValidRegexString(Scanner sc, String prompt, String regex, String errorMessage) {
        String input = "";
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();  
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty");
                continue;
            }
            if (input.matches(regex)) {
                break;
            } else {
                System.out.println("Invalid input: " + errorMessage);
            }
        }
        return input;
    }
    public static String getValidOption(Scanner sc, String prompt, ArrayList<String> options) {
        String input = "";
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            for (String opt : options) {
                if (opt.equalsIgnoreCase(input)) {
                    return opt;
                }
            }
            System.out.println("Invalid input. Please type exactly one of the given options: " + options);
        }
    }
}