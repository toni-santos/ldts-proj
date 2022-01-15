package pt.up.fe.ldts.ootb.game.ability.effect;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Set;

public interface Effect {
    void affect(Vector creaturePosition, Set<Vector> affectedPositions, Game game);
}
