package pt.up.fe.ldts.ootb.gui.render;

public record Sprite(int width, int height, int[] pixels) {
    public int pixelAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height)
            throw new IllegalArgumentException();

        return pixels[x + y*width];
    }
}
