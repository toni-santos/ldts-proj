import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import gui.GUI;
import model.Position;
import model.game.board.*;
import model.game.entity.Creature;
import view.CreatureView;
import view.TerrainView;
import view.View;
import model.game.entity.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * An all-encompassing class of methods related to running the game
 */
public class Game {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Game game = new Game(new GUI());
        File firstLevel = new File(Game.class.getResource("levels/lvl01.lvl").toURI());
        game.readLevelFile(firstLevel);
        game.start();
    }

    private GUI gui;
    private Board board;
    private Vector<Creature> robots = new Vector<>();

    public Game(GUI gui) {
        this.gui = gui;
    }

    private void start() throws IOException {
        int FPS = 1;
        int frameTime = 1000 / FPS;

        List<View> views = new ArrayList<>();

        for (Terrain t : board.getTerrains())
            views.add(new TerrainView(t));
        for (Creature c : robots)
            views.add(new CreatureView(c));

        while (true) {
            for (View v : views)
                v.draw(gui);

            gui.refresh();

            KeyStroke key = gui.getNextEvent();

            if (key.getCharacter() != null && (key.getCharacter() == 'q' || key.getCharacter() == 'Q'))
                gui.close();

            if (key.getKeyType() == KeyType.EOF)
                break;
        }
    }

    /**
     * Reads a file with information about the board and the robots that inhabit it
     *
     * @param mapFile A .lvl file stored in the resources folder
     */
    public void readLevelFile(File mapFile) {
        int width = 0;
        int height = 0;
        List<Terrain> terrains = new Vector<>();

        try {
            int y = 0;
            Scanner scanner = new Scanner(mapFile);
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
                        creature = new CreatureBuilder(Faction.ROBOT, Type.CANNON).build();
                        creature.setPos(new Position(parseInt(robotPos[0]), parseInt(robotPos[1])));
                        robots.add(creature);
                        break;
                    case "FLYING":
                        creature = new CreatureBuilder(Faction.ROBOT, Type.FLYING).build();
                        creature.setPos(new Position(parseInt(robotPos[0]), parseInt(robotPos[1])));
                        robots.add(creature);
                        break;
                    case "TANK":
                        creature = new CreatureBuilder(Faction.ROBOT, Type.TANK).build();
                        creature.setPos(new Position(parseInt(robotPos[0]), parseInt(robotPos[1])));
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
