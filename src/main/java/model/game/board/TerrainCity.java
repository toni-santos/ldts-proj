package model.game.board;

import model.Position;

public class TerrainCity extends Terrain {
    private int HP = 0;
    private boolean alive = true;

    public TerrainCity(Position pos, Integer HP) {
        super(pos);
        this.HP = HP;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        this.alive = false;
    }
}
