package model.game.entity;

import model.Position;

public abstract class Entity {
    Integer HP = 0;
    Integer movementRange = 0;
    Position pos = new Position();
    boolean alive = true;
    boolean flying = false;
    boolean attackReady = true;

    Entity(Position pos)  {
        this.pos = pos;
    }

    Entity(Position pos, boolean flying, Integer movementRange)  {
        this.pos = pos;
        this.flying = flying;
        this.movementRange = movementRange;
    }

    // TODO: range of movement

    public Integer getHP() {
        return HP;
    }

    public Position getPos() {
        return pos;
    }

    public void setHP(Integer HP) {
        this.HP = HP;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        this.alive = false;
    }
}
