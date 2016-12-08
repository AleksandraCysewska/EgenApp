package cysewska.com.services.textiles;

import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.InvoiceEntity;
import cysewska.com.models.entities.TextileEntity;
import cysewska.com.models.enums.Colors;
import cysewska.com.models.enums.TypeOfPayment;
import cysewska.com.repositories.TextilRepository;
import cysewska.com.services.contractors.AddContractorWindow;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ola on 2016-10-03.
 */
@Component
public class AddTextile implements Initializable {

    AnchorPane root;
    Stage stage;
    @FXML
    TextField t_quantity;
    @FXML
    TextField t_thickness;
    @FXML
    TextField t_name;

    @FXML
    ComboBox c_color;


    public void showWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_textile.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Dodawanie tkanin");
        stage.show();
        stage.getIcons().add(new Image("shop.png"));
        fxmlLoader.setController(AddTextile.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }
    public void save() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "SELECT * FROM TEXTILE ";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(TextileEntity.class);
        List<TextileEntity> results = query.list();


        session.save(new TextileEntity(
              results.stream().max(Comparator.comparing(TextileEntity::getId)).get().getId() + 1,
                t_name.getText(),
                c_color.getValue().toString(),
                Integer.parseInt(t_quantity.getText().toString()),
                Integer.parseInt( t_thickness.getText().toString())
        ));

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Colors> colorsList =
                FXCollections.observableArrayList(
                        Colors.values()
                );
        c_color.setItems(colorsList);
    }
}
