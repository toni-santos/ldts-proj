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
}
