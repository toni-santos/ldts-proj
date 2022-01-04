package model.game.entity;

import model.Position;

import java.util.Vector;

public class CanonRobot extends Robot {
    CanonRobot(Position pos, boolean flying, Integer movementRange, Vector<Ability> abilities) {
        super(pos, flying, movementRange, abilities);
    }
}
