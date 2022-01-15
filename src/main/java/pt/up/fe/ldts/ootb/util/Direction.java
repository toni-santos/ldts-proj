package pt.up.fe.ldts.ootb.util;

public enum Direction {
    UP(new Vector(0, -1)),
    DOWN(new Vector(0, 1)),
    LEFT(new Vector(-1, 0)),
    RIGHT(new Vector(1, 0));

    public final Vector offset;

    Direction(Vector offset) {
        this.offset = offset;
    }
}
