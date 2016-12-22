package cysewska.com.services.contractors;

import com.jfoenix.controls.JFXTextField;
import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.BranchEntity;
import cysewska.com.models.entities.DepartmentEntity;
import cysewska.com.models.enums.TypeOfNip;
import cysewska.com.repositories.BranchRepository;
import cysewska.com.repositories.DepartmentRepository;
import cysewska.com.services.validators.services.EmailValidator;
import cysewska.com.services.validators.services.IntegerValidator;
import cysewska.com.services.validators.services.StringValidator;
import cysewska.com.services.validators.ValidatorController;
import cysewska.com.services.validators.services.ZipValidator;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-09-18.
 */
@Component
public class AddContractorWindow implements Initializable {
    private final Logger logger = Logger.getLogger(this.getClass());

    private AnchorPane root;
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
    @Autowired
    MainView mainView;
    @FXML
    JFXTextField t_country;
    @FXML
    JFXTextField t_email;
    @FXML
    JFXTextField t_telephone;
    @FXML
    JFXTextField t_city;
    @FXML
    Button b_save;
    @Autowired
    ContractorView contractorView;
    Stage stage;
    @Autowired
    ValidatorController validatorController;
    List<BranchEntity> results3;
@Autowired
    DepartmentRepository departmentRepository;
    public void createView() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/crud_contractor.fxml"));
        fxmlLoader.setController(this);
        try {
            root = (AnchorPane) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));
        stage.setResizable(false);
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

    public DepartmentEntity createDepartment() {
            List<BranchEntity> results3 =   branchRepository.findAll();
        return new DepartmentEntity(null , t_department.getText(), c_type.getValue().toString(), t_nip.getText(), t_country.getText(),
                t_city.getText(), t_address.getText(), t_zip.getText(), t_email.getText(), t_telephone.getText(),
                results3.stream().filter(e -> e.getName().equals(c_branch.getValue().toString())).findFirst().get()
        );
    }
    public void addBranch(ActionEvent event) {

        try {
            mainView.addBranch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(ActionEvent event) {
        if (!(t_department.getValidators().get(0).getHasErrors() ||
                t_address.getValidators().get(0).getHasErrors()
                || t_city.getValidators().get(0).getHasErrors()
                || t_country.getValidators().get(0).getHasErrors()
                || t_email.getValidators().get(0).getHasErrors()
                || t_nip.getValidators().get(0).getHasErrors()
                || t_telephone.getValidators().get(0).getHasErrors()
                || t_zip.getValidators().get(0).getHasErrors()
        ) ) {


            List<String> depList = new ArrayList<>();
            List<DepartmentEntity> dep = departmentRepository.findAll();
            depList.addAll(dep.stream().map(DepartmentEntity::getName).collect(Collectors.toList()));
            mainView.getTableContractor().setItems(null);
            contractorView.fillTableData();
            ((Node) (event.getSource())).getScene().getWindow().hide();

        }
    }

    public void cancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @Autowired
    BranchRepository branchRepository;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

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


            ObservableList<TypeOfNip> optionsModels =
                    FXCollections.observableArrayList(
                            TypeOfNip.values()
                    );
            c_type.setItems(optionsModels);

             results3 =   branchRepository.findAll();
            List<String> texxtileList = results3.stream().map(BranchEntity::getName).collect(Collectors.toList());
            ObservableList<String> comboTextile =
                    FXCollections.observableArrayList(
                            texxtileList
                    );
            c_branch.setItems(comboTextile);
            c_branch.getSelectionModel().select(0);
            c_type.getSelectionModel().select(0);
    }


}
