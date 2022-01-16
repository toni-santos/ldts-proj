package pt.up.fe.ldts.ootb.menu.widget;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.gui.input.MouseClickEvent;
import pt.up.fe.ldts.ootb.gui.input.MouseClickListener;
import pt.up.fe.ldts.ootb.gui.input.MouseMoveEvent;
import pt.up.fe.ldts.ootb.gui.input.MouseMoveListener;
import pt.up.fe.ldts.ootb.gui.render.Renderable;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.util.Vector;

public class Button implements Widget, Renderable, MouseMoveListener, MouseClickListener {
    private final Vector topLeft;
    private final Vector bottomRight;
    private final String label;
    private final MouseClickListener clickListener;

    private boolean hovered;

    public Button(Vector topLeft, Vector bottomRight, String label, MouseClickListener clickListener) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.label = label;
        this.clickListener = clickListener;
    }

    @Override
    public void onMouseMove(MouseMoveEvent event) {
        hovered = event.position().x() >= topLeft.x()
               && event.position().x() < bottomRight.x()
               && event.position().y() >= topLeft.y()
               && event.position().y() < bottomRight.y();
    }

    @Override
    public void onMouseClick(MouseClickEvent event) {
        if (hovered)
            clickListener.onMouseClick(event);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawRectangle(topLeft, bottomRight, hovered ? 0xFF181C28 : 0xCC181C28, 0xFF4C5B72);
        renderer.drawText(topLeft.add(bottomRight.sub(topLeft).div(2)), label, 12, 0xFFFFFFFF);
    }

    @Override
    public void init(App app) {
        app.getInputHandler().addMouseMoveListener(this);
        app.getInputHandler().addMouseClickListener(this);
        app.getRenderer().addRenderable(this);
    }

    @Override
    public void dispose(App app) {
        app.getInputHandler().removeMouseMoveListener(this);
        app.getInputHandler().removeMouseClickListener(this);
        app.getRenderer().removeRenderable(this);
    }
}
