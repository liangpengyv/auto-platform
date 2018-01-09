package online.laoliang.autoplatform.dao;

import online.laoliang.autoplatform.domain.TaskInfoCache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskInfoCacheRepository extends JpaRepository<TaskInfoCache, Integer> {
    TaskInfoCache findByApiToken(String s);
}
