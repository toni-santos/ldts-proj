package pt.up.fe.ldts.ootb.game.state;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.Alien;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.Robot;
import pt.up.fe.ldts.ootb.game.entity.terrain.Terrain;
import pt.up.fe.ldts.ootb.gui.render.Renderable;
import pt.up.fe.ldts.ootb.gui.render.Renderer;

public abstract class BaseState implements State, Renderable {
    protected final Game game;

    public BaseState(Game game) {
        this.game = game;
    }

    @Override
    public void init(App app) {
        app.getRenderer().addRenderable(this);
    }

    @Override
    public void dispose(App app) {
        app.getRenderer().removeRenderable(this);
    }

    @Override
    public void render(Renderer renderer) {
        drawTerrain(renderer);
        drawBeforeCreatures(renderer);
        drawCreatures(renderer);
        drawAfterCreatures(renderer);
    }

    protected final void drawTerrain(Renderer renderer) {
        for (Terrain t : game.getBoard().terrains())
            t.render(renderer);
    }

    protected abstract void drawBeforeCreatures(Renderer renderer);

    protected final void drawCreatures(Renderer renderer) {
        for (Robot r : game.getRobots())
            r.render(renderer);
        for (Alien a : game.getAliens())
            a.render(renderer);
    }

    protected abstract void drawAfterCreatures(Renderer renderer);
}
