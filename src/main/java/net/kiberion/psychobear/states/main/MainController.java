package net.kiberion.psychobear.states.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import net.kiberion.swampmachine.mvcips.states.annotations.StateController;
import net.kiberion.swampmachine.mvcips.states.api.AbstractStateController;

@StateController
@Component
public class MainController extends AbstractStateController {

    private static final Logger log = LogManager.getLogger();
    
    public void onGroupSelected(String groupId) {
        log.info ("Group selected: "+groupId);
    }

}
