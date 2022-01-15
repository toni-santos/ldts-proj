package view;

import gui.GUI;
import model.game.terrain.Terrain;
import model.game.entity.Creature;
import model.game.Level;

import java.io.IOException;

public class LevelView implements View<Level> {
    private final Level level;
    private CreatureView cv = new CreatureView();
    private TerrainView tv = new TerrainView();
    
    public LevelView(Level level) {
        this.level = level;
    }
    
    @Override
    public void draw(GUI gui) throws IOException {
        gui.clear();
        drawBoard(gui);
        drawCreatures(gui);
        gui.refresh();
    }

    private void drawBoard(GUI gui) {
        for (Terrain t: level.getBoard().getTerrains()) {
            this.tv.setTerrain(t);
            tv.draw(gui);
        }
    }

    public void drawCreatures(GUI gui) {
        for (Creature c: level.getRobots()) {
            this.cv.setCreature(c);
            cv.draw(gui);
        }
    }
}
