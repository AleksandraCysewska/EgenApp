package cysewska.com.services.contractors;

import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.BranchEntity;
import cysewska.com.models.entities.DepartmentEntity;
import cysewska.com.models.entities.ModelEntity;
import cysewska.com.services.validators.Validator;
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
public class EditBranch implements Initializable{
    @Autowired
    MainView mainView;
    AnchorPane root;
    Stage stage;
    @Autowired
    ContractorView contractorView;
    @FXML
    TextField t_name;
    @Autowired
    Validator validator;
    public void showWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/model_branch.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));

        stage.setTitle("Edytowanie oddziału");
        stage.show();
        fxmlLoader.setController(EditBranch.class);
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

        List<BranchEntity> modelEntity = null;
        String dep = "SELECT * FROM BRANCH WHERE BRANCH_NAME = :PARAM";
        SQLQuery depQuery = session.createSQLQuery(dep);
        depQuery.setParameter("PARAM",mainView.getSelected() );
        depQuery.addEntity(BranchEntity.class);
        modelEntity = depQuery.list();

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            modelEntity.get(0).setName(t_name.getText());
            session.update(modelEntity.get(0));
            tx.commit();
            session.close();

        }catch (HibernateException e) {
            if (tx != null) tx.rollback();
        }
        catch(ConstraintViolationException e) {
           validator.validate(e);
        }
        mainView.getTableContractor().setItems(null);
        contractorView.fillTableData();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void cancel(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SessionFactory factory;
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        List<BranchEntity> modelEntity = null;
        String dep = "SELECT * FROM BRANCH WHERE BRANCH_NAME = :PARAM";
        SQLQuery depQuery = session.createSQLQuery(dep);
        depQuery.setParameter("PARAM",mainView.getSelected() );
        depQuery.addEntity(BranchEntity.class);
        modelEntity = depQuery.list();
        t_name.setText(modelEntity.get(0).getName());
        session.close();
    }
}
