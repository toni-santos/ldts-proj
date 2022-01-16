package pt.up.fe.ldts.ootb.game;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.game.entity.creature.Creature;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.Alien;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.Robot;
import pt.up.fe.ldts.ootb.game.entity.terrain.Terrain;
import pt.up.fe.ldts.ootb.game.state.InitialState;
import pt.up.fe.ldts.ootb.game.state.State;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private final App app;
    private State state;
    private final Board board;
    private final List<Robot> robots;
    private final List<Alien> aliens;

    public Game(App app, Level level) {
        this.app = app;

        board = level.board();
        robots = level.robots();
        aliens = level.aliens();
        state = new InitialState(this);
        state.init(app);
    }

    public void changeState(State state) {
        this.state.dispose(app);
        this.state = state;
        this.state.init(app);
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

    public Creature getCreatureAt(Vector position) {
        for (Creature creature : this.robots)
            if (creature.getPosition().equals(position))
                return creature;

        for (Creature creature : this.aliens)
            if (creature.getPosition().equals(position))
                return creature;

        return null;
    }

    public boolean isValidMovement(Creature creature, Vector position) {
        if (!board.isInsideBoard(position) || creature.getPosition().gridDistance(position) > creature.getMovementRange())
            return false;

        for (Robot robot: robots) {
            if (robot.getPosition().equals(position)) {
                return false;
            }
        }

        for (Alien alien: aliens) {
            if (alien.getPosition().equals(position)) {
                return false;
            }
        }

        return board.terrainAt(position).supportsCreature(creature);
    }

    public Set<Vector> movableTiles(Creature creature) {
        Set<Vector> movable = new HashSet<>();

        if (creature != null)
            for (Terrain terrain: getBoard().terrains())
                if (isValidMovement(creature, terrain.getPosition()))
                    movable.add(terrain.getPosition());

        return movable;
    }

    public int aliveRobots() {
        return (int) robots.stream().filter(Creature::isAlive).count();
    }

    public int aliveAliens() {
        return (int) aliens.stream().filter(Creature::isAlive).count();
    }
}
