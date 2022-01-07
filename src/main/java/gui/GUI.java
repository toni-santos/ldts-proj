package gui;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.Terminal;
import model.Position;

import java.io.IOException;

public class GUI {
    private Screen screen;

    public GUI(Screen screen) {
        this.screen = screen;
    }

    public void clear() {
        screen.clear();
    }

    public void close() throws IOException {
        screen.close();
    }

    public void refresh() throws IOException {
        screen.refresh();
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);
        screen.doResizeIfNecessary();
        return screen;
    }

    private void drawEntity(int x, int y, char c) {
        TextGraphics tg = screen.newTextGraphics();
        tg.putString(x, y, "" + c);
    }

    public void drawAlien(Position position) {
        drawEntity(position.getX(), position.getY(), 'a');
    }

    public void drawRobot(Position position) {
        drawEntity(position.getX(), position.getY(), 'r');
    }

    public void drawTerrain(Position position) {
        drawEntity(position.getX(), position.getY(), 't');
    }

    public void drawText(Position position, String t, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY(), t);
    }
}
