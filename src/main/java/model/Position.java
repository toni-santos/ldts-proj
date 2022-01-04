package model;

public class Position {
    Integer x = 0;
    Integer y = 0;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Position() {}

    public  Position right() {
        return new Position(x++, y);
    }

    public  Position left() { return new Position(x--, y);}

    public  Position up() {
        return new Position(x, y--);
    }

    public  Position down() {
        return new Position(x, y++);
    }

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
}
