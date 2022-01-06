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

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(this.tg);
        gui = new GUI(screen);
    }

    @Test
    void drawAlien() {
        gui.drawAlien(new Position(0,0));
        Mockito.verify(tg, Mockito.times(1)).putString(0, 0, "a");
    }

    @Test
    void drawRobot() {
        gui.drawRobot(new Position(4,5));
        Mockito.verify(tg, Mockito.times(1)).putString(4, 5, "r");
    }

    @Test
    void drawText() {
        gui.drawText(new Position(1, 1), "Testing drawText() method.", "#000000");
        Mockito.verify(tg, Mockito.times(1)).setForegroundColor(new TextColor.RGB(0, 0, 0));
        Mockito.verify(tg, Mockito.times(1)).putString(1, 1, "Testing drawText() method.");
    }
}
