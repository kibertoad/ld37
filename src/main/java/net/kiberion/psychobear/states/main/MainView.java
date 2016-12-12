package net.kiberion.psychobear.states.main;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.psychobear.model.global.GameModel;
import net.kiberion.psychobear.states.global.StatsSubView;
import net.kiberion.psychobear.states.main.subviews.ActivityGroupSubView;
import net.kiberion.psychobear.states.main.subviews.ScheduleSubView;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.gui.view.AbstractStateView;
import net.kiberion.swampmachine.utils.ListUtils;

@Component
@BoundCompositions(compositions = { "main" })
public class MainView extends AbstractStateView<GameModel>{

    @Autowired
    private ActivityGroupSubView activityGroupSubView;

    @Autowired
    private ActivityGroupSubView activitySubView;
    
    @Autowired
    private ScheduleSubView scheduleSubView;

    @Autowired
    private StatsSubView statsSubView;
    
    @Override
    protected Collection<AbstractStateSubView<?>> getAutoEnabledSubViews() {
        return ListUtils.buildList(scheduleSubView, statsSubView);
    }
    
}
