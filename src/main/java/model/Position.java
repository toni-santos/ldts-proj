package model;

import java.util.Objects;

/**
 * A class to define a position of (x,y) coordinates
 */
public class Position {

    /**
     * The x coordinate
     */
    private int x = 0;

    /**
     * The y coordinate
     */
    private int y = 0;

    /**
     * Creates a position object in x,y
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a position object in 0,0
     */
    public Position() {}

    /**
     * @return A new Position to the right of the original object
     */
    public  Position right() { return new Position(x+1, y); }

    /**
     * @return A new Position to the left of the original object
     */

    public  Position left() { return new Position(x-1, y);}

    /**
     * @return A new Position to the up of the original object
     */

    public  Position up() { return new Position(x, y-1); }

    /**
     * @return A new Position to the down of the original object
     */
    public  Position down() { return new Position(x, y+1); }

    /**
     * @return The x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return The y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the value of the x coordinate
     *
     * @param x The new x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the value of the y coordinate
     *
     * @param y The new y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @param o The object to evaluate
     * @return True if <i>the coordinates of x and y are the same</i>
     */
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

