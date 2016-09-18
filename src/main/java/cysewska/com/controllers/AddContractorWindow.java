package cysewska.com.controllers;

import cysewska.com.models.dto.ContractorDTO;
import cysewska.com.models.entities.BranchEntity;
import cysewska.com.models.entities.DepartmentEntity;
import cysewska.com.models.enums.TypeOfNip;
import cysewska.com.repositories.BranchRepository;
import cysewska.com.repositories.DepartmentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.dom4j.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.security.krb5.internal.crypto.EType;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-09-18.
 */
@Component
public class AddContractorWindow {

    private AnchorPane root;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    ComboBox t_branch,t_typeOfNip;
    TextField t_department,  t_nip,t_country,t_city,t_address,t_zip,t_email,t_telephone;
    Button save;
    Stage stage ;
    public void cos() throws IOException {

        createView();

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                        departmentRepository.save(new DepartmentEntity(
                        departmentRepository.findAll().stream().max(Comparator.comparing(DepartmentEntity::getId)).get().getId() + 1,
                        t_department.getText(),
                        TypeOfNip.valueOf( t_typeOfNip.getValue().toString()), t_nip.getText(), t_country.getText(),
                         t_city.getText(), t_address.getText(), t_zip.getText(), t_email.getText(),
                         t_telephone.getText(),
                         branchRepository.findAll().stream().filter(e -> e.getName().equals(t_branch.getValue())).findFirst().get()));


                stage.close();
            }
        });
        save.setLayoutX(302);
        save.setLayoutY(394);



        root.getChildren().addAll(save, t_address, t_branch, t_city, t_country, t_department, t_email, t_nip, t_telephone, t_typeOfNip, t_zip);

    }
    public void createView() throws IOException {
        root = ( AnchorPane) FXMLLoader.load(MainView.class.getResource("/fxml/crud_contractor.fxml"));
        Scene scene = new Scene(root);
         stage = new Stage();
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
        List<BranchEntity> all = branchRepository.findAll();
        List<String> nameBranch = all.stream().map(BranchEntity::getName).collect(Collectors.toList());

        ObservableList<String> optionsBranch =
                FXCollections.observableArrayList(
                        nameBranch
                );
        t_branch = new ComboBox(optionsBranch);
        t_branch.setLayoutX(205);
        t_branch.setLayoutY(23);
        t_branch.setPrefWidth(200);

         t_department = new TextField();
        t_department.setLayoutX(205);
        t_department.setLayoutY(62);
        t_department.setPrefWidth(200);

        ObservableList<TypeOfNip> options =
                FXCollections.observableArrayList(
                        TypeOfNip.EU,
                        TypeOfNip.PL
                );
        t_typeOfNip = new ComboBox(options);
        t_typeOfNip.setLayoutX(205);
        t_typeOfNip.setLayoutY(101);
        t_typeOfNip.setPrefWidth(200);


        t_nip = new TextField();
        t_nip.setLayoutX(205);
        t_nip.setLayoutY(137);
        t_nip.setPrefWidth(200);

         t_country = new TextField();
        t_country.setLayoutX(205);
        t_country.setLayoutY(176);
        t_country.setPrefWidth(200);

         t_city = new TextField();
        t_city.setLayoutX(205);
        t_city.setLayoutY(212);
        t_city.setPrefWidth(200);

         t_address = new TextField();
        t_address.setLayoutX(205);
        t_address.setLayoutY(246);
        t_address.setPrefWidth(200);

         t_zip = new TextField();
        t_zip.setLayoutX(205);
        t_zip.setLayoutY(282);
        t_zip.setPrefWidth(200);

         t_email = new TextField();
        t_email.setLayoutX(205);
        t_email.setLayoutY(318);
        t_email.setPrefWidth(200);

         t_telephone = new TextField();
        t_telephone.setLayoutX(205);
        t_telephone.setLayoutY(354);
        t_telephone.setPrefWidth(200);
         save = new Button("Zapisz");
    }
}
