package pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Random;
import java.util.Set;

public class RandomStrategy extends BaseStrategy {
    private final Random random;

    public RandomStrategy(Random random) {
        this.random = random;
    }

    public RandomStrategy() {
        this(new Random());
    }

    @Override
    public void planAttack(Creature creature, Game game) {
        Set<Vector> inRange = creature.getAbility().getTilesInRange(creature.getPosition(), game);

        if (inRange.size() > 0) {
            int rand = random.nextInt(inRange.size());

            Vector attackPos = (Vector) inRange.toArray()[rand];
            attackOffset = attackPos.sub(creature.getPosition());
        }
    }

    @Override
    public Vector move(Creature creature, Game game) {
        Set<Vector> tiles = game.movableTiles(creature);

        int rand = random.nextInt(tiles.size());

        return (Vector) tiles.toArray()[rand];
    }
}
