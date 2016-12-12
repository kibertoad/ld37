package net.kiberion.psychobear.states.activity.subviews;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.psychobear.events.StatsChangedEvent;
import net.kiberion.psychobear.model.PsychoBearGoods;
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

@Component
@SubView(id = ShoppingSubView.SUB_VIEW_ID, parentViews = { ProcessActivityView.class })
@BoundCompositions(compositions = { "activity-shopping" })
public class ShoppingSubView extends AbstractStateSubView<GameModel> {

    private static final Logger log = LogManager.getLogger();

    public static final String SUB_VIEW_ID = "activityShoppingView";

    private final ObservableButtonEntrySource entrySource = new ObservableButtonEntrySource();

    @Autowired
    private ActivityRegistry registry;

    @Autowired
    private ProcessActivityController controller;

    @Autowired
    private PlayerModel playerModel;

    public EntrySource<ButtonEntry> getGoodsList() {
        return entrySource;
    }

    protected void generateGoodsList() {
        log.info("Get goods");
        List<ButtonEntry> buttons = new ArrayList<>();

        for (PsychoBearGoods goods : registry.getGoods().values()) {
            CommonButtonEntry button = new CommonButtonEntry();
            button.setText(goods.getName() + " ($" + goods.getPrice() + ")");
            LambdaInvokable onClickEffect = () -> {

                if (playerModel.getCash() >= goods.getPrice()) {
                    playerModel.changeStatsFromMap(goods.getStatChanges());
                    controller.activityFinished();
                    playerModel.changeStat(PlayerModel.STAT_CASH, goods.getPrice() * -1);
                    getEventPublisher().publishEvent(new StatsChangedEvent(this));
                }
                return null;
            };

            button.setOnClickEffect(onClickEffect);
            buttons.add(button);
        }

        // Add "nothing" option
        CommonButtonEntry button = new CommonButtonEntry();
        button.setText("Nothing");
        LambdaInvokable onClickEffect = () -> {
            controller.activityFinished();
            return null;
        };
        button.setOnClickEffect(onClickEffect);
        buttons.add(button);

        entrySource.setValue(buttons);

    }

    @Override
    public void show() {
        super.show();
        generateGoodsList();
    }

}
