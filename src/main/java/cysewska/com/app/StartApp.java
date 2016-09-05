package cysewska.com.app;

import cysewska.com.entities.UsersEntity;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;

import org.hibernate.*;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import cysewska.com.views.ComplexView;

import java.util.List;
import java.util.Properties;


/**
 * Created by Ola on 2016-08-04.
 */


@ComponentScan(basePackages = "cysewska.com.views")
@EnableAutoConfiguration
/*@EntityScan(basePackages  = "cysewska.com.entities")*/
@SpringBootApplication
public class StartApp extends AbstractJavaFxApplicationSupport {

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        Properties properties = configuration.getProperties();

        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }
    public static void main(String[] args) {
  /*  UsersEntity usersEntity =  new UsersEntity(1, "ola", "ola100");
        Session session = sessionFactory.openSession();
        String hql = "FROM UserEntity";
        Query query = session.createQuery(hql);
        List<UsersEntity> persons = query.list();*/
        launchApp(StartApp.class, ComplexView.class, args);
        configureSessionFactory();
        Session session = null;
        Transaction tx=null;

        try {
            session = sessionFactory.openSession();

 /*      tx = session.beginTransaction();*/

            // Creating Contact entity that will be save to the sqlite database
    /*        UsersEntity myContact = new UsersEntity(66, "My Name", "my_email@email.com");
            UsersEntity yourContact = new UsersEntity(54, "Your Name", "your_email@email.com");

            // Saving to the database
           session.save(myContact);
            session.save(yourContact);

            // Committing the change in the database.
            session.flush();
            tx.commit();*/
            Query query = session.createQuery("from UsersEntity");
            List<UsersEntity> contactList = query.list();
            // Fetching saved data


            for (UsersEntity contact : contactList) {
                System.out.println("Id: " + contact.getId() + " | Name:"  + contact.getUsername() + " | Email:" + contact.getPassword());
            }

        } catch (Exception ex) {
            ex.printStackTrace();

            // Rolling back the changes to make the data consistent in case of any failure
            // in between multiple database write operations.
         //   tx.rollback();
        } finally{
            if(session != null) {
                session.close();
            }



        }
    }




    }

