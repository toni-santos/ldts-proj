package model.game.board;

import model.Position;

import java.util.Objects;

public abstract class Terrain {
    Position pos;

    Terrain(Position pos) {
        this.pos = pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Position getPos() {
        return pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Terrain)) return false;
        Terrain terrain = (Terrain) o;
        return getPos().equals(terrain.getPos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPos());
    }
}
