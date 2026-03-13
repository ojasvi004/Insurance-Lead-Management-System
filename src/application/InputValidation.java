package application;

import java.util.Scanner;

public class InputValidation {

    public static String getValidString(Scanner sc, String question) {
        String input = "";
        while (true) {
            System.out.println(question);
            input = sc.nextLine();
            if (!input.trim().isEmpty()) {
                break;
            }
            System.out.println("Please try again");
        }
        return input;
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
}