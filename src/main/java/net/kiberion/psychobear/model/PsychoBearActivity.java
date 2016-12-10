package net.kiberion.psychobear.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.kiberion.swampmachine.api.common.Condition;
import net.kiberion.swampmachine.api.common.ConditionHolder;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

public class PsychoBearActivity extends CommonModelEntityDescriptor implements ConditionHolder{

    private final List<Condition> conditions = new ArrayList<>();
    
    public PsychoBearActivity(String setName, String setCode) {
        setName (setName);
        setId(setCode);
    }
    
    @Override
    public Collection<Condition> getConditions() {
        return conditions;
    }
    
}
