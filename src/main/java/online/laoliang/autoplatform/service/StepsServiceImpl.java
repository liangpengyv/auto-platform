package online.laoliang.autoplatform.service;

import online.laoliang.autoplatform.dao.TaskInfoCacheRepository;
import online.laoliang.autoplatform.domain.TaskConfigureInfo;
import online.laoliang.autoplatform.domain.TaskInfoCache;
import online.laoliang.autoplatform.properties.CurrentProperties;
import online.laoliang.autoplatform.util.CiTaskUtil;
import online.laoliang.autoplatform.util.UserVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StepsServiceImpl implements StepsService {

    final String INVALID_TOKEN = "无效的 Token！";

    @Autowired
    private TaskInfoCacheRepository taskInfoCacheRepository;

    @Autowired
    private CurrentProperties currentProperties;

    @Override
    public String init(String authorization, TaskConfigureInfo taskConfigureInfo) {
        if (!UserVerify.verifyProject(taskInfoCacheRepository, authorization, taskConfigureInfo)) {
            return INVALID_TOKEN;
        }

        return CiTaskUtil.executePythonScript(currentProperties, "init", new String[]{taskConfigureInfo.getRepositoryName()});
    }

    @Override
    public String gitClone(String authorization, TaskConfigureInfo taskConfigureInfo) {
        if (!UserVerify.verifyProject(taskInfoCacheRepository, authorization, taskConfigureInfo)) {
            return INVALID_TOKEN;
        }

        return CiTaskUtil.executePythonScript(currentProperties, "git_clone", new String[]{taskConfigureInfo.getRepositoryName(), taskConfigureInfo.getRepositoryUrl(), taskConfigureInfo.getTargetBranch()});
    }

    @Override
    public String install(String authorization, TaskConfigureInfo taskConfigureInfo) {
        if (!UserVerify.verifyProject(taskInfoCacheRepository, authorization, taskConfigureInfo)) {
            return INVALID_TOKEN;
        }

        return CiTaskUtil.executePythonScript(currentProperties, "install", new String[]{taskConfigureInfo.getRepositoryName()});
    }

    @Override
    public String unitTest(String authorization, TaskConfigureInfo taskConfigureInfo) {
        if (!UserVerify.verifyProject(taskInfoCacheRepository, authorization, taskConfigureInfo)) {
            return INVALID_TOKEN;
        }

        return CiTaskUtil.executePythonScript(currentProperties, "unit_test", new String[]{taskConfigureInfo.getRepositoryName()});
    }

    @Override
    public String assemble(String authorization, TaskConfigureInfo taskConfigureInfo) {
        if (!UserVerify.verifyProject(taskInfoCacheRepository, authorization, taskConfigureInfo)) {
            return INVALID_TOKEN;
        }

        return CiTaskUtil.executePythonScript(currentProperties, "assemble", new String[]{taskConfigureInfo.getRepositoryName()});
    }

    @Override
    public String upload(String authorization, TaskConfigureInfo taskConfigureInfo) {
        if (!UserVerify.verifyProject(taskInfoCacheRepository, authorization, taskConfigureInfo)) {
            return INVALID_TOKEN;
        }

        String result = CiTaskUtil.executePythonScript(currentProperties, "upload", new String[]{taskConfigureInfo.getProjectType(), taskConfigureInfo.getBundleId(), taskConfigureInfo.getFirToken(), taskConfigureInfo.getChangeLog(), taskConfigureInfo.getRepositoryName()});

        // 将当前项目工作流的数据产物（预览Url、下载Url）更新到数据库
        if (result.charAt(result.length() - 1) == '0') {
            String[] strs = result.split("\n");
            String previewUrl = strs[1].split(" ")[2];
            String downloadUrl = strs[2].split(" ")[2];

            TaskInfoCache taskInfoCache = taskInfoCacheRepository.findByApiToken(authorization);
            taskInfoCache.setPreviewUrl(previewUrl);
            taskInfoCache.setDownloadUrl(downloadUrl);
            taskInfoCacheRepository.save(taskInfoCache);
        }

        return result;
    }

}
