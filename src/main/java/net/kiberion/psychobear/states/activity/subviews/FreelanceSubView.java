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
@SubView(id = FreelanceSubView.SUB_VIEW_ID, parentViews = { ProcessActivityView.class })
@BoundCompositions(compositions = { "activity-freelance" })
public class FreelanceSubView extends AbstractStateSubView<PlayerModel> {

    private static final Logger log = LogManager.getLogger();

    public static final String SUB_VIEW_ID = "activityFreelanceView";

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
        int progress = 34;
        int quality = 10;
        getModel().addFreelanceProgress(skill, progress);
        getModel().addFreelanceQuality(skill, quality);

        String taskName = StringUtils.lowerCase(activity.getName());
        String resultText;
        if (getModel().getFreelanceProgress(skill) >= 100) {
            int payment = 10;
            resultText = "You have finished your "+taskName+" task. Your payment is $" + payment;
            getModel().changeStat(PlayerModel.STAT_CASH, payment);
            getModel().resetFreelanceProgress(skill);
        } else {
            resultText = "You keep working on your "+taskName+" task. You are " + getModel().getFreelanceProgress(skill)
                    + "% done.";
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
