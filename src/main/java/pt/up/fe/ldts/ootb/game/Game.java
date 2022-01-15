package pt.up.fe.ldts.ootb.game;

import pt.up.fe.ldts.ootb.game.entity.creature.alien.Alien;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.Robot;
import pt.up.fe.ldts.ootb.game.state.State;

import java.util.List;

public class Game {
    private State state;
    private final Board board;
    private final List<Robot> robots;
    private final List<Alien> aliens;

    public Game(Board board, List<Robot> robots, List<Alien> aliens) {
        this.board = board;
        this.robots = robots;
        this.aliens = aliens;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public Board getBoard() {
        return board;
    }

    public List<Robot> getRobots() {
        return robots;
    }

    public List<Alien> getAliens() {
        return aliens;
    }
}
