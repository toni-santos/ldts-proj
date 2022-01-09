package view;

import gui.GUI;
import model.game.entity.Creature;

public class CreatureView implements View {
    private final Creature creature;

    public CreatureView(Creature creature) {
        this.creature = creature;
    }

    @Override
    public void draw(GUI gui) {
        // TODO
    }

    public Creature getCreature() {
        return creature;
    }
}
