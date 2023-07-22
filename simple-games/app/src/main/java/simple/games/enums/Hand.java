package simple.games.enums;

import java.util.Arrays;

public enum Hand {
    ROCK("Rock", "Scissors"),
    SCISSORS("Scissors", "Paper"),
    PAPER("Paper", "Rock");

    private final String handValue;
    private final String winsAgainst;

    Hand(String handValue, String winsAgainst) {
        this.handValue = handValue;
        this.winsAgainst = winsAgainst;
    }

    public String getHandValue() {
        return this.handValue;
    }

    public boolean winsAgainst(Hand other) {
        return this.winsAgainst.equals(other.getHandValue());
    }

    public static Hand fromString(String input) {
        return Arrays.stream(Hand.values())
                .filter(hand -> hand.getHandValue().equalsIgnoreCase(input))
                .findFirst()
                .orElse(null);
    }
}
