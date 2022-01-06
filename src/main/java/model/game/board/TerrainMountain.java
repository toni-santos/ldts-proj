package model.game.board;

import model.Position;

public class TerrainMountain extends Terrain {
    private int HP = 2;
    private boolean alive = true;

    public TerrainMountain(Position pos) {
        super(pos);
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
