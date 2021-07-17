package com.pd.auth.config;

import com.pd.database.datasource.BaseMybatisConfiguration;
import com.pd.database.properties.DatabaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis相关配置
 *
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@Slf4j
@Configuration
public class AuthorityMybatisAutoConfiguration extends BaseMybatisConfiguration {
    public AuthorityMybatisAutoConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }
}
