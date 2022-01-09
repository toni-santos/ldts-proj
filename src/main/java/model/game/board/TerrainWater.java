package model.game.board;

import model.Position;

/**
 * A type of terrain, an "ocean" with specific properties related to the moment to moment gameplay
 */
public class TerrainWater extends Terrain {

    /**
     * See {@link model.game.board.Terrain#Terrain}
     */
    public TerrainWater(Position pos) {
        super(pos);
    }
}
