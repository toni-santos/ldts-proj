package pt.up.fe.ldts.ootb.game.entity.creature.robot;

import pt.up.fe.ldts.ootb.game.ability.MissileAbility;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy.RobotStrategy;
import pt.up.fe.ldts.ootb.util.Vector;

public class CannonRobot extends Robot {
    public CannonRobot(Vector position, RobotStrategy robotStrategy) {
        super(position, robotStrategy);
        setAbility(new MissileAbility());
    }

    @Override
    public int getMaxHP() {
        return 2;
    }

    @Override
    public int getMovementRange() {
        return 3;
    }

    @Override
    public boolean isFlying() {
        return false;
    }

    @Override
    protected String getAliveSpritePath() {
        return "robot/cannon.png";
    }

    @Override
    protected String getDeadSpritePath() {
        return "robot/cannon_dead.png";
    }
}
