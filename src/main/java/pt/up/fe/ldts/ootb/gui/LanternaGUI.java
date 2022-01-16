package pt.up.fe.ldts.ootb.gui;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFontConfiguration;
import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.gui.input.KeyboardEvent;
import pt.up.fe.ldts.ootb.gui.input.MouseClickEvent;
import pt.up.fe.ldts.ootb.gui.input.MouseMoveEvent;
import pt.up.fe.ldts.ootb.gui.render.Sprite;
import pt.up.fe.ldts.ootb.gui.render.SpriteLoader;
import pt.up.fe.ldts.ootb.util.GUIUtil;
import pt.up.fe.ldts.ootb.util.Vector;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

public class LanternaGUI extends BaseGUI {
    private int PPC;
    private Screen screen;
    private TextGraphics textGraphics;
    private MyMouseAdapter mouseAdapter;

    public LanternaGUI(Screen screen, MyMouseAdapter mouseAdapter) {
        PPC = 1;

        this.screen = screen;
        textGraphics = screen.newTextGraphics();
        this.mouseAdapter = mouseAdapter;
    }

    public LanternaGUI() {
    }

    @Override
    public void init(App app) {
        if (screen == null)
            try {
                mouseAdapter = new MyMouseAdapter();

                PPC = getDefaultPPC();

                screen = createScreen(createTerminal(app, loadSquareFont()));
                textGraphics = screen.newTextGraphics();
            } catch (IOException | URISyntaxException | FontFormatException exception) {
                exception.printStackTrace();
                dispose(app);
            }
    }

