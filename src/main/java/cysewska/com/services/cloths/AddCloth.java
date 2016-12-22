package cysewska.com.services.cloths;

import cysewska.com.controllers.MainView;
import cysewska.com.models.dto.ClothDTO;
import cysewska.com.models.dto.TextileClothDTO;
import cysewska.com.models.entities.*;
import cysewska.com.models.enums.TypeOfNip;
import cysewska.com.repositories.ClothRepository;
import cysewska.com.repositories.ModelRepository;
import cysewska.com.services.contractors.AddContractorWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-09-24.
 */
@Component
public class AddCloth implements Initializable {

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

    List<TextileClothDTO> textileClothDTOs = new ArrayList<>();
    ObservableList<String> comboTextile;
    ObservableList<TextileClothDTO> listViewitems = FXCollections.observableArrayList();

        @FXML
    public void delete() {
        textileClothDTOs.remove( tableTextiles.getSelectionModel().getSelectedItem());
        listViewitems.setAll(textileClothDTOs);
        tableTextiles.setItems(listViewitems);
    }

    public void add() {

        textileClothDTOs.add(new TextileClothDTO(c_textile.getValue().toString(),  Double.parseDouble(t_quantity.getText().toString())));
        for (int i = 0; i < listViewitems.size(); i++) {
            listViewitems.remove(i);
        }
        listViewitems.setAll(textileClothDTOs);

        tableTextiles.setItems(listViewitems);
        System.out.println(listViewitems.size());
        t_quantity.setText(null);
    }

    public void createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_cloth.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane) fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Dodawanie towaru");
        stage.show();
        stage.getIcons().add(new Image("shop.png"));
        fxmlLoader.setController(AddCloth.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }


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
        c_model.getSelectionModel().select(0);
        c_textile.getSelectionModel().select(0);
        session2.getTransaction().commit();
        session2.close();
        b_save.setOnAction(t -> {


                    SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    String sql = "SELECT * FROM MODELS " +
                            "WHERE MODEL_NAME LIKE :model";
                    SQLQuery query = session.createSQLQuery(sql);
                    query.addEntity(ModelEntity.class);
                    query.setParameter("model", c_model.getValue().toString());
                    List<ModelEntity> results = query.list();

                    String sql2 = "SELECT * FROM CLOTH ";
                    SQLQuery query2 = session.createSQLQuery(sql2);
                    query2.addEntity(ClothEntity.class);
                    List<ClothEntity> clothList = query2.list();
            long l;
            try{
                 l = clothList.stream().max(Comparator.comparing(ClothEntity::getId)).get().getId() + 1;

            }catch(NoSuchElementException e)
            {
                l=1;
            }

                    ClothEntity clothEntity = new ClothEntity(
                            l,
                            t_name.getText(),
                            t_nameNO.getText(),
                            t_nameENG.getText(),
                            null,
                            null,
                            Integer.parseInt(t_price.getText()),
                            results.get(0));
                    session.save(clothEntity);

                    SQLQuery findID = session.createSQLQuery("SELECT * FROM TEXTILE_CLOTH");
                    findID.addEntity(Textile_Cloth_Entity.class);

            List<Textile_Cloth_Entity> textileCloth = findID.list();
            long textileClothID;
            try{
                 textileClothID = textileCloth.stream().max(Comparator.comparing(Textile_Cloth_Entity::getId)).get().getId() + 1;

            }catch (NoSuchElementException e){
                textileClothID=1;
            }


                    //textile
                    SQLQuery findTextile = session.createSQLQuery("SELECT * FROM TEXTILE");
                    findTextile.addEntity(TextileEntity.class);
                    List<TextileEntity> textiles = findTextile.list();
                    int temp=0;
                    for (TextileClothDTO listViewitem : listViewitems) {






                       session.save(new Textile_Cloth_Entity(
                                textileClothID + temp,
                                listViewitem.getQuantity(),
                                clothEntity,
                                textiles.stream().filter(e -> e.getName().equals(listViewitem.getName().toString())).findAny().get()
                        ));
                        temp++;}



                    session.getTransaction().commit();

                }
        );

    }
}
