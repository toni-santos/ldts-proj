package pt.up.fe.ldts.ootb.game.entity.terrain;

import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.gui.render.Sprite;
import pt.up.fe.ldts.ootb.gui.render.SpriteLoader;
import pt.up.fe.ldts.ootb.util.Vector;

public class TerrainNest extends Terrain {
    private final Sprite aliveSprite;
    private final Sprite deadSprite;

    private boolean alive = true;

    public TerrainNest(Vector pos) {
        super(pos);

        aliveSprite = SpriteLoader.load("terrain/nest_alive.png");
        deadSprite = SpriteLoader.load("terrain/nest_dead.png");
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public void attack() {
        this.alive = false;
    }

    @Override
    public void render(Renderer renderer) {
        drawSelf(renderer, alive ? aliveSprite : deadSprite);
    }

    @Override
    public boolean supportsCreature(Creature creature) {
        return false;
    }

    @Override
    public int getPower() {
        return isAlive()? 1: 0;
    }
}
