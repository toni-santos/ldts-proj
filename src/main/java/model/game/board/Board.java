package model.game.board;

import model.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.Integer.valueOf;

public class Board {
    private int width = 0;
    private int height = 0;
    private final List<Terrain> terrains = new Vector<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                this.terrains.add(new TerrainPlain(new Position(x, y)));
    }

    public Board(File mapFile) {
        try {
            int y = 0;
            Scanner scanner = new Scanner(mapFile);
            String sizeInfo = scanner.nextLine();
            String[] info = sizeInfo.split("x");
            this.width = Integer.parseInt(info[0]);
            this.height = Integer.parseInt(info[1]);
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
                            terrain = new TerrainCity(new Position(x, y), 1);
                            break;
                        case 'M': // Mountain Terrain
                            terrain = new TerrainMountain(new Position(x, y));
                            break;
                        case 'F': // Forest Terrain
                            terrain = new TerrainForest(new Position(x, y), false);
                            break;
                    }

                    if (terrain != null)
                        this.terrains.add(terrain);
                }
                y++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setTerrain(Terrain terrain) {
        this.terrains.set(terrain.getPos().getY() * width + terrain.getPos().getX(), terrain);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Terrain> getTerrains() {
        return terrains;
    }

    public Terrain getTerrainAt(Position position) {
        return this.terrains.get(position.getY() * width + position.getX());
    }
}
