package pt.up.fe.ldts.ootb.game.ability.range;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class CompositeRange implements Range {
    private final Range[] ranges;

    public CompositeRange(Range... ranges) {
        this.ranges = ranges;
    }

    @Override
    public Set<Vector> getTiles(Vector creaturePosition, Game game) {
        Set<Vector> tiles = new HashSet<>();

        for (Range range : ranges)
            tiles.addAll(range.getTiles(creaturePosition, game));

        return tiles;
    }
}
