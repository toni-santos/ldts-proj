package pt.up.fe.ldts.ootb.game.entity.terrain;

import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.gui.render.Sprite;
import pt.up.fe.ldts.ootb.gui.render.SpriteLoader;
import pt.up.fe.ldts.ootb.util.Vector;

public class TerrainForest extends Terrain {
    private final Sprite sprite;
    private final Sprite burningSprite;

    private boolean burning = false;

    public TerrainForest(Vector pos) {
        super(pos);

        sprite = SpriteLoader.load("terrain/forest.png");
        burningSprite = SpriteLoader.load("terrain/forest_burning.png");
    }

    public void setBurning() {
        this.burning = true;
    }

    public boolean isBurning() {
        return this.burning;
    }

    @Override
    public void render(Renderer renderer) {
        drawSelf(renderer, burning ? burningSprite : sprite);
    }

    @Override
    public boolean supportsCreature(Creature creature) {
        return true;
    }

    @Override
    public void attack() {
        super.attack();
        setBurning();
    }
}
