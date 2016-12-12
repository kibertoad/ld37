package net.kiberion.psychobear.model.global;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.psychobear.events.SkillsChangedEvent;
import net.kiberion.psychobear.events.StatsChangedEvent;
import net.kiberion.swampmachine.subscription.ObservableInt;

@Component
public class PlayerModel implements ApplicationEventPublisherAware{

    private ApplicationEventPublisher eventPublisher;
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
    
    @Getter
    private final MutableInt cash = new MutableInt ();
    
    @Getter
    private final Map<String, ObservableInt> stats = new LinkedHashMap<>();

    @Getter
    private final Map<String, ObservableInt> skills = new LinkedHashMap<>();
    
    public PlayerModel() {
        stats.put("health", new ObservableInt(1));
        stats.put("satiation", new ObservableInt(1));
        stats.put("inspiration", new ObservableInt(1));
        stats.put("insanity", new ObservableInt(1));
        stats.put("boredom", new ObservableInt(1));
        stats.put("cash", new ObservableInt(20));
        
        skills.put("programming", new ObservableInt(0));
        skills.put("writing", new ObservableInt(0));
        skills.put("drawing", new ObservableInt(0));
        skills.put("web-social", new ObservableInt(0));
        skills.put("philosophy", new ObservableInt(0));
        skills.put("cooking", new ObservableInt(0));
    }
    
    public void changeStat (String statId, int delta) {
        stats.get(statId).applyDelta(delta);
        eventPublisher.publishEvent(new StatsChangedEvent(this));
    }
    
    public void changeSkill (String skillId, int delta) {
        skills.get(skillId).applyDelta(delta);
        eventPublisher.publishEvent(new SkillsChangedEvent(this));
    }

}
