package model.game.entity;

import model.Position;

public class Alien extends Entity{

    Alien(Position pos) {
        super(pos);
    }

    Alien(Position pos, boolean flying, Integer movementRange) {
        super(pos, flying, movementRange);
    }
}
