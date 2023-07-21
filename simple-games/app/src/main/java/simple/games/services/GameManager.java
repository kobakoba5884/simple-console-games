package simple.games.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import simple.games.annotations.GameImpl;

public class GameManager {
    private List<Class<? extends Game>> gameClasses = new ArrayList<>();

    public GameManager(){
        this.generateGames();
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Game> toGameClass(Class<?> aClass) {
        if (Game.class.isAssignableFrom(aClass)) {
            return (Class<? extends Game>) aClass;
        } else {
            throw new IllegalArgumentException("Provided class " + aClass + " is not a subclass of Game");
        }
    }

    private void generateGames(){
        Reflections reflections = new Reflections("simple.games.services");

        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(GameImpl.class);

        annotatedClasses.stream()
            .filter(Game.class::isAssignableFrom)
            .map(this::toGameClass)
            .forEach(gameClasses::add);
    }

}
