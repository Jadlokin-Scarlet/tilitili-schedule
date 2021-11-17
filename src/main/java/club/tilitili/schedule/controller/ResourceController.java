package club.tilitili.schedule.controller;

import club.tilitili.schedule.entity.BaseModel;
import club.tilitili.schedule.entity.view.ResourceView;
import club.tilitili.schedule.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("api/resources")
public class ResourceController extends BaseController {
    private final ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("")
    @ResponseBody
    public BaseModel<?> getResources(@RequestParam List<String> needResourcesList) {
        HashMap<String, List<ResourceView>> resourceMap = new HashMap<>();
        needResourcesList.forEach(
                resourceName -> resourceMap.put(resourceName, resourceService.getResource(resourceName))
        );
        return new BaseModel<>("查询成功", true, resourceMap);
    }
}
