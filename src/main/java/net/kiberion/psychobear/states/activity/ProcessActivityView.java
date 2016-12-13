package net.kiberion.psychobear.states.activity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.psychobear.model.global.PlayerModel;
import net.kiberion.psychobear.processors.TurnProcessor;
import net.kiberion.psychobear.states.activity.subviews.VideosSubView;
import net.kiberion.psychobear.states.global.StatsSubView;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.gui.view.AbstractStateView;
import net.kiberion.swampmachine.utils.ListUtils;

@Component
@BoundCompositions(compositions = { "process-activity" })
public class ProcessActivityView extends AbstractStateView<PlayerModel>{

    @Autowired
    private TurnProcessor turnProcessor;
    
    @Autowired
    private VideosSubView videosSubView;
    
    @Override
    public void show() {
        super.show();
    }

    @Override
    protected Collection<Class<? extends AbstractStateSubView<?>>> getAutoEnabledSubViews() {
        return ListUtils.buildList(StatsSubView.class);
    }
    
}
