package pt.up.fe.ldts.ootb.game;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;
import net.jqwik.api.lifecycle.BeforeContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import pt.up.fe.ldts.ootb.game.entity.terrain.Terrain;
import pt.up.fe.ldts.ootb.util.Vector;

public class BoardTest {
    private static Board board;
    private static final int SIZE = 10;

    @BeforeAll
    @BeforeContainer
    public static void setup() {
        Terrain[] terrains = new Terrain[SIZE*SIZE];

        for (int i = 0; i < terrains.length; ++i)
            terrains[i] = Mockito.mock(Terrain.class);

        board = new Board(SIZE, SIZE, terrains);
    }

    @Property
    public void isInsideBoardTest(@ForAll @IntRange(max = SIZE-1) int x, @ForAll @IntRange(max = SIZE-1) int y) {
        Assertions.assertTrue(board.isInsideBoard(new Vector(x, y)));
    }

    @Property
    public void isOutsideBoardTest1(@ForAll @IntRange(min = SIZE) int x, @ForAll @IntRange(min = SIZE) int y) {
        Assertions.assertFalse(board.isInsideBoard(new Vector(x, y)));
    }

    @Property
    public void isOutsideBoardTest2(@ForAll @Negative int x, @ForAll @Negative int y) {
        Assertions.assertFalse(board.isInsideBoard(new Vector(x, y)));
    }
}
