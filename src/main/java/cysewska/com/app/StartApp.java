package cysewska.com.app;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import cysewska.com.views.contractors.ContractorAction;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


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
        launchApp(StartApp.class, ContractorAction.class, args);}
    }

