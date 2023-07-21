package simple.games.utils;

import java.util.stream.IntStream;

public class Calculate {
    public static int calculatePermutation(int n, int r){
        if (n < 0 || r < 0 || n < r) {
            throw new IllegalArgumentException("Invalid arguments for permutation calculation.");
        }

        return IntStream.rangeClosed(n - r + 1, n)
            .reduce(1, (a, b) -> a * b);
    }
}
