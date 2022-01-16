package pt.up.fe.ldts.ootb.game.entity.terrain;

import pt.up.fe.ldts.ootb.game.entity.Entity;
import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.util.Vector;

public abstract class Terrain extends Entity {
    public Terrain(Vector position) { super(position); }

    abstract public boolean supportsCreature(Creature creature);

    public int getPower() {
        return 0;
    };

    public void attack() {}

    public boolean pushInto(Creature creature) {
        attack();
        if (!supportsCreature(creature)) {
            creature.dealDamage(1);
            return false;
        }
        return true;
    }
}
