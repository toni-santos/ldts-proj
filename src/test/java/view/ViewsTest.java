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
        Terrain terrain = new TerrainPlain(new Position(3, 5));
        View view = new TerrainView(terrain);

        GUI gui = Mockito.mock(GUI.class);
        view.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawTerrain(terrain);
    }

    @Test
    public void alienCreatureViewTest() {
        Creature alien = new Creature(Creature.Type.CANNON, Creature.Faction.ALIEN, new Position(1, 3), 0, 0, false, null);
        View view = new CreatureView(alien);

        GUI gui = Mockito.mock(GUI.class);
        view.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawAlien(alien);
    }

    @Test
    public void robotCreatureViewTest() {
        Creature robot = new Creature(Creature.Type.CANNON, Creature.Faction.ROBOT, new Position(6, 2), 0, 0, false, null);
        View view = new CreatureView(robot);

        GUI gui = Mockito.mock(GUI.class);
        view.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawRobot(robot);
    }
}
