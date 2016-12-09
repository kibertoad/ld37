package net.kiberion.psychobear.spring;

import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.HeadlessAssetManager;
import com.badlogic.gdx.assets.loaders.HeadlessGl;
import com.badlogic.gdx.backends.headless.HeadlessFiles;

import net.kiberion.psychobear.spring.PsychoBearCoreConfiguration;
import net.kiberion.swampmachine.assets.GameConfig;

@Configuration
public class TestConfiguration extends PsychoBearCoreConfiguration{

    public TestConfiguration() {
        GameConfig.config.setPathToResourcesAsString("src/main/resources/");
        Gdx.files = new HeadlessFiles();
        Gdx.gl = new HeadlessGl();
    }

    @Override
    protected AssetManager initAssetManager() {
        return new HeadlessAssetManager();
    }   
}
