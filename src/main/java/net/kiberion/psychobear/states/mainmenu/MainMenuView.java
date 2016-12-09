package net.kiberion.psychobear.states.mainmenu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import net.kiberion.swampmachine.blueprints.common.state.CommonMainMenuView;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;

@Component
@BoundCompositions(compositions = { "main-menu" })
public class MainMenuView extends CommonMainMenuView {

    private static final Logger log = LogManager.getLogger();

    @Override
    public void initGUIElements() {
    }

}
