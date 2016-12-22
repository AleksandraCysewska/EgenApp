package cysewska.com.services.textiles;

import com.jfoenix.controls.JFXTextField;
import cysewska.com.controllers.MainView;
import cysewska.com.models.dto.TextilePWDTO;
import cysewska.com.models.entities.TextileEntity;
import cysewska.com.repositories.TextilRepository;
import cysewska.com.services.validators.ValidatorController;
import cysewska.com.services.validators.services.DoubleWithNullValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-10-26.
 */
@Component
public class AddPW implements Initializable {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    ValidatorController validatorController;
    @Autowired
    MainView mainView;
    AnchorPane root;
    Stage stage;
    @FXML
    TableView tab_textile;
    @FXML
    JFXTextField t_quantity;
    @FXML
    ComboBox c_textile;
    TableColumn col_name, col_quantity;
    @Autowired
    TextilRepository textilRepository;
    List<TextilePWDTO> textileClothDTOs = new ArrayList<>();
    ObservableList<String> comboTextile;
    ObservableList<TextilePWDTO> listViewitems = FXCollections.observableArrayList();
    @Autowired
    TextileViewImp textileViewImp;

    public void showWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_pw.fxml"));
        fxmlLoader.setController(this);
        try {
            root = (AnchorPane) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));
        stage.setTitle("Przyjęcie materiałów");
        stage.show();
        fxmlLoader.setController(AddPW.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();

    }

    public void save(ActionEvent event) {
        if (!(t_quantity.getValidators().get(0).getHasErrors())) {
            for (TextilePWDTO textileClothDTO : textileClothDTOs) {

                TextileEntity textileEntity = textilRepository.findAll().stream().
                        filter(e -> e.getName()
                                .equals(textileClothDTO.getName()))
                        .findAny()
                        .get();
                textilRepository.updateTextileQuantity(textileEntity.getId(),
                        textileClothDTO.getQuantity()+textileEntity.getTextileQuantity());

            }
            mainView.getTableTextile().setItems(null);
            textileViewImp.fillTableData();
            tab_textile.setItems(null);
            textileClothDTOs.clear();
            listViewitems.clear();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    public void cancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
            textileClothDTOs.clear();
            listViewitems.clear();
    }

    public void add() {

        if ((!t_quantity.getValidators().get(0).getHasErrors()  && !t_quantity.getText().equals(""))){
         textileClothDTOs.add(new TextilePWDTO(Double.parseDouble(t_quantity.getText().toString()), c_textile.getValue().toString()));
         for (int i = 0; i < listViewitems.size(); i++) {
             listViewitems.remove(i);
         }
         listViewitems.setAll(textileClothDTOs);
         tab_textile.setItems(listViewitems);
         System.out.println(listViewitems.size());
         t_quantity.setText(null);
     }


    }
@FXML
Button button;

    public void addTextile(){

    }
    public void delete() {
        textileClothDTOs.remove(tab_textile.getSelectionModel().getSelectedItem());
        listViewitems.setAll(textileClothDTOs);
        tab_textile.setItems(listViewitems);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button.setVisible(false);

        DoubleWithNullValidator validator = new DoubleWithNullValidator();
        validatorController.setValidatorsIfContainLetter(validator, t_quantity, "Podaj prawidłową liczbę tkaniny");


        List<String> texxtileList = textilRepository.findAll().stream()
                .map(TextileEntity::getName).collect(Collectors.toList());
        comboTextile =
                FXCollections.observableArrayList(
                        texxtileList
                );

        c_textile.setItems(comboTextile);
        if(texxtileList.size()==0){
            c_textile.setVisible(false);
            button.setVisible(true);
            button.setText("Dodaj najpierw tkaniny");
        }
        c_textile.getSelectionModel().selectFirst();

        this.col_name = new TableColumn("Nazwa tkaniny");
        this.col_quantity = new TableColumn("Liczba tkaniny");
        col_name.setCellValueFactory(new PropertyValueFactory<TextilePWDTO, String>("name"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<TextilePWDTO, Integer>("quantity"));
        tab_textile.getColumns().addAll(col_name, col_quantity);
        tab_textile.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tab_textile.setItems(listViewitems);
        tab_textile.getStyleClass().add( "custom-align");

    }
}