    @Override
    public void dispose(App app) {
        try {
            screen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getDefaultPPC() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        return Math.min(width / GUIUtil.WIDTH, height / GUIUtil.HEIGHT);
    }

    private Terminal createTerminal(App app, Font font) throws IOException {
        AWTTerminalFrame terminal = (AWTTerminalFrame) new DefaultTerminalFactory()
                .setTerminalEmulatorFontConfiguration(SwingTerminalFontConfiguration.newInstance(font))
                .setInitialTerminalSize(new TerminalSize(GUIUtil.WIDTH, GUIUtil.HEIGHT))
                .setForceAWTOverSwing(true)
                .createTerminal();

        terminal.getComponent(0).addMouseMotionListener(mouseAdapter);
        terminal.getComponent(0).addMouseListener(mouseAdapter);
        terminal.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                app.navigate(null);
            }
        });
        terminal.setResizable(false);

        return terminal;
    }

    private Font loadSquareFont() throws URISyntaxException, IOException, FontFormatException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        return font.deriveFont(Font.PLAIN, PPC);
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);
        return screen;
    }

    private TextColor compositeColors(int fg, TextColor bg) {
        int a = (fg >>> 24) & 0xFF;
        int r = compositeComponent((fg >> 16) & 0xFF, bg.getRed(), a);
        int g = compositeComponent((fg >> 8) & 0xFF, bg.getGreen(), a);
        int b = compositeComponent(fg & 0xFF, bg.getBlue(), a);

        return new TextColor.RGB(r, g, b);
    }

    private int compositeComponent(int fg, int bg, int alpha) {
        return ((fg * alpha) + (bg*(0xFF-alpha)))/255;
    }

    @Override
    public void getInput() {
        while (mouseAdapter.getClickEventQueue().size() > 0)
            notifyMouseClickListeners(mouseAdapter.getClickEventQueue().remove());

        while (mouseAdapter.getMoveEventQueue().size() > 0)
            notifyMouseMoveListeners(mouseAdapter.getMoveEventQueue().remove());

        while (true) {
            try {
                KeyStroke keyStroke = screen.pollInput();

                if (keyStroke == null || keyStroke.getKeyType() == KeyType.EOF)
                    return;

                if (keyStroke.getCharacter() != null)
                    notifyKeyboardListeners(new KeyboardEvent(keyStroke.getCharacter()));
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

        for (int x = topLeft.x(); x < bottomRight.x(); ++x)
            for (int y = topLeft.y(); y < bottomRight.y(); ++y)
                drawPixel(new Vector(x, y), color);
    }

    @Override
    public void drawRectangle(Vector topLeft, Vector bottomRight, int fill, int stroke) {
        Vector tmp = bottomRight.sub(topLeft);
        if (tmp.x() < 0 || tmp.y() < 0)
            throw new IllegalArgumentException();

        if (tmp.equals(new Vector(1, 1)))
            drawPixel(topLeft, stroke);
        else if (tmp.x() == 1 || tmp.y() == 1)
            drawLine(topLeft, bottomRight, stroke);
        else {
            Vector bottomLeft = topLeft.add(0, tmp.y()-1);
            Vector topRight = topLeft.add(tmp.x()-1, 0);
            bottomRight = bottomRight.sub(1, 1);

            drawRectangle(topLeft.add(1, 1), bottomRight, fill);
            drawLine(topLeft, topRight, stroke);
            drawLine(topLeft.add(0, 1), bottomLeft.sub(0, 1), stroke);
            drawLine(bottomLeft, bottomRight, stroke);
            drawLine(topRight.add(0, 1), bottomRight.sub(0, 1), stroke);
        }
    }

    private void drawLineLow(Vector from, Vector to, Vector d, int color) {
        int yi = 1;

        if (d.y() < 0) {
            yi = -1;
            d = new Vector(d.x(), -d.y());
        }

        int D = 2*d.y() - d.x();
        int y = from.y();

        for (int x = from.x(); x <= to.x(); x++) {
            drawPixel(new Vector(x, y), color);
            if (D > 0) {
                y += yi;
                D += 2 * (d.y() - d.x());
            } else
                D += 2 * d.y();
        }
    }

    private void drawLineHigh(Vector from, Vector to, Vector d, int color) {
        int xi = 1;

        if (d.x() < 0) {
            xi = -1;
            d = new Vector(-d.x(), d.y());
        }

        int D = 2*d.x() - d.y();
        int x = from.x();

        for (int y = from.y(); y <= to.y(); y++) {
            drawPixel(new Vector(x, y), color);
            if (D > 0) {
                x += xi;
                D += 2 * (d.x() - d.y());
            } else
                D += 2 * d.x();
        }
    }

    @Override
    public void drawLine(Vector from, Vector to, int color) {
        Vector d = to.sub(from);
        if (Math.abs(d.y()) < Math.abs(d.x()))
            if (from.x() > to.x())
                drawLineLow(to, from, d, color);
            else
                drawLineLow(from, to, d, color);
        else
            if (from.y() > to.y())
                drawLineHigh(to, from, d, color);
            else
                drawLineHigh(from, to, d, color);
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
        Sprite textSprite = SpriteLoader.fromText(text, size, color);

        drawSprite(position.sub(textSprite.width()/2, textSprite.height()/2), textSprite);
    }

    @Override
    public void drawPixel(Vector position, int color) {
        TextColor c = compositeColors(color, textGraphics.getCharacter(position.x(), position.y()).getBackgroundColor());

        screen.setCharacter(position.x(), position.y(), TextCharacter.DEFAULT_CHARACTER.withBackgroundColor(c));
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

    class MyMouseAdapter extends MouseAdapter {
        private final Queue<MouseMoveEvent> moveEventQueue = new LinkedList<>();
        private final Queue<MouseClickEvent> clickEventQueue = new LinkedList<>();

        public Queue<MouseMoveEvent> getMoveEventQueue() {
            return moveEventQueue;
        }

        public Queue<MouseClickEvent> getClickEventQueue() {
            return clickEventQueue;
        }

        private MouseMoveEvent awtToInternalMoveEvent(MouseEvent event) {
            return new MouseMoveEvent(new Vector(event.getX()/PPC, event.getY()/PPC));
        }

        private MouseClickEvent awtToInternalClickEvent(MouseEvent event) {
            return new MouseClickEvent(new Vector(event.getX()/PPC, event.getY()/PPC), event.getButton());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            moveEventQueue.add(awtToInternalMoveEvent(e));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            clickEventQueue.add(awtToInternalClickEvent(e));
        }
    }
}
