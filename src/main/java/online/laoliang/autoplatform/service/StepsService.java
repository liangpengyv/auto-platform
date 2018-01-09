package online.laoliang.autoplatform.service;

import online.laoliang.autoplatform.domain.TaskConfigureInfo;

public interface StepsService {
    String init(String authorization, TaskConfigureInfo taskConfigureInfo);

    String gitClone(String authorization, TaskConfigureInfo taskConfigureInfo);

    String install(String authorization, TaskConfigureInfo taskConfigureInfo);

    String unitTest(String authorization, TaskConfigureInfo taskConfigureInfo);

    String assemble(String authorization, TaskConfigureInfo taskConfigureInfo);

    String upload(String authorization, TaskConfigureInfo taskConfigureInfo);
}
