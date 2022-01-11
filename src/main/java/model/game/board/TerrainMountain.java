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
     * Deals 1 damage to the "mountain"
     */
    public void dealDamage() {
        if (this.HP - 1 == 0) {
            this.kill();
            return;
        }

        this.HP--;
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
