package simple.games.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HitAndBlow implements Game {
    private final String gameName = "Hit And Blow";
    private int numDigits = 4;

    public HitAndBlow() {

    }

    public HitAndBlow(int numDigits) {
        if (numDigits < 1 || numDigits > 10) {
            throw new IllegalArgumentException("The number of digits must be between 1 and 10.");
        }

        this.numDigits = numDigits;
    }

    @Override
    public void play() {

        System.out.println("start %s".formatted(gameName));

        // generating answers
        List<Integer> answers = generateAnswer();

        System.out.println(answers);
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

        return answers.subList(0, numDigits);
    }
}
