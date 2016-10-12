package cysewska.com.services.textiles;

import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.DepartmentEntity;
import cysewska.com.models.entities.TextileEntity;
import cysewska.com.models.enums.Colors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ola on 2016-10-10.
 */
@Component
public class EditTextile implements Initializable {

    Stage stage;
    AnchorPane root;
    @FXML
    TextField t_quantity;
    @FXML
    TextField t_thickness;
    @FXML
    TextField t_name;

    @FXML
    ComboBox c_color;

    @Autowired
    MainView mainView;
    List<TextileEntity> list;
    public void save(){
        SessionFactory factory;
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();




        TextileEntity textileEntity = list.get(0);
        Transaction tx = null;
        try{

            tx = session.beginTransaction();
            textileEntity.setName(t_name.getText().toString());
            textileEntity.setColors(c_color.getValue().toString());
            textileEntity.setTextileQuantity(Integer.parseInt(t_quantity.getText().toString()));
            textileEntity.setTextileThickness(Integer.parseInt(t_quantity.getText().toString()));



        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.update(textileEntity);
            tx.commit();
            session.close();
        }
    }


    public void createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_textile.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Edytowanie tkaniny");
        stage.show();
        stage.getIcons().add(new Image("shop.png"));
        fxmlLoader.setController(EditTextile.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Colors> colorsList =
                FXCollections.observableArrayList(
                        Colors.values()
                );
        c_color.setItems(colorsList);
        SessionFactory factory;
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        String dep = "SELECT * FROM TEXTILE WHERE TEXTILE_NAME = :PARAM";
        SQLQuery depQuery = session.createSQLQuery(dep);
        depQuery.setParameter("PARAM",mainView.getSelected() );
        depQuery.addEntity(TextileEntity.class);
        list = depQuery.list();
        t_name.setText(list.get(0).getName());
        c_color.getSelectionModel().select(list.get(0).getColors());
        t_quantity.setText(list.get(0).getTextileQuantity().toString());
        t_thickness.setText(list.get(0).getTextileThickness().toString());
session.close();

    }
}
