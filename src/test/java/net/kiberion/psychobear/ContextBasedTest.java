package net.kiberion.psychobear;

import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.kiberion.psychobear.spring.TestConfiguration;
import net.kiberion.swampmachine.loaders.LoaderHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfiguration.class })
public abstract class ContextBasedTest implements ApplicationContextAware {

    protected ApplicationContext applicationContext;

    @Autowired
    protected LoaderHelper loaderHelper;

    protected void loadAssets() {
        loaderHelper.startLoading();
        loaderHelper.finishLoading();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
