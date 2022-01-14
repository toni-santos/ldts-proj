package model;

import model.game.board.*;
import model.game.terrain.Terrain;
import model.game.terrain.TerrainPlain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


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