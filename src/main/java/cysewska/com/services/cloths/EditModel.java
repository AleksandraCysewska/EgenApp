package cysewska.com.services.cloths;

import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.BranchEntity;
import cysewska.com.models.entities.ModelEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by Ola on 2016-10-26.
 */
@Component
public class EditModel implements Initializable {
    @Autowired
    MainView mainView;
    @Autowired
    ClothViewImp clothViewImp;
    @FXML
    TextField t_name;
    AnchorPane root;
    Stage stage;

    private final Logger logger = Logger.getLogger(this.getClass());

    public void showWindow() {
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
        stage.setTitle("Edytowanie modelu");
        stage.show();
        fxmlLoader.setController(EditModel.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }
    public void save(ActionEvent event){
        SessionFactory factory;
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        List<ModelEntity> modelEntity = null;
        String dep = "SELECT * FROM MODELS WHERE MODEL_NAME = :PARAM";
        SQLQuery depQuery = session.createSQLQuery(dep);
        depQuery.setParameter("PARAM",mainView.getSelected() );
        depQuery.addEntity(ModelEntity.class);
        modelEntity = depQuery.list();

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            modelEntity.get(0).setModel(t_name.getText());
            session.update(modelEntity.get(0));
            tx.commit();
            session.close();
            mainView.getTableCloth().setItems(null);
            clothViewImp.fillClothTableData();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        }catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());

        }



    }
    public void cancel(ActionEvent event){
        ((Node) (event.getSource())).getScene().getWindow().hide();



    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SessionFactory factory;
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        List<ModelEntity> modelEntity = null;
        String dep = "SELECT * FROM MODELS WHERE MODEL_NAME = :PARAM";
        SQLQuery depQuery = session.createSQLQuery(dep);
        depQuery.setParameter("PARAM",mainView.getSelected() );
        depQuery.addEntity(ModelEntity.class);
        modelEntity = depQuery.list();
        t_name.setText(modelEntity.get(0).getModel());
        session.close();
    }
}
