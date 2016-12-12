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
        stats.put("health", new ObservableInt(1));
        stats.put("satiation", new ObservableInt(1));
        stats.put("inspiration", new ObservableInt(1));
        stats.put("insanity", new ObservableInt(1));
        stats.put("boredom", new ObservableInt(1));
        
        skills.put("programming", new ObservableInt(0));
        skills.put("writing", new ObservableInt(0));
        skills.put("cooking", new ObservableInt(0));
    }

}
