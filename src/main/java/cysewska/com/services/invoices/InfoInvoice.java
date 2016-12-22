package cysewska.com.services.invoices;

import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.InvoiceEntity;
import cysewska.com.models.entities.OrderEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ola on 2016-10-20.
 */
@Component
public class InfoInvoice implements Initializable {
    @FXML
    TextField t_netto;
    @FXML
    TextField t_brutto;
    @FXML
    TextField t_quantity;
    @FXML
    DatePicker t_createDate;
    @FXML
    DatePicker t_term;
    @FXML
    ComboBox c_order;
    @FXML
    ComboBox c_language;
    List<OrderEntity> orders;
    @FXML
    ComboBox c_payment;
    List<InvoiceEntity> invoiceEntityList;
    AnchorPane root;
    Stage stage;
    public void showWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_invoice.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));
        stage.setTitle("Informacje o fakturze");
        stage.show();
        fxmlLoader.setController(InfoInvoice.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();

    }
    public void addNewOrder() {

    }
    public void cancel() {}
    public void save(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
@Autowired
MainView mainView;
    @FXML
    Button save, cancel;
    @FXML
    GridPane gridMain;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String dep = "SELECT * FROM INVOICE WHERE INVOICE_NAME = :PARAM";
        SQLQuery depQuery = session.createSQLQuery(dep);
        depQuery.setParameter("PARAM", mainView.getSelected());
        depQuery.addEntity(InvoiceEntity.class);
        invoiceEntityList = depQuery.list();


        Label l1 = new Label(invoiceEntityList.get(0).getDate());
        t_createDate.setVisible(false);
        gridMain.add(l1, 1,3);

        Label l2 = new Label(invoiceEntityList.get(0).getTermOfPayment());
        t_term.setVisible(false);
        gridMain.add(l2, 1,5);

        Label l3 = new Label(invoiceEntityList.get(0).getWeightNetto().toString());
        t_netto.setVisible(false);
        gridMain.add(l3, 1,6);

        Label l4 = new Label(invoiceEntityList.get(0).getWeightNetto().toString());
        t_brutto.setVisible(false);
        gridMain.add(l4, 1,7);

        Label l5 = new Label(invoiceEntityList.get(0).getOrderEntity().getName());
        c_order.setVisible(false);
        gridMain.add(l5, 1,1);

        Label l6 = new Label(invoiceEntityList.get(0).getLanguage());
        c_language.setVisible(false);
        gridMain.add(l6, 1,2);

        Label l7 = new Label(invoiceEntityList.get(0).getTypeOfPayment());
        c_payment.setVisible(false);
        gridMain.add(l7, 1,4);


        Label l8 = new Label(invoiceEntityList.get(0).getTypeOfPayment());
        t_quantity.setVisible(false);
        gridMain.add(l8, 1,8);

     save.setText("Ok");
        cancel.setVisible(false);

        t_term.setPrefWidth(212);
        t_createDate.setPrefWidth(212);


        session.getTransaction().commit();
        session.close();
    }
}
