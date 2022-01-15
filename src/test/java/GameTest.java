import model.Position;
import model.game.Level;
import model.game.entity.*;
import model.game.terrain.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the {@link Game}
 */
public class GameTest {

    static Stream<Terrain> terrainStream() {
        return Stream.of(
                new TerrainFactory().setType(Terrain.Type.PLAIN).setPosition(new Position(0,0)).build(),
                new TerrainFactory().setType(Terrain.Type.WATER).setPosition(new Position(1,0)).build(),
                new TerrainFactory().setType(Terrain.Type.CITY).setPosition(new Position(2,0)).build(),
                new TerrainFactory().setType(Terrain.Type.MOUNTAIN).setPosition(new Position(0,1)).build(),
                new TerrainFactory().setType(Terrain.Type.FOREST).setPosition(new Position(1,1)).build(),
                new TerrainFactory().setType(Terrain.Type.PLAIN).setPosition(new Position(2,1)).build()
        );
    }

    static Stream<Creature> creatureStream() {
        Creature c1 = new CreatureBuilder()
                .setFaction(Creature.Faction.ROBOT)
                .setType(Creature.Type.CANNON)
                .setPosition(new Position(0,0))
                .build();
        Creature c2 = new CreatureBuilder()
                .setFaction(Creature.Faction.ROBOT)
                .setType(Creature.Type.CANNON)
                .setPosition(new Position(0,1))
                .build();
        Creature c3 = new CreatureBuilder()
                .setFaction(Creature.Faction.ROBOT)
                .setType(Creature.Type.CANNON)
                .setPosition(new Position(1,1))
                .build();

        return Stream.of(c1,c2,c3);
    }

    @Test
    void readLevelFileTest() throws URISyntaxException, IOException {
        Level level = new Level(1);

        assertEquals(3, level.getBoard().getWidth());
        assertEquals(2, level.getBoard().getHeight());

        for (int y = 0; y < level.getBoard().getHeight(); y++) {
            for (int x = 0; x < level.getBoard().getWidth(); x++) {
                Terrain elem = level.getBoard().getTerrainAt(new Position(x,y));
                assertEquals(1,terrainStream().filter(el -> el.equals(elem)).count());
            }
        }

        for (int i = 0; i < level.getRobots().size(); i++) {
            Creature elem = level.getRobots().get(i);
            assertEquals(1,creatureStream().filter(el -> el.equals(elem)).count());
        }
    }
}
