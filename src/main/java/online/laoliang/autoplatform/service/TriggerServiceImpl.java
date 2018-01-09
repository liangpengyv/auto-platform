package online.laoliang.autoplatform.service;

import online.laoliang.autoplatform.dao.TaskInfoCacheRepository;
import online.laoliang.autoplatform.domain.TaskInfoCache;
import online.laoliang.autoplatform.domain.TaskConfigureInfo;
import online.laoliang.autoplatform.util.Base64Codec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class TriggerServiceImpl implements TriggerService{

    @Autowired
    private TaskInfoCache taskInfoCache;

    @Autowired
    private TaskInfoCacheRepository taskInfoCacheRepository;

    @Override
    public String createApiToken(TaskConfigureInfo taskConfigureInfo) {
        String tokenStr = null;
        try {
            // 使用Git仓库地址进行Base64编码，用作该项目下次访问的Token
            tokenStr = Base64Codec.encoder(taskConfigureInfo.getRepositoryUrl());
            // ApiToken 存库
            taskInfoCache.setApiToken(tokenStr);
            taskInfoCacheRepository.save(taskInfoCache);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return tokenStr;
    }
}
