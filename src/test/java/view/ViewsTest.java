package view;

import gui.GUI;
import model.Position;
import model.game.terrain.Terrain;
import model.game.terrain.TerrainPlain;
import model.game.entity.Creature;
import model.game.entity.CreatureBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ViewsTest {
    @Test
    public void terrainViewTest() throws IOException {
        Terrain terrain = new TerrainPlain(new Position(3, 5));
        View view = new TerrainView(terrain);

        GUI gui = Mockito.mock(GUI.class);
        view.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawTerrain(terrain);
    }

    @Test
    public void alienCreatureViewTest() throws IOException {
        Creature alien = new CreatureBuilder()
                .setFaction(Creature.Faction.ALIEN)
                .setType(Creature.Type.CANNON)
                .setPosition(new Position(0,0))
                .build();
        View view = new CreatureView(alien);

        GUI gui = Mockito.mock(GUI.class);
        view.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawAlien(alien);
    }

    @Test
    public void robotCreatureViewTest() throws IOException {
        Creature robot = new CreatureBuilder()
                .setFaction(Creature.Faction.ROBOT)
                .setType(Creature.Type.CANNON)
                .setPosition(new Position(0,0))
                .build();
        View view = new CreatureView(robot);

        GUI gui = Mockito.mock(GUI.class);
        view.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawRobot(robot);
    }
}
