package cysewska.com.views;



import cysewska.com.repositories.BranchRepository;
import cysewska.com.repositories.UserRepository;
import cysewska.com.views.cloth.ClothViewImp;
import cysewska.com.views.contractors.ContractorView;
import cysewska.com.views.invoice.InvoiceViewImp;
import cysewska.com.views.orders.OrderViewImp;
import cysewska.com.views.textile.TextileViewImp;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.FXMLView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


@FXMLController
@FXMLView("/complex.fxml")
public class MainView extends AbstractFxmlView implements Initializable {
    @Autowired
    ClothViewImp clothViewImp;
    @Autowired
    ContractorView contractorView;
    @Autowired
    OrderViewImp orderViewImp;
    @Autowired
    InvoiceViewImp invoiceViewImp;
    @Autowired
    TextileViewImp textileViewImp;
    @FXML
   private MenuItem mi_addContractor;
    @FXML
    private TableView table_contractor;
    @FXML
    private TableView table_cloth;
    @FXML
    private TableView table_order;
    @FXML
    private TableView table_invoice;
    @FXML
    private TableView table_textile;
    @FXML
    SplitPane okienko;
    public TableView getTableContractor() {
        return table_contractor;
    }
    public TableView getTableCloth() {
        return table_cloth;
    }
    public TableView getTableOrder() {
        return table_order;
    }
    public TableView getTableInvoice() {
        return table_invoice;
    }
    public TableView getTableTextile() {
        return table_textile;
    }

    public void button1(ActionEvent actionEvent) throws NoSuchFieldException {

    }

    @FXML
    public void addContractor(ActionEvent actionEvent) throws IOException {
//        AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("/fxml/crud_contractor.fxml"));
        AnchorPane root = (AnchorPane) FXMLLoader.load(MainView.class.getResource("/fxml/crud_contractor.fxml"));
     //  Parent root = FXMLLoader.load(getClass().getResource("/fxml/crud_contractor.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {


           /* AnchorPane root = (AnchorPane) FXMLLoader.load(MainView.class.getResource("/complex.fxml"));
            Stage window = (Stage) root.getScene().getWindow();
            window.setFullScreen(true);*/


//        Stage stage = (Stage) table_contractor.getScene().getWindow();
       // stage.setFullScreen(true);
        contractorView.createTableName();
        contractorView.setTableName();
        contractorView.fillTableData();

        clothViewImp.createClothsTable();
        clothViewImp.setTableClothColumnName();
        clothViewImp.fillClothTableData();

        orderViewImp.createTableName();
        orderViewImp.setTableName();
        orderViewImp.fillTableData();

        invoiceViewImp.createTableName();
        invoiceViewImp.setTableName();
        invoiceViewImp.fillTableData();

        textileViewImp.createTableName();
        textileViewImp.setTableName();
        textileViewImp.fillTableData();


    }



}


