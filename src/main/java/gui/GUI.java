package gui;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import model.Position;
import model.game.entity.*;
import model.game.terrain.*;

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
        return new DefaultTerminalFactory()
                .setTerminalEmulatorFontConfiguration(AWTTerminalFontConfiguration.getDefaultOfSize(64))
                .setForceAWTOverSwing(true)
                .createTerminal();
    }

    /**
     * @brief Draws a new entity to the back buffer of the GUI.
     * @param x the x coordinate of the entity to be created.
     * @param y the y coordinate of the entity to be created.
     * @param c the character that will represent the entity.
     */

    private void drawEntity(int x, int y, char c) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setCharacter(x, y, TextCharacter.fromCharacter(c, null, screen.getBackCharacter(x, y).getBackgroundColor())[0]);
    }

    /**
     * @brief Draws a new alien using the drawEntity method.
     * @param creature the alien to draw.
     */
    public void drawAlien(Creature creature) {
        char c = 'E';

        if (creature.getType() == Creature.Type.TANK) {
            c = 't';
        } else if (creature.getType() == Creature.Type.CANNON) {
            c = 'c';
        } else if (creature.getType() == Creature.Type.FLYING) {
            c = 'f';
        }

        drawEntity(creature.getPos().getX(), creature.getPos().getY(), c);
    }

    /**
     * @brief Draws a new robot using the drawEntity method.
     * @param creature the robot to draw.
     */
    public void drawRobot(Creature creature) {
        char c = 'E';

        if (creature.getType() == Creature.Type.TANK) {
            c = 't';
        } else if (creature.getType() == Creature.Type.CANNON) {
            c = 'c';
        } else if (creature.getType() == Creature.Type.FLYING) {
            c = 'f';
        }

        drawEntity(creature.getPos().getX(), creature.getPos().getY(), c);
    }

    /**
     * @brief Draws a new terrain square using the drawEntity method.
     * @param terrain the terrain to draw.
     */
    public void drawTerrain(Terrain terrain) {
        TextColor color = null;

        if (terrain instanceof TerrainPlain)
            color = new TextColor.RGB(93, 201, 122);
        if (terrain instanceof TerrainForest)
            color = new TextColor.RGB(6, 46, 17);
        if (terrain instanceof TerrainMountain)
            color = new TextColor.RGB(54, 37, 6);
        if (terrain instanceof TerrainWater)
            color = new TextColor.RGB(73, 150, 201);
        if (terrain instanceof TerrainCity)
            color = new TextColor.RGB(162, 168, 163);

        TextCharacter character = TextCharacter.fromCharacter(' ', null, color)[0];

        TextGraphics tg = screen.newTextGraphics();
        tg.setCharacter(terrain.getPos().getX(), terrain.getPos().getY(), character);
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
