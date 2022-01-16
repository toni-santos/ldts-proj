package pt.up.fe.ldts.ootb.menu;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.gui.input.KeyboardEvent;
import pt.up.fe.ldts.ootb.gui.input.KeyboardListener;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.gui.render.Sprite;
import pt.up.fe.ldts.ootb.gui.render.SpriteLoader;
import pt.up.fe.ldts.ootb.util.Vector;

public class ControlsMenu extends BaseMenu implements KeyboardListener {
    private App app;
    private Sprite bg;

    @Override
    public void init(App app) {
        super.init(app);
        app.getInputHandler().addKeyboardListener(this);

        bg = SpriteLoader.load("bg.png");

        this.app = app;
    }

    @Override
    public void dispose(App app) {
        super.dispose(app);
        app.getInputHandler().removeKeyboardListener(this);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawSprite(new Vector(0, 0), bg);

        renderer.drawText(new Vector(48*4,20), "Controls", 24, 0xFFFFFFFF);

        renderer.drawText(new Vector(48*4,100), "<x> - Exit/Go Back", 12, 0xFFFFFFFF);
        renderer.drawText(new Vector(48*4,120), "<Enter> - Skip/Go Forward", 12, 0xFFFFFFFF);
    }

    @Override
    public void onKeyboardEvent(KeyboardEvent event) {
        if (event.character() == 'x')
            app.navigate(new MainMenu());
    }
}
