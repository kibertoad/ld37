package net.kiberion.psychobear.states.global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import net.kiberion.psychobear.events.SkillsChangedEvent;
import net.kiberion.psychobear.events.StatsChangedEvent;
import net.kiberion.psychobear.model.global.GameModel;
import net.kiberion.psychobear.model.global.PlayerModel;
import net.kiberion.psychobear.states.activity.ProcessActivityView;
import net.kiberion.psychobear.states.main.MainView;
import net.kiberion.swampmachine.annotations.SubView;
import net.kiberion.swampmachine.api.elements.TextEntry;
import net.kiberion.swampmachine.api.sources.EntrySource;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.elements.CommonTextEntry;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.subscription.ObservableInt;
import net.kiberion.swampmachine.subscription.ObservableTextEntrySource;

@Component
@SubView(id = StatsSubView.SUB_VIEW_ID, parentViews = {MainView.class, ProcessActivityView.class}, isConstant = true, zIndex = 1)
@BoundCompositions(compositions = { "stats" })
public class StatsSubView extends AbstractStateSubView<GameModel> {

    private static final Logger log = LogManager.getLogger();

    public static final String SUB_VIEW_ID = "statsView";

    private final ObservableTextEntrySource statSource = new ObservableTextEntrySource();
    private final ObservableTextEntrySource skillSource = new ObservableTextEntrySource();

    @Autowired
    private PlayerModel player;

    public EntrySource<TextEntry> getStatsList() {
        return statSource;
    }
    
    public EntrySource<TextEntry> getSkillsList() {
        return skillSource;
    }
    

    protected void fillStatsList() {
        log.info("Get stats");
        List<TextEntry> entries = new ArrayList<>();

        for (Entry<String, ObservableInt> entry : player.getStats().entrySet()) {
            CommonTextEntry textEntry = new CommonTextEntry (StringUtils.capitalize(entry.getKey())+": "+entry.getValue().getValue());
            entries.add(textEntry);
        }
        
        statSource.setValue(entries);
    }

    protected void fillSkillsList() {
        log.info("Get skills");
        List<TextEntry> entries = new ArrayList<>();

        for (Entry<String, ObservableInt> entry : player.getSkills().entrySet()) {
            CommonTextEntry textEntry = new CommonTextEntry (StringUtils.capitalize(entry.getKey())+": "+entry.getValue().getValue());
            entries.add(textEntry);
        }
        
        skillSource.setValue(entries);
    }
    

    @EventListener
    public void updateStatsList(StatsChangedEvent event) {
        fillStatsList();
    }
    
    @EventListener
    public void updateSkillsList(SkillsChangedEvent event) {
        fillSkillsList();
    }
    

    @Override
    public void show() {
        super.show();
        fillStatsList();
        fillSkillsList();
    }

}
