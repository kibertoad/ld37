package net.kiberion.psychobear.states.global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.psychobear.model.global.GameModel;
import net.kiberion.psychobear.model.global.PlayerModel;
import net.kiberion.psychobear.registries.ActivityRegistry;
import net.kiberion.psychobear.states.activity.ProcessActivityView;
import net.kiberion.psychobear.states.main.MainView;
import net.kiberion.swampmachine.api.elements.TextEntry;
import net.kiberion.swampmachine.api.sources.EntrySource;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.elements.CommonTextEntry;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.mvcips.states.annotations.SubView;
import net.kiberion.swampmachine.subscription.ObservableInt;
import net.kiberion.swampmachine.subscription.ObservableTextEntrySource;

@Component
@SubView(id = StatsSubView.SUB_VIEW_ID, parentViews = {MainView.class, ProcessActivityView.class})
@BoundCompositions(compositions = { "stats" })
public class StatsSubView extends AbstractStateSubView<GameModel> {

    private static final Logger log = LogManager.getLogger();

    public static final String SUB_VIEW_ID = "statsView";

    private final ObservableTextEntrySource entrySource = new ObservableTextEntrySource();

    @Autowired
    private ActivityRegistry registry;
    
    @Autowired
    private PlayerModel player;

    public EntrySource<TextEntry> getStatList() {
        return entrySource;
    }

    protected void fillStatsList() {
        log.info("Get videos");
        List<TextEntry> entries = new ArrayList<>();

        //List<PsychoBearVideo> videos = new ArrayList<>(registry.getVideos().values());

        for (Entry<String, ObservableInt> entry : player.getStats().entrySet()) {
            CommonTextEntry textEntry = new CommonTextEntry (StringUtils.capitalize(entry.getKey())+": "+entry.getValue().getValue());
            entries.add(textEntry);
        }
        
        entrySource.setValue(entries);

    }

    @Override
    public void show() {
        super.show();
        fillStatsList();
    }

}
