package simple.games.utils;

import java.util.Optional;
import java.util.Scanner;

import simple.games.enums.YesNoResponse;

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

    public static YesNoResponse requireYesNoResponse(String question) {
        String userInput;
        do {
            ConsoleUtil.print(
                    "%s (%s/%s)".formatted(question, YesNoResponse.YES.getValue(), YesNoResponse.NO.getValue()), true);
            userInput = ConsoleUtil.readInput("Your choice: ", false);

            if (!ValidatorUtil.isValidYesNoResponse(userInput)) {
                ConsoleUtil.print(
                        "Invalid response. Please enter '%s' or '%s'.".formatted(YesNoResponse.YES.getValue(),
                                YesNoResponse.NO.getValue()),
                        true);
            }
        } while (!ValidatorUtil.isValidYesNoResponse(userInput));

        Optional<YesNoResponse> response = YesNoResponse.fromString(userInput);

        if (response.isEmpty()) {
            throw new RuntimeException("mystery error");
        }

        return response.get();
    }
}
