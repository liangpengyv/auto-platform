package online.laoliang.autoplatform.controller;

import online.laoliang.autoplatform.domain.TaskConfigureInfo;
import online.laoliang.autoplatform.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriggerController {

    @Autowired
    private TriggerService triggerService;

    @PostMapping(value = "/trigger")
    public String trigger(@RequestBody TaskConfigureInfo taskConfigureInfo) {
        String apiToken = triggerService.createApiToken(taskConfigureInfo);
        return apiToken;
    }
}
