package pt.up.fe.ldts.ootb.game.state;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.Robot;
import pt.up.fe.ldts.ootb.gui.render.Renderer;

public class BeforePlayerState extends BaseState {
    public BeforePlayerState(Game game) {
        super(game);
    }

    @Override
    public void init(App app) {
        super.init(app);

        for (Robot robot : game.getRobots()) {
            if (robot.isAlive()) {
                robot.setPosition(robot.getStrategy().move(robot, game));
                robot.getStrategy().planAttack(robot, game);
            }
        }
        game.changeState(new MoveState(game));
    }

    @Override
    protected void drawBeforeCreatures(Renderer renderer) {

    }

    @Override
    protected void drawAfterCreatures(Renderer renderer) {

    }
}
