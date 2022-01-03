package model.game.board;

import model.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.Integer.valueOf;

public class Board {
    Integer height = 0;
    Integer width = 0;
    Vector<Vector<Terrain>> Terrains = new Vector<>();

    public Board(Integer height, Integer width) {
        this.height = height;
        this.width = width;

        for (int i = 0; i < height; i++) {
            Vector<Terrain> aux = new Vector<>();
            for (int j = 0; j < width; j++) {
                aux.add(new TerrainPlain(new Position(i,j)));
            }
            this.Terrains.add(aux);
        }
    }

    public Board(File mapFile) {
        try {
            int rowCounter = 0;
            Scanner scanner = new Scanner(mapFile);
            String sizeInfo = scanner.nextLine();
            String[] info = sizeInfo.split("x");
            this.width = valueOf(info[0]);
            this.height = valueOf(info[1]);
            while (scanner.hasNextLine()) {
                Vector<Terrain> row = new Vector<>();
                String line = scanner.nextLine();
                for (int i = 0; i < line.length(); i++){
                    Terrain terrain;
                    char c = line.charAt(i);

                    switch (c){
                        case 'P': // Plain Terrain
                            terrain = new TerrainPlain(new Position(i,rowCounter));
                            row.add(terrain);
                            break;
                        case 'W': // Water Terrain
                            terrain = new TerrainWater(new Position(i,rowCounter));
                            row.add(terrain);
                            break;
                        case 'C': // City Terrain
                            terrain = new TerrainCity(new Position(i,rowCounter), 1);
                            row.add(terrain);
                            break;
                        case 'M': // Mountain Terrain
                            terrain = new TerrainMountain(new Position(i,rowCounter));
                            row.add(terrain);
                            break;
                        case 'F': // Forest Terrain
                            terrain = new TerrainForest(new Position(i,rowCounter), false);
                            row.add(terrain);
                            break;
                    }
                }
                this.Terrains.add(row);
                rowCounter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addTerrain(Terrain terrain) {
        this.Terrains.get(terrain.getPos().getY()).remove(terrain.getPos().getX());
        this.Terrains.get(terrain.getPos().getY()).insertElementAt(terrain, terrain.getPos().getX());
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }

    public Vector<Vector<Terrain>> getTerrains() {
        return Terrains;
    }
}
