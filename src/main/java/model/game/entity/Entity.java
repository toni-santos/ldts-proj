package model.game.entity;

import model.Position;

public abstract class Entity {
    Integer HP = 0;
    Position pos = new Position();
    boolean alive = true;
    boolean flying = false;
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
