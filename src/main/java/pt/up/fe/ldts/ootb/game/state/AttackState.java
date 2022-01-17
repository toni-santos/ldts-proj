package pt.up.fe.ldts.ootb.game.state;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.Alien;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.util.GUIUtil;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Set;

public class AttackState extends BasePlayerState {

    public AttackState(Game game) {
        super(game);
    }

    @Override
    protected boolean interact(Alien alien, Vector position) {
        if (!alien.isAlive()
         || !alien.getAbility().getTilesInRange(alien.getPosition(), game).contains(position))
            return false;

        alien.getAbility().execute(
                alien.getPosition(),
                alien.getAbility().getAffectedTiles(
                        alien.getPosition(),
                        position,
                        game)
                ,game);

        return true;
    }

    @Override
    protected void nextState() {
        game.changeState(new AfterPlayerState(game));
    }

    @Override
    protected Set<Vector> previewTiles(Alien alien) {
        return alien.getAbility().getTilesInRange(alien.getPosition(), game);
    }

    @Override
    protected int previewColor() {
        return 0xCC0000;
    }

    @Override
    protected Set<Vector> selectedTiles(Alien alien) {
        return alien.getAbility().getTilesInRange(alien.getPosition(), game);
    }

    @Override
    protected int selectedColor() {
        return 0xFF0000;
    }

    @Override
    protected Set<Vector> hoveredTiles(Alien alien, Vector hovered) {
        return alien.getAbility().getAffectedTiles(alien.getPosition(), hovered, game);
    }

    @Override
    protected int hoveredColor() {
        return 0xFF00CC;
    }

    @Override
    protected void drawAfterCreatures(Renderer renderer) {
        renderer.drawText(new Vector(GUIUtil.WIDTH/2, GUIUtil.HEIGHT/2), "Attack stage", 20, 0xFFFFFFFF);
    }
}
