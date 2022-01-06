package model;

public class Position {
    int x = 0;
    int y = 0;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {}

    public  Position right() { return new Position(x+1, y); }

    public  Position left() { return new Position(x-1, y);}

    public  Position up() { return new Position(x, y-1); }

    public  Position down() { return new Position(x, y+1); }

    public int getX() { return x; }

    public int getY() { return y; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }
}
