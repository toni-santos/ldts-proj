package model.game.board;

import model.Position;

/**
 * A type of terrain, a "mountain" with specific properties related to the moment to moment gameplay
 */
public class TerrainMountain extends Terrain {
    /**
     * The number of hits the "mountain" can take before being destroyed
     */
    private int HP = 2;

    /**
     * Current state of the terrain
     */
    private boolean alive = true;

    /**
     * See {@link model.game.board.Terrain#Terrain}
     */
    public TerrainMountain(Position pos) {
        super(pos);
    }

    /**
     * @return The current "HP" of the city
     */
    public int getHP() {
        return HP;
    }

    /**
     * Sets the HP of the city
     *
     * @param HP The new HP of the city
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * @return True if <i>the city is not dead</i>
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Set the "mountain's" alive state to false (dead)
     */
    public void kill() {
        this.alive = false;
    }
}
