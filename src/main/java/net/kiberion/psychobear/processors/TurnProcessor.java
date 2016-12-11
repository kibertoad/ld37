package net.kiberion.psychobear.processors;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.psychobear.model.PsychoBearActivity;
import net.kiberion.psychobear.model.global.GameModel;

@Component
public class TurnProcessor {

    private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private GameModel gameModel;
    
    public void processNextTurn (Collection<PsychoBearActivity> scheduledActivities) {
        for (PsychoBearActivity activity : scheduledActivities) {
            log.info("Now processing: "+activity);
        }
        
    }
    
}
