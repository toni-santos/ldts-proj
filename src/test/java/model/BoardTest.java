package model;

import model.game.board.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Vector;
import java.util.stream.Stream;


/**
 * Tests the {@link model.game.board.Board}
 */
public class BoardTest {
    @Test
    void PlainBoardTest() {
        Board board = new Board(8,8);

        for (Terrain terrain : board.getTerrains())
                assertEquals(TerrainPlain.class, terrain.getClass());
    }
}