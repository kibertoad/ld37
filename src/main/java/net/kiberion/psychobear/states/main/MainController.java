package net.kiberion.psychobear.states.main;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.psychobear.model.PsychoBearActivity;
import net.kiberion.swampmachine.events.ShowSubViewEvent;
import net.kiberion.swampmachine.mvcips.states.annotations.StateController;
import net.kiberion.swampmachine.mvcips.states.api.AbstractStateController;
import net.kiberion.swampmachine.subscription.ObservableObject;

@StateController
@Component
public class MainController extends AbstractStateController {

    public enum DayTime {
        DAY, EVENING, NIGHT
    }
    
    @Getter
    private String selectedGroup;
    
    private ObservableObject<PsychoBearActivity> selectedActivitySlot;
     
    private final Map<DayTime, ObservableObject<PsychoBearActivity>> scheduledActivities = new LinkedHashMap<>();
    
    private static final Logger log = LogManager.getLogger();
    
    public MainController() {
        scheduledActivities.put(DayTime.DAY, new ObservableObject<PsychoBearActivity>());
        scheduledActivities.put(DayTime.EVENING, new ObservableObject<PsychoBearActivity>());
        scheduledActivities.put(DayTime.NIGHT, new ObservableObject<PsychoBearActivity>());
    }
    
    public void resetForNextDay () {
        selectedGroup = null;
    }
    
    public void onGroupSelected(String groupId) {
        log.info ("Group selected: "+groupId);
        
        this.selectedGroup = groupId;
        getApplicationEventPublisher().publishEvent(new ShowSubViewEvent(this, ActivitySubView.SUB_VIEW_ID, true));
    }

    public void cancelGroupSelection() {
        log.info ("Cancel group.");
        getApplicationEventPublisher().publishEvent(new ShowSubViewEvent(this, ScheduleSubView.SUB_VIEW_ID, true));
    }

    
    public void onActivitySelected(String activityId) {
        log.info ("Activity selected: "+activityId);
    }
    
    public void cancelActivitySelection() {
        log.info ("Cancel activity.");
        getApplicationEventPublisher().publishEvent(new ShowSubViewEvent(this, ActivityGroupSubView.SUB_VIEW_ID, true));
    }
    
    public void onDayTimeSelected(DayTime dayTime) {
        log.info ("Daytime selected: "+dayTime);
        this.selectedActivitySlot = scheduledActivities.get(dayTime);
        getApplicationEventPublisher().publishEvent(new ShowSubViewEvent(this, ActivityGroupSubView.SUB_VIEW_ID, true));
    }

    public void nextTurn() {
        log.info ("Next turn.");
    }
}
