package net.kiberion.psychobear.styles;

import org.springframework.beans.factory.annotation.Autowired;

import net.kiberion.swampmachine.assets.loaders.api.SyncLoader;
import net.kiberion.swampmachine.assets.util.LoadBeforeStartup;
import net.kiberion.swampmachine.styling.ColourGenerator;
import net.kiberion.swampmachine.styling.StyleFactory;

/**
 * @author kibertoad
 */
@LoadBeforeStartup
public class StyleInitter implements SyncLoader {

    @Autowired
    private StyleFactory styleFactory;
    
    
    @Override
    public void load() {
        ColourGenerator colourGenerator = ColourGenerator.instance();

        styleFactory.addButtonFontStyle("default", "DroidSans", 17);
        styleFactory.addButtonFontStyle("disabled", "DroidSans", 17);

        styleFactory.setButtonStyleColour("default", colourGenerator.getColour("white"));
        styleFactory.setButtonStyleColour("disabled", colourGenerator.getColour("gray"));

        /*
         * styleFactory.addLabelFontStyle(VNWindow.VN_TEXT_STYLE, "DroidSans",
         * 32); styleFactory.setLabelStyleColour(VNWindow.VN_TEXT_STYLE,
         * colourGenerator.getColour("white"));
         * 
         * styleFactory.addButtonFontStyle(VNWindow.VN_CHOICE_STYLE,
         * "DroidSans", 28, UiManager.instance().transparentSkin());
         * styleFactory.setButtonStyleColour(VNWindow.VN_CHOICE_STYLE,
         * colourGenerator.getColour("white"));
         * 
         * styleFactory.addButtonFontStyle(VNWindow.VN_CHOICE_STYLE_HOVERED,
         * "DroidSans", 28, UiManager.instance().transparentSkin());
         * styleFactory.setButtonStyleColour(VNWindow.VN_CHOICE_STYLE_HOVERED,
         * colourGenerator.getColour("transientgray"));
         * 
         * styleFactory.addWindowFontStyle(VNWindow.VN_TEXT_STYLE,
         * UiManager.instance().transparentSkin());
         */
    }
}
