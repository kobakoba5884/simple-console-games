package simple.games.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import simple.games.utils.Calculate;

public class HitAndBlow extends AbstractGame implements Game {
    private int numDigits;
    private int maxAttemps;
    private AtomicInteger tryCount;

    public HitAndBlow(int numDigits) {
        if (numDigits < 1 || numDigits > 10) {
            throw new IllegalArgumentException("The number of digits must be between 1 and 10.");
        }

        super.gameName = "Hit And Blow";

        this.numDigits = numDigits;
        this.maxAttemps = calculateMaxTries(10, numDigits);
        this.tryCount = new AtomicInteger(0);
    }

    @Override
    public void play() {

        System.out.println("start %s".formatted(super.gameName));

        // generating answers
        List<Integer> answers = generateAnswer();

        System.out.println(answers);

        while(tryCount.get() < maxAttemps){
            System.out.print("Enter your guess: ");

            String userInput = super.scanner.nextLine();

            System.out.println(userInput);

            tryCount.incrementAndGet();
        }

        if(tryCount.get() >= maxAttemps){
            System.out.println("You've reached the maximum number of attempts. Game over.");
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
        int permutation = Calculate.calculatePermutation(n, m);

        return (int) Math.ceil(Math.log(permutation) / Math.log(2));
    }
}
