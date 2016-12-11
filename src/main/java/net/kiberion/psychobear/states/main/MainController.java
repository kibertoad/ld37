package net.kiberion.psychobear.states.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.swampmachine.events.ShowSubViewEvent;
import net.kiberion.swampmachine.mvcips.states.annotations.StateController;
import net.kiberion.swampmachine.mvcips.states.api.AbstractStateController;

@StateController
@Component
public class MainController extends AbstractStateController {

    @Getter
    private String selectedGroup;
    
    private static final Logger log = LogManager.getLogger();
    
    public void reset () {
        selectedGroup = null;
    }
    
    public void onGroupSelected(String groupId) {
        log.info ("Group selected: "+groupId);
        
        this.selectedGroup = groupId;
        getApplicationEventPublisher().publishEvent(new ShowSubViewEvent(this, ActivitySubView.ACTIVITY_SUB_VIEW_ID, true));
    }

    public void onActivitySelected(String activityId) {
        log.info ("Activity selected: "+activityId);
    }
}
