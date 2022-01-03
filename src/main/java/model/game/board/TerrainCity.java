package model.game.board;

import model.Position;

public class TerrainCity extends Terrain {
    Integer HP = 0;
    boolean alive = true;

    TerrainCity(Position pos, Integer HP) {
        super(pos);
        this.HP = HP;
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
