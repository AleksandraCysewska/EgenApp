package cysewska.com.controllers;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainView implements Initializable {
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

    @Autowired
    AddContractorWindow add;
    @Autowired
    AddOrder addOrder;
    @FXML
    public void addContractor(ActionEvent actionEvent) throws IOException {

        add.cos();
    }
    @FXML
    public void addOrder(ActionEvent actionEvent)
    {
        addOrder.showWindow();
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


