package net.kiberion.psychobear.states.mainmenu;

import org.springframework.stereotype.Component;

import net.kiberion.swampmachine.blueprints.common.state.CommonMainMenuState;
import net.kiberion.swampmachine.mvcips.states.annotations.StartingState;
import net.kiberion.swampmachine.mvcips.states.annotations.StateControllers;

@Component
@StartingState
@StateControllers(controllers = { MainMenuController.class })
public class MainMenuState extends CommonMainMenuState{

}
