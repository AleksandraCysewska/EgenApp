package cysewska.com.services.invoices;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXTextField;
import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.InvoiceEntity;
import cysewska.com.models.entities.OrderEntity;
import cysewska.com.models.enums.ELanguage;
import cysewska.com.models.enums.TypeOfPayment;
import cysewska.com.repositories.InvoiceRepository;
import cysewska.com.repositories.OrderRepository;
import cysewska.com.services.validators.services.IntegerValidator;
import cysewska.com.services.validators.ValidatorController;
import cysewska.com.services.validators.services.DoubleValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-09-25.
 */
@Component
public class AddInvoice implements Initializable {
@FXML
Button save;
    private AnchorPane root;
    Stage stage;
    @Autowired
    InvoiceRepository invoiceRepository;
    @FXML
    Label addOrder;
    @FXML
    JFXTextField t_netto;
    @FXML
    JFXTextField t_brutto;
    @FXML
    JFXTextField t_quantity;
    @FXML
    DatePicker t_createDate;
    @FXML
    DatePicker t_term;
    @FXML
    ComboBox c_order;
    @FXML
    ComboBox c_language;
    @FXML
    ComboBox c_payment;
    @Autowired
    CreateInvoices createInvoices;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MainView mainView;
    @Autowired
    ValidatorController validatorController;
    @Autowired
    InvoiceViewImp invoiceViewImp;
    private final Logger logger = Logger.getLogger(this.getClass());

    public void save(ActionEvent event) throws IOException, DocumentException {
        List<InvoiceEntity> results = invoiceRepository.findAll();
        List<OrderEntity> orderEntities = orderRepository.findAll();

        long l ;
        try{
             l = results.stream().max(Comparator.comparing(InvoiceEntity::getId)).get().getId() + 1;
        }catch(NoSuchElementException e){l=1;

            logger.error(e.getMessage());
        }


        invoiceRepository.save(new InvoiceEntity(
                l,
                "INVOICE " + l + " " + c_language.getValue().toString(),
                c_language.getValue().toString(),
                c_payment.getValue().toString(),
                t_term.getValue().toString(),
                Integer.valueOf(t_netto.getText().toString()),
                Integer.valueOf(t_brutto.getText().toString()),
                Integer.valueOf(t_quantity.getText().toString()),
                t_createDate.getValue().toString(),
                orderEntities.stream().filter(e -> e.getName().equals(c_order.getValue().toString())).findAny().get()

        ));


       mainView.getTableInvoice().setItems(null);
        invoiceViewImp.fillTableData();
        createInvoices.createInvoice();
        ((Node) (event.getSource())).getScene().getWindow().hide();


    }

    public void cancel(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }
    public void addNewOrder(ActionEvent event) {
        try {
            mainView.addOrder(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showWindow() {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_invoice.fxml"));
            fxmlLoader.setController(this);
        try {
            root = (AnchorPane)fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        stage = new Stage();
            stage.setTitle("Dodawanie faktury");
            stage.show();
        stage.getIcons().add(new Image("shop.png"));
            fxmlLoader.setController(AddInvoice.class);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(false);
        stage.setResizable(false);
            stage.show();
            stage.centerOnScreen();
            stage.toFront();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoubleValidator validator = new DoubleValidator();
        validatorController.setValidatorsIfContainLetter(validator, t_brutto, "Podaj nazwę tkaniny");

        DoubleValidator validator2 = new DoubleValidator();
        validatorController.setValidatorsIfContainLetter(validator2, t_netto, "Podaj nazwę tkaniny");

        IntegerValidator validator3 = new IntegerValidator();
        validatorController.setIntegerValidators(validator3, t_quantity, "Podaj nazwę tkaniny");


        addOrder.setVisible(false);
        ObservableList<ELanguage> languageList =
                FXCollections.observableArrayList(
                        ELanguage.values()
                );
        c_language.setItems(languageList);

        ObservableList<TypeOfPayment> typeOfPayments =
                FXCollections.observableArrayList(
                        TypeOfPayment.values()
                );
        c_payment.setItems(typeOfPayments);

        List<OrderEntity> results = orderRepository.findAll();


        List<String> ordersList = results.stream().map(OrderEntity::getName).collect(Collectors.toList());
        ObservableList<String> orderEntities =
                FXCollections.observableArrayList(
                        ordersList
                );
        c_order.setItems(orderEntities);

        if(orderEntities.size()==0){
            addOrder.setVisible(true);
            c_order.setVisible(false);
            save.setDisable(true);
        }
        c_order.getSelectionModel().selectFirst();
        c_language.getSelectionModel().selectFirst();
        c_payment.getSelectionModel().selectFirst();

    }

}
