package online.laoliang.autoplatform.controller;

import online.laoliang.autoplatform.domain.TaskConfigureInfo;
import online.laoliang.autoplatform.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/")
    public String say() {
        return "Hello World";
    }

    @PostMapping(value = "/test")
    public String test(@RequestBody TaskConfigureInfo taskConfigureInfo) {
        return testService.test(taskConfigureInfo);
    }

}
