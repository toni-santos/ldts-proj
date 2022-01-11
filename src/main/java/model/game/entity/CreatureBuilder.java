package model.game.entity;

public class CreatureBuilder {
    Faction faction;
    Type type;

    public CreatureBuilder(Faction faction, Type type) {
        this.faction = faction;
        this.type = type;
    }

    public Creature build() {
        switch (this.faction) {
            case ALIEN:
                return new AlienBuilder(type).build();
            case ROBOT:
                return new RobotBuilder(type).build();
        }
        return null;
    }
}
