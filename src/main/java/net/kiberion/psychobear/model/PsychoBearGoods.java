package net.kiberion.psychobear.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

public class PsychoBearGoods extends CommonModelEntityDescriptor {

    @Getter
    @Setter
    private Map<String, Integer> statChanges;
    
    @Getter
    @Setter
    private int price;
    
    public PsychoBearGoods(String setName, String setCode, int price, Map<String, Integer> statChanges) {
        setName (setName);
        setId(setCode);
        setPrice(price);
        setStatChanges(statChanges);
    }
    
}
