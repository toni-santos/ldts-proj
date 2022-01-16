package pt.up.fe.ldts.ootb.game.state;

import pt.up.fe.ldts.ootb.App;

public interface State {
    void dispose(App app);
    void init(App app);
}
