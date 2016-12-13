package net.kiberion.psychobear.states.ending;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.swampmachine.api.view.StateView;
import net.kiberion.swampmachine.mvcips.states.GameState;
import net.kiberion.swampmachine.mvcips.states.annotations.State;
import net.kiberion.swampmachine.mvcips.states.annotations.StateControllers;

@Component
@State (id = EndingState.STATE_ID)
@StateControllers(controllers = { EndingController.class })
public class EndingState extends GameState{
    
    public static final String STATE_ID = "ending";

    @Autowired
    private EndingView view;
    
    @Override
    public StateView getView() {
        return view;
    };

}
