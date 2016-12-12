package net.kiberion.psychobear.events;

import org.springframework.context.ApplicationEvent;

public class FinishedProcessingActivityEvent extends ApplicationEvent{

    /**
     * 
     */
    private static final long serialVersionUID = 8135179173797437868L;

    public FinishedProcessingActivityEvent(Object source) {
        super(source);
    }

}
