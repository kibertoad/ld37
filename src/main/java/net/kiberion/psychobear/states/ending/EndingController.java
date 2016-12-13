package net.kiberion.psychobear.states.ending;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;

import net.kiberion.swampmachine.mvcips.states.annotations.StateController;
import net.kiberion.swampmachine.mvcips.states.api.AbstractStateController;

@StateController
@Component
public class EndingController extends AbstractStateController{

    public void gameOver () {
        Gdx.app.exit();
    }
    
}
