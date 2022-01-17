package pt.up.fe.ldts.ootb.game.state;

import pt.up.fe.ldts.ootb.App;
import pt.up.fe.ldts.ootb.game.Game;
import pt.up.fe.ldts.ootb.game.entity.creature.robot.Robot;
import pt.up.fe.ldts.ootb.gui.render.Renderer;

public class AfterPlayerState extends BaseState  {
    AfterPlayerState(Game game) {
        super(game);
    }

    @Override
    public void init(App app) {
        super.init(app);

        for (Robot robot: game.getRobots()) {
            if (robot.isAlive()) {
                robot.getStrategy().attack(robot, game);
            }
        }

        if (game.getBoard().nests() == 0 || game.aliveAliens() == 0)
            game.changeState(new LostState(game));
        else if (game.aliveRobots() <= 0)
            game.changeState(new WinState(game));
        else
            game.changeState(new BeforePlayerState(game));
    }

    @Override
    protected void drawBeforeCreatures(Renderer renderer) {

    }

    @Override
    protected void drawAfterCreatures(Renderer renderer) {

    }
}
