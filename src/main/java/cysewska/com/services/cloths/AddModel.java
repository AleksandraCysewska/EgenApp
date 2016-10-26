package cysewska.com.services.cloths;

import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.ModelEntity;
import cysewska.com.services.contractors.AddBranch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
import java.util.Comparator;
import java.util.List;

/**
 * Created by Ola on 2016-10-26.
 */
@Component
public class AddModel {
    @Autowired
    MainView mainView;

    @FXML
    TextField t_name;
    AnchorPane root;
    Stage stage;

    public void showWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/model_branch.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));
        stage.setTitle("Dodawanie modelu");
        stage.show();
        fxmlLoader.setController(AddModel.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }

    public void save(){
        SessionFactory sessionFactory2 = new Configuration().configure().buildSessionFactory();
        Session session2 = sessionFactory2.openSession();
        session2.beginTransaction();
        String sql3 = "SELECT * FROM MODELS ";
        SQLQuery query3 = session2.createSQLQuery(sql3);
        query3.addEntity(ModelEntity.class);
        List<ModelEntity> results3 = query3.list();
        ModelEntity modelEntity = new ModelEntity(
                results3.stream().max(Comparator.comparing(ModelEntity::getId)).get().getId() + 1,
                t_name.getText());
        session2.save(modelEntity);
        session2.getTransaction().commit();

        mainView.getTableCloth().refresh();

    }
    public void cancel(){

    }
}
