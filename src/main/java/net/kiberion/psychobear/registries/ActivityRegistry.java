package net.kiberion.psychobear.registries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.Getter;
import net.kiberion.psychobear.model.PsychoBearActivity;
import net.kiberion.psychobear.model.PsychoBearGoods;
import net.kiberion.psychobear.model.PsychoBearVideo;
import net.kiberion.psychobear.model.global.PlayerModel;
import net.kiberion.psychobear.states.activity.subviews.ContentSubView;
import net.kiberion.psychobear.states.activity.subviews.FreelanceSubView;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;
import net.kiberion.swampmachine.api.common.Condition;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.gui.listenerconditions.ObservableIntCondition.ComparisonOperator;
import net.kiberion.swampmachine.utils.MapUtils;

@ImmutableRegistry
@Component
public class ActivityRegistry {

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private PlayerModel playerModel;

    @Getter
    private final Map<String, CommonModelEntityDescriptor> groups = new LinkedHashMap<>();

    @Getter
    private final Map<String, PsychoBearActivity> activities = new LinkedHashMap<>();

    @Getter
    private final Map<String, PsychoBearVideo> videos = new HashMap<>();

    @Getter
    private final Map<String, PsychoBearGoods> goods = new HashMap<>();

    public ActivityRegistry() {
        // activity groups
        //addNewGroup("programming", "Programming");
        addNewGroup("content", "Hobby");
        addNewGroup("freelancing", "Freelancing");
        //addNewGroup("websocial", "Web-social");
        addNewGroup("websurfing", "Websurfing");

        // activities
        List<Condition> conditions = new ArrayList<>();

        addNewActivity("videos", "Watch online videos", "websurfing", "programming", conditions);
        addNewActivity("shopping", "Shop online", "websurfing", "", conditions);
        // addNewActivity("darknet", "Surf the Darknet", "websurfing", "",
        // conditions);

        // addNewActivity("publishing", "Publish content", "websocial",
        // conditions);
        //addNewActivity("persona", "Social media persona", "websocial", "websocial", conditions);
        addNewActivity("cult", "Cult", "websocial", "philosophy", conditions);

        // addNewActivity("website", "Website", "programming", conditions);
        // addNewActivity("games", "Games", "programming", conditions);
        addNewActivity("botnet", "Botnet", "programming", "programming", conditions);
        // addNewActivity("ai", "AI", "programming", conditions);

        addNewActivity("poetry", "Write poetry", "content", PlayerModel.SKILL_WRITING, conditions);
        addNewActivity("games", "Program games", "content", PlayerModel.SKILL_PROGRAMMING, conditions);
        //addNewActivity("fanfiction", "Fanfiction", "content", PlayerModel.SKILL_WRITING, conditions);
        addNewActivity("webcomics", "Draw webcomics", "content", PlayerModel.SKILL_DRAWING, conditions);
        addNewActivity("record", "Record a video", "content", PlayerModel.SKILL_WEBSOCIAL, conditions);

        addNewActivity("freelance-programming", "Programming", "freelancing", PlayerModel.SKILL_PROGRAMMING,
                conditions);
        addNewActivity("freelance-art", "Art", "freelancing", PlayerModel.SKILL_DRAWING, conditions);
        addNewActivity("freelance-writing", "Copywriting", "freelancing", PlayerModel.SKILL_WRITING, conditions);
        addNewActivity("freelance-marketing", "Social media marketing", "freelancing", PlayerModel.SKILL_WEBSOCIAL,
                conditions);

        // videos
        addNewVideo("programmingDummies", "Programming for dummies", PlayerModel.SKILL_PROGRAMMING, 5,
                PlayerModel.STAT_INSANITY, 1);

        // addNewVideo("cookingDummies", "Cooking for dummies",
        // PlayerModel.SKILL_COOKING, 5, PlayerModel.STAT_BOREDOM, 2);

        addNewVideo("writingDummies", "Poetry for dummies", PlayerModel.SKILL_WRITING, 5, PlayerModel.STAT_INSPIRATION,
                2);

        addNewVideo("drawingDummies", "Drawing for dummies", PlayerModel.SKILL_DRAWING, 5, PlayerModel.STAT_INSPIRATION,
                4);

        addNewVideo("marketingDummies", "Marketing for dummies", PlayerModel.SKILL_WEBSOCIAL, 5,
                PlayerModel.STAT_BOREDOM, 2);

        addNewVideo("philosophyDummies", "Philosophy for dummies", PlayerModel.SKILL_PHILOSOPHY, 5,
                PlayerModel.STAT_INSANITY, 5, PlayerModel.STAT_INSPIRATION, 5);

        // goods

        addNewGoods("dumplings", "Frozen dumplings", 10, PlayerModel.STAT_HEALTH, -5, PlayerModel.STAT_SATIATION, 30);
        addNewGoods("pizza", "Frozen pizza", 20, PlayerModel.STAT_HEALTH, -10, PlayerModel.STAT_SATIATION, 70,
                PlayerModel.STAT_BOREDOM, -5);
        addNewGoods("spinach", "Spinach", 15, PlayerModel.STAT_HEALTH, 5, PlayerModel.STAT_SATIATION, 5);
        addNewGoods("lasagne", "Lasagne", 30, PlayerModel.STAT_HEALTH, 5, PlayerModel.STAT_SATIATION, 20,
                PlayerModel.STAT_INSANITY, -5);
        addNewGoods("whiskey", "Whiskey", 30, PlayerModel.STAT_HEALTH, 5, PlayerModel.STAT_INSANITY, 5,
                PlayerModel.STAT_INSPIRATION, 5, PlayerModel.STAT_BOREDOM, -5);

    }

