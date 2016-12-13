package net.kiberion.psychobear.states.main.subviews;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.common.collect.ImmutableMap;

import lombok.Getter;
import net.kiberion.psychobear.model.PsychoBearActivity;
import net.kiberion.psychobear.model.global.PlayerModel;
import net.kiberion.psychobear.registries.ActivityRegistry;
import net.kiberion.psychobear.states.main.MainController;
import net.kiberion.psychobear.states.main.MainController.DayTime;
import net.kiberion.psychobear.states.main.MainView;
import net.kiberion.swampmachine.annotations.SubView;
import net.kiberion.swampmachine.api.elements.ButtonEntry;
import net.kiberion.swampmachine.api.invokables.LambdaInvokable;
import net.kiberion.swampmachine.api.sources.EntrySource;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.gui.annotations.Bound;
import net.kiberion.swampmachine.gui.annotations.BoundCompositions;
import net.kiberion.swampmachine.gui.elements.SwampTextButton;
import net.kiberion.swampmachine.gui.providers.ClickableElementSourceProvider;
import net.kiberion.swampmachine.gui.view.AbstractStateSubView;

@Component
@SubView(id = ScheduleSubView.SUB_VIEW_ID, parentViews = {MainView.class})
@BoundCompositions(compositions = { "mainSchedule" })
public class ScheduleSubView extends AbstractStateSubView<PlayerModel> {

    public static final String SUB_VIEW_ID = "scheduleSubView";

    private final Map<DayTime, CommonModelEntityDescriptor> dayTimeDescriptors = ImmutableMap
            .<DayTime, CommonModelEntityDescriptor> builder()
            .put(DayTime.DAY, new CommonModelEntityDescriptor("Day", DayTime.DAY.name()))
//            .put(DayTime.EVENING, new CommonModelEntityDescriptor("Evening", DayTime.EVENING.name()))
            .put(DayTime.NIGHT, new CommonModelEntityDescriptor("Night", DayTime.NIGHT.name()))
            .build();

    @Autowired
    @Getter
    private MainController controller;

    @Autowired
    private ActivityRegistry activityRegistry;

    @Bound(id = "nextTurn")
    private SwampTextButton nextTurn;

    private ClickableElementSourceProvider<CommonModelEntityDescriptor> activityGroupSourceProvider;

    public void updateActivityGroupList() {
        activityGroupSourceProvider.fillButtonList();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        activityGroupSourceProvider = new ClickableElementSourceProvider<CommonModelEntityDescriptor>() {

            @Override
            protected LambdaInvokable initOnClickEffect(CommonModelEntityDescriptor sourceElement) {
                return () -> {
                    controller.onDayTimeSelected((DayTime.valueOf(sourceElement.getId())));
                    return null;
                };
            }

            @Override
            public Collection<CommonModelEntityDescriptor> getElementList() {
                return dayTimeDescriptors.values();
            }

            @Override
            protected String initButtonText(CommonModelEntityDescriptor sourceElement) {
                PsychoBearActivity activity = controller.getActivity(DayTime.valueOf(sourceElement.getId()));
                StringBuilder text = new StringBuilder();
                text.append(sourceElement.getName());
                if (activity != null) {
                    CommonModelEntityDescriptor group = activityRegistry.getGroups().get(activity.getGroup());
                    Validate.notNull(group, "Unknown group: " + activity.getGroup());
                    text.append(": ").append(group.getName()).append(" - ").append(activity.getName());
                }
                return text.toString();
            }

        };
    }

    public EntrySource<ButtonEntry> getSchedule() {
        return activityGroupSourceProvider.getButtonSource();
    }

    @Override
    public void show() {
        super.show();
        updateActivityGroupList();

        boolean isAllActivitiesSelected = controller.isAllActivitiesSelected();
        //nextTurn.setDisabled(!isAllActivitiesSelected);
        //nextTurn.setVisible(isAllActivitiesSelected);
    }

    private boolean fire_once = true;
    private Animation animation;
    private float stateTime = 0.0f;
    private SpriteBatch spriteBatch;

    /*
    @Override
    public void render() {
        if (fire_once) {
            fire_once = false;

            animation = getAnimation("hacking/hacking.", 10, 0.04f);
            spriteBatch = new SpriteBatch();
        }
        stateTime += Gdx.graphics.getDeltaTime();
        spriteBatch.begin();
        spriteBatch.draw(animation.getKeyFrame(stateTime, true), 50, 50);
        spriteBatch.end();
    }
    */

    private final String PATH_PREFIX = "ani/";
    private final String PATH_SUFFIX = ".jpg";

    public Animation getAnimation(String image, int frameCount, float frameDuration) {
        TextureRegion[] frames = new TextureRegion[frameCount];

        for (int i = 0; i < frameCount; ++i) {
            frames[i] =
                new TextureRegion(
                    new Texture(
                        Gdx.files.internal(
                            PATH_PREFIX+image+String.format("%04d", i)+PATH_SUFFIX
                        )
                    )
                );
        }

        Animation animation = new Animation(frameDuration, frames);
        return animation;
    }

}
