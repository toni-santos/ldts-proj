package pt.up.fe.ldts.ootb.game.ability;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.ability.aoe.AreaOfEffect;
import pt.up.fe.ldts.ootb.game.ability.effect.Effect;
import pt.up.fe.ldts.ootb.game.ability.range.Range;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Set;

public abstract class BaseAbility implements Ability {
    private final Range range;
    private final AreaOfEffect aoe;
    private final Effect effect;

    public BaseAbility(Range range, AreaOfEffect aoe, Effect effect) {
        this.range = range;
        this.aoe = aoe;
        this.effect = effect;
    }

    @Override
    public Set<Vector> getAffectedTiles(Vector creaturePosition, Vector selectedPosition, Game game) {
        return aoe.getTiles(creaturePosition, selectedPosition, game);
    }

    @Override
    public Set<Vector> getTilesInRange(Vector creaturePosition, Game game) {
        return range.getTiles(creaturePosition, game);
    }

    @Override
    public void execute(Vector creaturePosition, Set<Vector> affectedPositions, Game game) {
        effect.affect(creaturePosition, affectedPositions, game);
    }
}
