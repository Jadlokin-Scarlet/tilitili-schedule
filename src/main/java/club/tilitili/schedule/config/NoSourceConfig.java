package club.tilitili.schedule.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "schedule.datasource.jdbcUrl", matchIfMissing = true)
@MapperScan("club.tilitili.schedule.mapper.mysql")
public class NoSourceConfig {
}
