package fengkongweishi.entity.apisearchlog;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huanghengkun
 * @date 2018/01/08
 */
public interface APISearchLogRepository extends JpaRepository<APISearchLog, Integer> {
    APISearchLog findFirstByApicodeAndParametersOrderByCreateTimeDesc(String apicode, String parameters);

}
