import model.Position;
import model.game.board.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
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

    @Test
    void readLevelFileTest() throws URISyntaxException {
        File mapFile = new File(getClass().getClassLoader().getResource("levels/test.lvl").toURI());
        Game game = new Game();
        game.readLevelFile(mapFile);

        assertEquals(3, game.getBoard().getWidth());
        assertEquals(2, game.getBoard().getHeight());

        // Current idea is to check if the created stream of terrain objects (by hand)
        // is the same as the one created through the file
        for (int y = 0; y < game.getBoard().getHeight(); y++) {
            for (int x = 0; x < game.getBoard().getWidth(); x++) {
                Terrain elem = game.getBoard().getTerrainAt(new Position(x,y));
                assertEquals(1,terrainStream().filter(el -> el.equals(elem)).count());
            }
        }
    }
}