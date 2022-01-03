package model;

import model.game.board.Board;
import model.game.board.Terrain;
import model.game.board.TerrainPlain;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Vector;


public class BoardTest {
    @Test
    public void PlainBoardTest() {
        Board board = new Board(8,8);

        for (Vector<Terrain> TerrainRow: board.getTerrains()) {
            for (Terrain terrain: TerrainRow) {
                assertEquals(TerrainPlain.class,terrain.getClass());
            }
        }
    }

    @Test
    public void FileBoardTest() throws URISyntaxException {
        File mapFile = new File(getClass().getClassLoader().getResource("levels/lvl01.lvl").toURI());
        Board board = new Board(mapFile);

        //TODO: write the test :P
    }
}