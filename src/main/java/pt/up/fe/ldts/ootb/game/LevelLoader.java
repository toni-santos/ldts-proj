package pt.up.fe.ldts.ootb.game;

import pt.up.fe.ldts.ootb.game.entity.creature.alien.Alien;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.CannonAlien;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.FlyingAlien;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.TankAlien;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.CannonRobot;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.FlyingRobot;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.Robot;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.TankRobot;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy.NearestStrategy;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy.RandomStrategy;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.strategy.RobotStrategy;
import pt.up.fe.ldts.ootb.game.entity.terrain.*;
import pt.up.fe.ldts.ootb.util.Vector;

import java.net.URL;
import java.util.*;

public final class LevelLoader {
    private static final Map<Character, TerrainCreator> terrainRegistry = new HashMap<>();
    private static final Map<String, RobotCreator> robotRegistry = new HashMap<>();
    private static final Map<String, RobotStrategy> strategyRegistry = new HashMap<>();
    private static final Map<String, AlienCreator> alienRegistry = new HashMap<>();

    static {
        terrainRegistry.put('F', TerrainForest::new);
        terrainRegistry.put('M', TerrainMountain::new);
        terrainRegistry.put('N', TerrainNest::new);
        terrainRegistry.put('P', TerrainPlain::new);
        terrainRegistry.put('W', TerrainWater::new);
    }

    static {
        strategyRegistry.put("RANDOM", new RandomStrategy());
        strategyRegistry.put("NEAREST", new NearestStrategy());
    }

    static {
        robotRegistry.put("CANNON", CannonRobot::new);
        robotRegistry.put("FLYING", FlyingRobot::new);
        robotRegistry.put("TANK", TankRobot::new);
    }

    static {
        alienRegistry.put("CANNON", CannonAlien::new);
        alienRegistry.put("FLYING", FlyingAlien::new);
        alienRegistry.put("TANK", TankAlien::new);
    }

    public static Level loadLevel(String fileName) {
        try {
            URL resource = LevelLoader.class.getClassLoader().getResource("levels/" + fileName);

            Scanner scanner = new Scanner(resource.openStream());

            int width = scanner.nextInt();
            int height = scanner.nextInt();
            int nRobots = scanner.nextInt();
            int nAliens = scanner.nextInt();

            Board board = loadBoard(scanner, width, height);
            List<Robot> robots = loadRobots(scanner, nRobots);
            List<Alien> aliens = loadAliens(scanner, nAliens);

            return new Level(board, robots, aliens);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Board loadBoard(Scanner scanner, int width, int height) {
        Terrain[] terrains = new Terrain[width * height];

        scanner.useDelimiter("");
        for (int y = 0; y < height; ++y)
            for (int x = 0; x < width; ++x) {
                char c;

                do
                    c = scanner.next().charAt(0);
                while (!terrainRegistry.containsKey(c));

                Terrain terrain = terrainRegistry.get(c).createTerrain(new Vector(x, y));

                terrains[x + y*width] = terrain;
            }
        scanner.reset();

        return new Board(width, height, terrains);
    }

    private static List<Robot> loadRobots(Scanner scanner, int nRobots) {
        List<Robot> robots = new ArrayList<>();

        for (int i = 0; i < nRobots; ++i) {
            String name = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            String strategyName = scanner.next();

            RobotStrategy strategy = strategyRegistry.get(strategyName);

            robots.add(robotRegistry.get(name).createRobot(new Vector(x, y), strategy));
        }

        return robots;
    }

    private static List<Alien> loadAliens(Scanner scanner, int nAliens) {
        List<Alien> aliens = new ArrayList<>();

        for (int i = 0; i < nAliens; ++i) {
            String name = scanner.next();
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            aliens.add(alienRegistry.get(name).createAlien(new Vector(x, y)));
        }

        return aliens;
    }

    public interface TerrainCreator {
        Terrain createTerrain(Vector position);
    }

    public interface RobotCreator {
        Robot createRobot(Vector position, RobotStrategy strategy);
    }

    public interface AlienCreator {
        Alien createAlien(Vector position);
    }
}
