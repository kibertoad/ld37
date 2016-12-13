package net.kiberion.psychobear.processors;

import java.util.Collection;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.psychobear.events.FinishedProcessingActivityEvent;
import net.kiberion.psychobear.model.PsychoBearActivity;
import net.kiberion.psychobear.model.global.PlayerModel;
import net.kiberion.psychobear.states.activity.ProcessActivityState;
import net.kiberion.psychobear.states.ending.EndingState;
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

    @Getter
    private PsychoBearActivity currentActivity;

    @Autowired
    private PlayerModel playerModel;

    private Iterator<PsychoBearActivity> iterator;
    
    @Getter
    private Ending reachedEnding;
    
    public enum Ending {
        HIGH_INSANITY, HIGH_BOREDOM, HUNGER, HIGH_FAME
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    protected void processTimeEffects() {
        playerModel.changeStat(PlayerModel.STAT_SATIATION, -5);
        
        if (playerModel.getStatValue (PlayerModel.STAT_INSPIRATION) >= 30) {
            playerModel.changeStat(PlayerModel.STAT_INSANITY, 1);
        } else {
            playerModel.changeStat(PlayerModel.STAT_INSANITY, -1);
        }

        if (playerModel.getStatValue (PlayerModel.STAT_INSANITY) >= 30) {
            playerModel.changeStat(PlayerModel.STAT_INSPIRATION, 2);
        } else {
            playerModel.changeStat(PlayerModel.STAT_INSPIRATION, -2);
        }
        
        playerModel.changeStat(PlayerModel.STAT_BOREDOM, 1);
    
        //check for endings
        
        if (playerModel.getStatValue(PlayerModel.STAT_BOREDOM) >= 100) {
            triggerEnding (Ending.HIGH_BOREDOM);
        }
        
        if (playerModel.getStatValue(PlayerModel.STAT_INSANITY) >= 100) {
            triggerEnding (Ending.HIGH_INSANITY);
        }

        if (playerModel.getStatValue(PlayerModel.STAT_FAME) >= 100) {
            triggerEnding (Ending.HIGH_FAME);
        }

        if (playerModel.getStatValue(PlayerModel.STAT_SATIATION) <= 0) {
            triggerEnding (Ending.HUNGER);
        }
        
    }
    
    private void triggerEnding(Ending ending) {
        reachedEnding = ending;
        eventPublisher.publishEvent(new ChangeStateEvent(this, EndingState.STATE_ID));
    }

    public void processNextTurn(Collection<PsychoBearActivity> scheduledActivities) {
        iterator = scheduledActivities.iterator();
        eventPublisher.publishEvent(new ChangeStateEvent(this, ProcessActivityState.STATE_ID));
        processNextActivity();
        processTimeEffects();
    }

    @EventListener
    public void processNextActivity(FinishedProcessingActivityEvent event) {
        processNextActivity();
    }

    public void processNextActivity() {
        if (!iterator.hasNext()) {
            eventPublisher.publishEvent(new ChangeStateEvent(this, MainState.MAIN_STATE_ID));
        } else {
            this.currentActivity = iterator.next();

            // ToDo remove this block later
            if (currentActivity == null) {
                processNextActivity();
            } else {

                log.info("Now processing: " + currentActivity);
                eventPublisher.publishEvent(new ShowSubViewEvent(this, this.currentActivity.getViewName(), true));
            }
        }
    }

}
