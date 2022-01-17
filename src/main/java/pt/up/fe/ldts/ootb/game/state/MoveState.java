package pt.up.fe.ldts.ootb.game.state;

import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.Alien;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.util.GUIUtil;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.Collections;
import java.util.Set;

public class MoveState extends BasePlayerState {

    public MoveState(Game game) {
        super(game);
    }

    @Override
    protected boolean interact(Alien alien, Vector position) {
        if (!game.isValidMovement(alien, position))
            return false;

        alien.setPosition(position);
        return true;
    }

    @Override
    protected void nextState() {
        game.changeState(new AttackState(game));
    }

    @Override
    protected Set<Vector> previewTiles(Alien alien) {
        return game.movableTiles(alien);
    }

    @Override
    protected int previewColor() {
        return 0x00CC00;
    }

    @Override
    protected Set<Vector> selectedTiles(Alien alien) {
        return game.movableTiles(alien);
    }

    @Override
    protected int selectedColor() {
        return 0x00FF00;
    }

    @Override
    protected Set<Vector> hoveredTiles(Alien alien, Vector hovered) {
        return Collections.singleton(hovered);
    }

    @Override
    protected int hoveredColor() {
        return 0x00FF44;
    }

    @Override
    protected void drawAfterCreatures(Renderer renderer) {
        renderer.drawText(new Vector(GUIUtil.WIDTH/2, GUIUtil.HEIGHT/2), "Move stage", 20, 0xFFFFFFFF);
    }
}
