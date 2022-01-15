package pt.up.fe.ldts.ootb.gui.input;

public interface InputHandler {
    void getInput();

    void addKeyboardListener(KeyboardListener listener);

    void removeKeyboardListener(KeyboardListener listener);

    void addMouseMoveListener(MouseMoveListener listener);

    void removeMouseMoveListener(MouseMoveListener listener);

    void addMouseClickListener(MouseClickListener listener);

    void removeMouseClickListener(MouseClickListener listener);
}
