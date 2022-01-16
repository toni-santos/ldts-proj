package pt.up.fe.ldts.ootb.game.ability.aoe;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class CompositeAOE implements AreaOfEffect {
    private final AreaOfEffect[] aoes;

    public CompositeAOE(AreaOfEffect... aoes) {
        this.aoes = aoes;
    }

    @Override
    public Set<Vector> getTiles(Vector creaturePosition, Vector selectedPosition, Game game) {
        Set<Vector> tiles = new HashSet<>();

        for (AreaOfEffect aoe : aoes)
            tiles.addAll(aoe.getTiles(creaturePosition, selectedPosition, game));

        return tiles;
    }
}
