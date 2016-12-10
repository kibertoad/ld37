package net.kiberion.psychobear.states.main;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.psychobear.registries.ActivityGroupList;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.invokables.LambdaInvokable;
import net.kiberion.swampmachine.api.sources.EntrySource;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.gui.components.ClickableElementList;
import net.kiberion.swampmachine.mvcips.states.annotations.StateController;
import net.kiberion.swampmachine.mvcips.states.api.AbstractStateController;

@StateController
@Component
public class MainController extends AbstractStateController implements InitializingBean {

    private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private ActivityGroupList groupList;

    private ClickableElementList<CommonModelEntityDescriptor> elementList;

    public void updateTraitList() {
        elementList.fillButtonList();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        elementList = new ClickableElementList<CommonModelEntityDescriptor>() {

            @Override
            protected LambdaInvokable initOnClickEffect(CommonModelEntityDescriptor sourceElement) {
                return () -> {
                    onGroupSelected(sourceElement.getId());
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
        return elementList.getButtonSource();
    }

    @Override
    public void show() {
        updateTraitList();
    }

    public void onGroupSelected(String groupId) {
        log.info ("Group selected: "+groupId);
    }

}
