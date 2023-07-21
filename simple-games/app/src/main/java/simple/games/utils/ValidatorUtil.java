package simple.games.utils;

import java.util.function.Predicate;

public class ValidatorUtil {
    public static Predicate<String> isNumber = input -> {
        if(!input.matches("\\d+")){
            System.out.println("Invalid input. Please enter only numbers.");
            return false;
        }

        return true;
    };

    public static boolean isNotWithinRange(int minNum, int maxNum, int inputNum) {
        return (inputNum < minNum || inputNum > maxNum);
    }
}
