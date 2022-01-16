package pt.up.fe.ldts.ootb.menu;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.gui.input.MouseClickEvent;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.gui.render.Sprite;
import pt.up.fe.ldts.ootb.gui.render.SpriteLoader;
import pt.up.fe.ldts.ootb.menu.widget.Button;
import pt.up.fe.ldts.ootb.menu.widget.Widget;
import pt.up.fe.ldts.ootb.util.Vector;

import java.net.URISyntaxException;

public class MainMenu extends BaseMenu  {
    private Sprite bg;

    private App app;

    private final Widget[] widgets = {
            new Button(new Vector(48, 100), new Vector(48*3, 120), "Play", this::playButton),
            new Button(new Vector(48, 140), new Vector(48*3, 160), "Options", this::optionsButton),
            new Button(new Vector(48, 180), new Vector(48*3, 200), "Exit", this::exitButton)
    };

    @Override
    public void init(App app) {
        super.init(app);

        bg = SpriteLoader.load("bg.png");

        for (Widget w : widgets)
            w.init(app);

        this.app = app;
    }

    @Override
    public void dispose(App app) {
        super.dispose(app);

        for (Widget w : widgets)
            w.dispose(app);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawSprite(new Vector(0, 0), bg);
        renderer.drawText(new Vector(48*4,40), "Out Of The Breach", 28, 0xFFFFFFFF);
    }

    private void playButton(MouseClickEvent event) {
        try {
            app.navigate(new GameMenu(app));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void optionsButton(MouseClickEvent event) {
        app.navigate(new ControlsMenu());
    }

    private void exitButton(MouseClickEvent event) {
        app.navigate(null);
    }
}
