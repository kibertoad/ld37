package net.kiberion.psychobear.states.main.subviews;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.psychobear.model.global.GameModel;
import net.kiberion.psychobear.registries.ActivityRegistry;
import net.kiberion.psychobear.states.main.MainController;
import net.kiberion.psychobear.states.main.MainView;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.invokables.LambdaInvokable;
import net.kiberion.swampmachine.api.sources.EntrySource;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.providers.ClickableElementSourceProvider;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.mvcips.states.annotations.SubView;

@Component
@SubView(id = ActivitySubView.SUB_VIEW_ID, parentView = MainView.class)
@BoundCompositions(compositions = { "mainActivities" })
public class ActivitySubView extends AbstractStateSubView<GameModel>{

    public static final String SUB_VIEW_ID = "activitySubView";
    
    @Autowired
    @Getter
    private MainController controller;

    @Autowired
    private ActivityRegistry activityRegistry;

    private ClickableElementSourceProvider<CommonModelEntityDescriptor> activitySourceProvider;

    public void updateActivityList() {
        activitySourceProvider.fillButtonList();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        activitySourceProvider = new ClickableElementSourceProvider<CommonModelEntityDescriptor>() {

            @Override
            protected LambdaInvokable initOnClickEffect(CommonModelEntityDescriptor sourceElement) {
                return () -> {
                    controller.onActivitySelected(sourceElement.getId());
                    return null;
                };
            }

            @Override
            public Collection<CommonModelEntityDescriptor> getElementList() {
                return activityRegistry.getActivitiesWithTag(controller.getSelectedGroup());
            }

        };
    }

    public EntrySource<ButtonEntry> getActivityList() {
        return activitySourceProvider.getButtonSource();
    }

    @Override
    public void show() {
        super.show();
        updateActivityList();
    }
    
}
