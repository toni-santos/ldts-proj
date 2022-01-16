package pt.up.fe.ldts.ootb.menu;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.LevelLoader;
import pt.up.fe.ldts.ootb.gui.render.Renderer;

import java.net.URISyntaxException;

public class GameMenu extends BaseMenu {
    private final Game game;

    public GameMenu(Game game) {
        super();
        this.game = game;
    }

    public GameMenu(App app) throws URISyntaxException {
        this(new Game(app, LevelLoader.loadLevel("lvl01.lvl")));
    }

    public Game getGame() {
        return game;
    }

    @Override
    public void render(Renderer renderer) {
    }

    @Override
    public void dispose(App app) {
        app.getRenderer().removeRenderable(this);
        game.getState().dispose(app);
    }
}
