package cysewska.com.controllers;



import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import cysewska.com.models.dto.ClothDTO;
import cysewska.com.models.dto.ContractorDTO;
import cysewska.com.models.dto.OrderDTO;
import cysewska.com.models.dto.TextileDTO;
import cysewska.com.models.entities.*;
import cysewska.com.repositories.*;
import cysewska.com.services.cloths.*;
import cysewska.com.services.contractors.*;
import cysewska.com.services.help.SendError;
import cysewska.com.services.invoices.*;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
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
    @Autowired
    CreateInvoices createInvoices;
    @Autowired
    AddBranch addBranch;
    @Autowired
    AddModel addModel;
    @Autowired
    AddPW addPW;
    public void addPW() throws IOException {

        addPW.showWindow();

    }
    public void addBranch() throws IOException {
        addBranch.showWindow();
    }
    @Autowired
    EditBranch editBranch;

    public void editBranch() throws IOException {

        List<BranchEntity> all = branchRepository.findAll();
        dialogData.addAll(all.stream().map(BranchEntity::getName).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setTitle("Edytowanie filii");
        choiceDialog.setHeaderText("Wybierz filię");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) {
            selected = result.get();}
        editBranch.showWindow();
    }

    public void addModel() throws IOException {
        addModel.showWindow();
    }
