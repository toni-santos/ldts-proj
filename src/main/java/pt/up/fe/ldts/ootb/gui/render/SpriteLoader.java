package pt.up.fe.ldts.ootb.gui.render;

import pt.up.fe.ldts.ootb.util.GUIUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class SpriteLoader {
    public static final Sprite DEFAULT_SPRITE;
    public static final Font FONT;

    static {
        int[] pixels = new int[GUIUtil.TILE_WIDTH*GUIUtil.TILE_HEIGHT];

        for (int x = 0; x < GUIUtil.TILE_WIDTH; x++)
            for (int y = 0; y < GUIUtil.TILE_HEIGHT; y++)
                pixels[x + y*GUIUtil.TILE_WIDTH] = (x-GUIUtil.TILE_WIDTH/2)*(y-GUIUtil.TILE_HEIGHT/2) > 0 ? 0xFF000000 : 0xFFFF00FF;

        DEFAULT_SPRITE = new Sprite(GUIUtil.TILE_WIDTH, GUIUtil.TILE_HEIGHT, pixels);

        Font font;

        try {
            URL resource = SpriteLoader.class.getClassLoader().getResource("fonts/OCR A Std Regular.ttf");
            File file = new File(resource.toURI());
            font = Font.createFont(Font.TRUETYPE_FONT, file);
        } catch (NullPointerException | IOException | URISyntaxException | FontFormatException e) {
            System.err.println("Font not found, using default font.");
            font = Font.decode(Font.SANS_SERIF);
        }

        FONT = font;
    }

    private static final Map<String, Sprite> sprites = new HashMap<>();

    public static Sprite load(String path) {
        if (sprites.containsKey(path))
            return sprites.get(path);

        try {
            URL resource = SpriteLoader.class.getClassLoader().getResource("sprites/" + path);
            BufferedImage image = ImageIO.read(resource);

            Sprite sprite = fromImage(image);
            sprites.put(path, sprite);
            return sprite;
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            System.err.println("Sprite at '" + path + "' was not found, using default sprite.");
            return DEFAULT_SPRITE;
        }
    }

    public static Sprite fromText(String text, int size, int color) {
        String path = "$text$." + size + "." + color + "." + text;

        if (sprites.containsKey(path))
            return sprites.get(path);

        Font font = FONT.deriveFont((float) size);

        FontRenderContext frc = new FontRenderContext(null, false, false);
        LineMetrics metrics = font.getLineMetrics(text, frc);

        int width = (int) Math.ceil(font.getStringBounds(text, frc).getWidth());
        int height = (int) Math.ceil(metrics.getHeight());
        int ascent = (int) Math.ceil(metrics.getAscent());

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics graphics = image.getGraphics();
        graphics.setColor(new Color(color & 0xFFFFFF));
        graphics.setFont(font);
        graphics.drawString(text, 0, ascent);

        Sprite sprite = fromImage(image);
        sprites.put(path, sprite);
        return sprite;
    }

    private static Sprite fromImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
        return new Sprite(width, height, pixels);
    }
}
