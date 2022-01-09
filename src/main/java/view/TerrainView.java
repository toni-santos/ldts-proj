package view;

import gui.GUI;
import model.game.board.Terrain;

public class TerrainView implements View {
    private final Terrain terrain;

    public TerrainView(Terrain terrain) {
        this.terrain = terrain;
    }

    @Override
    public void draw(GUI gui) {
        //TODO
    }

    public Terrain getTerrain() {
        return terrain;
    }
}
