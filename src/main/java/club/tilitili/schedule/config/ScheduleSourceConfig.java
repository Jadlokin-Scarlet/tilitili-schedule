package club.tilitili.schedule.config;

import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@MapperScan(basePackages = {"club.tilitili.schedule.mapper.schedule"}, sqlSessionTemplateRef  = "scheduleSqlSessionTemplate")
public class ScheduleSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "schedule.datasource")
    @Qualifier("scheduleDatasource")
    public DataSource scheduleDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Qualifier("scheduleSqlSessionFactory")
    public SqlSessionFactory scheduleSqlSessionFactory(@Qualifier("scheduleDataSource") DataSource dataSource) throws Exception {
        VFS.addImplClass(SpringBootVFS.class);
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        List<Resource> list = new ArrayList<>();
        list.addAll(Arrays.asList(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/schedule/*.xml")));
        list.addAll(Arrays.asList(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/schedule/automapper/*.xml")));
        bean.setMapperLocations(list.toArray(new Resource[]{}));
        bean.setTypeAliasesPackage("club.tilitili.schedule.entity");

        SqlSessionFactory sqlSessionFactory = bean.getObject();
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory;
    }

    @Bean
    public DataSourceTransactionManager scheduleTransactionManager(@Qualifier("scheduleDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Qualifier("scheduleSqlSessionTemplate")
    public SqlSessionTemplate scheduleSqlSessionTemplate( @Qualifier("scheduleSqlSessionFactory") SqlSessionFactory scheduleSqlSessionFactory) {
        return new SqlSessionTemplate(scheduleSqlSessionFactory);
    }
}
