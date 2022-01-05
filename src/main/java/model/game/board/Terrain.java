package model.game.board;

import model.Position;

public abstract class Terrain {
    private Position pos;

    public Terrain(Position pos) {
        this.pos = pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Position getPos() {
        return pos;
    }
}
