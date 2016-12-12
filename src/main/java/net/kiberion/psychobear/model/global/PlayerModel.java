package net.kiberion.psychobear.model.global;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.psychobear.events.SkillsChangedEvent;
import net.kiberion.psychobear.events.StatsChangedEvent;
import net.kiberion.swampmachine.subscription.ObservableInt;
import net.kiberion.swampmachine.utils.MapUtils;

@Component
public class PlayerModel implements ApplicationEventPublisherAware{

    public static final String STAT_HEALTH = "health";
    public static final String STAT_SATIATION = "satiation";
    public static final String STAT_INSPIRATION = "inspiration";
    public static final String STAT_INSANITY = "insanity";
    public static final String STAT_BOREDOM = "boredom";
    public static final String STAT_CASH = "cash";
    
    public static final String SKILL_PROGRAMMING = "programming";
    public static final String SKILL_WRITING = "writing";
    public static final String SKILL_DRAWING = "drawing";
    public static final String SKILL_WEBSOCIAL = "web-social";
    public static final String SKILL_PHILOSOPHY = "philosophy";
    public static final String SKILL_COOKING = "cooking";
            
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
        stats.put(STAT_HEALTH, new ObservableInt(40));
        stats.put(STAT_SATIATION, new ObservableInt(40));
        stats.put(STAT_INSPIRATION, new ObservableInt(30));
        stats.put(STAT_INSANITY, new ObservableInt(10));
        stats.put(STAT_BOREDOM, new ObservableInt(30));
        stats.put(STAT_CASH, new ObservableInt(20));
        
        skills.put(SKILL_PROGRAMMING, new ObservableInt(0));
        skills.put(SKILL_WRITING, new ObservableInt(0));
        skills.put(SKILL_DRAWING, new ObservableInt(0));
        skills.put(SKILL_WEBSOCIAL, new ObservableInt(0));
        skills.put(SKILL_PHILOSOPHY, new ObservableInt(0));
        skills.put(SKILL_COOKING, new ObservableInt(0));
    }
    
    /**
     * 
     * @param params - expected format: String, number, String, number etc
     */
    public void changeStats (Object... params) {
        Map<String, Integer> statChanges = MapUtils.buildMap(params);
        changeStatsFromMap(statChanges);
    }
    
    public void changeStatsFromMap (Map<String, Integer> statChanges) {
        for (Entry<String, Integer> entry : statChanges.entrySet()) {
            changeStat (entry.getKey(), entry.getValue());
        }
    }
    
    
    public void changeStat (String statId, int delta) {
        stats.get(statId).applyDelta(delta);
        eventPublisher.publishEvent(new StatsChangedEvent(this));
    }
    
    
    public void changeSkill (String skillId, int delta) {
        ObservableInt skill = skills.get(skillId);
        Validate.notNull(skill, "Unknown skill: "+skillId);
        skill.applyDelta(delta);
        eventPublisher.publishEvent(new SkillsChangedEvent(this));
    }

}
