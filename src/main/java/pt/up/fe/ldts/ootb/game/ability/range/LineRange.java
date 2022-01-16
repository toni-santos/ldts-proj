package pt.up.fe.ldts.ootb.game.ability.range;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.util.Direction;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class LineRange implements Range {
    private final Direction direction;
    private final int min, max;

    public LineRange(Direction direction, int min, int max) {
        this.direction = direction;
        this.min = min;
        this.max = max;
    }

    public LineRange(Direction direction, int min) {
        this(direction, min, Integer.MAX_VALUE);
    }

    public LineRange(Direction direction) {
        this(direction, 0);
    }

    @Override
    public Set<Vector> getTiles(Vector creaturePosition, Game game) {
        Set<Vector> tiles = new HashSet<>();

        for (int i = min + 1; i <= max; ++i) {
            Vector pos = direction.offset.mul(i).add(creaturePosition);
            if (game.getBoard().isInsideBoard(pos))
                tiles.add(pos);
            else
                break;
        }

        return tiles;
    }
}
