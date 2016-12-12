package net.kiberion.psychobear.states.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.swampmachine.api.view.StateView;
import net.kiberion.swampmachine.mvcips.states.GameState;
import net.kiberion.swampmachine.mvcips.states.annotations.State;
import net.kiberion.swampmachine.mvcips.states.annotations.StateControllers;

@Component
@State (id = ProcessActivityState.STATE_ID)
@StateControllers(controllers = { ProcessActivityController.class })
public class ProcessActivityState extends GameState{
    
    public static final String STATE_ID = "processActivity";

    @Autowired
    private ProcessActivityView view;
    
    @Override
    public StateView getView() {
        return view;
    };

}
