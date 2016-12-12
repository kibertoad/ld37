package net.kiberion.psychobear.states.activity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.psychobear.model.global.GameModel;
import net.kiberion.psychobear.processors.TurnProcessor;
import net.kiberion.psychobear.states.activity.subviews.VideosSubView;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.gui.view.AbstractStateView;

@Component
@BoundCompositions(compositions = { "process-activity" })
public class ProcessActivityView extends AbstractStateView<GameModel>{

    @Autowired
    private TurnProcessor turnProcessor;
    
    @Autowired
    private VideosSubView videosSubView;
    
    @Override
    public void show() {
        super.show();
    }

    @Override
    protected Collection<AbstractStateSubView<?>> getAutoEnabledSubViews() {
        return Collections.emptyList();
    }
    
}
