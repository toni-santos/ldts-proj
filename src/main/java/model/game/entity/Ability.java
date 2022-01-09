package model.game.entity;

import org.javatuples.Pair;

/**
 * The abilities that a creature can have
 */
public enum Ability {

    /**
     * A simple ability
     */
    PUNCH("Punch", 1, "Punches with extreme force, sending enemies 2 squares back!", 1, new Pair<String, Integer>("back", 2)),
    /**
     * A simple ability
     */
    LONG_RANGE_MISSILE("Long Range Missile", 6, "Sends a deadly missile dealing more damage but not pushing them!", 1, null),
    /**
     * A simple ability
     */
    GRENADE("Grenade",3, "Lobs a grenade at a given position, pushing everyone around it!", 1, null),
    /**
     * An ability (testing purposes)
     */
    ARMAGEDDON("Armageddon", 0, "Ends the level...", 21, null);

    /**
     * The name of the ability
     */
    private final String name;

    /**
     * The range of attack of the ability
     */
    private final int range;

    /**
     * A brief description of the ability
     */
    private final String description;

    /**
     * The damage the ability deals
     */
    private final int damage;

    /**
     * Pair created using the library javatuples that stores a String of the directions in which the attack pushes enemies and how far it does
     */
    private final Pair<String, Integer> push;

    /**
     * @param name The name of the ability
     * @param range The range of attack of the ability
     * @param description A brief description of the ability
     * @param damage The damage the ability deals
     * @param push The Pair that describes the push effects of the ability
     */
    Ability(String name, int range, String description, int damage, Pair<String, Integer> push) {
        this.name = name;
        this.range = range;
        this.description = description;
        this.damage =  damage;
        this.push = push;
    }

    /**
     * @return The name of the ability
     */
    public String getName() {
        return name;
    }

    /**
     * @return The damage the ability deals
     */
    public Integer getDamage() {
        return damage;
    }

    /**
     * @return The range of the ability
     */
    public Integer getRange() {
        return range;
    }

    /**
     * @return The pair that describes the push effects of the ability
     */
    public Pair<String, Integer> getPush() {
        return push;
    }

    /**
     * @return The description of the ability
     */
    public String getDescription() {
        return description;
    }
}
