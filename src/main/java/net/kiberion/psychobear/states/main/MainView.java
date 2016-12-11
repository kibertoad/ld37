package net.kiberion.psychobear.states.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kiberion.psychobear.model.global.GameModel;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.view.AbstractStateView;

@Component
@BoundCompositions(compositions = { "main" })
public class MainView extends AbstractStateView<GameModel>{

    @Autowired
    private ActivityGroupSubView activityGroupSubView;
    
    @Override
    public void show() {
        super.show();
        
        activityGroupSubView.show();
    }
    
}
