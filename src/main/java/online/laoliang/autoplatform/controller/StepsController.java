package online.laoliang.autoplatform.controller;

import online.laoliang.autoplatform.domain.TaskConfigureInfo;
import online.laoliang.autoplatform.service.StepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/steps")
public class StepsController {

    @Autowired
    private StepsService stepsService;

    @PostMapping(value = "/init")
    public String init(@RequestHeader String Authorization, @RequestBody TaskConfigureInfo taskConfigureInfo) {
        return stepsService.init(Authorization, taskConfigureInfo);
    }

    @PostMapping(value = "/git_clone")
    public String gitClone(@RequestHeader String Authorization, @RequestBody TaskConfigureInfo taskConfigureInfo) {
        return stepsService.gitClone(Authorization, taskConfigureInfo);
    }

    @PostMapping(value = "/install")
    public String install(@RequestHeader String Authorization, @RequestBody TaskConfigureInfo taskConfigureInfo) {
        return stepsService.install(Authorization, taskConfigureInfo);
    }

    @PostMapping(value = "/unit_test")
    public String unitTest(@RequestHeader String Authorization, @RequestBody TaskConfigureInfo taskConfigureInfo) {
        return stepsService.unitTest(Authorization, taskConfigureInfo);
    }

    @PostMapping(value = "/assemble")
    public String assemble(@RequestHeader String Authorization, @RequestBody TaskConfigureInfo taskConfigureInfo) {
        return stepsService.assemble(Authorization, taskConfigureInfo);
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestHeader String Authorization, @RequestBody TaskConfigureInfo taskConfigureInfo) {
        return stepsService.upload(Authorization, taskConfigureInfo);
    }
}
