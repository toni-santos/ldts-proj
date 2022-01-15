package states;

import controller.Controller;
import gui.GUI;
import view.View;

import java.io.IOException;

public abstract class State<T> {
    private final View<T> view;
    private final Controller<T> controller;
    private final T model;

    State(T model) {
        this.model = model;
        this.view = getView();
        this.controller = getController();
    }

    public abstract View<T> getView();
    public abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public abstract void step(GUI gui) throws IOException;
}
