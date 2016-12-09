package net.kiberion.psychobear.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.kiberion.audio.spring.CommonAudioConfiguration;
import net.kiberion.swampmachine.gui.composer.CompositionLoader;
import net.kiberion.swampmachine.gui.composition.elements.ElementPrototypeRegistry;
import net.kiberion.swampmachine.gui.spring.CommonGuiConfiguration;
import net.kiberion.swampmachine.gui.spring.MahlerCoreConfiguration;
import net.kiberion.swampmachine.gui.spring.MahlerGdxConfiguration;
import net.kiberion.swampmachine.gui.spring.MahlerGroovyConfiguration;
import net.kiberion.swampmachine.gui.spring.MahlerPebbleConfiguration;
import net.kiberion.swampmachine.gui.templates.ElementTemplateLoader;
import net.kiberion.swampmachine.mvcips.spring.CommonMVCIPSConfiguration;

@Configuration
@ComponentScan({ "net.kiberion.psychobear" })
@Import({ PsychoBearCoreConfiguration.class, PsychoBearStatesConfiguration.class, CommonGuiConfiguration.class,
        CommonMVCIPSConfiguration.class, MahlerCoreConfiguration.class, MahlerGdxConfiguration.class,
        MahlerGroovyConfiguration.class, MahlerPebbleConfiguration.class, CommonAudioConfiguration.class })
public class PsychoBearFullConfiguration {

    @Bean
    public ElementPrototypeRegistry elementRegistry() {
        return new ElementPrototypeRegistry("net.kiberion.swampmachine.gui.elements");
    }

    @Bean
    public CompositionLoader compositionLoader() {
        return new CompositionLoader(300);
    }

    @Bean
    public ElementTemplateLoader elementTemplateLoader() {
        return new ElementTemplateLoader(200);
    }

}
