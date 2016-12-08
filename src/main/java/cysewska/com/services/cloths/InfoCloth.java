package cysewska.com.services.cloths;

import cysewska.com.controllers.MainView;
import cysewska.com.models.dto.TextileClothDTO;
import cysewska.com.models.entities.ClothEntity;
import cysewska.com.models.entities.TextileEntity;
import cysewska.com.models.entities.Textile_Cloth_Entity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-10-20.
 */
@Component
public class InfoCloth implements Initializable{
    @FXML
    TextField t_nameENG;
    @FXML
    TextField t_nameNO;
    @FXML
    TextField t_price;
    @FXML
    TextField t_name;
    @FXML
    ComboBox c_model;
    @FXML
    Button b_save;
    @FXML
    ComboBox c_textile;
    @FXML
    TextField t_quantity;
    @Autowired
    MainView mainView;
    List<ClothEntity> list;
    @FXML
    TableView tableTextiles;
    TableColumn col_name, col_quantity;
    private AnchorPane root;
    Stage stage;

    List<TextileClothDTO> textileClothDTOs = new ArrayList<>();
    ObservableList<String> comboTextile;
    ObservableList<TextileClothDTO> listViewitems = FXCollections.observableArrayList();
    public void createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_cloth.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane) fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Informacje o towarze");
        stage.show();
        fxmlLoader.setController(InfoCloth.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("shop.png"));
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }
    public void delete(){}
    public void add(){}
    @FXML
    GridPane gridMain;
    @FXML
    Button  cancel;
    @FXML
    AnchorPane anchorNotNeed, anchorWithButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SessionFactory sessionFactory2 = new Configuration().configure().buildSessionFactory();
        Session session2 = sessionFactory2.openSession();
        session2.beginTransaction();

        String dep = "SELECT * FROM CLOTH WHERE CLOTH_NAME_PL = :PARAM";
        SQLQuery depQuery = session2.createSQLQuery(dep);
        depQuery.setParameter("PARAM", mainView.getSelected());
        depQuery.addEntity(ClothEntity.class);
        list = depQuery.list();

        Label l1 = new Label(list.get(0).getModelEntity().getModel());
        c_model.setVisible(false);
        gridMain.add(l1, 1,2);

        Label l2 = new Label(list.get(0).getClothNamePL());
        t_name.setVisible(false);
        gridMain.add(l2, 1,3);

        Label l3 = new Label(list.get(0).getClothNameENG());
        t_nameENG.setVisible(false);
        gridMain.add(l3, 1,4);

        Label l4 = new Label(list.get(0).getClothNameNO());
        t_nameNO.setVisible(false);
        gridMain.add(l4, 1,5);

        Label l5 = new Label(list.get(0).getPricePl().toString());
        t_price.setVisible(false);
        gridMain.add(l5, 1,6);

        anchorNotNeed.setMaxWidth(1);
        anchorNotNeed.setMaxHeight(1);

        anchorWithButton.setMaxHeight(50);
        b_save.setText("Ok");



        b_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                ((Node)(e.getSource())).getScene().getWindow().hide();

            }
        });
        cancel.setVisible(false);
        Set<Textile_Cloth_Entity> textile_cloths = list.get(0).getTextile_cloths();
        for (Textile_Cloth_Entity textile_cloth : textile_cloths) {

            listViewitems.add(new TextileClothDTO(textile_cloth.getClothEntity().getClothNamePL(),
                    textile_cloth.getTextileClothQuantities()));
        }


        String sql3 = "SELECT * FROM TEXTILE ";
        SQLQuery query3 = session2.createSQLQuery(sql3);
        query3.addEntity(TextileEntity.class);
        List<TextileEntity> results3 = query3.list();
        List<String> texxtileList = results3.stream().map(TextileEntity::getName).collect(Collectors.toList());
        comboTextile =
                FXCollections.observableArrayList(
                        texxtileList
                );
        c_textile.setItems(comboTextile);

        this.col_name = new TableColumn("Nazwa tkaniny");
        this.col_quantity = new TableColumn("Liczba tkaniny");


        col_name.setCellValueFactory(new PropertyValueFactory<TextileClothDTO, String>("name"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<TextileClothDTO, Integer>("quantity"));

        tableTextiles.getColumns().addAll(col_name, col_quantity);
        tableTextiles.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableTextiles.setItems(listViewitems);



                session2.getTransaction().commit();
        session2.close();
    }
}


