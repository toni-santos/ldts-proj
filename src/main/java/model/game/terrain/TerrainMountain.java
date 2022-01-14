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
        if (this.HP == 0)
            return;
        this.HP--;
    }

    /**
     * @return True if <i>the city is not dead</i>
     */
    public boolean isAlive() { return this.HP == 0; }
}
