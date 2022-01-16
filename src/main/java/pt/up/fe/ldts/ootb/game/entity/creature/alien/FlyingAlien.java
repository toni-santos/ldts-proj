package pt.up.fe.ldts.ootb.game.entity.creature.alien;

import pt.up.fe.ldts.ootb.game.ability.DiveAbility;
import pt.up.fe.ldts.ootb.util.Vector;

public class FlyingAlien extends Alien {
    public FlyingAlien(Vector position) {
        super(position);
        // TODO
        setAbility(new DiveAbility());
    }

    @Override
    public int getMaxHP() {
        return 2;
    }

    @Override
    public int getMovementRange() {
        return 4;
    }

    @Override
    public boolean isFlying() {
        return true;
    }

    @Override
    protected String getAliveSpritePath() {
        return "alien/flying.png";
    }

    @Override
    protected String getDeadSpritePath() {
        return "alien/flying_dead.png";
    }
}
