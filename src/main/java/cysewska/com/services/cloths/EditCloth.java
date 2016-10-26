package cysewska.com.services.cloths;

import cysewska.com.controllers.MainView;
import cysewska.com.models.dto.TextileClothDTO;
import cysewska.com.models.entities.*;
import cysewska.com.services.contractors.AddContractorWindow;
import cysewska.com.services.contractors.EditContractor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-10-07.
 */
@Component
public class EditCloth implements Initializable {

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
    private AnchorPane root;
    Stage stage;
    @FXML
    TableView tableTextiles;
    TableColumn col_name, col_quantity;

    public void add() {
        textileClothDTOs.add(new TextileClothDTO(c_textile.getValue().toString(), Integer.parseInt(t_quantity.getText().toString())));
        for (int i = 0; i < listViewitems.size(); i++) {
            listViewitems.remove(i);
        }
        listViewitems.setAll(textileClothDTOs);
        tableTextiles.setItems(listViewitems);
        System.out.println(listViewitems.size());
        t_quantity.setText(null);
    }

    public void delete() {
        textileClothDTOs.remove(tableTextiles.getSelectionModel().getSelectedItem());
        listViewitems.setAll(textileClothDTOs);
        tableTextiles.setItems(listViewitems);
    }


    List<TextileClothDTO> textileClothDTOs = new ArrayList<>();
    ObservableList<String> comboTextile;
    ObservableList<TextileClothDTO> listViewitems = FXCollections.observableArrayList();

    public void createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_cloth.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane) fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Edytowanie towaru");
        stage.show();
        fxmlLoader.setController(EditCloth.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("shop.png"));
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }

    @Autowired
    MainView mainView;
    List<ClothEntity> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SessionFactory sessionFactory2 = new Configuration().configure().buildSessionFactory();
        Session session2 = sessionFactory2.openSession();
        session2.beginTransaction();

        String models = "SELECT * FROM MODELS";
        SQLQuery modelsSQL = session2.createSQLQuery(models);
        modelsSQL.addEntity(ModelEntity.class);
        List<ModelEntity> list = modelsSQL.list();

        List<String> modelsList = new ArrayList<>();
        for (ModelEntity modelEntity : list) {
            modelsList.add(modelEntity.getModel());
        }
        ObservableList<String> optionsModels =
                FXCollections.observableArrayList(
                        modelsList
                );
        c_model.setItems(optionsModels);

        String dep = "SELECT * FROM CLOTH WHERE CLOTH_NAME_PL = :PARAM";
        SQLQuery depQuery = session2.createSQLQuery(dep);
        depQuery.setParameter("PARAM", mainView.getSelected());
        depQuery.addEntity(ClothEntity.class);
        this.list = depQuery.list();


        t_nameENG.setText(this.list.get(0).getClothNameENG());
        t_nameNO.setText(this.list.get(0).getClothNameNO());
        t_price.setText(this.list.get(0).getPricePl().toString());
        t_name.setText(this.list.get(0).getClothNamePL());
        c_model.setValue(this.list.get(0).getModelEntity().getModel());
        Set<Textile_Cloth_Entity> textile_cloths = this.list.get(0).getTextile_cloths();
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
        b_save.setOnAction(t -> {

            SessionFactory factory;
            factory = new Configuration().configure().buildSessionFactory();
            Session session = factory.openSession();
            DepartmentEntity departmentEntity = null;
            ClothEntity clothEntity = null;
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                clothEntity = (ClothEntity) session.get(ClothEntity.class, this.list.get(0).getId());
                clothEntity.setClothNameENG(t_nameENG.getText());
                clothEntity.setClothNameNO(t_nameNO.getText());
                clothEntity.setClothNamePL(t_name.getText());
                clothEntity.setPricePl(Integer.parseInt(t_price.getText()));
        //        clothEntity.getModelEntity().setModel();
/*
TODO ADD TEXTILES
 */

            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                e.printStackTrace();
            } finally {
                session.update(clothEntity);
                tx.commit();
                session.close();


            }

        });
    }
}

/*

        }*/
