package pt.up.fe.ldts.ootb.gui.render;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class SpriteLoader {
    public static final Sprite DEFAULT_SPRITE;

    static {
        int SIZE = 64;

        int[] pixels = new int[SIZE*SIZE];
        Arrays.fill(pixels, 0xFFFF00FF);
        DEFAULT_SPRITE = new Sprite(SIZE, SIZE, pixels);
    }

    private static final Map<String, Sprite> sprites = new HashMap<>();

    public static Sprite load(String path) {
        if (sprites.containsKey(path))
            return sprites.get(path);

        try {
            URL resource = SpriteLoader.class.getClassLoader().getResource("sprites/" + path);
            BufferedImage image = ImageIO.read(resource);

            int width = image.getWidth();
            int height = image.getHeight();

            int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
            Sprite sprite = new Sprite(width, height, pixels);

            sprites.put(path, sprite);

            return sprite;
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            System.err.println("Sprite at '" + path + "' was not found, using default sprite.");
            return DEFAULT_SPRITE;
        }
    }
}
