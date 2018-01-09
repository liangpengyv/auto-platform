package online.laoliang.autoplatform.service;

import online.laoliang.autoplatform.domain.TaskConfigureInfo;

public interface FinalizeService {
    String sendEmail(String authorization, TaskConfigureInfo taskConfigureInfo);
}
