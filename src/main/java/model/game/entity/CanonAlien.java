package model.game.entity;

import model.Position;

import java.util.Vector;

public class CanonAlien extends Alien {
    CanonAlien(Position pos, boolean flying, Integer movementRange, Vector<Ability> abilities) {
        super(pos, flying, movementRange, abilities);
    }
}
