package net.kiberion.psychobear.states.main.subviews;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.psychobear.model.global.GameModel;
import net.kiberion.psychobear.registries.ActivityRegistry;
import net.kiberion.psychobear.states.main.MainController;
import net.kiberion.psychobear.states.main.MainView;
import net.kiberion.swampmachine.annotations.SubView;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.invokables.LambdaInvokable;
import net.kiberion.swampmachine.api.sources.EntrySource;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.providers.ClickableElementSourceProvider;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;

@Component
@SubView(id = ActivityGroupSubView.SUB_VIEW_ID, parentViews = {MainView.class})
@BoundCompositions(compositions = { "mainActivityGroups" })
public class ActivityGroupSubView extends AbstractStateSubView<GameModel>{

    public static final String SUB_VIEW_ID = "activityGroupSubView";
    
    @Autowired
    @Getter
    private MainController controller;

    @Autowired
    private ActivityRegistry activityRegistry;

    private ClickableElementSourceProvider<CommonModelEntityDescriptor> activityGroupSourceProvider;

    public void updateActivityGroupList() {
        activityGroupSourceProvider.fillButtonList();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        activityGroupSourceProvider = new ClickableElementSourceProvider<CommonModelEntityDescriptor>() {

            @Override
            protected LambdaInvokable initOnClickEffect(CommonModelEntityDescriptor sourceElement) {
                return () -> {
                    controller.onGroupSelected(sourceElement.getId());
                    return null;
                };
            }

            @Override
            public Collection<CommonModelEntityDescriptor> getElementList() {
                return activityRegistry.getGroups().values();
            }

        };
    }

    public EntrySource<ButtonEntry> getActivityGroupList() {
        return activityGroupSourceProvider.getButtonSource();
    }

    @Override
    public void show() {
        super.show();
        updateActivityGroupList();
    }
    
}