    private void addNewVideo(String id, String name, String skillIncreased, Integer amountIncreased, Object... params) {
        Map<String, Integer> statChanges = MapUtils.buildMap(params);

        PsychoBearVideo video = new PsychoBearVideo(name, id, skillIncreased, amountIncreased, statChanges);
        videos.put(id, video);
    }

    private void addNewGoods(String id, String name, int price, Object... params) {
        Map<String, Integer> statChanges = MapUtils.buildMap(params);

        PsychoBearGoods newGoods = new PsychoBearGoods(name, id, price, statChanges);
        goods.put(id, newGoods);
    }

    private Condition createSkillCondition(String skillName, int skillValue, ComparisonOperator operator) {
        Condition condition = null;
        return condition;
    }

    private void addNewGroup(String id, String name) {
        groups.put(id, new CommonModelEntityDescriptor(name, id));
    }

    private void addNewActivity(String id, String name, String group, String skill, Collection<Condition> conditions) {
        PsychoBearActivity activity = new PsychoBearActivity(name, id, skill);
        activity.addTag(group);
        activity.setGroup(group);
        activity.getConditions().addAll(conditions);

        String viewName;
        if (group.equals("freelancing")) {
            viewName = FreelanceSubView.SUB_VIEW_ID;
        } else if (group.equals("content")) {
            viewName = ContentSubView.SUB_VIEW_ID;
        } else {
            viewName = "activity" + StringUtils.capitalize(id) + "View";
        }

        log.info("Binding view name: " + viewName);

        activity.setViewName(viewName);
        activities.put(id, activity);
    }

    public List<CommonModelEntityDescriptor> getGroupsWithTag(String tag) {
        return groups.values().parallelStream().filter((group) -> {
            return group.hasTag(tag);
        }).collect(Collectors.toList());
    }

    public List<CommonModelEntityDescriptor> getActivitiesWithTag(String tag) {
        return activities.values().parallelStream().filter((activity) -> {
            return activity.hasTag(tag);
        }).collect(Collectors.toList());
    }

}
