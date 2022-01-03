public class Position {
    Integer x = 0;
    Integer y = 0;

    Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    Position() {}

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
