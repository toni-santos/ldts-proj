package model.game.board;

import model.Position;

public class TerrainCity extends Terrain {
    boolean alive = true;

    public TerrainCity(Position pos) {
        super(pos);
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        this.alive = false;
    }
}
