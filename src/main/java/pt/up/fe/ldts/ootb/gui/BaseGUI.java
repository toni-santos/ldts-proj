package pt.up.fe.ldts.ootb.gui;

import pt.up.fe.ldts.ootb.gui.input.*;
import pt.up.fe.ldts.ootb.gui.render.Renderable;
import pt.up.fe.ldts.ootb.gui.render.Renderer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseGUI implements Renderer, InputHandler {
    protected final List<Renderable> renderables;
    protected final Set<KeyboardListener> keyboardListeners;
    protected final Set<MouseMoveListener> mouseMoveListeners;
    protected final Set<MouseClickListener> mouseClickListeners;

    public BaseGUI() {
        renderables = new ArrayList<>();
        keyboardListeners = new HashSet<>();
        mouseMoveListeners = new HashSet<>();
        mouseClickListeners = new HashSet<>();
    }

    @Override
    public void addRenderable(Renderable renderable) {
        renderables.add(renderable);
    }

    @Override
    public void removeRenderable(Renderable renderable) {
        renderables.remove(renderable);
    }

    @Override
    public void addKeyboardListener(KeyboardListener listener) {
        keyboardListeners.add(listener);
    }

    @Override
    public void removeKeyboardListener(KeyboardListener listener) {
        keyboardListeners.remove(listener);
    }

    @Override
    public void addMouseMoveListener(MouseMoveListener listener) {
        mouseMoveListeners.add(listener);
    }

    @Override
    public void removeMouseMoveListener(MouseMoveListener listener) {
        mouseMoveListeners.remove(listener);
    }

    @Override
    public void addMouseClickListener(MouseClickListener listener) {
        mouseClickListeners.add(listener);
    }

    @Override
    public void removeMouseClickListener(MouseClickListener listener) {
        mouseClickListeners.remove(listener);
    }

    protected void notifyRenderables() {
        for (Renderable renderable : renderables)
            renderable.render(this);
    }

    protected void notifyKeyboardListeners(KeyboardEvent event) {
        for (KeyboardListener listener : keyboardListeners)
            listener.onKeyboardEvent(event);
    }

    protected void notifyMouseMoveListeners(MouseMoveEvent event) {
        for (MouseMoveListener listener : mouseMoveListeners)
            listener.onMouseMove(event);
    }

    protected void notifyMouseClickListeners(MouseClickEvent event) {
        for (MouseClickListener listener : mouseClickListeners)
            listener.onMouseClick(event);
    }
}
