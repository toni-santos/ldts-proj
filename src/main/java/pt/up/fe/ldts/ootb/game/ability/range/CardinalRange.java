package pt.up.fe.ldts.ootb.game.ability.range;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.util.Direction;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Set;

public class CardinalRange implements Range {
    private final CompositeRange helperRange;

    public CardinalRange(int min, int max) {
        LineRange[] ranges = new LineRange[Direction.values().length];
        for (int i = 0; i < ranges.length; ++i)
            ranges[i] = new LineRange(Direction.values()[i], min, max);

        helperRange = new CompositeRange(ranges);
    }

    public CardinalRange(int min) {
        this(min, Integer.MAX_VALUE);
    }

    public CardinalRange() {
        this(0);;
    }

    @Override
    public Set<Vector> getTiles(Vector creaturePosition, Game game) {
        return helperRange.getTiles(creaturePosition, game);
    }
}
