package cysewska.com.services.contractors;

import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.BranchEntity;
import cysewska.com.models.entities.DepartmentEntity;
import cysewska.com.models.entities.ModelEntity;
import cysewska.com.services.validators.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Ola on 2016-10-26.
 */
@Component
public class AddBranch {
    @Autowired
    MainView mainView;
    @FXML
    TextField t_name;
    AnchorPane root;
    Stage stage;
    @Autowired
    Validator validator;
    @Autowired
    ContractorView contractorView;
    public void showWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/model_branch.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));
        stage.setTitle("Dodawanie oddziału");
        stage.show();
        fxmlLoader.setController(AddBranch.class);
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
        String sql3 = "SELECT * FROM BRANCH ";
        SQLQuery query3 = session2.createSQLQuery(sql3);
        query3.addEntity(BranchEntity.class);
        List<BranchEntity> results3 = query3.list();
        BranchEntity branchEntity = new BranchEntity(
                results3.stream().max(Comparator.comparing(BranchEntity::getId)).get().getId() + 1,
                t_name.getText());
        try{
            session2.save((branchEntity));
            session2.getTransaction().commit();
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
}
