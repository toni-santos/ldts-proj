package pt.up.fe.ldts.ootb.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectionTest {
    @Test
    public void directionTest() {
        Vector origin = new Vector(0, 0);
        Assertions.assertEquals(origin, Direction.DOWN.offset.add(Direction.UP.offset));
        Assertions.assertEquals(origin, Direction.LEFT.offset.add(Direction.RIGHT.offset));
        Assertions.assertEquals(origin, Direction.LEFT.offset.add(Direction.UP.offset)
                .add(Direction.RIGHT.offset).add(Direction.DOWN.offset));
    }
}
