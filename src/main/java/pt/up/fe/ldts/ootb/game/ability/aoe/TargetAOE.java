package pt.up.fe.ldts.ootb.game.ability.aoe;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Collections;
import java.util.Set;

public class TargetAOE implements AreaOfEffect {
    @Override
    public Set<Vector> getTiles(Vector creaturePosition, Vector selectedPosition, Game game) {
        return Collections.singleton(selectedPosition);
    }
}
