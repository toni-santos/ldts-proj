package view;

import gui.GUI;
import model.game.entity.Creature;

public class CreatureView implements View<Creature> {
    private Creature creature;

    public CreatureView(Creature creature) {
        this.creature = creature;
    }

    public CreatureView() {}

    @Override
    public void draw(GUI gui) {
        if (creature.getFaction() == Creature.Faction.ALIEN)
            gui.drawAlien(creature);
        else
            gui.drawRobot(creature);
    }

    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature c) { this.creature = c; }
}
