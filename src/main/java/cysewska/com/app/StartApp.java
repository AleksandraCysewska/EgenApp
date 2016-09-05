package cysewska.com.app;

import cysewska.com.entities.UsersEntity;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;

import org.hibernate.*;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import cysewska.com.views.ComplexView;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Properties;


/**
 * Created by Ola on 2016-08-04.
 */


@ComponentScan(basePackages = {"cysewska.com"})
//@EnableAutoConfiguration
@EntityScan(basePackages  = "cysewska.com.entities")
@SpringBootApplication
@EnableJpaRepositories(basePackages = "cysewska.com.repositories")
public class StartApp extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launchApp(StartApp.class, ComplexView.class, args);}
    }

