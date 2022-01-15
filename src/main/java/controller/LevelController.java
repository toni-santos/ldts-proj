package controller;

import model.game.Level;

public class LevelController implements Controller<Level> {
    private final Level level;

    public LevelController(Level level) {
        this.level = level;
    }

    @Override
    public void step() {
        // TODO: STEP ACTIONS
    }
}
