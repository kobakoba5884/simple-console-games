package simple.games.services;

import java.util.Random;

import simple.games.annotations.GameImpl;
import simple.games.enums.Hand;
import simple.games.utils.ConsoleUtil;

@GameImpl
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
        // Player chooses a hand
        while (this.playerHand == null) {
            String input = ConsoleUtil.readInput("Choose a hand (Rock/Scissors/Paper): ", false);
            this.playerHand = Hand.fromString(input);
            if (this.playerHand == null) {
                ConsoleUtil.print("Invalid input. Please enter Rock, Scissors, or Paper.", true);
            }
        }

        // CPU randomly chooses a hand
        this.cpuHand = Hand.values()[new Random().nextInt(Hand.values().length)];

        ConsoleUtil.print("You chose " + this.playerHand.getHandValue() + ", CPU chose " + this.cpuHand.getHandValue(),
                true);

        // Check the result
        if (playerHand == cpuHand) {
            ConsoleUtil.print("It's a draw!", true);
        } else if (playerHand.winsAgainst(cpuHand)) {
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
