package net.kiberion.psychobear.states.activity.subviews;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.psychobear.model.PsychoBearVideo;
import net.kiberion.psychobear.model.global.GameModel;
import net.kiberion.psychobear.model.global.PlayerModel;
import net.kiberion.psychobear.registries.ActivityRegistry;
import net.kiberion.psychobear.states.activity.ProcessActivityController;
import net.kiberion.psychobear.states.activity.ProcessActivityView;
import net.kiberion.swampmachine.annotations.SubView;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.invokables.LambdaInvokable;
import net.kiberion.swampmachine.api.sources.EntrySource;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.elements.CommonButtonEntry;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.subscription.ObservableButtonEntrySource;
import net.kiberion.swampmachine.utils.RandomUtils;

@Component
@SubView(id = VideosSubView.SUB_VIEW_ID, parentViews = {ProcessActivityView.class})
@BoundCompositions(compositions = { "activity-videos" })
public class VideosSubView extends AbstractStateSubView<GameModel> {

    private static final Logger log = LogManager.getLogger();

    public static final String SUB_VIEW_ID = "activityVideosView";

    private final ObservableButtonEntrySource entrySource = new ObservableButtonEntrySource();

    @Autowired
    private ActivityRegistry registry;
    
    @Autowired
    private ProcessActivityController controller;
    
    @Autowired
    private PlayerModel playerModel;

    public EntrySource<ButtonEntry> getVideoList() {
        return entrySource;
    }

    protected void generateNewVideoList() {
        log.info("Get videos");
        List<ButtonEntry> buttons = new ArrayList<>();

        List<PsychoBearVideo> videos = new ArrayList<>(registry.getVideos().values());

        IntStream.rangeClosed(1, 3).sequential().forEach((intValue) -> {
            PsychoBearVideo video = RandomUtils.removeRandomEntry(videos);
            CommonButtonEntry button = new CommonButtonEntry();
            button.setText(video.getName());

            LambdaInvokable onClickEffect = () -> {
                playerModel.changeSkill(video.getSkill(), video.getSkillIncrease());
                playerModel.changeStatsFromMap (video.getStatChanges());
                controller.activityFinished();
                return null;
            };

            button.setOnClickEffect(onClickEffect);
            buttons.add(button);
        });
        entrySource.setValue(buttons);

    }

    @Override
    public void show() {
        super.show();
        generateNewVideoList();
    }

}
