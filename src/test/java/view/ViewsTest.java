package view;

import gui.GUI;
import model.Position;
import model.game.board.Terrain;
import model.game.board.TerrainPlain;
import model.game.entity.Creature;
import model.game.entity.CreatureBuilder;
import model.game.entity.Faction;
import model.game.entity.Type;
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
        Creature alien = new CreatureBuilder(Faction.ALIEN, Type.CANNON).build();
        alien.setPos(new Position(0,0));
        View view = new CreatureView(alien);

        GUI gui = Mockito.mock(GUI.class);
        view.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawAlien(alien);
    }

    @Test
    public void robotCreatureViewTest() {
        Creature robot = new CreatureBuilder(Faction.ROBOT, Type.CANNON).build();
        robot.setPos(new Position(0,0));
        View view = new CreatureView(robot);

        GUI gui = Mockito.mock(GUI.class);
        view.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawRobot(robot);
    }
}
