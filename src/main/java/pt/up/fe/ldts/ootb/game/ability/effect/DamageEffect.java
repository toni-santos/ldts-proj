package pt.up.fe.ldts.ootb.game.ability.effect;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Set;

public class DamageEffect implements Effect {
    private final int damage;

    public DamageEffect(int damage) {
        this.damage = damage;
    }

    @Override
    public void affect(Vector creaturePosition, Set<Vector> affectedPositions, Game game) {
        for (Vector pos: affectedPositions) {
            Creature creature = game.getCreatureAt(pos);

            if (creature != null) {
                creature.dealDamage(damage);
            }
        }
    }
}
