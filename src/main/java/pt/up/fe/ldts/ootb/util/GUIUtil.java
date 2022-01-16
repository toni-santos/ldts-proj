package pt.up.fe.ldts.ootb.util;

public final class GUIUtil {
    public static final int TILE_WIDTH = 48;
    public static final int TILE_HEIGHT = 48;

    public static final int BOARD_WIDTH = 8*TILE_WIDTH;
    public static final int BOARD_HEIGHT = 8*TILE_HEIGHT;

    public static final int LEFT_OFFSET = 0;
    public static final int RIGHT_OFFSET = 0;
    public static final int TOP_OFFSET = 0;
    public static final int BOTTOM_OFFSET = 0;

    public static final int WIDTH = LEFT_OFFSET + BOARD_WIDTH + RIGHT_OFFSET;
    public static final int HEIGHT = TOP_OFFSET + BOARD_HEIGHT + BOTTOM_OFFSET;

    public static Vector boardToScreen(Vector position) {
        return new Vector(position.x() * TILE_WIDTH, position.y() * TILE_HEIGHT).add(LEFT_OFFSET, TOP_OFFSET);
    }

    public static Vector screenToBoard(Vector position) {
        position = position.sub(LEFT_OFFSET, TOP_OFFSET);

        return new Vector(position.x()/TILE_WIDTH, position.y()/TILE_HEIGHT);
    }
}
