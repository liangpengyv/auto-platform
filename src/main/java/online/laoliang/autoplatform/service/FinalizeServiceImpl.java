package online.laoliang.autoplatform.service;

import online.laoliang.autoplatform.dao.TaskInfoCacheRepository;
import online.laoliang.autoplatform.domain.TaskConfigureInfo;
import online.laoliang.autoplatform.properties.CurrentProperties;
import online.laoliang.autoplatform.util.CiTaskUtil;
import online.laoliang.autoplatform.util.UserVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalizeServiceImpl implements FinalizeService {

    final String INVALID_TOKEN = "无效的 Token！";

    @Autowired
    private TaskInfoCacheRepository taskInfoCacheRepository;

    @Autowired
    private CurrentProperties currentProperties;

    @Override
    public String sendEmail(String authorization, TaskConfigureInfo taskConfigureInfo) {
        if (!UserVerify.verifyProject(taskInfoCacheRepository, authorization, taskConfigureInfo)) {
            return INVALID_TOKEN;
        }

        return CiTaskUtil.executePythonScript(currentProperties, "send_email", new String[]{taskConfigureInfo.getEmailAddress(), taskConfigureInfo.getRepositoryName(), taskInfoCacheRepository.findByApiToken(authorization).getPreviewUrl()});
    }

}
