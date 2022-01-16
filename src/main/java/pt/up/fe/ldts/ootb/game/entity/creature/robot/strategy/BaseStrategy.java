package pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Collections;
import java.util.Set;

public abstract class BaseStrategy implements RobotStrategy {
    protected Vector attackOffset = null;

    @Override
    public Set<Vector> getAttack(Creature creature, Game game) {
        if (attackOffset == null || !creature.isAlive())
            return Collections.EMPTY_SET;

        Vector pos = creature.getPosition().add(attackOffset);

        return creature.getAbility().getAffectedTiles(creature.getPosition(), pos, game);
    }

    @Override
    public void attack(Creature creature, Game game) {
        creature.getAbility().execute(creature.getPosition(), getAttack(creature, game), game);
    }
}
