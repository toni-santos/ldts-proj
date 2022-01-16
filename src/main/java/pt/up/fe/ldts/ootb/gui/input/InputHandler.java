package pt.up.fe.ldts.ootb.gui.input;

import pt.up.fe.ldts.ootb.App;

public interface InputHandler {
    void getInput();

    void addKeyboardListener(KeyboardListener listener);

    void removeKeyboardListener(KeyboardListener listener);

    void addMouseMoveListener(MouseMoveListener listener);

    void removeMouseMoveListener(MouseMoveListener listener);

    void addMouseClickListener(MouseClickListener listener);

    void removeMouseClickListener(MouseClickListener listener);

    void init(App app);

    void dispose(App app);
}
