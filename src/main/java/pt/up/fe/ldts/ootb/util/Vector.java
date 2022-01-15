package pt.up.fe.ldts.ootb.util;

public record Vector(int x, int y) {

    public Vector add(Vector other) {
        return add(other.x, other.y);
    }

    public Vector add(int x, int y) {
        return new Vector(this.x + x, this.y + y);
    }

    public Vector sub(Vector other) {
        return sub(other.x, other.y);
    }

    public Vector sub(int x, int y) {
        return new Vector(this.x - x, this.y - y);
    }

    public Vector mul(int n) {
        return new Vector(x*n, y*n);
    }

    public Vector div(int n) {
        return new Vector(x/n, y/n);
    }
}
