package pt.up.fe.ldts.ootb.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.Alien;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.CannonAlien;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.FlyingAlien;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.TankAlien;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.CannonRobot;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.FlyingRobot;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.Robot;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.TankRobot;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy.RandomStrategy;
import pt.up.fe.ldts.ootb.game.entity.terrain.*;
import pt.up.fe.ldts.ootb.util.Vector;

public class LevelLoaderTest {
    private static final String TEST_NAME = "lvl01.lvl";
    private static final int TEST_WIDTH = 3;
    private static final int TEST_HEIGHT = 2;
    private static final Class[] TEST_TERRAINS = {
            TerrainPlain.class, TerrainWater.class, TerrainNest.class,
            TerrainMountain.class, TerrainForest.class, TerrainPlain.class
    };
    private static final Robot[] TEST_ROBOTS = {
            new CannonRobot(new Vector(0, 0), new RandomStrategy()),
            new FlyingRobot(new Vector(0, 1), new RandomStrategy()),
            new TankRobot(new Vector(1, 1), new RandomStrategy())
    };
    private static final Alien[] TEST_ALIENS = {
            new CannonAlien(new Vector(1, 0)),
            new FlyingAlien(new Vector(2, 0)),
            new TankAlien(new Vector(2, 1))
    };

    @Test
    public void loadTestLevelTest() {
        Level level = LevelLoader.loadLevel(TEST_NAME);

        Assertions.assertNotNull(level);

        Assertions.assertEquals(TEST_WIDTH, level.board().width());
        Assertions.assertEquals(TEST_HEIGHT, level.board().height());

        Assertions.assertEquals(TEST_TERRAINS.length, level.board().terrains().length);
        for (int i = 0; i < TEST_TERRAINS.length; ++i)
            Assertions.assertInstanceOf(TEST_TERRAINS[i], level.board().terrains()[i]);

        Assertions.assertEquals(TEST_ALIENS.length, level.aliens().size());
        for (int i = 0; i < TEST_ALIENS.length; ++i) {
            Alien t = TEST_ALIENS[i], a = level.aliens().get(i);
            Assertions.assertInstanceOf(t.getClass(), a);
            Assertions.assertEquals(t.getPosition(), a.getPosition());
        }

        Assertions.assertEquals(TEST_ROBOTS.length, level.robots().size());
        for (int i = 0; i < TEST_ROBOTS.length; ++i) {
            Robot t = TEST_ROBOTS[i], r = level.robots().get(i);
            Assertions.assertInstanceOf(t.getClass(), r);
            Assertions.assertEquals(t.getPosition(), r.getPosition());
            Assertions.assertInstanceOf(t.getStrategy().getClass(), r.getStrategy());
        }
    }

}
