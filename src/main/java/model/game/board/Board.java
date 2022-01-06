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
    private List<Terrain> terrains = new Vector<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                this.terrains.add(new TerrainPlain(new Position(x, y)));
    }

    public Board(int width, int height, List<Terrain> terrains) {
        this.width = width;
        this.height = height;
        this.terrains = terrains;

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