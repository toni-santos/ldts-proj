package gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GUITest {
    private Screen screen;
    private GUI gui;
    private TextGraphics tg;

    /**
     * @brief Pre-requisites for every test.
     */
    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(this.tg);
        gui = new GUI(screen);
    }

    /**
     * @brief Tries to draw a new alien and verifies it by checking if the TextGraphics mock called the putString method
     * once with the 'a' character.
     */
    /*
    @Test
    void drawAlien() {
        gui.drawAlien(new Position(0,0));
        Mockito.verify(tg, Mockito.times(1)).putString(0, 0, "a");
    }
    */

    /**
     * @brief Tries to draw a new robot and verifies it by checking if the TextGraphics mock called the putString method
     * once with the 'r' character.
     */
    /*
    @Test
    void drawRobot() {
        gui.drawRobot(new Position(4,5));
        Mockito.verify(tg, Mockito.times(1)).putString(4, 5, "r");
    }
    */

    /**
     * @brief Tries to draw new text and verifies it by checking if the TextGraphics mock called the putString method
     * once with the wanted string and if it set the right foreground color by calling the setForegroundColor method
     * once.
     */
    @Test
    void drawText() {
        gui.drawText(new Position(1, 1), "Testing drawText() method.", "#000000");
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(0, 0, 0));
        Mockito.verify(tg, Mockito.times(1)).putString(1, 1, "Testing drawText() method.");
    }
}
