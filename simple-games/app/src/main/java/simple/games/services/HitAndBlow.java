package simple.games.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import simple.games.annotations.GameImpl;
import simple.games.functions.Validator;
import simple.games.utils.CalculateUtil;
import simple.games.utils.ConsoleUtil;

@GameImpl
public class HitAndBlow extends AbstractGame{
    private int numDigits;
    private int maxAttemps;
    private AtomicInteger tryCount;
    private List<Predicate<String>> inputErrorChecks = new ArrayList<>();
    private int hits;
    private int blows;

    public HitAndBlow(){
        this(4);
    }

    public HitAndBlow(int numDigits) {
        if (numDigits < 1 || numDigits > 10) {
            throw new IllegalArgumentException("The number of digits must be between 1 and 10.");
        }

        super.gameName = "Hit And Blow";

        this.numDigits = numDigits;
        this.maxAttemps = this.calculateMaxTries(10, numDigits);
        this.tryCount = new AtomicInteger(0);
        this.generateInputErrorChecks();
    }

    @Override
    public void play() {

        ConsoleUtil.print("start %s".formatted(super.gameName), true);

        // generating answers
        List<Integer> answers = this.generateAnswer();

        // validating user input
        while(this.tryCount.get() < maxAttemps){
            String userInput = ConsoleUtil.readInput("Enter your guess: ", false);

            if(!this.isValidInput(userInput)){
                continue;
            }

            this.calculateHitsAndBlows(userInput, answers);

            if(this.hits == this.numDigits){
                ConsoleUtil.print("Congratulations! You've got the correct answer.", true);
                break;
            }

            this.tryCount.incrementAndGet();
        }

        if(this.tryCount.get() >= this.maxAttemps  && hits != this.numDigits){
            ConsoleUtil.print("You've reached the maximum number of attempts. Game over.", true);
        }
    }

    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    @Override
    public void end() {
        throw new UnsupportedOperationException("Unimplemented method 'end'");
    }

    private List<Integer> generateAnswer() {
        List<Integer> answers = IntStream.rangeClosed(0, 9)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));

        Collections.shuffle(answers);

        return answers.subList(0, this.numDigits);
    }

    private int calculateMaxTries(int n, int m){
        int permutation = CalculateUtil.calculatePermutation(n, m);

        return (int) Math.ceil(Math.log(permutation) / Math.log(2));
    }

    private void generateInputErrorChecks(){
        inputErrorChecks.add(Validator.isNumber);

        inputErrorChecks.add(input -> {
            if(input.length() != this.numDigits) {
                ConsoleUtil.print("Invalid input. Please enter exactly %d digits.".formatted(this.numDigits), true);
                return false;
            }
            return true;
        });

        inputErrorChecks.add(input -> {
            if(IntStream.range(0, this.numDigits - 1).anyMatch(i -> input.indexOf(input.charAt(i)) != input.lastIndexOf(input.charAt(i)))) {
                ConsoleUtil.print("Invalid input. Please do not enter duplicate digits.", true);
                return false;
            }
            return true;
        });
    }

    private boolean isValidInput(String userInput){
        return inputErrorChecks.stream().allMatch(check -> check.test(userInput));
    }

    private void calculateHitsAndBlows(String userInput, List<Integer> answers) {
        // reset hits and blows
        this.hits = 0;
        this.blows = 0;

        IntStream.range(0, this.numDigits).forEach(i -> {
            int digit = Character.getNumericValue(userInput.charAt(i));
            if(answers.get(i) == digit){
                this.hits++;
            } else if(answers.contains(digit)){
                this.blows++;
            }
        });

        ConsoleUtil.print("Hits: %d, Blows: %d".formatted(this.hits, this.blows), true);
    }
}
