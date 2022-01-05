package model.game.board;

import model.Position;

public class TerrainForest extends Terrain {
    private boolean burning = false;

    public TerrainForest(Position pos, boolean burning) {
        super(pos);
        this.burning = burning;
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public boolean getBurning() {
        return  burning;
    }
}
