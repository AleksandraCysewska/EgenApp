package cysewska.com.services.contractors;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.BranchEntity;
import cysewska.com.models.entities.DepartmentEntity;
import cysewska.com.models.enums.TypeOfNip;
import cysewska.com.repositories.BranchRepository;
import cysewska.com.repositories.DepartmentRepository;
import cysewska.com.services.validators.ValidatorController;
import cysewska.com.services.validators.services.EmailValidator;
import cysewska.com.services.validators.services.IntegerValidator;
import cysewska.com.services.validators.services.StringValidator;
import cysewska.com.services.validators.services.ZipValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-10-07.
 */
@Component
public class EditContractor implements Initializable {
    private final Logger logger = Logger.getLogger(this.getClass());

    @FXML
    JFXTextField t_department;
    @FXML
    ComboBox c_branch;
    @FXML
    ComboBox c_type;
    @FXML
    JFXTextField t_nip;
    @FXML
    JFXTextField t_address;
    @FXML
    JFXTextField t_zip;
    @FXML
    JFXTextField t_country;
    @FXML
    JFXTextField t_email;
    @FXML
    JFXTextField t_telephone;
    @FXML
    JFXTextField t_city;

    @Autowired
    MainView mainView;

    DepartmentEntity departmentEntities = null;
    List<BranchEntity> results3;
    @Autowired
    ContractorView contractorView;
    AnchorPane root;
    Stage stage;
@Autowired
ValidatorController validatorController;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    BranchRepository branchRepository;
    public void cancel(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    public void save(  ActionEvent event){


        DepartmentEntity departmentEntity = null;

        List<DepartmentEntity> departmentList= departmentRepository.findAll();
      /*  List<String> depList = departmentList.stream().
                map(DepartmentEntity::getName).collect(Collectors.toList());
*/

                departmentEntity = departmentRepository.findOne( departmentEntities.getId());
                departmentEntity.setName(t_department.getText().toString());
                departmentEntity.setNip(t_nip.getText().toString());
                departmentEntity.setCountry(t_country.getText().toString());
                departmentEntity.setCity(t_city.getText().toString());
                departmentEntity.setAddress(t_address.getText().toString());
                departmentEntity.setZip(t_zip.getText().toString());
                departmentEntity.setEmail(t_email.getText().toString());
                departmentEntity.setTelephone(t_telephone.getText().toString());

                departmentEntity.setBranchEntity(results3.stream().filter(e -> e.getName().
                        equals(c_branch.getValue().toString())).findFirst().get());
                departmentEntity.setTypeOfNip(c_type.getValue().toString());


         //       session.update(departmentEntity);


                mainView.getTableContractor().setItems(null);
                contractorView.fillTableData();
                ((Node)(event.getSource())).getScene().getWindow().hide();





    }
    public void addBranch(ActionEvent event) {
        try {
            mainView.addBranch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createView()  {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/crud_contractor.fxml"));
        fxmlLoader.setController(this);
        try {
            root = (AnchorPane)fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        stage = new Stage();
        stage.setTitle("Edytowanie kontrahenta");
        stage.show();
        stage.setResizable(false);
        stage.getIcons().add(new Image("shop.png"));
        fxmlLoader.setController(EditContractor.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        departmentEntities = departmentRepository.findAll().stream().filter(e->e.getName().equals(mainView.getSelected())).findAny().get();

        StringValidator validator = new StringValidator();
        validatorController.setStringValidator(validator, t_department, "Podaj prawidłową nazwę działu");

        IntegerValidator validator2 = new IntegerValidator();
        validatorController.setIntegerValidators(validator2, t_nip, "Podaj prawidłowy NIP");

        StringValidator validator3 = new StringValidator();
        validatorController.setStringValidator(validator3, t_country, "Podaj prawidłowy kraj");

        StringValidator validator4 = new StringValidator();
        validatorController.setStringValidator(validator4, t_city, "Podaj prawidłowe miasto");

        EmailValidator validator5 = new EmailValidator();
        validatorController.setEmailValidators(validator5, t_email, "Podaj prawidłowy adres email");

        IntegerValidator validator6 = new IntegerValidator();
        validatorController.setIntegerValidators(validator6, t_telephone, "Podaj prawidłowy telefon");

        ZipValidator validator7 = new ZipValidator();
        validatorController.setZipValidators(validator7, t_zip, "Podaj prawidłowy kod pocztowy");


        t_department.setText(departmentEntities.getName());
            t_nip.setText(departmentEntities.getNip());
            t_country.setText(departmentEntities.getCountry());
            t_city.setText(departmentEntities.getCity());
            t_address.setText(departmentEntities.getAddress());
            t_zip.setText(departmentEntities.getZip());
            t_email.setText(departmentEntities.getEmail());
            t_telephone.setText(departmentEntities.getTelephone());
            c_branch.getSelectionModel().select(departmentEntities.getBranchEntity().getName());
            c_type.getSelectionModel().select(departmentEntities.getTypeOfNip());


        ObservableList<TypeOfNip> optionsModels =
                FXCollections.observableArrayList(
                        TypeOfNip.values()
                );
        c_type.setItems(optionsModels);


       results3 = branchRepository.findAll();
        List<String> texxtileList = results3.stream().map(BranchEntity::getName).collect(Collectors.toList());
        ObservableList<String> comboTextile =
                FXCollections.observableArrayList(
                        texxtileList
                );
        c_branch.setItems(comboTextile);
        c_branch.getSelectionModel().selectFirst();
        c_type.getSelectionModel().selectFirst();


    }
    }

