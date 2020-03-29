package by.laniakea.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/*@Configuration
@ComponentScan("by.laniakea.util")
@Import(HibernateConfiguration.class)*/


@Configuration
@ComponentScan("by.laniakea")
@PropertySource({"classpath:database.properties", "classpath:hibernate1.properties"})
@EnableJpaRepositories(basePackages = "by.laniakea.repository")
@EnableTransactionManagement
public class TestConfiguration {

    @Bean
    public DataSource dataSource(@Value("${dh.url}") String url,
                                 @Value("${dh.username}") String username,
                                 @Value("${dh.password}") String password,
                                 @Value("${dh.driver}") String driverName) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverName);

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Properties jpaProperties ) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("by.laniakea");
        factory.setDataSource(dataSource);
        factory.setJpaProperties(jpaProperties);
        return factory;
    }

    @Bean
    public Properties jpaProperties(
            @Value("classpath:hibernate1.properties") Resource databaseResource) throws IOException {
        Properties properties = new Properties();
        properties.load(databaseResource.getInputStream());

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

}
