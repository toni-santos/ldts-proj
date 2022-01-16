package pt.up.fe.ldts.ootb.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.Alien;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.TankAlien;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.CannonRobot;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.Robot;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy.RandomStrategy;
import pt.up.fe.ldts.ootb.gui.input.InputHandler;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.util.Vector;

import java.net.URISyntaxException;

public class GameTest {
    private static Game game;
    private static final Robot TEST_ROBOT =
            new CannonRobot(new Vector(0, 0), new RandomStrategy());
    private static final Alien TEST_ALIEN =
            new TankAlien(new Vector(2, 1));

    @BeforeAll
    public static void setup() {
        game = new Game(LevelLoader.loadLevel("lvl02.lvl"));
    }

    @Test
    public void getCreatureAtTest() {
        Assertions.assertInstanceOf(TEST_ALIEN.getClass(), game.getCreatureAt(new Vector(2, 1)));
        Assertions.assertEquals(TEST_ALIEN.getPosition(), game.getCreatureAt(new Vector(2, 1)).getPosition());

        Assertions.assertInstanceOf(TEST_ROBOT.getClass(), game.getCreatureAt(new Vector(0, 0)));
        Assertions.assertEquals(TEST_ROBOT.getPosition(), game.getCreatureAt(new Vector(0, 0)).getPosition());
    }

    @Test
    public void isValidMovementTest() {
        Assertions.assertTrue(game.isValidMovement(TEST_ALIEN, new Vector(2,0)));
        Assertions.assertFalse(game.isValidMovement(TEST_ALIEN, new Vector(1,0)));

        Assertions.assertTrue(game.isValidMovement(TEST_ROBOT, new Vector(2,0)));
        Assertions.assertFalse(game.isValidMovement(TEST_ROBOT, new Vector(1,0)));
    }
}
