import model.Position;
import model.game.board.*;
import model.game.entity.Creature;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.CacheRequest;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Game {
    private Board board;
    private Vector<Creature> robots;

    public void readLevelFile(File mapFile) throws URISyntaxException {
        int width = 0;
        int height = 0;
        List<Terrain> terrains = new Vector<>();

        try {
            int y = 0;
            Scanner scanner = new Scanner(mapFile);
            String sizeInfo = scanner.nextLine();
            String[] info = sizeInfo.split("x");
            width = Integer.parseInt(info[0]);
            height = Integer.parseInt(info[1]);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                for (int x = 0; x < line.length(); x++){
                    Terrain terrain = null;
                    char c = line.charAt(x);

                    switch (c){
                        case 'P': // Plain Terrain
                            terrain = new TerrainPlain(new Position(x, y));
                            break;
                        case 'W': // Water Terrain
                            terrain = new TerrainWater(new Position(x, y));
                            break;
                        case 'C': // City Terrain
                            terrain = new TerrainCity(new Position(x, y));
                            break;
                        case 'M': // Mountain Terrain
                            terrain = new TerrainMountain(new Position(x, y));
                            break;
                        case 'F': // Forest Terrain
                            terrain = new TerrainForest(new Position(x, y), false);
                            break;
                    }

                    if (terrain != null)
                        terrains.add(terrain);
                }
                y++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        board = new Board(width, height, terrains);
    }

    public Board getBoard() { return board; }

    public Vector<Creature> getRobots() { return robots; }

}
