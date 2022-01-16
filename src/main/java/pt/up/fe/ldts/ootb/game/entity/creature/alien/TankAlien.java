package pt.up.fe.ldts.ootb.game.entity.creature.alien;

import pt.up.fe.ldts.ootb.game.ability.SuperPunchAbility;
import pt.up.fe.ldts.ootb.util.Vector;

public class TankAlien extends Alien {
    public TankAlien(Vector position) {
        super(position);
        setAbility(new SuperPunchAbility());
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
        return "alien/tank.png";
    }

    @Override
    protected String getDeadSpritePath() {
        return "alien/tank_dead.png";
    }
}
