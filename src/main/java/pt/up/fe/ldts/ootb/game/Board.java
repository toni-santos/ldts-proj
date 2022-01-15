package pt.up.fe.ldts.ootb.game;

import pt.up.fe.ldts.ootb.game.entity.terrain.Terrain;

public record Board(int width, int height, Terrain[] terrains) {
}
