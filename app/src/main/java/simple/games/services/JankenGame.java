package simple.games.services;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.EqualsAndHashCode;
import simple.games.annotations.GameImpl;
import simple.games.enums.Hand;
import simple.games.utils.ConsoleUtil;

@Data
@GameImpl
@EqualsAndHashCode(callSuper = true)
public class JankenGame extends AbstractGame {
    private Hand playerHand;
    private Hand cpuHand;

    public JankenGame() {
        super.setGameName("Janken");
        super.setGameDescription("Janken game description ---------------");
    }

    @Override
    public void initialize() {
        this.playerHand = null;
        this.cpuHand = null;
    }

    @Override
    public void play() {
        String typeOfHand = Arrays.stream(Hand.values())
                .map(Hand::getHandValue)
                .collect(Collectors.joining("/"));

        // Player chooses a hand
        while (Objects.isNull(this.playerHand)) {
            String input = ConsoleUtil.readInput("Choose a hand (%s): ".formatted(typeOfHand), false);
            Optional<Hand> optPlayerHand = Hand.fromString(input);

            if (optPlayerHand.isPresent()) {
                this.playerHand = optPlayerHand.get();
            } else {
                ConsoleUtil.print("Invalid input. Please enter (%s).".formatted(typeOfHand), true);
            }
        }

        // CPU randomly chooses a hand
        this.cpuHand = Hand.values()[new Random().nextInt(Hand.values().length)];

        ConsoleUtil.print("You chose " + this.playerHand.getHandValue() + ", CPU chose " + this.cpuHand.getHandValue(),
                true);

        // Check the result
        if (this.playerHand == this.cpuHand) {
            ConsoleUtil.print("It's a draw!", true);
        } else if (this.playerHand.winsAgainst(this.cpuHand)) {
            ConsoleUtil.print("You win!", true);
        } else {
            ConsoleUtil.print("You lose!", true);
        }
    }

    @Override
    public void end() {
        ConsoleUtil.print("Thanks for playing Janken game!", true);
    }
}
