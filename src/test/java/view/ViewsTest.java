package view;

import gui.GUI;
import model.Position;
import model.game.board.Terrain;
import model.game.board.TerrainPlain;
import model.game.entity.Creature;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ViewsTest {
    @Test
    public void terrainViewTest() {
        Position pos = new Position(3, 5);
        Terrain terrain = new TerrainPlain(pos);
        View view = new TerrainView(terrain);

        GUI gui = Mockito.mock(GUI.class);
        view.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawTerrain(pos);
    }

    @Test
    public void alienCreatureViewTest() {
        Position pos = new Position(1, 3);
        Creature alien = new Creature(Creature.Type.CANNON, Creature.Faction.ALIEN, pos, 0, 0, false, null);
        View view = new CreatureView(alien);

        GUI gui = Mockito.mock(GUI.class);
        view.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawAlien(pos);
    }

    @Test
    public void robotCreatureViewTest() {
        Position pos = new Position(6, 2);
        Creature robot = new Creature(Creature.Type.CANNON, Creature.Faction.ROBOT, pos, 0, 0, false, null);
        View view = new CreatureView(robot);

        GUI gui = Mockito.mock(GUI.class);
        view.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawRobot(pos);
    }
}
