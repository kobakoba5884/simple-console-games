package simple.games.services;

import java.util.Objects;
import java.util.Scanner;

public abstract class AbstractGame {
    protected String gameName;
    protected final Scanner scanner;

    public AbstractGame() {
        this.scanner = new Scanner(System.in);
    }

    public void closeScanner() {
        if(!Objects.isNull(scanner)) {
            scanner.close();
        }
    }
}
