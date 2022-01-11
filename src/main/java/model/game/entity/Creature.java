package model.game.entity;

import model.Position;

import java.util.Objects;
import java.util.Vector;

/**
 * An unit controlled by the player or the game's "AI"
 */
public class Creature {

    /**
     * The factions a creature can belong to (mutually exclusive)
     */
    public enum Faction {

        /**
         * The faction of creatures that are controlled by the player
         */
        ALIEN,

        /**
         * The faction of creatures controlled by the "AI"
         */
        ROBOT;
    }

    /**
     * The types of unit a creature can be (mutually exclusive)
     */
    public enum Type {

        /**
         * A high HP unit
         */
        TANK,

        /**
         * A high damage and low HP unit
         */
        CANNON,

        /**
         * A flying unit
         */
        FLYING
    }

    /**
     * The type of the creature
     */
    private Type type;

    /**
     * The faction of the creature
     */
    private Faction faction;

    /**
     * The position of the creature
     */
    private Position pos;

    /**
     * The HP of the creature
     */
    private int HP;

    /**
     * The range of movement of a creature
     */
    private int movementRange;

    /**
     * The status of the creature (true if alive, false if deceased)
     */
    private boolean alive = true;

    /**
     * The ability of the unit to fly
     */
    private boolean flying;

    /**
     * The abilities that the unit possesses
     */
    private Vector<Ability> abilities;

    /**
     * Creates a creature with the given parameters
     *
     * @param type The type of the creature
     * @param faction The faction of the creature
     * @param HP The HP of the creature
     * @param movementRange The range of movement of the creature
     * @param flying The ability of the unit to fly
     * @param abilities The abilities that the unit possesses
     */
    public Creature(Type type, Faction faction, int HP, int movementRange, boolean flying, Vector<Ability> abilities)  {
        this.type = type;
        this.faction = faction;
        this.HP = HP;
        this.movementRange = movementRange;
        this.flying = flying;
        this.abilities = abilities;
    }

    /**
     * @return The current HP of the creature
     */
    public int getHP() { return HP; }

    /**
     * @return The current position of the creature
     */
    public Position getPos() { return pos; }

    public void setPos(Position pos) { this.pos = pos; }

    /**
     * @return The type of the creature
     */
    public Type getType() { return type; }

    /**
     * @return The faction of the creature
     */
    public Faction getFaction() { return faction; }

    /**
     * @return True if <i>the creature is alive</i>
     */
    public boolean isAlive() { return alive; }

    /**
     * @return The range of movement of the creature
     */
    public int getMovementRange() { return movementRange; }

    /**
     * @return The ability to fly of the creature
     */
    public boolean isFlying() { return flying; }

    /**
     * @return The creature's abilities
     */
    public Vector<Ability> getAbilities() { return abilities; }

    /**
     * Set the creature's alive state to false (dead)
     */
    public void kill() { this.alive = false; }

    /**
     * @param o The object to evaluate
     * @return True if <i>all of the attributes of the creatures being compared are exactly the same (HP, movementRange, alive, flying, type, faction, position and abilities)</i>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Creature)) return false;
        Creature creature = (Creature) o;
        return getHP() == creature.getHP() && getMovementRange() == creature.getMovementRange() && isAlive() == creature.isAlive() && isFlying() == creature.isFlying() && getType() == creature.getType() && getFaction() == creature.getFaction() && getPos().equals(creature.getPos()) && getAbilities().equals(creature.getAbilities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, faction, getPos(), getHP(), getMovementRange(), isAlive(), isFlying(), getAbilities());
    }
}
