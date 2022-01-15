package pt.up.fe.ldts.ootb.util;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Assertions;

public class VectorTest {
    @Property
    public void addTest(@ForAll int x1, @ForAll int y1, @ForAll int x2, @ForAll int y2) {
        Vector v1 = new Vector(x1, y1);
        Vector result = new Vector(x1 + x2, y1 + y2);

        Assertions.assertEquals(result, v1.add(new Vector(x2, y2)));
        Assertions.assertEquals(result, v1.add(x2, y2));
    }

    @Property
    public void subTest(@ForAll int x1, @ForAll int y1, @ForAll int x2, @ForAll int y2) {
        Vector v1 = new Vector(x1, y1);
        Vector result = new Vector(x1 - x2, y1 - y2);

        Assertions.assertEquals(result, v1.sub(new Vector(x2, y2)));
        Assertions.assertEquals(result, v1.sub(x2, y2));
    }

    @Property
    public void mulTest(@ForAll int x, @ForAll int y, @ForAll int n) {
        Vector v = new Vector(x, y);
        Vector result = new Vector(x*n, y*n);

        Assertions.assertEquals(result, v.mul(n));
    }

    @Property
    public void divTest(@ForAll int x, @ForAll int y, @ForAll int n) {
        Vector v = new Vector(x, y);

        if (n == 0) {
            Assertions.assertThrows(ArithmeticException.class, () -> v.div(n));
        } else {
            Vector result = new Vector(x / n, y / n);

            Assertions.assertEquals(result, v.div(n));
        }
    }
}
