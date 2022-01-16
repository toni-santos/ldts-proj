package pt.up.fe.ldts.ootb.game.ability.effect;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.game.entity.terrain.Terrain;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Set;

public class PushEffect implements Effect {
    private final int distance;

    public PushEffect(int distance) {
        this.distance = distance;
    }

    @Override
    public void affect(Vector creaturePosition, Set<Vector> affectedPositions, Game game) {
        for (Vector pos: affectedPositions) {
            Creature creature = game.getCreatureAt(pos);

            if (creature != null) {
                Vector pushVector = getPushVector(creaturePosition, creature.getPosition());

                for (int i = 1; i <= distance; i++) {
                    Vector newPos = creature.getPosition().add(pushVector);
                    if (game.isValidMovement(creature, newPos))
                        creature.setPosition(newPos);
                    else {
                        creature.dealDamage(1);

                        Terrain t = game.getBoard().terrainAt(newPos);
                        if (t != null) t.attack();
                        break;
                    }
                }
            }
        }
    }

    private Vector getPushVector(Vector creaturePosition, Vector targetPosition) {
        if (this.distance < 0) {
            return creaturePosition.sub(targetPosition).normalize();
        } else {
            return targetPosition.sub(creaturePosition).normalize();
        }
    }
}
