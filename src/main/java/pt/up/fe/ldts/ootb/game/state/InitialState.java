package pt.up.fe.ldts.ootb.game.state;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.gui.input.KeyboardEvent;
import pt.up.fe.ldts.ootb.gui.input.KeyboardListener;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.util.GUIUtil;
import pt.up.fe.ldts.ootb.util.Vector;

public class InitialState extends BaseState implements KeyboardListener {
    public InitialState(Game game) {
        super(game);
    }

    @Override
    protected void drawBeforeCreatures(Renderer renderer) {

    }

    @Override
    protected void drawAfterCreatures(Renderer renderer) {
        renderer.drawText(new Vector(GUIUtil.WIDTH/2, GUIUtil.HEIGHT/2), "Press <Enter> to start", 20, 0xFFFFFFFF);
    }

    @Override
    public void init(App app) {
        super.init(app);
        app.getInputHandler().addKeyboardListener(this);
    }

    @Override
    public void dispose(App app) {
        super.dispose(app);
        app.getInputHandler().removeKeyboardListener(this);
    }

    @Override
    public void onKeyboardEvent(KeyboardEvent event) {
        if (event.character() == '\n')
            game.changeState(new BeforePlayerState(game));
    }
}
