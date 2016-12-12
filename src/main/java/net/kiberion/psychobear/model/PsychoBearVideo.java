package net.kiberion.psychobear.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

public class PsychoBearVideo extends CommonModelEntityDescriptor {

    @Getter
    @Setter
    private String skill;
    
    @Getter
    @Setter
    private int skillIncrease;
    
    @Getter
    @Setter
    private Map<String, Integer> statChanges;
    
    public PsychoBearVideo(String setName, String setCode, String skill, int skillIncrease, Map<String, Integer> statChanges) {
        setName (setName);
        setId(setCode);
        setSkill(skill);
        setSkillIncrease(skillIncrease);
        setStatChanges(statChanges);
    }
    
}
