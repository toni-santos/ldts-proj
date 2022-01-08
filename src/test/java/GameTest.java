import model.Position;
import model.game.board.*;
import model.game.entity.Ability;
import model.game.entity.Creature;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    static Stream<Terrain> terrainStream() {
        return Stream.of(
            new TerrainPlain(new Position(0,0)),new TerrainWater(new Position(1,0)), new TerrainCity(new Position(2,0)),
            new TerrainMountain(new Position(0,1)), new TerrainForest(new Position(1,1), false), new TerrainPlain(new Position(2,1))
        );
    }

    static Stream<Creature> creatureStream() {
        return Stream.of(
            new Creature(Creature.Type.CANNON, Creature.Faction.ROBOT, new Position(0,0), 2, 5, false, new Vector<>(Arrays.asList(Ability.LONG_RANGE_MISSILE, Ability.GRENADE))),
            new Creature(Creature.Type.FLYING, Creature.Faction.ROBOT, new Position(0,1), 2, 6, true, new Vector<>(Arrays.asList(Ability.LONG_RANGE_MISSILE, Ability.ARMAGEDDON))),
            new Creature(Creature.Type.TANK, Creature.Faction.ALIEN, new Position(1,1), 3, 4, false, new Vector<>(Arrays.asList(Ability.PUNCH, Ability.GRENADE)))
        );
    }

    @Test
    void readLevelFileTest() throws URISyntaxException {
        File mapFile = new File(getClass().getClassLoader().getResource("levels/test.lvl").toURI());
        Game game = new Game();
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
