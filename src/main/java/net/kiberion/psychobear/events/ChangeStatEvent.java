package net.kiberion.psychobear.events;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class ChangeStatEvent extends ApplicationEvent{

    /**
     * 
     */
    private static final long serialVersionUID = 1328422518819744513L;
    
    @Getter
    private final String skillId;
    
    @Getter
    private final int changeAmount;
    
    public ChangeStatEvent(Object source, String skillId, int changeAmount) {
        super(source);
        this.skillId = skillId;
        this.changeAmount = changeAmount;
    }
    
}
