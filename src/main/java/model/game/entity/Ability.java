package model.game.entity;

import org.javatuples.Pair;

public enum Ability {

    PUNCH("Punch", 1, "Punches with extreme force, sending enemies 2 squares back!", 1, new Pair<String, Integer>("back", 2)),
    LONG_RANGE_MISSILE("Long Range Missile", 6, "Sends a deadly missile dealing more damage but not pushing them!", 1, null),
    GRENADE("Grenade",3, "Lobs a grenade at a given position, pushing everyone around it!", 1, null),
    ARMAGEDDON("Armageddon", 0, "Ends the level...", 21, null);

    private final String name;
    private final int range;
    private final String description;
    private final int damage;
    /**
     * @brief This pair is created using the library javatuples and is used to store a String of the directions in which the attack pushes enemies and how far it does
     */
    private final Pair<String, Integer> push;

    private Ability(String name, int range, String description, int damage, Pair<String, Integer> push) {
        this.name = name;
        this.range = range;
        this.description = description;
        this.damage =  damage;
        this.push = push;
    }

    public String getName() {
        return name;
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
