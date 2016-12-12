package net.kiberion.psychobear.events;

import org.springframework.context.ApplicationEvent;

public class SkillsChangedEvent extends ApplicationEvent{

    /**
     * 
     */
    private static final long serialVersionUID = -6417194891144520298L;

    public SkillsChangedEvent(Object source) {
        super(source);
    }

}
