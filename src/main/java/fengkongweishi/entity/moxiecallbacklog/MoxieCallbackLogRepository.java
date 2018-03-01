package fengkongweishi.entity.moxiecallbacklog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author huanghengkun
 * @date 2018/01/19
 */
public interface MoxieCallbackLogRepository extends JpaRepository<MoxieCallbackLog, Integer> {
    List<MoxieCallbackLog> findByTaskId(String taskId);
}
