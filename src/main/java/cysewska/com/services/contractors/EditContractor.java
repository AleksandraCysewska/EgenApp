package cysewska.com.services.contractors;

import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.BranchEntity;
import cysewska.com.models.entities.DepartmentEntity;
import cysewska.com.models.enums.TypeOfNip;
import cysewska.com.services.contractors.AddContractorWindow;
import cysewska.com.services.validators.Validator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
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
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-10-07.
 */
@Component
public class EditContractor implements Initializable {
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
    @FXML
    Validator validator;
    public void cancel(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    public void save(){
        SessionFactory factory;
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        DepartmentEntity departmentEntity = null;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
             departmentEntity = (DepartmentEntity)session.get(DepartmentEntity.class, departmentEntities.get(0).getId());
            departmentEntity.setName(t_department.getText().toString());
            departmentEntity.setNip(t_nip.getText().toString());
            departmentEntity.setCountry(t_country.getText().toString());
            departmentEntity.setCity(t_city.getText().toString());
            departmentEntity.setAddress(t_address.getText().toString());
            departmentEntity.setZip(t_zip.getText().toString());
            departmentEntity.setEmail(t_email.getText().toString());
            departmentEntity.setTelephone(t_telephone.getText().toString());

            departmentEntity.setBranchEntity(results3.stream().filter(e->e.getName().equals(c_branch.getValue().toString())).findFirst().get());
            departmentEntity.setTypeOfNip(c_type.getValue().toString());
            session.update(departmentEntity);
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
    }
        @Autowired
        ContractorView contractorView;
    AnchorPane root;
    Stage stage;
    public void createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/crud_contractor.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Edytowanie kontrahenta");
        stage.show();
        stage.getIcons().add(new Image("shop.png"));
        fxmlLoader.setController(EditContractor.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }
@Autowired
    MainView mainView;
    List<DepartmentEntity> departmentEntities = null;
    List<BranchEntity> results3;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SessionFactory sessionFactory2 = new Configuration().configure().buildSessionFactory();
        Session session2 = sessionFactory2.openSession();
        session2.beginTransaction();

            String dep = "SELECT * FROM DEPARTMENT WHERE DEPARTMENT_NAME = :PARAM";
            SQLQuery depQuery = session2.createSQLQuery(dep);
            depQuery.setParameter("PARAM",mainView.getSelected() );
            depQuery.addEntity(DepartmentEntity.class);
            departmentEntities = depQuery.list();






            t_department.setText(departmentEntities.get(0).getName());
            t_nip.setText(departmentEntities.get(0).getNip());
            t_country.setText(departmentEntities.get(0).getCountry());
            t_city.setText(departmentEntities.get(0).getCity());
            t_address.setText(departmentEntities.get(0).getAddress());
            t_zip.setText(departmentEntities.get(0).getZip());
            t_email.setText(departmentEntities.get(0).getEmail());
            t_telephone.setText(departmentEntities.get(0).getTelephone());
            c_branch.getSelectionModel().select(departmentEntities.get(0).getBranchEntity().getName());
            c_type.getSelectionModel().select(departmentEntities.get(0).getTypeOfNip());


        ObservableList<TypeOfNip> optionsModels =
                FXCollections.observableArrayList(
                        TypeOfNip.values()
                );
        c_type.setItems(optionsModels);


        String sql3 = "SELECT * FROM BRANCH ";
        SQLQuery query3 = session2.createSQLQuery(sql3);

        query3.addEntity(BranchEntity.class);
       results3 = query3.list();
        List<String> texxtileList = results3.stream().map(BranchEntity::getName).collect(Collectors.toList());
        ObservableList<String> comboTextile =
                FXCollections.observableArrayList(
                        texxtileList
                );
        c_branch.setItems(comboTextile);
        session2.getTransaction().commit();
        session2.close();


    }
    }

