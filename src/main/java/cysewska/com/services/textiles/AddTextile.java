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
 * Created by Ola on 2016-10-03.
 */
@Component
public class AddTextile implements Initializable {
    private final Logger logger = Logger.getLogger(this.getClass());

    AnchorPane root;
    Stage stage;
    @FXML
    JFXTextField t_quantity;
    @FXML
    JFXTextField t_thickness;
    @FXML
    JFXTextField t_name;
    @Autowired
    TextilRepository textilRepository;
    @FXML
    ComboBox c_color;
    @Autowired
    ValidatorController validatorController;
    @Autowired
    MainView mainView;
    @Autowired
    TextileViewImp textileViewImp;

    public void cancel(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void showWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_textile.fxml"));
        fxmlLoader.setController(this);
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        stage = new Stage();
        stage.setTitle("Dodawanie tkanin");
        stage.show();
        stage.getIcons().add(new Image("shop.png"));
        fxmlLoader.setController(AddTextile.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }

    public void save(ActionEvent event) {
        if (!(t_name.getValidators().get(0).getHasErrors() ||
                t_quantity.getValidators().get(0).getHasErrors()
                || t_thickness.getValidators().get(0).getHasErrors())) {

            textilRepository.save(new TextileEntity(
                    null,
                    t_name.getText(),
                    c_color.getValue().toString(),
                    Double.parseDouble(t_quantity.getText()),
                    Double.parseDouble(t_thickness.getText())
            ));
            ((Node) (event.getSource())).getScene().getWindow().hide();
            mainView.getTableTextile().setItems(null);
            textileViewImp.fillTableData();
        }
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
    }
}
