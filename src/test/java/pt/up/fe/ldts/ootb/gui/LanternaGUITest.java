package pt.up.fe.ldts.ootb.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.MouseAction;
import com.googlecode.lanterna.input.MouseActionType;
import com.googlecode.lanterna.screen.Screen;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.lifecycle.BeforeProperty;
import net.jqwik.api.lifecycle.BeforeTry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.ootb.gui.input.*;
import pt.up.fe.ldts.ootb.gui.render.Renderable;
import pt.up.fe.ldts.ootb.gui.render.Sprite;
import pt.up.fe.ldts.ootb.util.Vector;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LanternaGUITest {
    private static final Map<Integer, TextColor> COLORS = new HashMap<>();
    private static final Map<Vector, Integer> ELLIPSE_AREAS = new HashMap<>();

    private LanternaGUI gui;
    private Screen screen;
    private TextGraphics textGraphics;

    @BeforeAll
    public static void setupColors() {
        COLORS.put(0xFFFFFFFF, new TextColor.RGB(0xFF, 0xFF, 0xFF));
        COLORS.put(0xFFFFFF00, new TextColor.RGB(0xFF, 0xFF, 0x00));
        COLORS.put(0xFFFF00FF, new TextColor.RGB(0xFF, 0x00, 0xFF));
        COLORS.put(0xFF00FFFF, new TextColor.RGB(0x00, 0xFF, 0xFF));
        COLORS.put(0x00000000, null);
        COLORS.put(0x44FFFFFF, new TextColor.RGB(0xFF, 0xFF, 0xFF));
        COLORS.put(0x22FF0000, new TextColor.RGB(0xFF, 0x00, 0x00));
        COLORS.put(0x5600FF00, new TextColor.RGB(0x00, 0xFF, 0x00));
        COLORS.put(0x890000FF, new TextColor.RGB(0x00, 0x00, 0xFF));
    }

    @BeforeAll
    public static void setupEllipseAreas() {
        // (Taken from another program)
        ELLIPSE_AREAS.put(new Vector(3, 3), 9);
        ELLIPSE_AREAS.put(new Vector(4, 4), 12);
        ELLIPSE_AREAS.put(new Vector(5, 5), 21);
        ELLIPSE_AREAS.put(new Vector(8, 8), 52);
        ELLIPSE_AREAS.put(new Vector(20, 20), 316);
        ELLIPSE_AREAS.put(new Vector(40, 8), 260);
        ELLIPSE_AREAS.put(new Vector(8, 40), 260);
        ELLIPSE_AREAS.put(new Vector(128, 32), 3212);
        ELLIPSE_AREAS.put(new Vector(32, 128), 3212);
    }

    @BeforeTry
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

    @Test
    public void getInputTest() throws IOException {
        KeyboardListener keyboardListener = Mockito.mock(KeyboardListener.class);
        MouseMoveListener mouseMoveListener = Mockito.mock(MouseMoveListener.class);
        MouseClickListener mouseClickListener = Mockito.mock(MouseClickListener.class);

        gui.addKeyboardListener(keyboardListener);
        gui.addMouseMoveListener(mouseMoveListener);
        gui.addMouseClickListener(mouseClickListener);

        Mockito.when(screen.pollInput()).thenReturn(
                new KeyStroke('A', false, false, false),
                new MouseAction(MouseActionType.MOVE, 0, new TerminalPosition(4, 7)),
                new MouseAction(MouseActionType.CLICK_DOWN, 3, new TerminalPosition(3, 9)),
                null
        );

        gui.getInput();

        Mockito.verify(keyboardListener, Mockito.times(1)).onKeyboardEvent(new KeyboardEvent());
        Mockito.verify(mouseMoveListener, Mockito.times(1)).onMouseMove(new MouseMoveEvent(new Vector(4, 7)));
        Mockito.verify(mouseClickListener, Mockito.times(1)).onMouseClick(new MouseClickEvent(new Vector(3, 9), 3));
    }

    @Test
    public void renderTest() throws IOException {
        Renderable renderable = Mockito.mock(Renderable.class);
        gui.addRenderable(renderable);
        gui.render();

        Mockito.verify(renderable, Mockito.times(1)).render(gui);
        Mockito.verify(screen, Mockito.times(1)).refresh();
    }

    @Test
    public void drawEllipseTest() {
        Vector tl = new Vector(0, 0);

        for (Vector v : ELLIPSE_AREAS.keySet()) {
            gui.drawEllipse(tl, v, 0xFFFFFFFF);
            Mockito.verify(screen, Mockito.times(ELLIPSE_AREAS.get(v)))
                    .setCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.any());
            Mockito.reset(screen);
        }
    }

    @Property
    public void drawRectangleTest(
            @ForAll @IntRange(max = 0xFFFF) int x1,
            @ForAll @IntRange(max = 0xFFFF) int y1,
            @ForAll @IntRange(max = 0xFFFF) int x2,
            @ForAll @IntRange(max = 0xFFFF) int y2) {
        Vector topLeft = new Vector(Math.min(x1, x2), Math.min(y1, y2));
        Vector bottomRight = new Vector(Math.max(x1, x2), Math.max(y1, y2));

        gui.drawRectangle(topLeft, bottomRight, 0xFFFFFFFF);
        
        Mockito.verify(textGraphics).fillRectangle(Mockito.any(), Mockito.any(), Mockito.any());
    }

    @Property
    public void drawLineTest(
            @ForAll @IntRange(max = 0xFFFF) int x1,
            @ForAll @IntRange(max = 0xFFFF) int y1,
            @ForAll @IntRange(max = 0xFFFF) int x2,
            @ForAll @IntRange(max = 0xFFFF) int y2) {
        Vector topLeft = new Vector(x1, y1);
        Vector bottomRight = new Vector(x2, y2);

        gui.drawLine(topLeft, bottomRight, 0xFFFFFFFF);

        Mockito.verify(textGraphics)
                .drawLine(Mockito.eq(x1), Mockito.eq(y1), Mockito.eq(x2), Mockito.eq(y2), Mockito.any());
    }

    @Test
    public void drawSpriteTest() {
        int[] pixels = new int[100 * 100];
        Arrays.fill(pixels, 0xFFFFFFFF);
        Sprite sprite = new Sprite(100, 100, pixels);

        gui.drawSprite(new Vector(0, 0), sprite);

        Mockito.verify(screen, Mockito.times(100 * 100))
                .setCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.any());
        Mockito.reset(screen);

        gui.drawPartialSprite(new Vector(0, 0), sprite, new Vector(50, 50), new Vector(75, 75));

        Mockito.verify(screen, Mockito.times(25 * 25))
                .setCharacter(Mockito.anyInt(), Mockito.anyInt(), Mockito.any());
        Mockito.reset(screen);
    }

    @Test
    public void drawTextTest() {
        // TODO
    }

    @Test
    public void drawPixelTest() {
        Vector pos = new Vector(2, 6);

        for (int color : COLORS.keySet()) {
            gui.drawPixel(pos, color);

            TextColor c = COLORS.get(color);

            if (c != null)
                Mockito.verify(screen, Mockito.times(1))
                    .setCharacter(
                            pos.x(), pos.y(),
                            TextCharacter.DEFAULT_CHARACTER.withBackgroundColor(COLORS.get(color)));
            else
                Mockito.verifyNoInteractions(screen);

            Mockito.clearInvocations(screen);
        }
    }

}
