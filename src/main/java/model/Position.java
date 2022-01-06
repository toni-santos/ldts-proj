package model;

import java.util.Objects;

public class Position {
    private int x = 0;
    private int y = 0;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {}

    public  Position right() { return new Position(x+1, y); }

    public  Position left() { return new Position(x-1, y);}

    public  Position up() { return new Position(x, y-1); }

    public  Position down() { return new Position(x, y+1); }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return getX() == (position.getX()) && getY() == (position.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}

