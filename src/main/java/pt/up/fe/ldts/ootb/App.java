package pt.up.fe.ldts.ootb;

import pt.up.fe.ldts.ootb.gui.LanternaGUI;
import pt.up.fe.ldts.ootb.gui.input.InputHandler;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.menu.MainMenu;
import pt.up.fe.ldts.ootb.menu.Menu;

public class App {
    public static void main(String[] args) {
        LanternaGUI gui = new LanternaGUI();
        new App(gui, gui).run();
    }

    private final Renderer renderer;
    private final InputHandler inputHandler;
    private Menu menu;
    private Menu nextMenu;

    public App(Renderer renderer, InputHandler handler) {
        this.renderer = renderer;
        this.inputHandler = handler;

        renderer.init(this);
        inputHandler.init(this);

        navigate(new MainMenu());
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public void navigate(Menu menu) {
        nextMenu = menu;
    }

    public void run() {
        do {
            if (nextMenu != menu) {
                if (menu != null)
                    menu.dispose(this);

                menu = nextMenu;

                if (menu != null)
                    menu.init(this);
            }

            inputHandler.getInput();

            renderer.render();
        } while (menu != null);

        renderer.dispose(this);
        inputHandler.dispose(this);
    }
}
