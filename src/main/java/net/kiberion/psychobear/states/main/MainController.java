package net.kiberion.psychobear.states.main;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.psychobear.model.PsychoBearActivity;
import net.kiberion.psychobear.processors.TurnProcessor;
import net.kiberion.psychobear.registries.ActivityRegistry;
import net.kiberion.psychobear.states.main.subviews.ActivityGroupSubView;
import net.kiberion.psychobear.states.main.subviews.ActivitySubView;
import net.kiberion.psychobear.states.main.subviews.ScheduleSubView;
import net.kiberion.swampmachine.events.ShowSubViewEvent;
import net.kiberion.swampmachine.mvcips.states.annotations.StateController;
import net.kiberion.swampmachine.mvcips.states.api.AbstractStateController;
import net.kiberion.swampmachine.subscription.ObservableObject;
import net.kiberion.swampmachine.utils.ObservableUtils;

@StateController
@Component
public class MainController extends AbstractStateController {

    private static final Logger log = LogManager.getLogger();

    public enum DayTime {
        DAY, EVENING, NIGHT
    }
    
    
    @Getter
    private String selectedGroup;
    
    private ObservableObject<PsychoBearActivity> selectedActivitySlot;
     
    private final Map<DayTime, ObservableObject<PsychoBearActivity>> scheduledActivities = new LinkedHashMap<>();
    
    @Autowired
    private TurnProcessor turnProcessor;

    @Autowired
    private ActivityRegistry activityRegistry;
    
    public MainController() {
        scheduledActivities.put(DayTime.DAY, new ObservableObject<PsychoBearActivity>());
        scheduledActivities.put(DayTime.EVENING, new ObservableObject<PsychoBearActivity>());
        scheduledActivities.put(DayTime.NIGHT, new ObservableObject<PsychoBearActivity>());
    }
    
    public void resetForNextDay () {
        selectedGroup = null;
    }
    
    public void onGroupSelected(String groupId) {
        Validate.notBlank(groupId);
        log.info ("Group selected: "+groupId);
        this.selectedGroup = groupId;
        getApplicationEventPublisher().publishEvent(new ShowSubViewEvent(this, ActivitySubView.SUB_VIEW_ID, true));
    }

    public void cancelGroupSelection() {
        log.info ("Cancel group.");
        getApplicationEventPublisher().publishEvent(new ShowSubViewEvent(this, ScheduleSubView.SUB_VIEW_ID, true));
    }

    
    public void onActivitySelected(String activityId) {
        Validate.notBlank(activityId);
        log.info ("Activity selected: "+activityId);
        this.selectedActivitySlot.setValue(activityRegistry.getActivities().get(activityId));
        getApplicationEventPublisher().publishEvent(new ShowSubViewEvent(this, ScheduleSubView.SUB_VIEW_ID, true));
    }
    
    public void cancelActivitySelection() {
        log.info ("Cancel activity.");
        getApplicationEventPublisher().publishEvent(new ShowSubViewEvent(this, ActivityGroupSubView.SUB_VIEW_ID, true));
    }
    
    public void onDayTimeSelected(DayTime dayTime) {
        Validate.notNull(dayTime);
        log.info ("Daytime selected: "+dayTime);
        this.selectedActivitySlot = scheduledActivities.get(dayTime);
        getApplicationEventPublisher().publishEvent(new ShowSubViewEvent(this, ActivityGroupSubView.SUB_VIEW_ID, true));
    }

    public void nextTurn() {
        log.info ("Next turn.");
        turnProcessor.processNextTurn(ObservableUtils.toEntityCollection(scheduledActivities.values()));
    }
    
    public PsychoBearActivity getActivity (DayTime dayTime) {
        return scheduledActivities.get(dayTime).getValue();
    }

    public boolean isAllActivitiesSelected() {
        for (ObservableObject<PsychoBearActivity> activity : scheduledActivities.values()) {
            if (activity.getValue() == null) {
                return false;
            }
        }
        
        return true;
    }
}
