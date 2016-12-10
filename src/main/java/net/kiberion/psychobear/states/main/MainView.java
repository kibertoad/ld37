package net.kiberion.psychobear.states.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.view.AbstractStateView;

@Component
@BoundCompositions(compositions = { "main" })
public class MainView extends AbstractStateView<GameModel>{

    @Autowired
    @Getter
    private MainController controller;
    
    @Override
    public void postInjection() {
        super.postInjection();
    }
    
}
