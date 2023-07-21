package simple.games.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import simple.games.annotations.GameImpl;
import simple.games.utils.CalculateUtil;
import simple.games.utils.ConsoleUtil;
import simple.games.utils.ValidatorUtil;

@GameImpl
public class HitAndBlow extends AbstractGame {
    private int numDigits;
    private int maxAttemps;
    private AtomicInteger tryCount;
    private List<Predicate<String>> inputErrorChecks = new ArrayList<>();
    private int hits;
    private int blows;

    public HitAndBlow() {
        super.setGameName("Hit And Blow");
        super.setGameDescription("Hit And Blow's description ---------------");
    }

    @Override
    public void initialize() {
        this.numDigits = getValidNumDigits();
        this.maxAttemps = this.calculateMaxTries(10, this.numDigits);
        this.tryCount = new AtomicInteger(0);
        this.generateInputErrorChecks();
    }

    @Override
    public void play() {

        ConsoleUtil.print("start %s".formatted(super.gameName), true);

        // generating answers
        List<Integer> answers = this.generateAnswer();

        // validating user input
        while (this.tryCount.get() < maxAttemps) {
            String userInput = ConsoleUtil.readInput(
                    "Enter your guess (Attempt %d out of %d): ".formatted(this.tryCount.get() + 1, this.maxAttemps),
                    false);

            if (!this.isValidInput(userInput)) {
                continue;
            }

            this.calculateHitsAndBlows(userInput, answers);

            if (this.hits == this.numDigits) {
                ConsoleUtil.print("Congratulations! You've got the correct answer.", true);
                break;
            }

            this.tryCount.incrementAndGet();
        }

        if (this.tryCount.get() >= this.maxAttemps && hits != this.numDigits) {
            ConsoleUtil.print("You've reached the maximum number of attempts. Game over.", true);
        }
    }

    @Override
    public void end() {
        ConsoleUtil.print("Thanks for playing %s!".formatted(super.gameName), true);
        ConsoleUtil.print("You made %d attempts.".formatted(this.tryCount.get()), true);
        
        if (this.hits == this.numDigits) {
            ConsoleUtil.print("Congratulations! You've won.", true);
        } else {
            ConsoleUtil.print("Better luck next time.", true);
        }
    }

    private List<Integer> generateAnswer() {
        List<Integer> answers = IntStream.rangeClosed(0, 9)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        Collections.shuffle(answers);

        return answers.subList(0, this.numDigits);
    }

    private int calculateMaxTries(int n, int m) {
        int permutation = CalculateUtil.calculatePermutation(n, m);

        return (int) Math.ceil(Math.log(permutation) / Math.log(2));
    }

    private void generateInputErrorChecks() {
        inputErrorChecks.add(ValidatorUtil.isNumber);

        inputErrorChecks.add(input -> {
            if (input.length() != this.numDigits) {
                ConsoleUtil.print("Invalid input. Please enter exactly %d digits.".formatted(this.numDigits), true);
                return false;
            }
            return true;
        });

        inputErrorChecks.add(input -> {
            if (IntStream.range(0, this.numDigits - 1)
                    .anyMatch(i -> input.indexOf(input.charAt(i)) != input.lastIndexOf(input.charAt(i)))) {
                ConsoleUtil.print("Invalid input. Please do not enter duplicate digits.", true);
                return false;
            }
            return true;
        });
    }

    private boolean isValidInput(String userInput) {
        return inputErrorChecks.stream().allMatch(check -> check.test(userInput));
    }

    private void calculateHitsAndBlows(String userInput, List<Integer> answers) {
        // reset hits and blows
        this.hits = 0;
        this.blows = 0;

        IntStream.range(0, this.numDigits).forEach(i -> {
            int digit = Character.getNumericValue(userInput.charAt(i));
            if (answers.get(i) == digit) {
                this.hits++;
            } else if (answers.contains(digit)) {
                this.blows++;
            }
        });

        ConsoleUtil.print("Hits: %d, Blows: %d".formatted(this.hits, this.blows), true);
    }

    private int getValidNumDigits() {
        int userNumDigits;
        do {
            ConsoleUtil.print("Please select the number of digits for the game (1-10): ", true);
            userNumDigits = ConsoleUtil.readIntInput("Your selection: ", false);
            if (ValidatorUtil.isNotWithinRange(1, 10, userNumDigits)) {
                ConsoleUtil.print("Invalid number of digits. Please enter a number between 1 and 10.", true);
            }
        } while (ValidatorUtil.isNotWithinRange(1, 10, userNumDigits));
        return userNumDigits;
    }
}
