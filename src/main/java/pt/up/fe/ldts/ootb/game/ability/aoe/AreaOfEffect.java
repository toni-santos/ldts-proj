package pt.up.fe.ldts.ootb.game.ability.aoe;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Set;

public interface AreaOfEffect {
    Set<Vector> getTiles(Vector creaturePosition, Vector selectedPosition, Game game);
}
