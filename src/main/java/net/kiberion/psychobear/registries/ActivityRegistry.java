package net.kiberion.psychobear.registries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import net.kiberion.psychobear.model.PsychoBearVideo;
import net.kiberion.psychobear.model.global.PlayerModel;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;
import net.kiberion.swampmachine.api.common.Condition;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.gui.listenerconditions.ObservableIntCondition.ComparisonOperator;

@ImmutableRegistry
@Component
public class ActivityRegistry {

    private static final Logger log = LogManager.getLogger();
    
    @Autowired
    private PlayerModel playerModel;
    
    @Getter
    private final Map<String, CommonModelEntityDescriptor> groups = new HashMap<>();
    
    @Getter
    private final Map<String, PsychoBearActivity> activities = new HashMap<>();

    @Getter
    private final Map<String, PsychoBearVideo> videos = new HashMap<>();

    public ActivityRegistry() {
        //activity groups
        addNewGroup("programming", "Programming");
        addNewGroup("content", "Content creation");
        addNewGroup("freelancing", "Freelancing");
        addNewGroup("websocial", "Web-social");
        addNewGroup("websurfing", "Websurfing");
        
        
        
        //activities
        List<Condition> conditions = new ArrayList<>();
        
        addNewActivity("videos", "Watch online videos", "websurfing", conditions);
        addNewActivity("shopping", "Shop online", "websurfing", conditions);
        addNewActivity("darknet", "Surf the Darknet", "websurfing", conditions);
        
        addNewActivity("publishing", "Publish content", "websocial", conditions);
        addNewActivity("persona", "Social media persona", "websocial", conditions);
        addNewActivity("cult", "Cult", "websocial", conditions);

        addNewActivity("website", "Website", "programming", conditions);
        addNewActivity("games", "Games", "programming", conditions);
        addNewActivity("botnet", "Botnet", "programming", conditions);
        addNewActivity("ai", "AI", "programming", conditions);
        
        addNewActivity("poetry", "Poetry", "content", conditions);
        addNewActivity("fanfiction", "Fanfiction", "content", conditions);
        addNewActivity("webcomics", "Webcomics", "content", conditions);
        addNewActivity("record", "Video", "content", conditions);
        
        addNewActivity("freelance-programming", "Programming", "freelancing", conditions);
        addNewActivity("freelance-art", "Art", "freelancing", conditions);
        addNewActivity("freelance-writing", "Copywriting", "freelancing", conditions);
        addNewActivity("freelance-marketing", "Social media marketing", "freelancing", conditions);
        
        
        //videos
        addNewVideo("programmingDummies", "Programming for dummies", "programming", 5);
        addNewVideo("cookingDummies", "Cooking for dummies", "cooking", 5);
        addNewVideo("writingDummies", "Poetry for dummies", "poetry", 5);
        addNewVideo("drawingDummies", "Drawing for dummies", "drawing", 5);
        addNewVideo("marketingDummies", "Marketing for dummies", "marketing", 5);
        addNewVideo("philosophyDummies", "Philosophy for dummies", "philosophy", 5);
    }
    

    private void addNewVideo (String id, String name, String skill, int skillIncrease) {
        PsychoBearVideo video = new PsychoBearVideo(id, name, skill, skillIncrease);
        videos.put(id, video);
    }
    
    private Condition createSkillCondition (String skillName, int skillValue, ComparisonOperator operator) {
        Condition condition = null;
        return condition;
    }
    
    private void addNewGroup (String id, String name) {
        groups.put(id, new CommonModelEntityDescriptor(name, id));
    }
    
    private void addNewActivity (String id, String name, String group, Collection<Condition> conditions) {
        PsychoBearActivity activity = new PsychoBearActivity(name, id);
        activity.addTag(group);
        activity.setGroup(group);
        activity.getConditions().addAll(conditions);
        
        String viewName = "activity"+StringUtils.capitalize(id)+"View";
        log.info("Binding view name: "+viewName);
        
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
