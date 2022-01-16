package pt.up.fe.ldts.ootb.game.entity.terrain;

import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.gui.render.Sprite;
import pt.up.fe.ldts.ootb.gui.render.SpriteLoader;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class TerrainMountain extends Terrain {
    private final Map<Integer, Sprite> sprites;

    public static final int MAX_HP = 2;
    private int hp = MAX_HP;

    public TerrainMountain(Vector pos) {
        super(pos);

        sprites = new HashMap<>();
        sprites.put(2, SpriteLoader.load("terrain/mountain_2.png"));
        sprites.put(1, SpriteLoader.load("terrain/mountain_1.png"));
        sprites.put(0, SpriteLoader.load("terrain/mountain_0.png"));
    }

    public int getHP() {
        return hp;
    }

    @Override
    public void attack() {
        if (this.hp == 0)
            return;
        this.hp--;
    }

    public boolean isAlive() { return this.hp > 0; }

    @Override
    public void render(Renderer renderer) {
        drawSelf(renderer, sprites.get(hp));
    }

    @Override
    public boolean supportsCreature(Creature creature) {
        return !isAlive();
    }
}
