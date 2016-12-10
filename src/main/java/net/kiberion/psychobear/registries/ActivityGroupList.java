package net.kiberion.psychobear.registries;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.Getter;
import net.kiberion.swampmachine.annotations.ImmutableRegistry;
import net.kiberion.swampmachine.entities.common.impl.CommonModelEntityDescriptor;

@ImmutableRegistry
@Component
public class ActivityGroupList {

    @Getter
    private final Map<String, CommonModelEntityDescriptor> groups = new HashMap<>();

    public ActivityGroupList() {
        addNewGroup("programming", "Programming");
        addNewGroup("content", "Content creation");
        addNewGroup("freelancing", "Freelancing");
        addNewGroup("websocial", "Web-social");
        addNewGroup("websurfing", "Websurfing");
        addNewGroup("shopping", "Shopping");
    }
    
    private void addNewGroup (String id, String name) {
        groups.put(id, new CommonModelEntityDescriptor(name, id));
    }
    
    public List<CommonModelEntityDescriptor> getGroupsWithTag(String tag) {
        return groups.values().parallelStream().filter((group) -> {
            return group.hasTag(tag);
        }).collect(Collectors.toList());
    }
    
}
