package model.game.board;

import model.Position;

public class TerrainMountain extends Terrain {
    Integer HP = 2;
    boolean alive = true;

    public TerrainMountain(Position pos) {
        super(pos);
    }

    public Integer getHP() {
        return HP;
    }

    public void setHP(Integer HP) {
        this.HP = HP;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        this.alive = false;
    }
}
