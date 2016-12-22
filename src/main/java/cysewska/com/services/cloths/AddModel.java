package cysewska.com.services.cloths;

import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.ModelEntity;
import cysewska.com.services.contractors.AddBranch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-10-26.
 */
@Component
public class AddModel {

    private final Logger logger = Logger.getLogger(this.getClass());

    @FXML
    TextField t_name;
    AnchorPane root;
    Stage stage;



    public void showWindow()  {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/model_branch.fxml"));
        fxmlLoader.setController(this);
        try {
            root = (AnchorPane)fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));
        stage.setTitle("Dodawanie modelu");
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.show();
        fxmlLoader.setController(AddModel.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }

    public void save(ActionEvent event){
        SessionFactory sessionFactory2 = new Configuration().configure().buildSessionFactory();
        Session session2 = sessionFactory2.openSession();
        session2.beginTransaction();
        String sql3 = "SELECT * FROM MODELS ";
        SQLQuery query3 = session2.createSQLQuery(sql3);
        query3.addEntity(ModelEntity.class);
        List<ModelEntity> results3 = query3.list();
        List<String> modelList = results3.stream().map(ModelEntity::getModel).collect(Collectors.toList());
        long id;


            try{
                id=  results3.stream().max(Comparator.comparing(ModelEntity::getId)).get().getId() + 1;
            }catch(NoSuchElementException e){
                id=1;
                logger.error(e.getMessage());
            }

            ModelEntity modelEntity = new ModelEntity(
            id,
            t_name.getText());
                session2.save(modelEntity);
                session2.getTransaction().commit();
                session2.close();

            ((Node) (event.getSource())).getScene().getWindow().hide();




    }
    public void cancel(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
