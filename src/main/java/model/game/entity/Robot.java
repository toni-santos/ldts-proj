package model.game.entity;

import model.Position;

public class Robot extends Entity{
    Robot(Position pos) {
        super(pos);
    }

    Robot(Position pos, boolean flying, Integer movementRange) {
        super(pos, flying, movementRange);
    }
}
