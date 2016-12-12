package net.kiberion.psychobear.states.activity;

import org.springframework.stereotype.Component;

import net.kiberion.psychobear.events.FinishedProcessingActivityEvent;
import net.kiberion.swampmachine.mvcips.states.annotations.StateController;
import net.kiberion.swampmachine.mvcips.states.api.AbstractStateController;

@StateController
@Component
public class ProcessActivityController extends AbstractStateController{

    public void activityFinished () {
        getApplicationEventPublisher().publishEvent(new FinishedProcessingActivityEvent(this));
    }
    
}
