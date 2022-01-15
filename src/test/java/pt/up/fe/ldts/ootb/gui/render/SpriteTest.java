package pt.up.fe.ldts.ootb.gui.render;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;
import net.jqwik.api.lifecycle.BeforeContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpriteTest {
    private static final int TEST_SPRITE_WIDTH = 4;
    private static final int TEST_SPRITE_HEIGHT = 4;
    private static final int[] TEST_SPRITE = {
            0xFF000000, 0xFF444444, 0xFF888888, 0xFFCCCCCC,
            0xFFFF0000, 0xFF00FF00, 0xFF0000FF, 0xFFFFFFFF,
            0xFF00FFFF, 0xFFFF00FF, 0xFFFFFF00, 0x00000000,
            0xFF000000, 0xCC000000, 0x88000000, 0x44000000
    };

    @Test
    public void testSpriteTest() {
        Sprite test = SpriteLoader.load("test.png");

        Assertions.assertNotNull(test);

        Assertions.assertEquals(TEST_SPRITE_WIDTH, test.width());
        Assertions.assertEquals(TEST_SPRITE_HEIGHT, test.height());
        Assertions.assertArrayEquals(TEST_SPRITE, test.pixels());
    }

    private static final int SPRITE_SIZE = 100;
    private static Sprite sprite;

    @BeforeContainer
    static void pixelAtTestSetup() {
        int[] pixels = new int[SPRITE_SIZE*SPRITE_SIZE];

        int i = 0;
        for (int y = 0; y < SPRITE_SIZE; ++y)
            for (int x = 0; x < SPRITE_SIZE; ++x)
                pixels[i++] = x << 2 + y;

        sprite = new Sprite(SPRITE_SIZE, SPRITE_SIZE, pixels);
    }

    @Property
    public void pixelAtTest(@ForAll @IntRange(max = SPRITE_SIZE-1) int x, @ForAll @IntRange(max = SPRITE_SIZE-1) int y) {
        int pixel = sprite.pixelAt(x, y);

        Assertions.assertEquals(x << 2 + y, pixel);
    }

    @Property
    public void pixelAtBigValuesTest(@ForAll @IntRange(min = SPRITE_SIZE) int x, @ForAll @IntRange(min = SPRITE_SIZE) int y) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sprite.pixelAt(x, y));
    }

    @Property
    public void pixelAtNegativeValuesTest(@ForAll @Negative int x, @ForAll @Negative int y) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sprite.pixelAt(x, y));
    }

    @Property
    public void spriteNotFoundTest(@ForAll String name) {
        Sprite notFound = SpriteLoader.load(name);

        Assertions.assertEquals(SpriteLoader.DEFAULT_SPRITE, notFound);
    }
}
