package model.game.entity;

import org.javatuples.Pair;

public enum Name {
    PUNCH,
    LONG_RANGE_MISSILE,
    GRENADE,
    ARMAGEDDON
}

public class Ability {
    String name;
    Integer range;
    String description;
    Integer damage;
    /**
     * @brief This pair is created using the library javatuples and is used to store a String of the directions in which the attack pushes enemies and how far it does
     */
    Pair<String, Integer> push;

    public Ability(Name name) {
        switch (name) {
            case PUNCH:
                this.name = "Punch";
                this.range = 1;
                this.description = "Punches with extreme force, sending enemies 2 squares back!";
                this.damage = 1;
                this.push.setAt0("back").setAt1(2);
                break;
            case LONG_RANGE_MISSILE:
                this.name = "Long Range Missile";
                this.range = 10;
                this.description = "Sends a deadly missile dealing more damage but not pushing them!";
                this.damage = 2;
                this.push = null;
                break;
            case GRENADE:
                this.name = "Grenade";
                this.range = 3;
                this.description = "Lobs a grenade at a given position, pushing everyone around it!";
                this.damage = 1;
                this.push.setAt0("N-E-W-S").setAt1(1);
                break;
            case ARMAGEDDON:
                this.name = "ARMAGEDDON";
                this.range = 0;
                this.description = "Ends the level...";
                this.damage = -256;
                this.push = null;
                break;
        }
    }

    public Integer getDamage() {
        return damage;
    }

    public Integer getRange() {
        return range;
    }

    public Pair<String, Integer> getPush() {
        return push;
    }

    public String getDescription() {
        return description;
    }
}
