package cysewska.com.controllers;



import cysewska.com.models.dto.ContractorDTO;
import cysewska.com.models.entities.*;
import cysewska.com.repositories.*;
import cysewska.com.services.cloths.AddCloth;
import cysewska.com.services.cloths.ClothViewImp;
import cysewska.com.services.cloths.EditCloth;
import cysewska.com.services.contractors.AddContractorWindow;
import cysewska.com.services.contractors.ContractorView;
import cysewska.com.services.contractors.EditContractor;
import cysewska.com.services.invoices.AddInvoice;
import cysewska.com.services.invoices.EditInvoice;
import cysewska.com.services.invoices.InvoiceViewImp;
import cysewska.com.services.orders.AddOrder;
import cysewska.com.services.orders.EditOrder;
import cysewska.com.services.orders.InfoOrders;
import cysewska.com.services.orders.OrderViewImp;
import cysewska.com.services.textiles.AddTextile;
import cysewska.com.services.textiles.EditTextile;
import cysewska.com.services.textiles.TextileViewImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MainView implements Initializable {


    boolean edit;

    public boolean isEdit() {
        return edit;
    }

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
    @FXML
    ComboBox c_search;
@Autowired
    OrderRepository orderRepository;
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

    @Autowired
    AddInvoice addInvoice;

    @FXML
    public void addInvoice(ActionEvent actionEvent) throws IOException {
        edit = false;
        addInvoice.showWindow();
    }

    public void button1(ActionEvent actionEvent) throws NoSuchFieldException {
    }

    @Autowired
    AddContractorWindow add;
    @Autowired
    AddOrder addOrder;

    @FXML
    public void addContractor(ActionEvent actionEvent) throws IOException {
        edit = false;
        add.cos(selected, ""+edit);
    }

    @Autowired
    AddCloth addCloth;
    @Autowired
    AddTextile addTextile;

    @FXML
    public void addTextile(ActionEvent actionEvent) throws IOException {
        edit = false;
        addTextile.showWindow();

    }

    @FXML
    public void add_cloth(ActionEvent actionEvent) throws IOException {

        edit = false;
        addCloth.createView();
    }

    @FXML
    public void addOrder(ActionEvent actionEvent) throws IOException {
        edit = false;
        addOrder.createView();
    }
@FXML
TabPane tabbedPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> comboContractor =
                FXCollections.observableArrayList(
                      "Filia", "Oddział", "Adres");

        ObservableList<String> comboInvoice =
                FXCollections.observableArrayList(
                        "Filia", "Oddział", "Nazwa faktury","Data wystawienia", "Termin platności");

        ObservableList<String> comboOrder =
                FXCollections.observableArrayList(
                        "Filia", "Oddział", "Nazwa zamówienia","Data wystawienia", "Data realizacji");

        ObservableList<String> comboCloths =
                FXCollections.observableArrayList(
                        "Model", "Nazwa ubrania", "Cena");

        ObservableList<String> comboTextiles =
                FXCollections.observableArrayList(
                       "Nazwa tkaniny", "Kolor", "Liczba");

        c_search.getItems().setAll(comboContractor);
        c_search.getSelectionModel().select(0);

        tabbedPane.getSelectionModel().selectedItemProperty().addListener(
                (ov, t, t1) -> {
                    if (tabbedPane.getSelectionModel().isSelected(0)) {
                        c_search.getItems().setAll(comboContractor);
                        c_search.getSelectionModel().select(0);
                    }
                    if (tabbedPane.getSelectionModel().isSelected(1)) {
                        c_search.getItems().setAll(comboOrder);
                        c_search.getSelectionModel().select(0);
                    }
                    if (tabbedPane.getSelectionModel().isSelected(2)) {
                        c_search.getItems().setAll(comboCloths);
                        c_search.getSelectionModel().select(0);
                    }
                    if (tabbedPane.getSelectionModel().isSelected(3)) {
                        c_search.getItems().setAll(comboInvoice);
                        c_search.getSelectionModel().select(0);
                    }
                    if (tabbedPane.getSelectionModel().isSelected(4)) {
                        c_search.getItems().setAll(comboTextiles);
                        c_search.getSelectionModel().select(0);
                    }
                }
        );
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
public void refreshContractor(){
    table_contractor.refresh();

}
    ContractorDTO selectedItem;
    @Autowired
    InfoOrders infoOrders;
    public void showOrder() throws IOException {
        infoOrders.createView();


    }
    public void showCloth(){}
    public void showInvoice(){}

    public ContractorDTO getSelectedItem() {
        return selectedItem;
    }

    public String getSelected() {
        return selected;
    }

    @Autowired

    DepartmentRepository departmentRepository;
    private final String[] arrayData = {"323"};
    private List<String> dialogData = new ArrayList<>();
    String selected="cancel";


    @Autowired
    EditContractor editContractor;
    public void editContractor() throws IOException {

        List<DepartmentEntity> all = departmentRepository.findAll();

        for (DepartmentEntity departmentEntity : all) {
            dialogData.add(departmentEntity.getName());
        }


        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setTitle("Edycja kontrahenta");
        choiceDialog.setHeaderText("Wybierz kontrahenta");


        Optional<String> result = choiceDialog.showAndWait();


        if (result.isPresent()) {

            selected = result.get();
            System.out.println("****" + selected);
        }
     //   selectedItem = (ContractorDTO) table_contractor.getSelectionModel().getSelectedItem();
        edit=true;
        editContractor.createView();
    }
    @Autowired
    ClothRepository clothRepository;
@Autowired
    EditCloth editCloth;
    @Autowired
    EditOrder editOrder;
    public void editOrder(
    ) throws IOException {

        List<OrderEntity> all = orderRepository.findAll();

        for (OrderEntity departmentEntity : all) {
            dialogData.add(departmentEntity.getName());
        }


        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setTitle("Edycja zamówienia");
        choiceDialog.setHeaderText("Wybierz zamówienie");


        Optional<String> result = choiceDialog.showAndWait();


        if (result.isPresent()) {

            selected = result.get();
            System.out.println("****" + selected);
        }
        //   selectedItem = (ContractorDTO) table_contractor.getSelectionModel().getSelectedItem();
        edit=true;
        editOrder.createView();
    }
    public void editCloth() throws IOException {
        List<ClothEntity> all = clothRepository.findAll();

        for (ClothEntity departmentEntity : all) {
            dialogData.add(departmentEntity.getClothNamePL());
        }
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setTitle("Edycja towaru");
        choiceDialog.setHeaderText("Wybierz towar");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) {
            selected = result.get();
        }
        edit=true;
        editCloth.createView();



    }


    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    EditInvoice editInvoice;
    public void editInvoice() throws IOException {
        List<InvoiceEntity> all = invoiceRepository.findAll();
        dialogData.addAll(all.stream().map(InvoiceEntity::getName).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setHeaderText("Wybierz fakturę");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) selected = result.get();
        editInvoice.showWindow();
    }
@Autowired
    TextilRepository textilRepository;
    @Autowired
    EditTextile editTextile;
    public void editTextile() throws IOException {

        List<TextileEntity> all = textilRepository.findAll();
        dialogData.addAll(all.stream().map(TextileEntity::getName).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setHeaderText("Wybierz tkaninę");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) selected = result.get();


        editTextile.createView();

    }


}


