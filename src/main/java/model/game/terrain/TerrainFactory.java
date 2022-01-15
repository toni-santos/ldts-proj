package model.game.terrain;

import model.Position;

public class TerrainFactory {
    private Position position;
    private Terrain.Type type;


    public TerrainFactory() { }

    public Terrain build() {
        switch (this.type) {
            case PLAIN:
                return new TerrainPlain(position);
            case CITY:
                return new TerrainCity(position);
            case FOREST:
                return new TerrainForest(position);
            case MOUNTAIN:
                return new TerrainMountain(position);
            case WATER:
                return new TerrainWater(position);
        }
        return null;
    }

    public TerrainFactory setPosition(Position position) {
        this.position = position;
        return this;
    }

    public TerrainFactory setType(Terrain.Type type) {
        this.type = type;
        return this;
    }
}
