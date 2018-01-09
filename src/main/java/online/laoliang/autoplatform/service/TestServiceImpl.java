package online.laoliang.autoplatform.service;

import online.laoliang.autoplatform.domain.TaskConfigureInfo;
import online.laoliang.autoplatform.properties.CurrentProperties;
import online.laoliang.autoplatform.util.CiTaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private CurrentProperties currentProperties;

    @Override
    public String test(TaskConfigureInfo taskConfigureInfo) {
        return CiTaskUtil.executePythonScript(currentProperties, "test", null);
    }
}
