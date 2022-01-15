package states;

import com.googlecode.lanterna.input.KeyStroke;
import controller.Controller;
import controller.LevelController;
import gui.GUI;
import model.game.Level;
import view.LevelView;
import view.View;

import java.io.IOException;


public class PlayState extends State<Level> {

    public PlayState(Level level) {
        super(level);
    }

    @Override
    public View<Level> getView() {
        return new LevelView(getModel());
    }

    @Override
    public Controller<Level> getController() {
        return new LevelController(getModel());
    }

    @Override
    public void step(GUI gui) throws IOException {
        getView().draw(gui);
        // new step gameplay
        KeyStroke key = gui.getNextEvent();

        if (key.getCharacter() != null && (key.getCharacter() == 'q' || key.getCharacter() == 'Q'))
            gui.close();
    }
}
