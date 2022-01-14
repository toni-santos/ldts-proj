package view;

import gui.GUI;
import model.game.terrain.Terrain;

public class TerrainView implements View<Terrain> {
    private Terrain terrain;

    public TerrainView(Terrain terrain) {
        this.terrain = terrain;
    }

    public TerrainView() {}

    @Override
    public void draw(GUI gui) {
        gui.drawTerrain(terrain);
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
}
