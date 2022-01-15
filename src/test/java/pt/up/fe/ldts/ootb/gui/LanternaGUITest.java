package pt.up.fe.ldts.ootb.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.ootb.gui.input.*;
import pt.up.fe.ldts.ootb.gui.render.Renderable;
import pt.up.fe.ldts.ootb.util.Vector;

public class LanternaGUITest {
    private LanternaGUI gui;
    private Screen screen;
    private TextGraphics textGraphics;

    @BeforeEach
    public void setUp() {
        screen = Mockito.mock(Screen.class);
        textGraphics = Mockito.mock(TextGraphics.class);

        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);

        gui = new LanternaGUI(screen);
    }

    @Test
    public void notifyRenderablesTest() {
        Renderable renderable = Mockito.mock(Renderable.class);

        gui.addRenderable(renderable);
        gui.notifyRenderables();
        gui.render();

        Mockito.verify(renderable, Mockito.times(2)).render(gui);

        gui.removeRenderable(renderable);
        gui.notifyRenderables();
        gui.render();

        Mockito.verify(renderable, Mockito.times(2)).render(gui);
    }

    @Test
    public void notifyKeyboardListenersTest() {
        KeyboardListener keyboardListener = Mockito.mock(KeyboardListener.class);
        KeyboardEvent event = new KeyboardEvent();

        gui.addKeyboardListener(keyboardListener);
        gui.notifyKeyboardListeners(event);

        Mockito.verify(keyboardListener, Mockito.times(1)).onKeyboardEvent(event);

        gui.removeKeyboardListener(keyboardListener);
        gui.notifyKeyboardListeners(event);

        Mockito.verify(keyboardListener, Mockito.times(1)).onKeyboardEvent(event);
    }

    @Test
    public void notifyMouseMoveListenersTest() {
        MouseMoveListener mouseMoveListener = Mockito.mock(MouseMoveListener.class);
        MouseMoveEvent event = new MouseMoveEvent(new Vector(0, 0));

        gui.addMouseMoveListener(mouseMoveListener);
        gui.notifyMouseMoveListeners(event);

        Mockito.verify(mouseMoveListener, Mockito.times(1)).onMouseMove(event);

        gui.removeMouseMoveListener(mouseMoveListener);
        gui.notifyMouseMoveListeners(event);

        Mockito.verify(mouseMoveListener, Mockito.times(1)).onMouseMove(event);
    }

    @Test
    public void notifyMouseClickListenersTest() {
        MouseClickListener mouseClickListener = Mockito.mock(MouseClickListener.class);
        MouseClickEvent event = new MouseClickEvent(new Vector(0, 0), 0);

        gui.addMouseClickListener(mouseClickListener);
        gui.notifyMouseClickListeners(event);

        Mockito.verify(mouseClickListener, Mockito.times(1)).onMouseClick(event);

        gui.removeMouseClickListener(mouseClickListener);
        gui.notifyMouseClickListeners(event);

        Mockito.verify(mouseClickListener, Mockito.times(1)).onMouseClick(event);
    }
}
