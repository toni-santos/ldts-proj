package pt.up.fe.ldts.ootb.gui;

import pt.up.fe.ldts.ootb.gui.render.Sprite;
import pt.up.fe.ldts.ootb.util.Vector;

public class LanternaGUI extends BaseGUI {
    @Override
    public void getInput() {
        // TODO
    }

    @Override
    public void drawCircle(Vector center, int radius, int fill) {
        // TODO
    }

    @Override
    public void drawRectange(Vector topLeft, Vector bottomRight, int fill) {
        // TODO
    }

    @Override
    public void drawLine(Vector from, Vector to, int color, int weight) {
        // TODO
    }

    @Override
    public void drawSprite(Vector position, Sprite sprite) {
        // TODO
    }

    @Override
    public void drawPartialSprite(Vector position, Sprite sprite, Vector topLeft, Vector bottomRight) {
        // TODO
    }

    @Override
    public void drawText(Vector position, String text, int size, int color) {
        // TODO
    }

    @Override
    public void drawPixel(Vector position, int color) {
        // TODO
    }

    @Override
    public void render() {
        notifyRenderables();
    }
}
