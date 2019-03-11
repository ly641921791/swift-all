package com.swift.spring.boot.autoconfigure;

import com.swift.custom.mapper.method.Delete;
import com.swift.custom.mapper.method.DeleteByColumn;
import com.swift.custom.mapper.method.DeleteById;
import com.swift.custom.mapper.method.FindAll;
import com.swift.custom.mapper.method.FindAllById;
import com.swift.custom.mapper.method.Insert;
import com.swift.custom.mapper.method.Select;
import com.swift.custom.mapper.method.SelectById;
import com.swift.custom.mapper.method.SelectRecordByColumn;
import com.swift.custom.mapper.method.SelectRecordsByColumn;
import com.swift.custom.mapper.method.Update;
import com.swift.custom.mapper.method.UpdateById;
import com.swift.session.SwiftConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * Modified from {@link MybatisAutoConfiguration}
 *
 * <ol>
 * <li>Modify {@link #sqlSessionFactory(DataSource)} method to replace {@link SwiftConfiguration} </li>
 * </ol>
 *
 * @author ly
 */
@Slf4j
@ConditionalOnBean(DataSource.class)
@EnableConfigurationProperties(SwiftProperties.class)
@org.springframework.context.annotation.Configuration
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class SwiftAutoConfiguration implements InitializingBean {

    private final SwiftProperties properties;

    private final Interceptor[] interceptors;

    private final ResourceLoader resourceLoader;

    private final DatabaseIdProvider databaseIdProvider;

    private final List<ConfigurationCustomizer> configurationCustomizers;

    public SwiftAutoConfiguration(SwiftProperties properties,
                                  ObjectProvider<Interceptor[]> interceptorsProvider,
                                  ResourceLoader resourceLoader,
                                  ObjectProvider<DatabaseIdProvider> databaseIdProvider,
                                  ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        this.properties = properties;
        this.interceptors = interceptorsProvider.getIfAvailable();
        this.resourceLoader = resourceLoader;
        this.databaseIdProvider = databaseIdProvider.getIfAvailable();
        this.configurationCustomizers = configurationCustomizersProvider.getIfAvailable();
    }

    @Override
    public void afterPropertiesSet() {
        checkConfigFileExists();
    }

    private void checkConfigFileExists() {
        if (this.properties.isCheckConfigLocation() && StringUtils.hasText(this.properties.getConfigLocation())) {
            Resource resource = this.resourceLoader.getResource(this.properties.getConfigLocation());
            Assert.state(resource.exists(), "Cannot find config location: " + resource
                    + " (please add config file or check your Mybatis configuration)");
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        applyConfiguration(factory);
        if (this.properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(this.properties.getConfigurationProperties());
        }
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            factory.setPlugins(this.interceptors);
        }
        if (this.databaseIdProvider != null) {
            factory.setDatabaseIdProvider(this.databaseIdProvider);
        }
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (this.properties.getTypeAliasesSuperType() != null) {
            factory.setTypeAliasesSuperType(this.properties.getTypeAliasesSuperType());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            factory.setMapperLocations(this.properties.resolveMapperLocations());
        }

        return factory.getObject();
    }

    private void applyConfiguration(SqlSessionFactoryBean factory) {
        SwiftConfiguration configuration = this.properties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
            configuration = new SwiftConfiguration();
        }
        if (configuration != null && !CollectionUtils.isEmpty(this.configurationCustomizers)) {
            for (ConfigurationCustomizer customizer : this.configurationCustomizers) {
                customizer.customize(configuration);
            }
        }

        // TODO 日后删除，改为可配置实
        configuration.addMapperMethodResolver(new Insert());
        configuration.addMapperMethodResolver(new Delete());
        configuration.addMapperMethodResolver(new DeleteById());
        configuration.addMapperMethodResolver(new DeleteByColumn());
        configuration.addMapperMethodResolver(new Update());
        configuration.addMapperMethodResolver(new UpdateById());
        configuration.addMapperMethodResolver(new FindAll());
        configuration.addMapperMethodResolver(new FindAllById());
        configuration.addMapperMethodResolver(new Select());
        configuration.addMapperMethodResolver(new SelectById());
        configuration.addMapperMethodResolver(new SelectRecordByColumn());
        configuration.addMapperMethodResolver(new SelectRecordsByColumn());

        factory.setConfiguration(configuration);
    }

}
