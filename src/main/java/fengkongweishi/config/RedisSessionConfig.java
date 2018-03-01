package fengkongweishi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author huanghengkun
 * @date 2018/1/8
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 186400)
public class RedisSessionConfig {

}
