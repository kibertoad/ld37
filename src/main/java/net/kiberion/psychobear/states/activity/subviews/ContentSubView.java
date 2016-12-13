package net.kiberion.psychobear.states.activity.subviews;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.psychobear.model.PsychoBearActivity;
import net.kiberion.psychobear.model.global.PlayerModel;
import net.kiberion.psychobear.processors.TurnProcessor;
import net.kiberion.psychobear.registries.ActivityRegistry;
import net.kiberion.psychobear.states.activity.ProcessActivityView;
import net.kiberion.swampmachine.annotations.SubView;
import net.kiberion.swampmachine.api.elements.TextEntry;
import net.kiberion.swampmachine.api.sources.EntrySource;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.elements.CommonTextEntry;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.subscription.ObservableTextEntrySource;
import net.kiberion.swampmachine.utils.ListUtils;

@Component
@SubView(id = ContentSubView.SUB_VIEW_ID, parentViews = { ProcessActivityView.class })
@BoundCompositions(compositions = { "activity-content" })
public class ContentSubView extends AbstractStateSubView<PlayerModel> {

    private static final Logger log = LogManager.getLogger();

    public static final String SUB_VIEW_ID = "activityContentView";

    private final ObservableTextEntrySource entrySource = new ObservableTextEntrySource();

    @Autowired
    private ActivityRegistry registry;

    @Autowired
    private TurnProcessor turnProcessor;

    public EntrySource<TextEntry> getProgressText() {
        return entrySource;
    }

    protected void processProgress() {
        PsychoBearActivity activity = turnProcessor.getCurrentActivity();
        String skill = activity.getSkill();
        int progress = 20;
        int quality = 10;
        getModel().addContentProgress(skill, progress);
        getModel().addContentQuality(skill, quality);

        String taskName = StringUtils.lowerCase(activity.getName());
        String resultText;
        if (getModel().getContentProgress(skill) >= 100) {
            resultText = "You have finished your " + taskName + " content creation.";
            getModel().resetContentProgress(skill);
        } else {
            resultText = "You keep working on your " + taskName + " task. You are "
                    + getModel().getContentProgress(skill) + "% done.";
        }
        TextEntry text = new CommonTextEntry(resultText);

        entrySource.setValue(ListUtils.buildList(text));
    }

    @Override
    public void show() {
        super.show();
        processProgress();
    }

}
