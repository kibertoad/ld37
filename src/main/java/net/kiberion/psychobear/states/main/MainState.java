package net.kiberion.psychobear.states.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.swampmachine.gui.view.StateView;
import net.kiberion.swampmachine.mvcips.states.GameState;
import net.kiberion.swampmachine.mvcips.states.annotations.NewGameState;
import net.kiberion.swampmachine.mvcips.states.annotations.State;
import net.kiberion.swampmachine.mvcips.states.annotations.StateControllers;

@Component
@NewGameState
@State (id = MainState.MAIN_STATE_ID)
@StateControllers(controllers = { MainController.class })
public class MainState extends GameState{
    
    public static final String MAIN_STATE_ID = "main";

    @Autowired
    private MainView view;
    
    @Override
    public StateView getView() {
        return view;
    };

}
