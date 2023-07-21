package simple.games.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.reflections.Reflections;

import simple.games.annotations.GameImpl;
import simple.games.utils.ConsoleUtil;

public class GameManager {
    private List<Game> games = new ArrayList<>();

    public GameManager() {
        this.generateGames();

        if (this.games.isEmpty()) {
            ConsoleUtil.print("Sorry. Be developing games now...... Please wait....", true);

            System.exit(0);
        }
    }

    public Game chooseGame() {
        ConsoleUtil.print("Available games:", true);

        IntStream.range(0, this.games.size())
                .mapToObj(i -> "\t%d: %s".formatted((i + 1), this.games.get(i).getGameName()))
                .forEach(message -> ConsoleUtil.print(message, true));

        int userInput;
        String message = this.games.size() == 1 ? "Currently, there is only 1 game available."
                    : "Please choose a game by entering a number from 1 to %d.".formatted(this.games.size());

        do {
            ConsoleUtil.print(message, true);

            userInput = ConsoleUtil.readIntInput("Enter the number of the game you want to play: ", false);
        } while (userInput < 1 || userInput > this.games.size());

        Game selectedGame = this.games.get(userInput - 1);
        
        return selectedGame;
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Game> toGameClass(Class<?> clazz) {
        if (Game.class.isAssignableFrom(clazz)) {
            return (Class<? extends Game>) clazz;
        } else {
            throw new IllegalArgumentException(
                    "Provided class %s is not a subclass of Game".formatted(clazz.toString()));
        }
    }

    private void addGame(Class<? extends Game> gameClass) {
        try {
            this.games.add(gameClass.getDeclaredConstructor().newInstance());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to instantiate game class %s".formatted(gameClass.toString()), e);
        }
    }

    private void generateGames() {
        Reflections reflections = new Reflections("simple.games.services");

        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(GameImpl.class);

        annotatedClasses.stream()
                .filter(Game.class::isAssignableFrom)
                .map(this::toGameClass)
                .forEach(this::addGame);
    }

}
