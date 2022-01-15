package pt.up.fe.ldts.ootb.gui.render;

import pt.up.fe.ldts.ootb.util.Vector;

public interface Renderer {
    void drawCircle(Vector topLeft, Vector bottomRight, int color);

    void drawRectange(Vector topLeft, Vector bottomRight, int color);

    void drawLine(Vector from, Vector to, int color);

    void drawSprite(Vector position, Sprite sprite);

    void drawPartialSprite(Vector position, Sprite sprite, Vector topLeft, Vector bottomRight);

    void drawText(Vector position, String text, int size, int color);

    void drawPixel(Vector position, int color);

    void render();

    void addRenderable(Renderable renderable);

    void removeRenderable(Renderable renderable);
}
