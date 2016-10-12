package cysewska.com.services.orders;

import cysewska.com.services.textiles.AddTextile;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ola on 2016-10-12.
 */
@Component
public class InfoOrders implements Initializable{

AnchorPane root;
    Stage stage;

public void delete(){}
    public void add(){}
    public void saveOrder(){}

    public void createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_order.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Informacje o zamówieniu");
        stage.show();
        fxmlLoader.setController(InfoOrders.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
