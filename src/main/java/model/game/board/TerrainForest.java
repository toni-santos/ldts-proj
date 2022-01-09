package model.game.board;

import model.Position;

/**
 * A type of terrain, a "forest" with specific properties related to the moment to moment gameplay
 */
public class TerrainForest extends Terrain {
    /**
     * True if the "forest" is burning, false otherwise
     */
    private boolean burning = false;

    /**
     * See {@link model.game.board.Terrain#Terrain}
     */
    public TerrainForest(Position pos, boolean burning) {
        super(pos);
        this.burning = burning;
    }

    /**
     * Sets the terrain on fire, or stops it
     *
     * @param burning The current state of the city (true if burning, false otherwise)
     */
    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    /**.
     * @return True if <i>the terrain is burning</i>
     */
    public boolean getBurning() {
        return burning;
    }
}
