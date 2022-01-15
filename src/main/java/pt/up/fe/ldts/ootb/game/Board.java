package pt.up.fe.ldts.ootb.game;

import pt.up.fe.ldts.ootb.game.entity.terrain.Terrain;
import pt.up.fe.ldts.ootb.util.Vector;

public record Board(int width, int height, Terrain[] terrains) {
    public Terrain terrainAt(Vector position) {
        if (!isInsideBoard(position))
            return null;

        return terrains[position.x() + position.y()*width];
    }

    public boolean isInsideBoard(Vector position) {
        return position.x() >= 0 && position.x() < width && position.y() >= 0 && position.y() < height;
    }
}
