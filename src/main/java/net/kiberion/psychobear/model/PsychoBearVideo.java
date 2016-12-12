package net.kiberion.psychobear.model;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

public class PsychoBearVideo extends CommonModelEntityDescriptor {

    //Which view is opened when this activity is processed
    @Getter
    @Setter
    private String viewName;
    
    @Getter
    @Setter
    private String skill;
    
    @Getter
    @Setter
    private int skillIncrease;
    
    public PsychoBearVideo(String setName, String setCode, String skill, int skillIncrease) {
        setName (setName);
        setId(setCode);
    }
    
}
