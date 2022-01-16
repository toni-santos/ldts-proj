package pt.up.fe.ldts.ootb.game.ability.range;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Set;

public interface Range {
    Set<Vector> getTiles(Vector creaturePosition, Game game);
}
