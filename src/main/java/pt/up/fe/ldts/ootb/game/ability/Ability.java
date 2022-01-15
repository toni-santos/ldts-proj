package pt.up.fe.ldts.ootb.game.ability;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Set;

public interface Ability {
    Set<Vector> getTilesInRange(Vector creaturePosition, Game game);
    Set<Vector> getAffectedTiles(Vector creaturePosition, Vector selectedPosition, Game game);
    void execute(Vector creaturePosition, Set<Vector> affectedPositions, Game game);
}
