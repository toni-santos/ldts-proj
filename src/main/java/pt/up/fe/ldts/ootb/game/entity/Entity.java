package pt.up.fe.ldts.ootb.game.entity;

import pt.up.fe.ldts.ootb.gui.render.Renderable;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.gui.render.Sprite;
import pt.up.fe.ldts.ootb.util.Vector;

public abstract class Entity implements Renderable {
    private Vector position;

    public Entity(Vector position) {
        this.position = position;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector vector) {
        this.position = vector;
    }

    protected void drawSelf(Renderer renderer, Sprite sprite) {
        renderer.drawSprite(position.mul(48), sprite);
    }
}
