/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package simple.games;

import simple.games.services.Game;
import simple.games.services.HitAndBlow;

public class App {
    public static void main(String[] args) {

        Game game = new HitAndBlow(10);

        game.play();
    }
}
