package pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Set;

public interface RobotStrategy {
    void attack(Creature creature, Game game);
    Set<Vector> getAttack(Creature creature, Game game);
    Vector move(Creature creature, Game game);
    void planAttack(Creature creature, Game game);
}
