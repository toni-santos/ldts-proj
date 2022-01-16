package pt.up.fe.ldts.ootb.game.state;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.gui.input.KeyboardEvent;
import pt.up.fe.ldts.ootb.gui.input.KeyboardListener;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.menu.MainMenu;
import pt.up.fe.ldts.ootb.util.Vector;

public class WinState extends BaseState implements KeyboardListener {
    private App app;

    public WinState(Game game) {
        super(game);
    }

    @Override
    public void init(App app) {
        super.init(app);
        app.getInputHandler().addKeyboardListener(this);

        this.app = app;
    }

    @Override
    public void dispose(App app) {
        super.dispose(app);
        app.getInputHandler().removeKeyboardListener(this);
    }

    @Override
    protected void drawBeforeCreatures(Renderer renderer) {

    }

    @Override
    protected void drawAfterCreatures(Renderer renderer) {
        renderer.drawText(new Vector(4*48, 4*48), "YOU WIN!! :D", 20, 0xFFFFFFFF);
    }

    @Override
    public void onKeyboardEvent(KeyboardEvent event) {
        if (event.character() == '\n')
            app.navigate(new MainMenu());
    }
}
