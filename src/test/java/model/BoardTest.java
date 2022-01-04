package model;

import model.game.board.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Vector;
import java.util.stream.Stream;


public class BoardTest {
    static Stream<Terrain> terrainStream() {
        return Stream.of(
                new TerrainPlain(new Position(0,0)),new TerrainWater(new Position(1,0)), new TerrainCity(new Position(2,0)),
                new TerrainMountain(new Position(0,1)), new TerrainForest(new Position(1,1), false), new TerrainPlain(new Position(2,1))
        );
    }

    @Test
    void PlainBoardTest() {
        Board board = new Board(8,8);

        for (Vector<Terrain> TerrainRow: board.getTerrains()) {
            for (Terrain terrain: TerrainRow) {
                assertEquals(TerrainPlain.class,terrain.getClass());
            }
        }
    }

    @Test
    void FileBoardTest() throws URISyntaxException {
        File mapFile = new File(getClass().getClassLoader().getResource("levels/test.lvl").toURI());
        Board board = new Board(mapFile);

        assertEquals(3, board.getWidth());
        assertEquals(2, board.getHeight());

        // Current idea is to check if the created stream of terrain objects (by hand)
        // is the same as the one created through the file
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                final Terrain elem = board.getTerrains().elementAt(i).elementAt(j);
                assertEquals(1,terrainStream().filter(el -> el.equals(elem)).count());
            }
        }
    }
}