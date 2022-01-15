package pt.up.fe.ldts.ootb.game.entity.creature;

import pt.up.fe.ldts.ootb.game.ability.Ability;
import pt.up.fe.ldts.ootb.game.entity.Entity;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.gui.render.Sprite;
import pt.up.fe.ldts.ootb.gui.render.SpriteLoader;
import pt.up.fe.ldts.ootb.util.Vector;

public abstract class Creature extends Entity {
    private final Sprite aliveSprite;
    private final Sprite deadSprite;

    private int hp;
    private Ability ability;

    public Creature(Vector position) {
        super(position);

        hp = getMaxHP();

        aliveSprite = SpriteLoader.load(getAliveSpritePath());
        deadSprite = SpriteLoader.load(getDeadSpritePath());
    }

    public int getHP() {
        return hp;
    }

    public void dealDamage(int damage) {
        this.hp = Math.max(this.hp - damage, 0);
    }

    public boolean isAlive() { return hp > 0; }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public abstract int getMaxHP();

    public abstract int getMovementRange();

    public abstract boolean isFlying();

    @Override
    public void render(Renderer renderer) {
        if (isAlive()) {
            drawSelf(renderer, aliveSprite);

            final int SIZE = 4;
            final int Y_OFFSET = 2;

            int healthWidth = 1 + (SIZE + 1) * getMaxHP();

            Vector topLeft = getPosition().mul(48).add(24 - healthWidth / 2, Y_OFFSET);
            Vector bottomRight = topLeft.add(healthWidth, SIZE + 2);
            renderer.drawRectangle(topLeft, bottomRight, 0xFF0C0F16);

            topLeft = topLeft.add(1, 1);
            for (int i = 0; i < getHP(); ++i) {
                renderer.drawRectangle(topLeft, topLeft.add(SIZE, SIZE), 0xFF27B629);
                topLeft = topLeft.add(SIZE + 1, 0);
            }
        } else {
            drawSelf(renderer, deadSprite);
        }
    }

    protected abstract String getAliveSpritePath();
    protected abstract String getDeadSpritePath();
}
