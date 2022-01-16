package pt.up.fe.ldts.ootb.menu;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.gui.render.Renderable;

public abstract class BaseMenu implements Menu, Renderable {
    @Override
    public void init(App app) {
        app.getRenderer().addRenderable(this);
    }

    @Override
    public void dispose(App app) {
        app.getRenderer().removeRenderable(this);
    }
}
