package pt.up.fe.ldts.ootb.game;

import pt.up.fe.ldts.ootb.game.entity.creature.alien.Alien;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.Robot;

import java.util.List;

public record Level(Board board, List<Robot> robots, List<Alien> aliens) {
}
