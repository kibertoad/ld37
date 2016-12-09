package net.kiberion.psychobear.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.kiberion.psychobear.model.global.AcademagiaTrait;
import net.kiberion.swampmachine.blueprints.common.loaders.TraitLoader;
import net.kiberion.swampmachine.blueprints.common.registries.TraitRegistry;
import net.kiberion.swampmachine.loaders.AbstractLoader;
import net.kiberion.swampmachine.spring.CoreConfiguration;

@Configuration
public class PsychoBearLoaderConfiguration extends CoreConfiguration {

    @Bean
    public TraitRegistry<AcademagiaTrait> traitRegistry () {
        return new TraitRegistry<>();
    }
    
    @Bean
    public AbstractLoader traitLoader() {
        return new TraitLoader<> (AcademagiaTrait.class);
    }
   
}
