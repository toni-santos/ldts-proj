import gui.GUI;
import model.game.Level;
import states.PlayState;
import states.State;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * An all-encompassing class of methods related to running the game
 */
public class Game {

    public static void main(String[] args) throws IOException, URISyntaxException {
       new Game().start();
    }
    private State state;
    private final GUI gui;
    public int currLevel = 1;

    public Game() throws IOException, URISyntaxException {
        this.gui = new GUI();
        this.state = new PlayState(new Level(currLevel));
    }

    private void start() throws IOException {
        int FPS = 1;
        int frameTime = 1000 / FPS;

        while (true) {
            state.step(gui);
        }
    }
}
