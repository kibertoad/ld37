package net.kiberion.psychobear.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import net.kiberion.swampmachine.spring.CommonLoaderConfiguration;
import net.kiberion.swampmachine.spring.CoreConfiguration;
import net.kiberion.swampmachine.spring.SwampmachineExtrasConfiguration;

@Configuration
@Import({ PsychoBearLoaderConfiguration.class, CommonLoaderConfiguration.class, SwampmachineExtrasConfiguration.class})
public class PsychoBearCoreConfiguration extends CoreConfiguration {

}
