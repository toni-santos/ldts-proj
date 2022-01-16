package pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.Alien;
import pt.up.fe.ldts.ootb.util.Direction;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.*;

public class NearestStrategy extends BaseStrategy {
    private final Random random;

    public NearestStrategy(Random random) { this.random = random; }

    public NearestStrategy() { this(new Random()); }

    @Override
    public void planAttack(Creature creature, Game game) {
        Set<Vector> inRange = creature.getAbility().getTilesInRange(creature.getPosition(), game);
        Vector attackPos = null;

        for (Alien alien: game.getAliens()) {
            if (inRange.contains(alien.getPosition())) {
                attackPos = alien.getPosition();
                break;
            }
        }

        if (attackPos == null) {
            int rand = random.nextInt(inRange.size());

            attackPos = (Vector) inRange.toArray()[rand];
        }
        attackOffset = attackPos.sub(creature.getPosition());
    }

    @Override
    public Vector move(Creature creature, Game game) {

        int oldDist = Integer.MAX_VALUE;
        Vector newPosition = null;

        for (Alien alien: game.getAliens()) {
            int newDist = creature.getPosition().distance(alien.getPosition());

            if (newDist < oldDist && creature.getMovementRange() <= newDist) {
                oldDist = newDist;
                newPosition = bfs(creature, alien.getPosition(), game);
            }
        }

        if (newPosition != null) {
            return newPosition;
        } else {
            Set<Vector> tiles = game.movableTiles(creature);

            int rand = random.nextInt(tiles.size());

            return (Vector) tiles.toArray()[rand];
        }
    }

    private Vector bfs(Creature creature, Vector target, Game game) {
        Set<Vector> visited = new HashSet<>();
        Queue<Vector> queue = new LinkedList<>();

        visited.add(target);
        queue.offer(target);

        while(!queue.isEmpty()) {
            Vector v = queue.poll();

            if (game.isValidMovement(creature, v))
                return v;

            for (Direction d : Direction.values()) {
                Vector nv = v.add(d.offset);

                if (!visited.contains(nv)) {
                    queue.offer(nv);
                    visited.add(nv);
                }
            }
        }

        return null;
    }
}
