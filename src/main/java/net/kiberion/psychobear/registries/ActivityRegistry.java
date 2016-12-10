package net.kiberion.psychobear.registries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.psychobear.model.PsychoBearActivity;
import net.kiberion.psychobear.model.global.PlayerModel;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;
import net.kiberion.swampmachine.api.common.Condition;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;
import net.kiberion.swampmachine.gui.listenerconditions.ObservableIntCondition.ComparisonOperator;

@ImmutableRegistry
@Component
public class ActivityRegistry {

    @Autowired
    private PlayerModel playerModel;
    
    @Getter
    private final Map<String, CommonModelEntityDescriptor> groups = new HashMap<>();
    
    private final Map<String, PsychoBearActivity> activities = new HashMap<>();

    public ActivityRegistry() {
        addNewGroup("programming", "Programming");
        addNewGroup("content", "Content creation");
        addNewGroup("freelancing", "Freelancing");
        addNewGroup("websocial", "Web-social");
        addNewGroup("websurfing", "Websurfing");
        
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
        activity.getConditions().addAll(conditions);
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
