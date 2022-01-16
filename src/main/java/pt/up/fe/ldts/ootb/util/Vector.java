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

    public Vector normalize() {
        return new Vector(Integer.signum(x), Integer.signum(y));
    }

    public double length() {
        return Math.sqrt(x*x + y*y);
    }

    public int distance(Vector v1) {
        return (int) this.sub(v1).length();
    }

    public int gridDistance(Vector v) {
        return Math.abs(x - v.x) + Math.abs(y - v.y);
    }
}
