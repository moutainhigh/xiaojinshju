package fengkongweishi.entity.userremainderlog;

import fengkongweishi.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huanghengkun
 * @date 2018/01/08
 */
public interface UserRemainderLogRepository extends JpaRepository<UserRemainderLog, Integer> {
    Page<UserRemainderLog> findByUserOrderByIdDesc(User user, Pageable page);
}
