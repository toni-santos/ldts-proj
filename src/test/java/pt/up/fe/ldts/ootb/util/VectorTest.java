package pt.up.fe.ldts.ootb.util;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.lifecycle.BeforeContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashSet;
import java.util.Set;

public class VectorTest {
    private static final Set<Vector> NORMALIZED_VECTORS = new HashSet<>();

    @BeforeAll
    @BeforeContainer
    public static void setupNormalizedVectors() {
        NORMALIZED_VECTORS.add(new Vector(0, 0));
        NORMALIZED_VECTORS.add(new Vector(1, 0));
        NORMALIZED_VECTORS.add(new Vector(1, 1));
        NORMALIZED_VECTORS.add(new Vector(0, 1));
        NORMALIZED_VECTORS.add(new Vector(0, -1));
        NORMALIZED_VECTORS.add(new Vector(-1, -1));
        NORMALIZED_VECTORS.add(new Vector(-1, 0));
        NORMALIZED_VECTORS.add(new Vector(-1, 1));
        NORMALIZED_VECTORS.add(new Vector(1, -1));
    }

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

    @Property
    public void normalizeTest(@ForAll int x, @ForAll int y) {
        Vector v = new Vector(x, y);

        Assertions.assertTrue(NORMALIZED_VECTORS.contains(v.normalize()));
    }
}
