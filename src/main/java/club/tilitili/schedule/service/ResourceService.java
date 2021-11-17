package club.tilitili.schedule.service;

import club.tilitili.schedule.entity.view.ResourceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Service
public class ResourceService {
    private final Map<String, Supplier<List<ResourceView>>> resourceMap = new HashMap<>();

    @Autowired
    public ResourceService(TilitiliJobService tilitiliJobService) {
        resourceMap.put("jobNameResource", tilitiliJobService::getJobNameResource);
    }

    public List<ResourceView> getResource(String resourceName) {
        return resourceMap.getOrDefault(resourceName, Collections::emptyList).get();
    }
}
