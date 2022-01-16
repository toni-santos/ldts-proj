package pt.up.fe.ldts.ootb.game.entity.creature.robot;

import pt.up.fe.ldts.ootb.game.ability.PunchAbility;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy.RobotStrategy;
import pt.up.fe.ldts.ootb.util.Vector;

public class TankRobot extends Robot {
    public TankRobot(Vector position, RobotStrategy robotStrategy) {
        super(position, robotStrategy);
        setAbility(new PunchAbility());
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
        return false;
    }

    @Override
    protected String getAliveSpritePath() {
        return "robot/tank.png";
    }

    @Override
    protected String getDeadSpritePath() {
        return "robot/tank_dead.png";
    }
}
