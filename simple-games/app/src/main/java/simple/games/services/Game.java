package simple.games.services;

public interface Game {
    String getGameName();

    void initialize();
    
    void play();

    void end();
}
