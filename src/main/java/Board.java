import java.util.ArrayList;
import java.util.Vector;

public class Board {
    Integer height = 0;
    Integer width = 0;
    Vector<Vector<Terrain>> Terrains = new Vector<>();

    Board(Integer height, Integer width) {
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

    Board(Integer height, Integer width, ArrayList<Terrain> TerrainArray) {
        this.height = height;
        this.width = width;

        for (int i = 0; i < height; i++) {
            Vector<Terrain> aux = new Vector<>();
            for (int j = 0; j < width; j++) {
                aux.add(new TerrainPlain(new Position(i,j)));
            }
            this.Terrains.add(aux);
        }

        for (Terrain terrain: TerrainArray) {
            this.Terrains.get(terrain.getPos().getY()).remove(terrain.getPos().getX());
            this.Terrains.get(terrain.getPos().getY()).insertElementAt(terrain, terrain.getPos().getX());
        }
    }

    public void addTerrain(Terrain terrain) {
        this.Terrains.get(terrain.getPos().getY()).remove(terrain.getPos().getX());
        this.Terrains.get(terrain.getPos().getY()).insertElementAt(terrain, terrain.getPos().getX());
    }
}
