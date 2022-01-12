import gui.GUI;
import model.Position;
import model.game.board.*;
import model.game.entity.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the {@link Game}
 */
public class GameTest {

    static Stream<Terrain> terrainStream() {
        return Stream.of(
                new TerrainPlain(new Position(0,0)),new TerrainWater(new Position(1,0)), new TerrainCity(new Position(2,0)),
                new TerrainMountain(new Position(0,1)), new TerrainForest(new Position(1,1)), new TerrainPlain(new Position(2,1))
        );
    }

    static Stream<Creature> creatureStream() {
        Creature c1 = new CreatureBuilder(Faction.ROBOT, Type.CANNON).build();
        c1.setPos(new Position(0,0));
        Creature c2 = new CreatureBuilder(Faction.ROBOT, Type.FLYING).build();
        c2.setPos(new Position(0,1));
        Creature c3 = new CreatureBuilder(Faction.ROBOT, Type.TANK).build();
        c3.setPos(new Position(1,1));

        return Stream.of(c1,c2,c3);
    }

    @Test
    void readLevelFileTest() throws URISyntaxException {
        File mapFile = new File(getClass().getClassLoader().getResource("levels/test.lvl").toURI());
        GUI gui = Mockito.mock(GUI.class);
        Game game = new Game(gui);
        game.readLevelFile(mapFile);

        assertEquals(3, game.getBoard().getWidth());
        assertEquals(2, game.getBoard().getHeight());

        for (int y = 0; y < game.getBoard().getHeight(); y++) {
            for (int x = 0; x < game.getBoard().getWidth(); x++) {
                Terrain elem = game.getBoard().getTerrainAt(new Position(x,y));
                assertEquals(1,terrainStream().filter(el -> el.equals(elem)).count());
            }
        }

        for (int i = 0; i < game.getRobots().size(); i++) {
            Creature elem = game.getRobots().get(i);
            assertEquals(1,creatureStream().filter(el -> el.equals(elem)).count());
        }
    }
}
