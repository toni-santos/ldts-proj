package gui;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.Position;

import java.io.IOException;

public class GUI {
    /**
     * @note Changes made to the GUI through these methods are only visible after calling the refresh method.
     */

    private Screen screen;

    /**
     * @brief Constructor of the GUI.
     * @param screen the Lanterna screen that forms the GUI.
     */
    public GUI(Screen screen) {
        this.screen = screen;
    }

    public GUI() throws IOException {
        this.screen = createScreen(createTerminal());
    }

    /**
     * @brief Clears the GUI.
     */
    public void clear() {
        screen.clear();
    }

    /**
     * @brief Closes the GUI.
     * @throws IOException
     */
    public void close() throws IOException {
        screen.close();
    }

    /**
     * @brief updates the screen by taking the contents from the back buffer to the front buffer.
     * @throws IOException
     */
    public void refresh() throws IOException {
        screen.refresh();
    }

    public KeyStroke getNextEvent() throws IOException {
        return screen.readInput();
    }

    /**
     * @brief Creates a new Screen object using the TerminalScreen class.
     * @param terminal the terminal under the screen to be created.
     * @return a Screen object.
     * @throws IOException
     */
    private Screen createScreen(Terminal terminal) throws IOException {
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        screen.setCursorPosition(null);
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal() throws IOException {
        return new DefaultTerminalFactory().createTerminal();
    }

    /**
     * @brief Draws a new entity to the back buffer of the GUI.
     * @param x the x coordinate of the entity to be created.
     * @param y the y coordinate of the entity to be created.
     * @param c the character that will represent the entity.
     */

    private void drawEntity(int x, int y, char c) {
        TextGraphics tg = screen.newTextGraphics();
        tg.putString(x, y, "" + c);
    }

    /**
     * @brief Draws a new alien using the drawEntity method.
     * @param position the position (x and y coordinates) of the alien.
     */
    public void drawAlien(Position position) {
        drawEntity(position.getX(), position.getY(), 'a');
    }

    /**
     * @brief Draws a new robot using the drawEntity method.
     * @param position the position (x and y coordinates) of the robot.
     */
    public void drawRobot(Position position) {
        drawEntity(position.getX(), position.getY(), 'r');
    }

    /**
     * @brief Draws a new terrain square using the drawEntity method.
     * @param position the position (x and y coordinates) of the terrain.
     */
    public void drawTerrain(Position position) {
        drawEntity(position.getX(), position.getY(), 't');
    }

    /**
     * @brief Draws new text to the back buffer of the GUI.
     * @param position the position (x and y coordinates) of the text.
     * @param t the text to be created.
     * @param color the color of the text.
     */
    public void drawText(Position position, String t, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(position.getX(), position.getY(), t);
    }
}
