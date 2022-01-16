package pt.up.fe.ldts.ootb.game.state;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.entity.creature.alien.Alien;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.Robot;
import pt.up.fe.ldts.ootb.gui.input.*;
import pt.up.fe.ldts.ootb.gui.render.Renderer;
import pt.up.fe.ldts.ootb.util.GUIUtil;
import pt.up.fe.ldts.ootb.util.Vector;

import java.util.HashSet;
import java.util.Set;

public abstract class BasePlayerState extends BaseState
        implements MouseClickListener, MouseMoveListener, KeyboardListener {

    private Alien selectedAlien;
    private Vector hovered = new Vector(-1, -1);
    private final Set<Alien> interactedAliens = new HashSet<>();

    public BasePlayerState(Game game) {
        super(game);
    }

    @Override
    public void init(App app) {
        super.init(app);
        app.getInputHandler().addMouseClickListener(this);
        app.getInputHandler().addMouseMoveListener(this);
        app.getInputHandler().addKeyboardListener(this);
    }

    @Override
    public void dispose(App app) {
        super.dispose(app);
        app.getInputHandler().removeMouseClickListener(this);
        app.getInputHandler().removeMouseMoveListener(this);
        app.getInputHandler().removeKeyboardListener(this);
    }

    protected final void drawInteraction(Renderer renderer, Vector position, int color) {
        position = GUIUtil.boardToScreen(position);
        renderer.drawRectangle(position, position.add(GUIUtil.TILE_WIDTH, GUIUtil.TILE_HEIGHT),
                0x44000000 ^ color, 0x88000000 ^ color);
    }

    @Override
    protected void drawBeforeCreatures(Renderer renderer) {
        for (Robot r : game.getRobots())
            for (Vector v : r.getStrategy().getAttack(r, game))
                if (game.getBoard().isInsideBoard(v))
                    drawInteraction(renderer, v, 0xFFFF00);

        if (selectedAlien != null) {
            var preview = previewTiles(selectedAlien);
            for (Vector v : preview)
                drawInteraction(renderer, v, selectedColor());

            if (preview.contains(hovered))
                for (Vector v : hoveredTiles(selectedAlien, hovered))
                    drawInteraction(renderer, v, hoveredColor());
        }
        else
            for (Alien alien : game.getAliens())
                if (alien.isAlive() && !hasInteracted(alien) && hovered.equals(alien.getPosition()))
                    for (Vector v : previewTiles(alien))
                        drawInteraction(renderer, v, previewColor());
    }

    @Override
    public void onMouseMove(MouseMoveEvent event) {
        hovered = GUIUtil.screenToBoard(event.position());
    }

    @Override
    public void onMouseClick(MouseClickEvent event) {
        Vector position = GUIUtil.screenToBoard(event.position());

        if (selectedAlien != null && interact(selectedAlien, position)) {
            interactedAliens.add(selectedAlien);
            selectedAlien = null;
            return;
        }

        selectedAlien = null;
        for (Alien alien : game.getAliens())
            if (alien.isAlive() && !hasInteracted(alien) && position.equals(alien.getPosition()))
                selectedAlien = alien;
    }

    @Override
    public void onKeyboardEvent(KeyboardEvent event) {
        if (event.character() == '\n')
            nextState();
        else if (event.character() == 'x')
            selectedAlien = null;
    }

    protected abstract boolean interact(Alien alien, Vector position);

    protected abstract void nextState();

    protected abstract Set<Vector> previewTiles(Alien alien);
    protected abstract int previewColor();
    protected abstract Set<Vector> selectedTiles(Alien alien);
    protected abstract int selectedColor();
    protected abstract Set<Vector> hoveredTiles(Alien alien, Vector hovered);
    protected abstract int hoveredColor();

    protected boolean hasInteracted(Alien alien) {
        return interactedAliens.contains(alien);
    }
}
