package cysewska.com.controllers;

import cysewska.com.models.entities.*;
import cysewska.com.repositories.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by Ola on 2016-09-19.
 */
@Component
public class AddOrder {

    ComboBox c_cloth;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    ClothRepository clothRepository;

    Set<String> stringSet = new HashSet<>();
    @FXML
    AnchorPane root = null;
    @FXML
    TextField t_date_submit;
    @FXML
    TextField t_date_execution;
    @FXML
    TextArea t_description;
    @FXML
    TextField t_quantity;
    @FXML
    TextField t_name;
    @FXML
            Button add;
    List<OrderEntity> orders = new ArrayList<>();
    List<ClothEntity> clothEntities = new ArrayList<>();
    List<DepartmentEntity> departmentEntities = new ArrayList<>();
    ListView listView;

    Button b_save;

    ComboBox c_dep;
    @Autowired
    OrderRepository orderRepository;
@Autowired
    OrderClothRepository orderClothRepository;
    ObservableList<String> items = FXCollections.observableArrayList("Nazwy i ilości");
    ;
    ComboBox c_branch;

    public void delete(ActionEvent actionEvent) {
        int selectedItem = listView.getSelectionModel().getSelectedIndex();
        items.remove(selectedItem);
    }

    @FXML
    public void add(ActionEvent actionEvent) {

    }

    public ListView getListView() {
        return listView;
    }

    public void edit() {

    }


    public void saveOrder() {
        insert();


    }

    public void insert() {


    }
@Transactional
    public void showWindow() {
        orders = orderRepository.findAll();
        departmentEntities = departmentRepository.findAll();
        clothEntities = clothRepository.findAll();
        try {
            root = (AnchorPane) FXMLLoader.load(MainView.class.getResource("/fxml/add_order.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_order.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
 listView = new ListView();
        listView.setLayoutX(0);
        listView.setLayoutY(185);
        listView.setPrefHeight(215);
        listView.setPrefWidth(596);

                List<BranchEntity> branchEntities = branchRepository.findAll();
        ObservableList<String> listOfBranch = FXCollections.observableArrayList();
        ;

        for (BranchEntity branchEntity : branchEntities) {
            listOfBranch.addAll(branchEntity.getName());
        }
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        ObservableList<String> listOfDepartment = FXCollections.observableArrayList();
        ;

        for (DepartmentEntity departmentEntity : departmentEntities) {
            listOfDepartment.addAll(departmentEntity.getName());
        }
        c_branch = new ComboBox(listOfBranch);
        c_branch.setLayoutX(114);
        c_branch.setLayoutY(38);
        c_branch.setPrefWidth(200);
        c_branch.setPrefHeight(21);

        c_dep = new ComboBox(listOfDepartment);
        c_dep.setLayoutX(114);
        c_dep.setLayoutY(68);
        c_dep.setPrefWidth(200);
        c_dep.setPrefHeight(21);

        t_quantity = new TextField();
        t_quantity.setLayoutX(240);
        t_quantity.setLayoutY(109);
        t_quantity.setPrefWidth(200);

        t_name = new TextField();
        t_name.setLayoutX(14);
        t_name.setLayoutY(109);
        t_name.setPrefWidth(200);


        b_save = new Button("zapisz");
        b_save.setLayoutX(186);
        b_save.setLayoutY(142);

        add = new Button("dodaj");
        add.setLayoutX(460);
        add.setLayoutY(97);
        add.setPrefWidth(64);


                add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {


                String s = t_name.getText() + "," + t_quantity.getText();
                stringSet.add(s);
                items.setAll(stringSet);
                listView.setItems(items);
            }
        });

        t_date_submit = new TextField();
        t_date_submit.setLayoutX(114);
        t_date_submit.setLayoutY(11);
        t_date_submit.setPrefWidth(144);

        t_date_execution = new TextField();
        t_date_execution.setLayoutX(440);
        t_date_execution.setLayoutY(14);
        t_date_execution.setPrefWidth(144);

        t_description = new TextArea();
        t_description.setLayoutX(384);
        t_description.setLayoutY(46);
        t_description.setPrefWidth(200);
        t_description.setPrefHeight(38);
        t_description.setWrapText(true);

    c_branch.setOnAction((event) -> {
        List<DepartmentEntity> collect = departmentRepository.findAll().stream().filter(e -> e.getBranchEntity().getName().equals(c_branch.getSelectionModel().getSelectedItem().toString())).collect(Collectors.toList());
        ObservableList<String> departmentList = FXCollections.observableArrayList();
        departmentList.addAll(collect.stream().map(DepartmentEntity::getName).collect(Collectors.toList()));
        c_dep.setItems(departmentList);
    });

        b_save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

              Set<OrderClothEntity> addListCloth = new HashSet<>();

                OrderEntity orderEntity = new OrderEntity();
                long l = orders.stream().max(Comparator.comparing(OrderEntity::getId)).get().getId() + 1;
    /*
    TODO SPLIT STRING , INTO 2 Tables
     */
                ObservableList<String> items = listView.getItems();
                ArrayList<String> quantities = new ArrayList<>();
                ArrayList<String> cloth = new ArrayList<>();
                int y=0;
                System.out.println(items.size());
                for (int i = 0; i < items.size(); i++) {
                    String[]   parts = items.get(i).split(",");
                    for (String part : parts) {

                        System.out.println(part + "   " + i );
                        if (y==1) {
                            quantities.add(part)  ;
                            y=0;
                        }
                        else {
                            cloth.add(part);
                            y++;
                        }

                    }
                }


                OrderEntity order = new OrderEntity(
                        l,
                        "ORDER " +l,
                        t_description.getText().toString(),
                        t_date_submit.getText().toString(),
                        t_date_execution.getText(),
                        departmentEntities.stream().filter(e -> e.getName().equals(c_dep.getValue())).findAny().get()

                );

                orderRepository.save(order);
                ArrayList<String> clothEntityArrayList = new ArrayList<>(listView.getItems());
                int k=0;
                for (String clothEntity : clothEntityArrayList) {
                    final int j=k;
                    ClothEntity clothEntity1 = clothRepository.findAll().stream().filter(e -> e.getClothNamePL().equals(cloth.get(j))).findFirst().get();
                OrderClothEntity i =     new OrderClothEntity(orderClothRepository.findAll().stream().max(Comparator.comparing(OrderClothEntity::getId)).get().getId() + 1,
                            Long.valueOf( quantities.get(k)),
                            order,
                            clothEntity1);
                    addListCloth.add( i);
                    orderClothRepository.save(i);


                    k++;
                }




                stage.close();
            }
        });
        root.getChildren().addAll(c_branch, c_dep, b_save, listView, add, t_quantity, t_name, t_date_submit, t_date_execution, t_description);


    }
}
