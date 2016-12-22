package cysewska.com.services.textiles;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.TextileEntity;
import cysewska.com.models.enums.Colors;
import cysewska.com.repositories.TextilRepository;
import cysewska.com.services.validators.ValidatorController;
import cysewska.com.services.validators.services.DoubleValidator;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ola on 2016-10-10.
 */
@Component
public class EditTextile implements Initializable {
    private final Logger logger = Logger.getLogger(this.getClass());

    Stage stage;
    AnchorPane root;
    @FXML
    JFXTextField t_quantity;
    @FXML
    JFXTextField t_thickness;
    @FXML
    JFXTextField t_name;
    @Autowired
    ValidatorController validatorController;
    @FXML
    ComboBox c_color;
    @Autowired
    MainView mainView;
    @Autowired
    TextilRepository textilRepository;
    @Autowired
    TextileViewImp textileViewImp;
    TextileEntity textileEntity;

    public void cancel(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void save(ActionEvent event){
        if (!(t_name.getValidators().get(0).getHasErrors() ||
                t_quantity.getValidators().get(0).getHasErrors()
                || t_thickness.getValidators().get(0).getHasErrors())) {

            textilRepository.updateTextile(textileEntity.getId(),
                    t_name.getText().toString(),
                    c_color.getValue().toString(),
                    Double.parseDouble(t_quantity.getText().toString()),
                    Double.parseDouble(t_thickness.getText().toString())
                    );
            mainView.getTableTextile().setItems(null);
            textileViewImp.fillTableData();
        ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }

    public void createView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_textile.fxml"));
        fxmlLoader.setController(this);
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        stage = new Stage();
        stage.setTitle("Edytowanie tkaniny");
        stage.show();
        stage.getIcons().add(new Image("shop.png"));
        fxmlLoader.setController(EditTextile.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validatorController.setValidators(validator, t_name, "Podaj nazwę tkaniny");
        DoubleValidator validator2 = new DoubleValidator();
        validatorController.setValidatorsIfContainLetter(validator2, t_quantity, "Podaj wartość po przecinku");
        DoubleValidator validator3 = new DoubleValidator();
        validatorController.setValidatorsIfContainLetter(validator3, t_thickness, "Podaj wartość po przecinku");

        ObservableList<Colors> colorsList =
                FXCollections.observableArrayList(
                        Colors.values()
                );
        c_color.setItems(colorsList);

        c_color.getSelectionModel().selectFirst();
         textileEntity = textilRepository.findAll().stream().
                filter(e -> e.getName().equals(mainView.getSelected())).findAny().get();

        t_name.setText(textileEntity.getName());
        c_color.getSelectionModel().select(textileEntity.getColors());
        t_quantity.setText(textileEntity.getTextileQuantity().toString());
        t_thickness.setText(textileEntity.getTextileThickness().toString());
    }
}
