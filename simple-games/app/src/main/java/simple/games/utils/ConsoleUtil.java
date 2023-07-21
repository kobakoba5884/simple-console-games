package simple.games.utils;

import java.util.Scanner;

public class ConsoleUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static void print(String message, boolean addNewLine) {
        if (addNewLine) {
            System.out.println(message);
        } else {
            System.out.print(message);
        }
    }

    public static String readInput(String message, boolean addNewLine) {
        print(message, addNewLine);

        return scanner.nextLine();
    }

    public static int readIntInput(String message, boolean addNewLine) {
        while (true) {
            try {
                String input = readInput(message, addNewLine);

                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                print("Invalid input. Please enter a number.", true);
            }
        }
    }
}
