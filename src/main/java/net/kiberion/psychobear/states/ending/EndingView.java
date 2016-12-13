package net.kiberion.psychobear.states.ending;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.psychobear.model.global.PlayerModel;
import net.kiberion.psychobear.processors.TurnProcessor;
import net.kiberion.swampmachine.api.elements.TextEntry;
import net.kiberion.swampmachine.api.sources.EntrySource;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.elements.CommonTextEntry;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.gui.view.AbstractStateView;
import net.kiberion.swampmachine.subscription.ObservableTextEntrySource;
import net.kiberion.swampmachine.utils.ListUtils;

@Component
@BoundCompositions(compositions = { "ending" })
public class EndingView extends AbstractStateView<PlayerModel> {

    @Autowired
    private TurnProcessor turnProcessor;

    private final ObservableTextEntrySource entrySource = new ObservableTextEntrySource();

    @Override
    public void show() {
        super.show();
        processEnding();
    }

    public EntrySource<TextEntry> getEndingText() {
        return entrySource;
    }

    public void processEnding() {
        String resultText = "Somehow it all ended";

        switch (turnProcessor.getReachedEnding()) {
        case HIGH_BOREDOM: {
            resultText = "It was too boring to carry on.";
            break;
        }
        case HIGH_INSANITY: {
            resultText = "Look at me! Can it be? Who is this creature that I see? Free!..";
            break;
        }
        case HIGH_FAME: {
            resultText = "Now you are famous. Is that what you wanted?..";
            break;
        }
        case HUNGER: {
            resultText = "You can't survive on ideas alone, you know. Hunger can get the best of us.";
            break;
        }
        }

        TextEntry text = new CommonTextEntry(resultText);
        entrySource.setValue(ListUtils.buildList(text));
    }

    @Override
    protected Collection<Class<? extends AbstractStateSubView<?>>> getAutoEnabledSubViews() {
        return Collections.emptyList();
    }

}
