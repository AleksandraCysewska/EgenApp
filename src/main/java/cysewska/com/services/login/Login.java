package cysewska.com.services.login;

import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.OrderEntity;
import cysewska.com.models.entities.UsersEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by Ola on 2016-10-29.
 */
@Component
public class Login {

    @FXML
    TextField login;
    @FXML
    PasswordField password;
    String loginName;
    AnchorPane root;
    Stage stage;
    @Autowired
    MainView mainView;

    public String getLoginName() {
        return loginName;
    }

    @Autowired

    AuthentitactionController authentitactionController;

    public void showWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));
      //  stage.setResizable(false);
        stage.setTitle("Logowanie");
        stage.show();
        fxmlLoader.setController(Login.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
     //   stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }
    public void login(){


        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String sql = "SELECT * FROM USER WHERE USERNAME=:PARAM";
        SQLQuery query = session.createSQLQuery(sql);
        query.setParameter("PARAM",login.getText() );
        query.addEntity(UsersEntity.class);
        List<UsersEntity> results = query.list();
        sessionFactory.close();
         loginName = login.getText();

        if( authentitactionController.checkPassword(password.getText(), results.get(0).getPassword()) ) {
            mainView.doStuffAfterLogged();
            stage.close();
        }
        else {
            System.out.println(password.getText() + " 0 "  +results.get(0).getPassword() );
        }
    }
}
