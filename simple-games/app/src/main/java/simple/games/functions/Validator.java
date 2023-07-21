package simple.games.functions;

import java.util.function.Predicate;

public class Validator {
    public static Predicate<String> isNumber = input -> {
        if(!input.matches("\\d+")){
            System.out.println("Invalid input. Please enter only numbers.");
            return false;
        }

        return true;
    };
}
