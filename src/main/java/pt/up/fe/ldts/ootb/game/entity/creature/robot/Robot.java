package pt.up.fe.ldts.ootb.game.entity.creature.robot;

import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy.RobotStrategy;
import pt.up.fe.ldts.ootb.util.Vector;

public abstract class Robot extends Creature {
    private final RobotStrategy robotStrategy;

    public Robot(Vector position, RobotStrategy robotStrategy) {
        super(position);
        this.robotStrategy = robotStrategy;
    }

    public RobotStrategy getStrategy() {
        return robotStrategy;
    }
}
