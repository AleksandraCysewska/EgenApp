package cysewska.com.app;

import cysewska.com.entities.UsersEntity;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import cysewska.com.views.ComplexView;

import java.util.List;


/**
 * Created by Ola on 2016-08-04.
 */


@ComponentScan(basePackages = "cysewska.com.views")
@EnableAutoConfiguration
@EntityScan(basePackages  = "cysewska.com.entities")
@SpringBootApplication
public class StartApp extends AbstractJavaFxApplicationSupport {
    @Autowired
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
  /*  UsersEntity usersEntity =  new UsersEntity(1, "ola", "ola100");
        Session session = sessionFactory.openSession();
        String hql = "FROM UserEntity";
        Query query = session.createQuery(hql);
        List<UsersEntity> persons = query.list();*/
            launchApp(StartApp.class, ComplexView.class, args);
        }
    }

