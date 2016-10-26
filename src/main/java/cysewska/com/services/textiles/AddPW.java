package cysewska.com.services.textiles;

import cysewska.com.controllers.MainView;
import cysewska.com.services.contractors.AddBranch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Ola on 2016-10-26.
 */
@Component
public class AddPW {

    @Autowired
    MainView mainView;
    AnchorPane root;
    Stage stage;
    @FXML
    TableView tab_textile;



    public void showWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_pw.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));
        stage.setTitle("Przyjęcie materiałów");
        stage.show();
        fxmlLoader.setController(AddPW.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();
    }
    public void save(){}
    public void cancel(){}
    public void add(){}
    public void delete(){}

}
