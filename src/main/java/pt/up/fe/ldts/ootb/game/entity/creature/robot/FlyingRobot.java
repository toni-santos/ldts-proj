package pt.up.fe.ldts.ootb.game.entity.creature.robot;

import pt.up.fe.ldts.ootb.game.ability.DiveAbility;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy.RobotStrategy;
import pt.up.fe.ldts.ootb.util.Vector;

public class FlyingRobot extends Robot {
    public FlyingRobot(Vector position, RobotStrategy robotStrategy) {
        super(position, robotStrategy);
        setAbility(new DiveAbility());
    }

    @Override
    public int getMaxHP() {
        return 3;
    }

    @Override
    public int getMovementRange() {
        return 3;
    }

    @Override
    public boolean isFlying() {
        return true;
    }

    @Override
    protected String getAliveSpritePath() {
        return "robot/flying.png";
    }

    @Override
    protected String getDeadSpritePath() {
        return "robot/flying_dead.png";
    }
}
