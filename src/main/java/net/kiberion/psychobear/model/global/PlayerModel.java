package net.kiberion.psychobear.model.global;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.swampmachine.subscription.ObservableInt;

@Component
public class PlayerModel {

    @Getter
    private final MutableInt cash = new MutableInt ();
    
    @Getter
    private final Map<String, ObservableInt> stats = new LinkedHashMap<>();

    @Getter
    private final Map<String, ObservableInt> skills = new LinkedHashMap<>();
    
    public PlayerModel() {
        stats.put("fitness", new ObservableInt(1));
        stats.put("finesse", new ObservableInt(1));
        stats.put("charm", new ObservableInt(1));
        stats.put("luck", new ObservableInt(1));
        stats.put("strength", new ObservableInt(1));
        stats.put("intelligence", new ObservableInt(1));
        stats.put("insight", new ObservableInt(1));
        
        skills.put("programming", new ObservableInt(0));
    }

}
