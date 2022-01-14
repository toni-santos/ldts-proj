package view;

import gui.GUI;

import java.io.IOException;

public interface View<T> {
    void draw(GUI gui) throws IOException;
}
