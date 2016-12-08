package cysewska.com.services.invoices;

import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.ClothEntity;
import cysewska.com.models.entities.DepartmentEntity;
import cysewska.com.models.entities.InvoiceEntity;
import cysewska.com.models.entities.OrderEntity;
import cysewska.com.models.enums.ELanguage;
import cysewska.com.models.enums.TypeOfPayment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-10-10.
 */
@Component
public class EditInvoice implements Initializable {
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
    public void save(){
        SessionFactory factory;
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        InvoiceEntity invoiceEntity = null;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            invoiceEntity = (InvoiceEntity)session.get(InvoiceEntity.class, invoiceEntityList.get(0).getId());
            invoiceEntity.setOrderEntity(orders.stream().filter(e->e.getName().equals(c_order.getValue().toString())).findAny().get());
            invoiceEntity.setLanguage(c_language.getValue().toString());
            invoiceEntity.setDate(t_createDate.getValue().toString());
            invoiceEntity.setTypeOfPayment(c_payment.getValue().toString());
            invoiceEntity.setTermOfPayment(t_term.getValue().toString());
            invoiceEntity.setWeightNetto(Integer.parseInt(t_netto.getText()));
            invoiceEntity.setWeightBrutto(Integer.parseInt(t_brutto.getText()));
            invoiceEntity.setQuantityOfPallet(Integer.parseInt(t_quantity.getText().toString()));


        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.update(invoiceEntity);
            tx.commit();
            session.close();
        }
        mainView.getTableInvoice().setItems(null);
        invoiceViewImp.fillTableData();
    }

    @Autowired
    InvoiceViewImp invoiceViewImp;

    public void cancel(){}
    AnchorPane root;
    Stage stage;
    public void showWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_invoice.fxml"));
        fxmlLoader.setController(this);
        root = (AnchorPane)fxmlLoader.load();
        stage = new Stage();
        stage.getIcons().add(new Image("shop.png"));
        stage.setTitle("Edytowanie faktury");
        stage.show();
        fxmlLoader.setController(EditInvoice.class);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.show();
        stage.centerOnScreen();
        stage.toFront();

    }
@Autowired
    MainView mainView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();



        String sql = "SELECT * FROM ORDERS ";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(OrderEntity.class);
         orders = query.list();

        List<String> ordersList = orders.stream().map(OrderEntity::getName).collect(Collectors.toList());
        ObservableList<String> orderEntities =
                FXCollections.observableArrayList(
                        ordersList
                );
        c_order.setItems(orderEntities);



        String dep = "SELECT * FROM INVOICE WHERE INVOICE_NAME = :PARAM";
        SQLQuery depQuery = session.createSQLQuery(dep);
        depQuery.setParameter("PARAM", mainView.getSelected());
        depQuery.addEntity(InvoiceEntity.class);
   invoiceEntityList = depQuery.list();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");


        c_language.getSelectionModel().select(invoiceEntityList.get(0).getLanguage());
        c_payment.getSelectionModel().select(invoiceEntityList.get(0).getTypeOfPayment());
        c_order.getSelectionModel().select(invoiceEntityList.get(0).getOrderEntity().getName());
        t_createDate.setValue(LocalDate.parse(String.format(invoiceEntityList.get(0).getDate(), formatter)));
        t_term.setValue(LocalDate.parse(String.format(invoiceEntityList.get(0).getTermOfPayment(), formatter)));
        t_netto.setText(invoiceEntityList.get(0).getWeightNetto().toString());
        t_brutto.setText(invoiceEntityList.get(0).getWeightNetto().toString());
        t_quantity.setText(invoiceEntityList.get(0).getQuantityOfPallet().toString());

t_term.setPrefWidth(212);
        t_createDate.setPrefWidth(212);


        session.getTransaction().commit();
        session.close();

    }
}
