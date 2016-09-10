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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TableView table_contractor;
    @FXML
    private TableView table_cloth;
    @FXML
    private TableView table_order;
    @FXML
    private TableView table_invoice;
    @FXML
    private TableView table_textile;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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


