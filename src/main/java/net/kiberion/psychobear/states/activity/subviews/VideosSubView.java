package net.kiberion.psychobear.states.activity.subviews;

import org.springframework.stereotype.Component;

import net.kiberion.psychobear.model.global.GameModel;
import net.kiberion.psychobear.states.activity.ProcessActivityView;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.mvcips.states.annotations.SubView;

@Component
@SubView(id = VideosSubView.SUB_VIEW_ID, parentView = ProcessActivityView.class)
@BoundCompositions(compositions = { "activity-videos" })
public class VideosSubView extends AbstractStateSubView<GameModel>{

    public static final String SUB_VIEW_ID = "activity-videos-view";

}
