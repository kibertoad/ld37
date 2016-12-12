package net.kiberion.psychobear.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.api.common.Condition;
import net.kiberion.swampmachine.api.common.ConditionHolder;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

public class PsychoBearActivity extends CommonModelEntityDescriptor implements ConditionHolder{

    private final List<Condition> conditions = new ArrayList<>();
    
    //Which view is opened when this activity is processed
    @Getter
    @Setter
    private String viewName;
    
    @Getter
    @Setter
    private String skill;
    
    public PsychoBearActivity(String setName, String setCode, String skill) {
        setName (setName);
        setId(setCode);
        setSkill (skill);
    }
    
    @Override
    public Collection<Condition> getConditions() {
        return conditions;
    }
    
}
