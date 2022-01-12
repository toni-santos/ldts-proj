package model.game.entity;

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
