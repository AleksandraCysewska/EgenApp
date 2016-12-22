package cysewska.com.controllers;


import com.itextpdf.text.*;
import cysewska.com.models.dto.ContractorDTO;
import cysewska.com.models.entities.*;
import cysewska.com.repositories.*;
import cysewska.com.services.SearchItems;
import cysewska.com.services.cloths.*;
import cysewska.com.services.contractors.*;
import cysewska.com.services.help.SendError;
import cysewska.com.services.invoices.*;
import cysewska.com.services.login.Login;
import cysewska.com.services.orders.AddOrder;
import cysewska.com.services.orders.EditOrder;
import cysewska.com.services.orders.InfoOrders;
import cysewska.com.services.orders.OrderViewImp;
import cysewska.com.services.textiles.AddPW;
import cysewska.com.services.textiles.AddTextile;
import cysewska.com.services.textiles.EditTextile;
import cysewska.com.services.textiles.TextileViewImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


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
    @Autowired
    AddInvoice addInvoice;
    @Autowired
    CreateInvoices createInvoices;
    @Autowired
    AddBranch addBranch;
    @Autowired
    AddModel addModel;
    @Autowired
    AddPW addPW;
    @Autowired
    EditBranch editBranch;
    @Autowired
    EditModel editModel;
    @Autowired
    SendError sendError;
    @Autowired
    AddContractorWindow add;
    @Autowired
    AddOrder addOrder;
    @Autowired
    AddCloth addCloth;
    @Autowired
    AddTextile addTextile;
    @FXML
    MenuBar menu;
    @Autowired
    BranchRepository branchRepository;
    @FXML
    TabPane tabbedPane;
    @Autowired
    Login login;
    @Autowired
    SearchItems searchItems;
    ContractorDTO selectedItem;
    @Autowired
    ModelRepository modelRepository;
    @Autowired
    InfoOrders infoOrders;
    @FXML
    TextField t_search;
    @Autowired
    InfoCloth infoCloth;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    ClothRepository clothRepository;
    @Autowired
    EditCloth editCloth;
    @Autowired
    EditOrder editOrder;
    @Autowired
    GenerateRaports generateRaports;
    @Autowired
    TextilRepository textilRepository;
    @Autowired
    EditTextile editTextile;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    EditInvoice editInvoice;
    @Autowired
    InfoInvoice infoInvoice;
    @Autowired
    EditContractor editContractor;
    ObservableList<String> comboInvoice;
    ObservableList<String> comboOrder;
    ObservableList<String> comboCloths;
    ObservableList<String> comboTextiles;
    ObservableList<String> comboContractor;
    private List<String> dialogData = new ArrayList<>();
    String selected = "cancel";
    private final Logger logger = Logger.getLogger(ch.qos.logback.core.ConsoleAppender.class);

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

    public void addPW() throws IOException {
        addPW.showWindow();
    }

    public void addBranch() throws IOException {
        addBranch.showWindow();
    }

    public void editBranch() throws IOException {
        List<BranchEntity> all = branchRepository.findAll();
        dialogData.addAll(all.stream().map(BranchEntity::getName).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setTitle("Edytowanie filii");
        choiceDialog.setHeaderText("Wybierz filię");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) {
            selected = result.get();
        }
        editBranch.showWindow();
    }

    public void addModel() throws IOException {
        addModel.showWindow();
    }

    public void editModel() throws IOException {
        List<ModelEntity> all = modelRepository.findAll();
        dialogData.addAll(all.stream().map(ModelEntity::getModel).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setTitle("Edytowanie modelu");
        choiceDialog.setHeaderText("Wybierz model");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) {
            selected = result.get();
        }
        editModel.showWindow();
    }

    public void addInvoice(ActionEvent actionEvent) throws IOException, DocumentException {
        addInvoice.showWindow();
    }

    public void sendError() throws IOException {
        sendError.showWindow();
    }

    public void addContractor(ActionEvent actionEvent)  {

        add.createView();
    }

    public void addTextile(ActionEvent actionEvent) throws IOException {
        addTextile.showWindow();
    }

    public void add_cloth(ActionEvent actionEvent) throws IOException {
        addCloth.createView();
    }

    public void addOrder(ActionEvent actionEvent) throws IOException {
        addOrder.createView();
    }

    public void setComboSearchItems() {
        comboContractor = FXCollections.observableArrayList(
                "Filia", "Oddział", "Adres");
        comboInvoice = FXCollections.observableArrayList(
                "Filia", "Oddział", "Nazwa faktury", "Data wystawienia", "Termin platności");
        comboOrder = FXCollections.observableArrayList(
                "Filia", "Oddział", "Nazwa zamówienia", "Data wystawienia", "Data realizacji");
        comboCloths = FXCollections.observableArrayList(
                "Model", "Nazwa ubrania", "Cena");
        comboTextiles = FXCollections.observableArrayList(
                "Nazwa tkaniny", "Kolor", "Liczba");
    }

  /*  public void doStuffBeforeLogged(){
        menu.setDisable(true);
        table_contractor.setItems(null);
        table_textile.setItems(null);
        table_cloth.setItems(null);
        table_invoice.setItems(null);
        table_order.setItems(null);
        tabbedPane.setDisable(true);
        c_search.setDisable(true);
        t_search.setDisable(true);
    }
*/
    public void doStuffAfterLogged(){
        textileViewImp.fillTableData();
        invoiceViewImp.fillTableData();
        orderViewImp.fillTableData();
        clothViewImp.fillClothTableData();
        contractorView.fillTableData();
        menu.setDisable(false);
        tabbedPane.setDisable(false);
        c_search.setDisable(false);
        t_search.setDisable(false);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchItems.searchItems(t_search, tabbedPane, c_search,
                table_contractor, table_order, table_cloth, table_textile);
        okienko.setStyle("-fx-box-border: transparent;");
        setComboSearchItems();
        c_search.getItems().setAll(comboContractor);
        c_search.getSelectionModel().select(0);
        doStuffAfterLogged();
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
        clothViewImp.createClothsTable();
        clothViewImp.setTableClothColumnName();
        orderViewImp.createTableName();
        orderViewImp.setTableName();
        invoiceViewImp.createTableName();
        invoiceViewImp.setTableName();
        textileViewImp.createTableName();
        textileViewImp.setTableName();
//        doStuffBeforeLogged();
       // doStuffAfterLogged();
        try {
            login.showWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void showOrder() throws IOException {
        List<OrderEntity> all = orderRepository.findAll();
        dialogData.addAll(all.stream().map(OrderEntity::getName).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setTitle("Edycja zamówienia");
        choiceDialog.setHeaderText("Wybierz zamówienie");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) {
            selected = result.get();
        }
        infoOrders.createView();
    }

    public void showCloth() throws IOException {
        List<ClothEntity> all = clothRepository.findAll();
        dialogData.addAll(all.stream().map(ClothEntity::getClothNamePL).collect(Collectors.toList()));
        try{
            ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
            choiceDialog.setTitle("Informacje o towarze");
            choiceDialog.setHeaderText("Wybierz towar");
            Optional<String> result = choiceDialog.showAndWait();
            if (result.isPresent()) {
                selected = result.get();
            }
            infoCloth.createView();
        }catch(IndexOutOfBoundsException e){
            logger.error("Brak danych dla towarów");
        }


    }

    public void showInvoice() throws IOException {
        List<InvoiceEntity> all = invoiceRepository.findAll();
        dialogData.addAll(all.stream().map(InvoiceEntity::getName).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setHeaderText("Wybierz fakturę");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) selected = result.get();
        infoInvoice.showWindow();
    }

    public String getSelected() {
        return selected;
    }

    public void editContractor() {
        List<DepartmentEntity> all = null;
         all = departmentRepository.findAll();


         List<String> dialog = new ArrayList<>();
        dialog.addAll(all.stream().map(DepartmentEntity::getName).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialog.get(0), dialog);
        choiceDialog.setTitle("Edycja kontrahenta");
        choiceDialog.setHeaderText("Wybierz kontrahenta");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) {
            selected = result.get();
            editContractor.createView();
        }

    }

    public void editOrder() throws IOException {
        List<OrderEntity> all = orderRepository.findAll();
        dialogData.addAll(all.stream().map(OrderEntity::getName).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setTitle("Edycja zamówienia");
        choiceDialog.setHeaderText("Wybierz zamówienie");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) {
            selected = result.get();
        }
        editOrder.createView();
    }

    public void generateRaport() throws FileNotFoundException, DocumentException {
        generateRaports.generate();
    }

    public void editCloth() throws IOException {
        List<ClothEntity> all = clothRepository.findAll();
        dialogData.addAll(all.stream().map(ClothEntity::getClothNamePL).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setTitle("Edycja towaru");
        choiceDialog.setHeaderText("Wybierz towar");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) {
            selected = result.get();
        }
        editCloth.createView();
    }

    public void editInvoice() throws IOException {
        List<InvoiceEntity> all = invoiceRepository.findAll();
        dialogData.addAll(all.stream().map(InvoiceEntity::getName).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setHeaderText("Wybierz fakturę");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) selected = result.get();
        editInvoice.showWindow();
    }

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


