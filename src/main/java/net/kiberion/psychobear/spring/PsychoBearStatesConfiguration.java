package net.kiberion.psychobear.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.kiberion.swampmachine.mvcips.states.CommonLoadingModel;
import net.kiberion.swampmachine.mvcips.states.CommonLoadingState;
import net.kiberion.swampmachine.mvcips.states.CommonLoadingView;

@Configuration
@ComponentScan({ "net.kiberion.states" })
public class PsychoBearStatesConfiguration {

    @Bean
    public CommonLoadingState loadingState() {
        return new CommonLoadingState();
    }
    
    @Bean
    public CommonLoadingView loadingView() {
        return new CommonLoadingView();
    }
    
    @Bean
    public CommonLoadingModel loadingModel() {
        return new CommonLoadingModel();
    }
    

}
