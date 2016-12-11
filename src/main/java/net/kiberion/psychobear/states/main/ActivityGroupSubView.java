package net.kiberion.psychobear.states.main;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.psychobear.model.global.GameModel;
import net.kiberion.psychobear.registries.ActivityRegistry;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.invokables.LambdaInvokable;
import net.kiberion.swampmachine.api.sources.EntrySource;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.gui.providers.ClickableElementSourceProvider;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;
import net.kiberion.swampmachine.mvcips.states.annotations.SubView;

@Component
@SubView(id = ActivityGroupSubView.ACTIVITY_GROUP_SUB_VIEW_ID, parentView = MainView.class)
public class ActivityGroupSubView extends AbstractStateSubView<GameModel>{

    public static final String ACTIVITY_GROUP_SUB_VIEW_ID = "activityGroupSubView";
    
    @Autowired
    @Getter
    private MainController controller;

    @Autowired
    private ActivityRegistry groupList;

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
                return groupList.getGroups().values();
            }

        };
    }

    public EntrySource<ButtonEntry> getActivityGroupList() {
        return activityGroupSourceProvider.getButtonSource();
    }

    @Override
    public void show() {
        updateActivityGroupList();
    }
    
}
