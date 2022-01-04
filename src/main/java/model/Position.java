package model;

import java.util.Objects;

public class Position {
    Integer x = 0;
    Integer y = 0;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Position() {}

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return getX().equals(position.getX()) && getY().equals(position.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
