package pt.up.fe.ldts.ootb.game.ability.aoe;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class SideAOE implements AreaOfEffect{
    @Override
    public Set<Vector> getTiles(Vector creaturePosition, Vector selectedPosition, Game game) {
        Vector aoeVector = selectedPosition.sub(creaturePosition).normalize();
        Set<Vector> aoeTiles = new HashSet<>();
        // This might not work for edge cases and it might not make a difference idk

        if (aoeVector.x() == 0) {
            aoeTiles.add(selectedPosition.add(-1,0));
            aoeTiles.add(selectedPosition.add(1,0));
        } else {
            aoeTiles.add(selectedPosition.add(0,-1));
            aoeTiles.add(selectedPosition.add(0,1));
        }
        return aoeTiles;
    }
}
