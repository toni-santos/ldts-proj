package model.game.board;

import model.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import static java.lang.Integer.valueOf;

/**
 * The board in which a level of the game is played
 */
public class Board {

    /**
     * The width of the board
     */
    private int width = 0;

    /**
     * The height of the board
     */
    private int height = 0;

    /**
     * The list of Terrains that make up the board of the level
     */
    private List<Terrain> terrains = new Vector<>();

    /**
     * Constructor of a board object
     *
     * <p>
     *     Note: a board created with this constructor is created with all terrains as "plain terrains"
     * </p>
     *
     * @param width The width of the board
     * @param height The height of the board
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                this.terrains.add(new TerrainPlain(new Position(x, y)));
    }

    /**
     * Constructor of a board object
     *
     * @param width The width of the board
     * @param height The height of the board
     * @param terrains A list of the Terrains that make up the board
     */
    public Board(int width, int height, List<Terrain> terrains) {
        this.width = width;
        this.height = height;
        this.terrains = terrains;

    }

    /**
     * Replaces a Terrain based on a given Terrain
     *
     * @param terrain The terrain that will be inserted
     */
    public void setTerrain(Terrain terrain) {
        this.terrains.set(terrain.getPos().getY() * width + terrain.getPos().getX(), terrain);
    }

    /**
     * @return The width of the board
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The height of the board
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return The list of Terrains that make up the board
     */
    public List<Terrain> getTerrains() {
        return terrains;
    }

    /**
     * @param position The position of the board we wish to retrieve the terrain from
     * @return The terrain at a given position
     */
    public Terrain getTerrainAt(Position position) {
        return this.terrains.get(position.getY() * width + position.getX());
    }
}