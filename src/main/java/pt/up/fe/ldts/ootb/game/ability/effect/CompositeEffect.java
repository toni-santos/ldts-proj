package pt.up.fe.ldts.ootb.game.ability.effect;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Set;

public class CompositeEffect implements Effect {
    private final Effect[] effects;

    public CompositeEffect(Effect... effects) {
        this.effects = effects;
    }

    @Override
    public void affect(Vector creaturePosition, Set<Vector> affectedPositions, Game game) {
        for (Effect effect : effects)
            effect.affect(creaturePosition, affectedPositions, game);
    }
}
