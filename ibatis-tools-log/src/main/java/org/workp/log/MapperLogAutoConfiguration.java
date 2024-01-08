package org.workp.log;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.workp.log.prop.MapperLogProperties;

import java.util.List;

@Configuration
@EnableConfigurationProperties(MapperLogProperties.class)
@ConditionalOnBean(SqlSessionFactory.class)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@Lazy(false)
public class MapperLogAutoConfiguration implements InitializingBean {
    public static final Logger log = LoggerFactory.getLogger(MapperLogAutoConfiguration.class);
    private final Environment environment;
    private final List<SqlSessionFactory> sqlSessionFactoryList;
    private final MapperLogProperties mapperLogProperties;


    public MapperLogAutoConfiguration(List<SqlSessionFactory> sqlSessionFactoryList, MapperLogProperties mapperLogProperties, Environment environment) {
        this.sqlSessionFactoryList = sqlSessionFactoryList;
        this.environment = environment;
        this.mapperLogProperties = mapperLogProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!mapperLogProperties.isEnabled()) {
            log.info("为启动mapperlog, 不进行任何配置");
            return;
        }

        log.info("[框架注入]MapperLogAutoConfiguration start");
        MapperLogInterceptor mapperLogInterceptor = new MapperLogInterceptor();
        List<String> activeEnv = mapperLogProperties.getActiveEnv();
        String activeProfile;
        if (environment != null && environment.getActiveProfiles() != null && environment.getActiveProfiles().length > 0) {
            activeProfile = environment.getActiveProfiles()[0];
        } else {
            activeProfile = "";
        }
        log.info("[框架配置]mapperlog.activeEnv = {}", activeEnv);
        log.info("[默认环境]当前环境 = {}", activeProfile);
        boolean addInterceptor = false;
        if (activeEnv == null || activeEnv.size() == 0) {
            // 默认配置
            addInterceptor = true;
        } else if (activeProfile == null || activeProfile.trim().length() == 0) {
            // 默认配置
            addInterceptor = true;
        } else {
            addInterceptor = activeEnv.contains(activeProfile);
        }
        log.info("[框架配置]是否注入：{}", addInterceptor ? "是" : "否");
        if (addInterceptor) {
            log.info("[框架注入]注入自定义mybatis日志解析器");
            for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
                sqlSessionFactory.getConfiguration().addInterceptor(mapperLogInterceptor);
            }
        }
    }
}
