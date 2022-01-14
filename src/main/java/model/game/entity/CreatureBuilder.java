package model.game.entity;

import model.Position;

import java.util.Arrays;
import java.util.Vector;

public class CreatureBuilder {
    Creature.Faction faction;
    Creature.Type type;

    private Position position;

    public CreatureBuilder() { }

    public Creature build() {
        switch (this.faction) {
            case ROBOT:
                switch (this.type) {
                    case TANK:
                        return new RobotTank(position, 3, 4, false, new Vector<>(Arrays.asList(Ability.PUNCH, Ability.GRENADE)));
                    case CANNON:
                        return new RobotCannon(position,2, 5, false, new Vector<>(Arrays.asList(Ability.LONG_RANGE_MISSILE, Ability.GRENADE)));
                    case FLYING:
                        return new RobotFlying(position,2, 6, true, new Vector<>(Arrays.asList(Ability.LONG_RANGE_MISSILE, Ability.ARMAGEDDON)));
                }
                break;
            case ALIEN:
                switch (this.type) {
                    case TANK:
                        return new AlienTank(position,3, 4, false, new Vector<>(Arrays.asList(Ability.PUNCH, Ability.GRENADE)));
                    case CANNON:
                        return new AlienCannon(position,2, 5, false, new Vector<>(Arrays.asList(Ability.LONG_RANGE_MISSILE, Ability.GRENADE)));
                    case FLYING:
                        return new AlienFlying(position,2, 6, true, new Vector<>(Arrays.asList(Ability.LONG_RANGE_MISSILE, Ability.ARMAGEDDON)));
                }
                break;
        }
        return null;
    }

    public CreatureBuilder setFaction(Creature.Faction faction) {
        this.faction = faction;
        return this;
    }

    public CreatureBuilder setType(Creature.Type type) {
        this.type = type;
        return this;
    }

    public  CreatureBuilder setPosition(Position position) {
        this.position = position;
        return this;
    }
}
