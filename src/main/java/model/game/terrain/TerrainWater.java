package model.game.terrain;

import model.Position;

/**
 * A type of terrain, an "ocean" with specific properties related to the moment to moment gameplay
 */
public class TerrainWater extends Terrain {

    /**
     * See {@link Terrain#Terrain}
     */
    public TerrainWater(Position pos) {
        super(pos);
    }
}
