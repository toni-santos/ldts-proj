package pt.up.fe.ldts.ootb.game.entity.terrain;

import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.gui.render.Sprite;
import pt.up.fe.ldts.ootb.gui.render.SpriteLoader;
import pt.up.fe.ldts.ootb.util.Vector;

public class TerrainPlain extends Terrain {
    private final Sprite sprite;

    public TerrainPlain(Vector pos) {
        super(pos);
        sprite = SpriteLoader.load("terrain/plain.png");
    }

    @Override
    public void render(Renderer renderer) {
        drawSelf(renderer, sprite);
    }

    @Override
    public boolean supportsCreature(Creature creature) {
        return true;
    }
}
