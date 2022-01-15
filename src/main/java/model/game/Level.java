package model.game;

import model.Position;
import model.game.board.*;
import model.game.entity.Creature;
import model.game.entity.CreatureBuilder;
import model.game.terrain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.Integer.parseInt;

public class Level {
    private Board board;
    private Vector<Creature> robots = new Vector<>();
    //private Vector<Creature> aliens = new Vector<>();

    public Level(int levelNum) throws URISyntaxException {
        String level = "levels/lvl0" + levelNum + ".lvl";
        File levelFile = new File(getClass().getClassLoader().getResource(level).toURI());
        readLevelFile(levelFile);
    }

    /**
     * Reads a file with information about the board and the robots that inhabit it
     *
     */
    private void readLevelFile(File levelFile) {
        int width = 0;
        int height = 0;
        List<Terrain> terrains = new Vector<>();

        try {
            int y = 0;
            Scanner scanner = new Scanner(levelFile);
            String sizeInfo = scanner.nextLine();
            String[] info = sizeInfo.split("x");
            width = parseInt(info[0]);
            height = parseInt(info[1]);

            for (int l = 0; l < height; l++) {
                String line = scanner.nextLine();

                for (int x = 0; x < line.length(); x++){
                    Terrain terrain = null;
                    char c = line.charAt(x);

                    switch (c) {
                        case 'P' -> // Plain Terrain
                                terrain = new TerrainPlain(new Position(x, y));
                        case 'W' -> // Water Terrain
                                terrain = new TerrainWater(new Position(x, y));
                        case 'C' -> // City Terrain
                                terrain = new TerrainCity(new Position(x, y));
                        case 'M' -> // Mountain Terrain
                                terrain = new TerrainMountain(new Position(x, y));
                        case 'F' -> // Forest Terrain
                                terrain = new TerrainForest(new Position(x, y));
                    }

                    if (terrain != null)
                        terrains.add(terrain);
                }
                y++;
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                Creature creature;

                String[] robotInfo = line.split("_");
                String[] robotPos = robotInfo[1].split(",");

                switch (robotInfo[0]) {
                    case "CANNON":
                        creature = new CreatureBuilder()
                                .setFaction(Creature.Faction.ROBOT)
                                .setType(Creature.Type.CANNON)
                                .setPosition(new Position(parseInt(robotPos[0]), parseInt(robotPos[1])))
                                .build();
                        robots.add(creature);
                        break;
                    case "FLYING":
                        creature = new CreatureBuilder()
                                .setFaction(Creature.Faction.ROBOT)
                                .setType(Creature.Type.FLYING)
                                .setPosition(new Position(parseInt(robotPos[0]), parseInt(robotPos[1])))
                                .build();
                        robots.add(creature);
                        break;
                    case "TANK":
                        creature = new CreatureBuilder()
                                .setFaction(Creature.Faction.ROBOT)
                                .setType(Creature.Type.TANK)
                                .setPosition(new Position(parseInt(robotPos[0]), parseInt(robotPos[1])))
                                .build();
                        robots.add(creature);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.board = new Board(width, height, terrains);
    }

    /**
     * @return The current board of the game
     */
    public Board getBoard() { return board; }

    /**
     * @return The robots of the current game
     */
    public Vector<Creature> getRobots() { return robots; }

}