@Autowired
EditModel editModel;
    public void editModel() throws IOException {

        List<ModelEntity> all = modelRepository.findAll();
        dialogData.addAll(all.stream().map(ModelEntity::getModel).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setTitle("Edytowanie modelu");
        choiceDialog.setHeaderText("Wybierz model");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) {
            selected = result.get();}

        editModel.showWindow();

    }


    @FXML
    public void addInvoice(ActionEvent actionEvent) throws IOException, DocumentException {
        addInvoice.showWindow();
      //  createInvoices.createInvoice();
    }
    @Autowired
    SendError sendError;
    public void sendError() throws IOException {
        sendError.showWindow();
    }


    @Autowired
    AddContractorWindow add;
    @Autowired
    AddOrder addOrder;

    @FXML
    public void addContractor(ActionEvent actionEvent) throws IOException {
        add.createView();
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
    @Autowired
    BranchRepository branchRepository;
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

        t_search.textProperty().addListener((observable, oldValue, newValue) -> {
          switch(tabbedPane.getSelectionModel().getSelectedIndex())
          {
              case 0: {
                  List<BranchEntity> branchEntities = branchRepository.findAll();
                  List<BranchEntity> collect1 = null;
                  List<DepartmentEntity> collect2 = null;
                  List<DepartmentEntity> departmentEntities = departmentRepository.findAll();

                  switch (c_search.getSelectionModel().getSelectedIndex()) {
                      case 0: {
                          collect1 = branchEntities.stream().filter(
                                  (e) -> e.getName().toLowerCase().startsWith(t_search.getText().toLowerCase()) ||
                                          e.getName().toLowerCase().contains(t_search.getText().toLowerCase()))
                                  .collect(Collectors.toList());

                          collect2 = new ArrayList<>();
                          for (BranchEntity branchEntity : collect1) {
                              for (DepartmentEntity departmentEntity : branchEntity.getDepartments()) {
                                  collect2.add(
                                          new DepartmentEntity(departmentEntity.getId(),
                                                  departmentEntity.getName(),
                                                  departmentEntity.getTypeOfNip(),
                                                  departmentEntity.getNip(),
                                                  departmentEntity.getCountry(),
                                                  departmentEntity.getCity(),
                                                  departmentEntity.getAddress(),
                                                  departmentEntity.getZip(),
                                                  departmentEntity.getEmail(),
                                                  departmentEntity.getTelephone(),
                                                  branchEntity));
                              }
                          }
                          break;
                      }
                      case 1: {
                          collect2 = departmentEntities.stream().filter((e) ->
                                  e.getName().toLowerCase().startsWith(t_search.getText().toLowerCase())
                                          || e.getName().toLowerCase().contains(t_search.getText().toLowerCase())
                          ).collect(Collectors.toList());
                          break;
                      }
                      case 2: {
                          collect2 = departmentEntities.stream().filter((e) ->
                                  e.getAddress().toLowerCase().startsWith(t_search.getText().toLowerCase())
                                          || e.getAddress().toLowerCase().contains(t_search.getText().toLowerCase())
                          ).collect(Collectors.toList());
                          break;
                      }
                  }


                  List<ContractorDTO> contractorDTOs = new ArrayList<>();
                  if (collect2.size() > 0) {
                      contractorDTOs.addAll(collect2.stream().map(departmentEntity -> new ContractorDTO(
                              departmentEntity.getBranchEntity().getName(), departmentEntity.getName(),
                              departmentEntity.getNip(), departmentEntity.getCountry(),
                              departmentEntity.getCity(), departmentEntity.getAddress(),
                              departmentEntity.getZip(), departmentEntity.getEmail(),
                              departmentEntity.getTelephone()
                      )).collect(Collectors.toList()));
                  }
                  ObservableList<ContractorDTO> data;
                  data = FXCollections.observableArrayList(contractorDTOs);
                  table_contractor.setItems(data);
                  break;


              }
              case 1 : {
                  List<BranchEntity> branchEntities2 = branchRepository.findAll();
                  List<BranchEntity> collect11 = null;
                  List<OrderEntity> collect22 = null;
                  List<OrderEntity> departmentEntities2 = orderRepository.findAll();


                  switch (c_search.getSelectionModel().getSelectedIndex()) {
                      case 0: {
                          collect11 = branchEntities2.stream().filter(
                                  (e) -> e.getName().toLowerCase().startsWith(t_search.getText().toLowerCase()) ||
                                          e.getName().toLowerCase().contains(t_search.getText().toLowerCase()))
                                  .collect(Collectors.toList());

                          collect22 = new ArrayList<>();
                          for (BranchEntity branchEntity : collect11) {
                              for (DepartmentEntity departmentEntity : branchEntity.getDepartments()) {
                                  for (OrderEntity orderEntity : departmentEntity.getOrders()) {
                                      collect22.add(
                                              new OrderEntity(
                                                      orderEntity.getId(),
                                                      orderEntity.getName(),
                                                      orderEntity.getNote(),
                                                      orderEntity.getDateOfSubmit(),
                                                      orderEntity.getDateOfExecution(),
                                                      departmentEntity
                                                      ));
                                  }

                              }
                          }
                          break;
                      }
                      case 1: {
                          collect22 = departmentEntities2.stream().filter((e) ->
                                  e.getDepartmentEntity().getName().toLowerCase().startsWith(t_search.getText().toLowerCase())
                                          || e.getDepartmentEntity().getName().toLowerCase().contains(t_search.getText().toLowerCase())
                          ).collect(Collectors.toList());
                          break;
                      }
                      case 2: {
                          collect22 = departmentEntities2.stream().filter((e) ->
                                  e.getName().toLowerCase().startsWith(t_search.getText().toLowerCase())
                                          || e.getName().toLowerCase().contains(t_search.getText().toLowerCase())
                          ).collect(Collectors.toList());
                          break;
                      }
                      case 3 :
                      {
                          collect22 = departmentEntities2.stream().filter((e) ->
                                  e.getDateOfSubmit().toLowerCase().startsWith(t_search.getText().toLowerCase())
                                          || e.getDateOfSubmit().toLowerCase().contains(t_search.getText().toLowerCase())
                          ).collect(Collectors.toList());
                          break;
                      }
                      case 4 :
                      {
                          collect22 = departmentEntities2.stream().filter((e) ->
                                  e.getDateOfExecution().toLowerCase().startsWith(t_search.getText().toLowerCase())
                                          || e.getDateOfExecution().toLowerCase().contains(t_search.getText().toLowerCase())
                          ).collect(Collectors.toList());
                          break;
                      }
                  }
                  List<OrderDTO> contractorDTOs2 = new ArrayList<>();
                  if (collect22.size() > 0) {
                      contractorDTOs2.addAll(collect22.stream().map(departmentEntity -> new OrderDTO(
                              departmentEntity.getDepartmentEntity().getName(),
                              departmentEntity.getDepartmentEntity().getBranchEntity().getName(),
                              departmentEntity.getName(),
                              departmentEntity.getNote(),
                              departmentEntity.getDateOfSubmit(),
                              departmentEntity.getDateOfExecution()
                      )).collect(Collectors.toList()));
                  }
                  ObservableList<OrderDTO> data2;
                  data2 = FXCollections.observableArrayList(contractorDTOs2);
                  table_order.setItems(data2);
                  break;
              }
              case 2:
              {
                  List<ClothEntity> clothEntities = clothRepository.findAll();
                  List<ClothEntity> collect1 = null;


                  switch (c_search.getSelectionModel().getSelectedIndex()) {
                      case 0: {
                          collect1 = clothEntities.stream().filter(
                                  (e) -> e.getModelEntity().getModel().toLowerCase().startsWith(t_search.getText().toLowerCase()) ||
                                          e.getModelEntity().getModel().toLowerCase().contains(t_search.getText().toLowerCase()))
                                  .collect(Collectors.toList());
                          break;
                      }
                      case 1 :
                      {
                          collect1 = clothEntities.stream().filter(
                                  (e) -> e.getClothNamePL().toLowerCase().startsWith(t_search.getText().toLowerCase()) ||
                                          e.getClothNamePL().toLowerCase().contains(t_search.getText().toLowerCase()))
                                  .collect(Collectors.toList());
                          break;
                      }
                      case 2 :
                      {
                          collect1 = clothEntities.stream().filter(
                                  (e) -> e.getPricePl().toString().startsWith(t_search.getText()))
                                  .collect(Collectors.toList());
                          break;
                      }
                  }

                  List<ClothDTO> contractorDTOs2 = new ArrayList<>();
                  if (collect1.size() > 0) {
                      contractorDTOs2.addAll(collect1.stream().map(clothEntity -> new ClothDTO(
                              clothEntity.getModelEntity().getModel(),
                              clothEntity.getClothNamePL(),
                              clothEntity.getClothNameNO(),
                              clothEntity.getClothNameENG(),
                              clothEntity.getPriceEuro(),
                              clothEntity.getPricePl()

                      )).collect(Collectors.toList()));

                  }
                  ObservableList<ClothDTO> data2;
                  data2 = FXCollections.observableArrayList(contractorDTOs2);
                  table_cloth.setItems(data2);
                  break;
              }

              case 3:
              {
                  System.out.println("faktury");
                  break;
              }

              case 4:
              {
                  List<TextileEntity> textilRepositoryAll = textilRepository.findAll();
                  List<TextileEntity> collect1 = null;


                  switch (c_search.getSelectionModel().getSelectedIndex()) {
                      case 0: {
                          collect1 = textilRepositoryAll.stream().filter(
                                  (e) -> e.getName().toLowerCase().startsWith(t_search.getText().toLowerCase()) ||
                                          e.getName().toLowerCase().contains(t_search.getText().toLowerCase()))
                                  .collect(Collectors.toList());
                          break;
                      }
                      case 1: {
                          collect1 = textilRepositoryAll.stream().filter(
                                  (e) -> e.getColors().toLowerCase().startsWith(t_search.getText().toLowerCase()) ||
                                          e.getColors().toLowerCase().contains(t_search.getText().toLowerCase()))
                                  .collect(Collectors.toList());
                          break;
                      }
                      case 2: {
                          collect1 = textilRepositoryAll.stream().filter(
                                  (e) -> e.getTextileQuantity().toString().startsWith(t_search.getText()))
                                  .collect(Collectors.toList());
                          break;
                      }
                  }

                  List<TextileDTO> contractorDTOs2 = new ArrayList<>();
                  if (collect1.size() > 0) {
                      contractorDTOs2.addAll(collect1.stream().map(clothEntity -> new TextileDTO(
                              clothEntity.getName(),
                              clothEntity.getColors(),
                              clothEntity.getTextileQuantity(),
                              clothEntity.getTextileThickness()

                      )).collect(Collectors.toList()));

                  }
                  ObservableList<TextileDTO> data2;
                  data2 = FXCollections.observableArrayList(contractorDTOs2);
                  table_textile.setItems(data2);
                  break;
              }


          }


        });
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
    ModelRepository modelRepository;
    @Autowired
    InfoOrders infoOrders;
    public void showOrder() throws IOException {
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


        infoOrders.createView();


    }
    @FXML
    TextField t_search;
    @Autowired
    InfoCloth infoCloth;
    public void showCloth() throws IOException {

        List<ClothEntity> all = clothRepository.findAll();

        for (ClothEntity departmentEntity : all) {
            dialogData.add(departmentEntity.getClothNamePL());
        }
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setTitle("Informacje o towarze");
        choiceDialog.setHeaderText("Wybierz towar");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) {
            selected = result.get();
        }
        edit=true;

        infoCloth.createView();


    }
    @Autowired
    InfoInvoice infoInvoice;
    public void showInvoice() throws IOException {

        List<InvoiceEntity> all = invoiceRepository.findAll();
        dialogData.addAll(all.stream().map(InvoiceEntity::getName).collect(Collectors.toList()));
        ChoiceDialog choiceDialog = new ChoiceDialog(dialogData.get(0), dialogData);
        choiceDialog.setHeaderText("Wybierz fakturę");
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) selected = result.get();
        infoInvoice.showWindow();



    }

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
    @Autowired
    GenerateRaports generateRaports;
    public void generateRaport() throws FileNotFoundException, DocumentException {

        generateRaports.generate();

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


