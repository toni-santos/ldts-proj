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
        if (creature.getFaction() == Creature.Faction.ALIEN)
            gui.drawAlien(creature.getPos());
        else
            gui.drawRobot(creature.getPos());
    }

    public Creature getCreature() {
        return creature;
    }
}
