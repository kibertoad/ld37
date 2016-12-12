package net.kiberion.psychobear.processors;

import java.util.Collection;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.psychobear.model.PsychoBearActivity;
import net.kiberion.psychobear.model.global.GameModel;
import net.kiberion.psychobear.states.activity.ProcessActivityState;
import net.kiberion.psychobear.states.main.MainController.DayTime;
import net.kiberion.psychobear.states.main.MainState;
import net.kiberion.swampmachine.events.ChangeStateEvent;
import net.kiberion.swampmachine.events.ShowSubViewEvent;

@Component
public class TurnProcessor implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    private static final Logger log = LogManager.getLogger();

    @Getter
    private DayTime currentDayTime;

    private PsychoBearActivity currentActivity;

    @Autowired
    private GameModel gameModel;

    private Iterator<PsychoBearActivity> iterator;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    public void processNextTurn(Collection<PsychoBearActivity> scheduledActivities) {
        iterator = scheduledActivities.iterator();
        eventPublisher.publishEvent(new ChangeStateEvent(this, ProcessActivityState.STATE_ID));
        processNextActivity();
    }

    public void processNextActivity() {
        if (!iterator.hasNext()) {
            eventPublisher.publishEvent(new ChangeStateEvent(this, MainState.MAIN_STATE_ID));
        } else {
            this.currentActivity = iterator.next();
            log.info("Now processing: " + currentActivity);
            eventPublisher.publishEvent(new ShowSubViewEvent(this, this.currentActivity.getViewName(), true));
        }
    }

}
