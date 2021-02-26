package org.valentinenikolaev.webcrud.utils.beans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.*;
import org.valentinenikolaev.webcrud.exceptions.WebCrudException;
import org.valentinenikolaev.webcrud.models.Account;
import org.valentinenikolaev.webcrud.models.Event;
import org.valentinenikolaev.webcrud.models.File;
import org.valentinenikolaev.webcrud.models.User;

import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "org.valentinenikolaev.webcrud.repository")
@Import(value = ControllersBeans.class)
public class RepositoryBeans {

    private final Logger log = LogManager.getLogger(RepositoryBeans.class);
    private Properties appProperties;

    @Bean
    @Scope(value = "singleton")
    public SessionFactory sessionFactory() {
        Properties dbProperties = getProperties();
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();

            configuration.addAnnotatedClass(User.class)
                         .addAnnotatedClass(File.class)
                         .addAnnotatedClass(Event.class)
                         .addAnnotatedClass(Account.class);

            configuration.setProperty("hibernate.connection.driver_class", dbProperties.getProperty("db.driver"))
                         .setProperty("hibernate.connection.url", dbProperties.getProperty("db.url"))
                         .setProperty("hibernate.connection.username", dbProperties.getProperty("db.user"))
                         .setProperty("hibernate.connection.password", dbProperties.getProperty("db.password"))
                         .setProperty("hibernate.dialect", dbProperties.getProperty("hibernate.dialect"))
                         .setProperty("hibernate.default_schema", dbProperties.getProperty("db.schema"))
                         .setProperty("showSQl", dbProperties.getProperty("hibernate.showSQl"));

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            return configuration.buildSessionFactory(builder.build());
        } catch (HibernateException hibernateException) {
            log.error("Hibernate`s connection configuration can`t be created.", hibernateException);
            throw new HibernateException(hibernateException);
        }
    }

    private Properties getProperties() {
        if (appProperties != null) {
            return appProperties;
        }

        try {
            appProperties = new Properties();
            appProperties.load(RepositoryBeans.class.getClassLoader().getResourceAsStream("application.properties"));
            return appProperties;
        } catch (IOException e) {
            String message = "Properties file not found or can`t be loaded!";
            log.error(message, e);
            throw new WebCrudException(message);
        }
    }
}
