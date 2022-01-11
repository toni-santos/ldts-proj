package model.game.entity;

import java.util.Arrays;
import java.util.Vector;

public class AlienBuilder {
    Type type;

    public AlienBuilder(Type type){
        this.type = type;
    }

    public Creature build() {
        switch (this.type) {
            case TANK:
                return new Creature(Creature.Type.TANK, Creature.Faction.ALIEN, 3, 4, false, new Vector<>(Arrays.asList(Ability.PUNCH, Ability.GRENADE)));
            case CANNON:
                return new Creature(Creature.Type.CANNON, Creature.Faction.ROBOT, 2, 5, false, new Vector<>(Arrays.asList(Ability.LONG_RANGE_MISSILE, Ability.GRENADE)));
            case FLYING:
                return new Creature(Creature.Type.FLYING, Creature.Faction.ROBOT, 2, 6, true, new Vector<>(Arrays.asList(Ability.LONG_RANGE_MISSILE, Ability.ARMAGEDDON)));
        }
        return null;
    }
}
