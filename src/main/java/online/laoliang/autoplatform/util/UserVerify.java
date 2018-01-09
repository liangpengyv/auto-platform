package online.laoliang.autoplatform.util;

import online.laoliang.autoplatform.dao.TaskInfoCacheRepository;
import online.laoliang.autoplatform.domain.TaskConfigureInfo;

import java.io.UnsupportedEncodingException;

public class UserVerify {

    /**
     * 验证 ApiToken
     *
     * @param apiToken
     * @param taskConfigureInfo
     * @return
     */
    public static Boolean verifyProject(TaskInfoCacheRepository apiTokenRepository, String apiToken, TaskConfigureInfo taskConfigureInfo) {
        // 通过查看数据库中是否存在此 ApiToken，判断该项目是否注册过
        if (apiTokenRepository.findByApiToken(apiToken) == null) {
            return false;
        }

        // 判断 ApiToken 与传入的项目配置信息是否吻合
        String repositoryUrl = null;
        try {
            // 解码传入的 ApiToken 得到 Git 仓库地址，并与传入的任务配置参数中的数据进行对比
            repositoryUrl = Base64Codec.decoder(apiToken);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!taskConfigureInfo.getRepositoryUrl().equals(repositoryUrl)) {
            return false;
        }

        return true;
    }
}
