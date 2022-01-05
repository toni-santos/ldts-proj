package view;

import model.game.board.Terrain;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ElementViewTest {
    private Alien alien;
    private Robot robot;
    private Terrain terrain;
    private AlienView alienview;
    private RobotView robotview;
    private TerrainView terrainview;

    @Test
    void testdrawAlien(){
        alien = new Alien(pos, flying, movementRange, abilities);
        alienview.draw(alien);
    }

    @Test
    void testdrawRobot(){
        robot = new Robot(pos, flying, movementRange, abilities);
        robotview.draw(robot);
    }

    @Test
    void testdrawTerrain(){
        terrain = new Terrain(pos);
        terrainview.draw(terrain);
    };
}
