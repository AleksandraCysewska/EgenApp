package cysewska.com.services.orders;

import cysewska.com.controllers.MainView;
import cysewska.com.models.dto.ClothOrderDTO;
import cysewska.com.models.dto.TextileClothDTO;
import cysewska.com.models.entities.*;
import cysewska.com.repositories.*;
import cysewska.com.services.contractors.AddContractorWindow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Ola on 2016-09-19.
 */
@Component
public class AddOrder implements Initializable {

  AnchorPane root;

    @FXML
    TextArea t_description;
    @FXML
    TextField t_quantity;

    TableColumn col_quantity, col_name;
    @FXML
    ComboBox c_cloth;
    @FXML
    TableView tableTextiles;
    ObservableList<ClothOrderDTO> listViewitems = FXCollections.observableArrayList();
    @FXML
    DatePicker t_create;
    @FXML
    DatePicker t_do;
    @FXML
    ComboBox c_department;
    ObservableList<String> items = FXCollections.observableArrayList("Nazwy i ilości");
    @FXML
    ComboBox c_branch;
    List<ClothOrderDTO> textileClothDTOs = new ArrayList<>();



    public void delete(ActionEvent actionEvent) {
            textileClothDTOs.remove( tableTextiles.getSelectionModel().getSelectedItem());
            listViewitems.setAll(textileClothDTOs);
            tableTextiles.setItems(listViewitems);
    }


    public void add(ActionEvent actionEvent) {
        textileClothDTOs.add(new ClothOrderDTO(c_cloth.getValue().toString(), Integer.parseInt(t_quantity.getText().toString())));
        for (int i = 0; i < listViewitems.size(); i++) {
            listViewitems.remove(i);
        }
        listViewitems.setAll(textileClothDTOs);
        tableTextiles.setItems(listViewitems);
        System.out.println(listViewitems.size());
        t_quantity.setText(null);
    }

Stage stage;
    public void createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_order.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Dodawanie zamówienia");
        stage.show();
        stage.getIcons().add(new Image("shop.png"));
        fxmlLoader.setController(AddOrder.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }


    public void saveOrder(){

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "SELECT * FROM ORDERS ";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(OrderEntity.class);
        List<OrderEntity> results = query.list();

        String sqlDep = "SELECT * FROM DEPARTMENT ";
        SQLQuery queryDep = session.createSQLQuery(sqlDep);
        queryDep.addEntity(DepartmentEntity.class);
        List<DepartmentEntity> departmentEntities = queryDep.list();
        long l = results.stream().max(Comparator.comparing(OrderEntity::getId)).get().getId() + 1;

        OrderEntity order =    new OrderEntity(
                l,
                "ORDER " + l,
                t_description.getText(),
                t_create.getValue().toString(),
                t_do.getValue().toString(),
                departmentEntities.stream().filter(e->e.getName().equals(c_department.getValue().toString())).findAny().get()
        );
        session.save(order);




        SQLQuery findTextileCloh = session.createSQLQuery("SELECT * FROM ORDER_CLOTH");
        findTextileCloh.addEntity(OrderClothEntity.class);
        List<OrderClothEntity> textile_cloth_entities = findTextileCloh.list();

        SQLQuery findTextile = session.createSQLQuery("SELECT * FROM CLOTH");
        findTextile.addEntity(ClothEntity.class);
        List<ClothEntity> textiles = findTextile.list();
int licznik =0;
        for (ClothOrderDTO listViewitem : listViewitems) {
            session.save(new OrderClothEntity(
                    textile_cloth_entities.stream().max(Comparator.comparing(OrderClothEntity::getId)).get().getId() + 1 + licznik,
                    Long.valueOf(listViewitem.getQuantity().toString()),
                    order,
                    textiles.stream().filter(e -> e.getClothNamePL().equals(listViewitem.getName().toString())).findAny().get()
            ));
            licznik++;
        }

        session.getTransaction().commit();
        session.close();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        SessionFactory sessionFactory2 = new Configuration().configure().buildSessionFactory();
        Session session2 = sessionFactory2.openSession();
        session2.beginTransaction();

        String sql3 = "SELECT * FROM BRANCH ";
        SQLQuery query3 = session2.createSQLQuery(sql3);
        query3.addEntity(BranchEntity.class);
        List<BranchEntity> results3 = query3.list();
        List<String> texxtileList = results3.stream().map(BranchEntity::getName).collect(Collectors.toList());
         ObservableList comboTextile =
                FXCollections.observableArrayList(
                        texxtileList
                );
        c_branch.setItems(comboTextile);

        String sqlDep = "SELECT * FROM DEPARTMENT ";
        SQLQuery queryDep = session2.createSQLQuery(sqlDep);
        queryDep.addEntity(DepartmentEntity.class);
        List<DepartmentEntity> departmentEntities = queryDep.list();
        List<String> collect = departmentEntities.stream().map(DepartmentEntity::getName).collect(Collectors.toList());
        ObservableList comboDep =
                FXCollections.observableArrayList(
                        collect
                );
        c_department.setItems(comboDep);

        String sqlCloth = "SELECT * FROM CLOTH ";
        SQLQuery queryCloth = session2.createSQLQuery(sqlCloth);
        queryCloth.addEntity(ClothEntity.class);
        List<ClothEntity> clothEntities = queryCloth.list();
        List<String> collect1 = clothEntities.stream().map(ClothEntity::getClothNamePL).collect(Collectors.toList());
        ObservableList comboCloth=
                FXCollections.observableArrayList(
                        collect1
                );
        c_cloth.setItems(comboCloth);

        c_branch.valueProperty().addListener((ov, t, t1) -> {
            List<DepartmentEntity> collect11 = departmentEntities.stream().filter(e -> e.getBranchEntity().getName()
                    .equals(c_branch.getValue().toString())).collect(Collectors.toList());
           List<String> listdep = collect11.stream().map(DepartmentEntity::getName).collect(Collectors.toList());
            ObservableList comboDep1 =
                    FXCollections.observableArrayList(
                            listdep
                    );
            c_department.setItems(comboDep1);
        });
        this.col_name = new TableColumn("Nazwa tkaniny");
        this.col_quantity = new TableColumn("Liczba tkaniny");


        col_name.setCellValueFactory(new PropertyValueFactory<ClothOrderDTO, String>("name"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<ClothOrderDTO, Integer>("quantity"));

        tableTextiles.getColumns().addAll(col_name, col_quantity);
        tableTextiles.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableTextiles.setItems(listViewitems);

        session2.getTransaction().commit();
        session2.close();
        c_branch.getSelectionModel().select(0);
        c_cloth.getSelectionModel().select(0);
        c_department.getSelectionModel().select(0);

    }
}
