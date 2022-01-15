package pt.up.fe.ldts.ootb.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.input.MouseAction;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import pt.up.fe.ldts.ootb.gui.input.KeyboardEvent;
import pt.up.fe.ldts.ootb.gui.input.MouseClickEvent;
import pt.up.fe.ldts.ootb.gui.input.MouseMoveEvent;
import pt.up.fe.ldts.ootb.gui.render.Sprite;
import pt.up.fe.ldts.ootb.util.Vector;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LanternaGUI extends BaseGUI {
    private final Screen screen;
    private final TextGraphics textGraphics;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
        textGraphics = screen.newTextGraphics();
    }

    private Terminal createTerminal(int width, int height, Font font) throws IOException {
        return new DefaultTerminalFactory()
                .setTerminalEmulatorFontConfiguration(SwingTerminalFontConfiguration.newInstance(font))
                .setInitialTerminalSize(new TerminalSize(width, height))
                .createTerminal();
    }

    private Font loadSquareFont() throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        return font.deriveFont(Font.PLAIN, 3);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);
        screen.doResizeIfNecessary();
        return screen;
    }

    private boolean isTransparent(int color) {
        return ((color >>> 24) & 0xFF) == 0;
    }

    private TextColor textColorFromColor(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        return new TextColor.RGB(r, g, b);
    }

    private TextCharacter textCharacterFromColor(int color) {
        return TextCharacter.DEFAULT_CHARACTER.withBackgroundColor(textColorFromColor(color));
    }

    private Vector vectorFromTerminalPosition(TerminalPosition position) {
        return new Vector(position.getColumn(), position.getRow());
    }

    private TerminalPosition terminalPositionFromVector(Vector vector) {
        return new TerminalPosition(vector.x(), vector.y());
    }

    private TerminalSize terminalSizeFromVector(Vector vector) {
        return new TerminalSize(vector.x(), vector.y());
    }

    @Override
    public void getInput() {
        while (true) {
            try {
                KeyStroke keyStroke = screen.pollInput();

                if (keyStroke == null)
                    return;

                if (keyStroke.getKeyType() == KeyType.MouseEvent) {
                    MouseAction action = (MouseAction) keyStroke;

                    switch (action.getActionType()) {
                        case DRAG, MOVE -> notifyMouseMoveListeners(new MouseMoveEvent(
                                vectorFromTerminalPosition(action.getPosition())
                        ));
                        case CLICK_DOWN -> notifyMouseClickListeners(new MouseClickEvent(
                                vectorFromTerminalPosition(action.getPosition()),
                                action.getButton()
                        ));
                    }
                } else {
                    notifyKeyboardListeners(new KeyboardEvent());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void drawEllipse(Vector topLeft, Vector bottomRight, int color) {
        Vector tmp = bottomRight.sub(topLeft);
        if (tmp.x() < 0 || tmp.y() < 0)
            throw new IllegalArgumentException();

        double a = tmp.x()/2d;
        double b = tmp.y()/2d;
        double h = topLeft.x() + a;
        double k = topLeft.y() + b;

        for (double x = topLeft.x() + .5; x < bottomRight.x(); ++x)
            for (double y = topLeft.y() + .5; y < bottomRight.y(); ++y)
                if ((x-h)*(x-h)/(a*a) + (y-k)*(y-k)/(b*b) <= 1)
                    drawPixel(new Vector((int)x, (int)y), color);
    }

    @Override
    public void drawRectangle(Vector topLeft, Vector bottomRight, int color) {
        Vector tmp = bottomRight.sub(topLeft);
        if (tmp.x() < 0 || tmp.y() < 0)
            throw new IllegalArgumentException();

        textGraphics.fillRectangle(
                terminalPositionFromVector(topLeft),
                terminalSizeFromVector(tmp),
                textCharacterFromColor(color));
    }

    @Override
    public void drawLine(Vector from, Vector to, int color) {
        textGraphics.drawLine(from.x(), from.y(), to.x(), to.y(), textCharacterFromColor(color));
    }

    @Override
    public void drawSprite(Vector position, Sprite sprite) {
        drawPartialSprite(position, sprite, new Vector(0, 0), new Vector(sprite.width(), sprite.height()));
    }

    @Override
    public void drawPartialSprite(Vector position, Sprite sprite, Vector topLeft, Vector bottomRight) {
        for (int x = topLeft.x(); x < bottomRight.x(); ++x)
            for (int y = topLeft.y(); y < bottomRight.y(); ++y)
                drawPixel(position.sub(topLeft).add(x, y), sprite.pixelAt(x, y));
    }

    @Override
    public void drawText(Vector position, String text, int size, int color) {
        // TODO
    }

    @Override
    public void drawPixel(Vector position, int color) {
        if (!isTransparent(color))
            screen.setCharacter(position.x(), position.y(), textCharacterFromColor(color));
    }

    @Override
    public void render() {
        notifyRenderables();
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
