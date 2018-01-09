package online.laoliang.autoplatform.controller;

import online.laoliang.autoplatform.domain.TaskConfigureInfo;
import online.laoliang.autoplatform.service.FinalizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/finalize")
public class FinalizeController {

    @Autowired
    private FinalizeService finalizeService;

    @PostMapping(value = "/send_email")
    public String sendEmail(@RequestHeader String Authorization, @RequestBody TaskConfigureInfo taskConfigureInfo){
        return finalizeService.sendEmail(Authorization, taskConfigureInfo);
    }
}
