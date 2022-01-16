package pt.up.fe.ldts.ootb.game.entity.creature.alien;

import pt.up.fe.ldts.ootb.game.ability.DoubleMissileAbility;
import pt.up.fe.ldts.ootb.util.Vector;

public class CannonAlien extends Alien {
    public CannonAlien(Vector position) {
        super(position);
        setAbility(new DoubleMissileAbility());
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
        return "alien/cannon.png";
    }

    @Override
    protected String getDeadSpritePath() {
        return "alien/cannon_dead.png";
    }
}
