package net.kiberion.psychobear;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import net.kiberion.multimedia.Music;
import net.kiberion.psychobear.spring.PsychoBearFullConfiguration;
import net.kiberion.swampmachine.mvcips.states.util.SpringGameApplication;

/**
 * The main game class for "Psychobear". 
 *
 * @author kibertoad
 */
public class Psychobear extends SpringGameApplication {

	private static final Logger log = LogManager.getLogger();
	
    @Override
    protected Class<?> getConfigurationClass() {
        return PsychoBearFullConfiguration.class;
    }
    
    @Override
    public void create() {
        super.create();

        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Music.instance().setEnabled(false);

        setScreen(getStateRegistry().getLoadingState());
    }
    
    /**
     * Entry point.
     * --development option forces regenerating atlases
     *
     * @param args the command line arguments
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException {

        Psychobear game = new Psychobear();

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.vSyncEnabled = true;
        cfg.width = 1280;
        cfg.height = 720;
        cfg.fullscreen = false;
        cfg.useGL30 = true;
        ShaderProgram.prependVertexCode = "#version 140\n#define varying out\n#define attribute in\n";
        ShaderProgram.prependFragmentCode = "#version 140\n#define varying in\n#define texture2D texture\n#define gl_FragColor fragColor\nout vec4 fragColor;\n";        

        LwjglApplication app = new LwjglApplication(game, cfg);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        log.debug("Do full resize");
    }
}
