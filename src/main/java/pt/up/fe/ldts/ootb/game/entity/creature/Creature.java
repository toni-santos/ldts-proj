package pt.up.fe.ldts.ootb.game.entity.creature;

import pt.up.fe.ldts.ootb.game.ability.Ability;
import pt.up.fe.ldts.ootb.game.entity.Entity;
import pt.up.fe.ldts.ootb.util.Vector;

public abstract class Creature extends Entity {
    private int hp;
    private Ability ability;

    public Creature(Vector position) {
        super(position);
    }

    public int getHp() {
        return hp;
    }

    public void dealDamage(int damage) {
        this.hp = Math.max(this.hp - damage, 0);
    }

    public boolean isAlive() { return hp > 0; }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public abstract int getMaxHP();

    public abstract int getMovementRange();

    public abstract boolean isFlying();
}
