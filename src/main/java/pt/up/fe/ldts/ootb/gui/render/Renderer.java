package pt.up.fe.ldts.ootb.gui.render;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.util.Vector;

public interface Renderer {
    void drawEllipse(Vector topLeft, Vector bottomRight, int color);

    void drawRectangle(Vector topLeft, Vector bottomRight, int color);

    void drawRectangle(Vector topLeft, Vector bottomRight, int fill, int stroke);

    void drawLine(Vector from, Vector to, int color);

    void drawSprite(Vector position, Sprite sprite);

    void drawPartialSprite(Vector position, Sprite sprite, Vector topLeft, Vector bottomRight);

    void drawText(Vector position, String text, int size, int color);

    void drawPixel(Vector position, int color);

    void render();

    void addRenderable(Renderable renderable);

    void removeRenderable(Renderable renderable);

    void init(App app);

    void dispose(App app);
}
