package model.game.entity;

import model.Position;

import java.util.Vector;

public abstract class Entity {
    Integer HP;
    Integer movementRange;
    Position pos;
    boolean alive = true;
    boolean flying;
    Vector<Ability> abilities;

    Entity(Position pos, boolean flying, Integer movementRange, Vector<Ability> abilities)  {
        this.pos = pos;
        this.flying = flying;
        this.movementRange = movementRange;
        this.abilities = abilities;
    }

    public Integer getHP() {
        return HP;
    }

    public Position getPos() {
        return pos;
    }

    public boolean isAlive() {
        return alive;
    }

    public Integer getMovementRange() { return movementRange; }

    public boolean isFlying() { return flying; }

    public Vector<Ability> getAbilities() { return abilities; }

    public void setHP(Integer HP) {
        this.HP = HP;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setMovementRange(Integer movementRange) { this.movementRange = movementRange; }

    public void addAbilities(Ability ability) { this.getAbilities().add(ability); }

    public void kill() {
        this.alive = false;
    }
}
