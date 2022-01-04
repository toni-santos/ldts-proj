package model.game.entity;

import model.Position;

import java.util.Vector;

public abstract class Robot extends Entity{
    Robot(Position pos, boolean flying, Integer movementRange, Vector<Ability> abilities) {
        super(pos, flying, movementRange, abilities);
    }
}
