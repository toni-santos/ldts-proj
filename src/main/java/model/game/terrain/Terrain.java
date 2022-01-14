package model.game.board;

import model.Position;
import model.game.entity.Creature;

import java.util.Objects;

/**
 * The terrain that makes up the board
 */
public abstract class Terrain {

    private Position pos;

    public Terrain(Position pos) { this.pos = pos; }

    public Position getPos() { return pos; }

    /**
     * @param o The object to evaluate
     * @return True if <i>the position of the terrains is the same</i>
     */
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
