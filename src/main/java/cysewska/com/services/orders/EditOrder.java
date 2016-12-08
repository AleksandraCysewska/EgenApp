package cysewska.com.services.orders;

import cysewska.com.app.StartApp;
import cysewska.com.controllers.MainView;
import cysewska.com.models.dto.ClothOrderDTO;
import cysewska.com.models.dto.TextileClothDTO;
import cysewska.com.models.entities.*;
import cysewska.com.services.contractors.AddContractorWindow;
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
import org.springframework.core.annotation.Order;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-10-10.
 */
@Component
public class EditOrder implements Initializable {
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
    List<OrderEntity> list;
    List<DepartmentEntity> departmentEntities;
    List<BranchEntity> results3;
    public void saveOrder(){

        SessionFactory factory;
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        OrderEntity orderEntity = null;
        ClothEntity clothEntity = null;
        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            orderEntity = (OrderEntity) session.get(OrderEntity.class, list.get(0).getId());
            orderEntity.setNote(t_description.getText());
            orderEntity.setDateOfExecution(t_do.getValue().toString());
            orderEntity.setDateOfSubmit(t_create.getValue().toString());
            orderEntity.setDepartmentEntity(departmentEntities.stream().filter(e->e.getName().equals(c_department.getValue())).findAny().get());
            orderEntity.getDepartmentEntity().setBranchEntity(results3.stream().filter(e->e.getName().equals(c_branch.getValue())).findFirst().get());
            /*
            todo add cloths
             */






        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.update(clothEntity);
            tx.commit();
            session.close();


        }

    }







    public void add(){
    /*
    TODO REMEMBER OLD VALUE
     */

        textileClothDTOs.add(new ClothOrderDTO(c_cloth.getValue().toString(),Integer.parseInt(t_quantity.getText().toString())));
        for(int i=0;i<listViewitems.size();i++){
        listViewitems.remove(i);
        }
        listViewitems.setAll(textileClothDTOs);
        tableTextiles.setItems(listViewitems);
        System.out.println(listViewitems.size());
        t_quantity.setText(null);

    }
    public void delete(){

        textileClothDTOs.remove( tableTextiles.getSelectionModel().getSelectedItem());
        listViewitems.setAll(textileClothDTOs);
        tableTextiles.setItems(listViewitems);


    }
    AnchorPane root;
    Stage stage;
    public void createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_order.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));
        stage.setTitle("Edytowanie zamówienia");
        stage.show();
        fxmlLoader.setController(EditOrder.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }
@Autowired
    MainView mainView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SessionFactory sessionFactory2 = new Configuration().configure().buildSessionFactory();
        Session session2 = sessionFactory2.openSession();
        session2.beginTransaction();


        String dep = "SELECT * FROM ORDERS WHERE ORDER_NAME = :PARAM";
        SQLQuery depQuery = session2.createSQLQuery(dep);
        depQuery.setParameter("PARAM",mainView.getSelected() );
        depQuery.addEntity(OrderEntity.class);
         list = depQuery.list();


        String sql3 = "SELECT * FROM BRANCH ";
        SQLQuery query3 = session2.createSQLQuery(sql3);
        query3.addEntity(BranchEntity.class);
        results3 = query3.list();
        List<String> texxtileList = results3.stream().map(BranchEntity::getName).collect(Collectors.toList());
        ObservableList comboTextile =
                FXCollections.observableArrayList(
                        texxtileList
                );
        c_branch.setItems(comboTextile);

        String sqlDep = "SELECT * FROM DEPARTMENT ";
        SQLQuery queryDep = session2.createSQLQuery(sqlDep);
        queryDep.addEntity(DepartmentEntity.class);
       departmentEntities = queryDep.list();
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

        c_branch.getSelectionModel().select(list.get(0).getDepartmentEntity().getBranchEntity().getName());
        c_department.getSelectionModel().select(list.get(0).getDepartmentEntity().getName());
        t_create.setValue(LocalDate.parse(String.format(list.get(0).getDateOfSubmit(), formatter)));
        t_do.setValue(LocalDate.parse(String.format(list.get(0).getDateOfExecution(),formatter )));
        t_description.setText(list.get(0).getNote());

        Set<OrderClothEntity> orderClothEntities = list.get(0).getOrderClothEntities();

        ObservableList orders =
                FXCollections.observableArrayList(
                        orderClothEntities
                );


        tableTextiles.setItems(orders);

        Set<OrderClothEntity> textile_cloths = list.get(0).getOrderClothEntities();
        for (OrderClothEntity textile_cloth : textile_cloths) {

            listViewitems.add(new ClothOrderDTO(textile_cloth.getClothEntity().getClothNamePL(),
                    Integer.parseInt(textile_cloth.getQuantity().toString())));
        }
        session2.getTransaction().commit();
        session2.close();
    }
}
