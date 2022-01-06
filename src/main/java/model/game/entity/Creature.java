package model.game.entity;

import model.Position;

import java.util.Vector;

public class Creature {

    public enum Faction {
        ALIEN,
        ROBOT;
    }

    public enum Type {
        TANK,
        CANON,
        FLYING
    }

    Type type;
    Faction faction;
    Position pos;
    int HP;
    int movementRange;
    boolean alive = true;
    boolean flying;
    Vector<Ability> abilities;

    Creature(Type type, Faction faction, Position pos, int HP, int movementRange, boolean flying, Vector<Ability> abilities)  {
        this.type = type;
        this.faction = faction;
        this.pos = pos;
        this.HP = HP;
        this.movementRange = movementRange;
        this.flying = flying;
        this.abilities = abilities;
    }

    public int getHP() { return HP; }

    public Position getPos() { return pos; }

    public boolean isAlive() { return alive; }

    public int getMovementRange() { return movementRange; }

    public boolean isFlying() { return flying; }

    public Vector<Ability> getAbilities() { return abilities; }

    public void kill() { this.alive = false; }
}
