package cysewska.com.services.contractors;

import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.BranchEntity;
import cysewska.com.models.entities.DepartmentEntity;
import cysewska.com.models.enums.TypeOfNip;
import cysewska.com.services.validators.Validator;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-09-18.
 */
@Component
public class AddContractorWindow implements Initializable {

    private AnchorPane root;
  @FXML
  TextField t_department;
    @FXML
    ComboBox c_branch;
    @FXML
    ComboBox c_type;
    @FXML
            TextField t_nip;
    @FXML
            TextField t_address;
    @FXML
            TextField t_zip;
    @FXML
    TextField t_country;
    @FXML
    TextField t_email;
    @FXML
            TextField t_telephone;
    @FXML
    TextField t_city;

    Stage stage ;
@Autowired
    Validator validator;

    public void createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/crud_contractor.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));

        stage.setTitle("Dodawanie kontrahenta");
        stage.show();
        fxmlLoader.setController(AddContractorWindow.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }
    public DepartmentEntity createDepartment(){
        SessionFactory sessionFactory2 = new Configuration().configure().buildSessionFactory();
        Session session2 = sessionFactory2.openSession();
        session2.beginTransaction();
        String sql3 = "SELECT * FROM BRANCH ";
        SQLQuery query3 = session2.createSQLQuery(sql3);
        query3.addEntity(BranchEntity.class);
        List<BranchEntity> results3 = query3.list();

        String dep = "SELECT * FROM DEPARTMENT ";
        SQLQuery depQuery = session2.createSQLQuery(dep);
        depQuery.addEntity(DepartmentEntity.class);
        List<DepartmentEntity> departmentEntities = depQuery.list();
        DepartmentEntity departmentEntity = new DepartmentEntity(
                departmentEntities.stream().max(Comparator.comparing(DepartmentEntity::getId)).get().getId() + 1,
                t_department.getText(),
                c_type.getValue().toString(),
                t_nip.getText(),
                t_country.getText(),
                t_city.getText(),
                t_address.getText(),
                t_zip.getText(),
                t_email.getText(),
                t_telephone.getText(),
                results3.stream().filter(e -> e.getName().equals(c_branch.getValue().toString())).findFirst().get()
        );
        session2.getTransaction().commit();
        return departmentEntity;
    }

    public void save(ActionEvent event) {

        SessionFactory sessionFactory2 = new Configuration().configure().buildSessionFactory();
        Session session2 = sessionFactory2.openSession();
        session2.beginTransaction();
        try{
            session2.save((createDepartment()));
            session2.getTransaction().commit();
        }
        catch(ConstraintViolationException e) {
            validator.validate(e);
        }

        mainView.getTableContractor().setItems(null);
        contractorView.fillTableData();
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }
    @FXML
    Button b_save;
    @Autowired
    ContractorView contractorView;
    public void cancel(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();

    }



@Autowired
MainView mainView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            b_save.setGraphic(new ImageView("accept.png"));

            SessionFactory sessionFactory2 = new Configuration().configure().buildSessionFactory();
            Session session2 = sessionFactory2.openSession();
            session2.beginTransaction();
            ObservableList<TypeOfNip> optionsModels =
                    FXCollections.observableArrayList(
                            TypeOfNip.values()
                    );
            c_type.setItems(optionsModels);


            String sql3 = "SELECT * FROM BRANCH ";
            SQLQuery query3 = session2.createSQLQuery(sql3);
            query3.addEntity(BranchEntity.class);
            List<BranchEntity> results3 = query3.list();
            List<String> texxtileList = results3.stream().map(BranchEntity::getName).collect(Collectors.toList());
            ObservableList<String> comboTextile =
                    FXCollections.observableArrayList(
                            texxtileList
                    );
            c_branch.setItems(comboTextile);
            session2.getTransaction().commit();
            session2.close();
        c_branch.getSelectionModel().select(0);
        c_type.getSelectionModel().select(0);


    }


}
