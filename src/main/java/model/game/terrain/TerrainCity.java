package model.game.terrain;

import model.Position;

/**
 * A type of terrain, a "city" with specific properties related to the moment to moment gameplay
 */
public class TerrainCity extends Terrain {
    /**
     * Current state of the "city"
     */
    private boolean alive = true;

    /**
     * See {@link Terrain#Terrain}
     */
    public TerrainCity(Position pos) {
        super(pos);
    }

    /**
     * @return True if <i>the "city" is not dead</i>
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Set the "city's" alive state to false (dead)
     */
    public void kill() {
        this.alive = false;
    }
}