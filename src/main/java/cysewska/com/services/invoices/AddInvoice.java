package cysewska.com.services.invoices;

import com.itextpdf.text.DocumentException;
import cysewska.com.controllers.MainView;
import cysewska.com.models.entities.InvoiceEntity;
import cysewska.com.models.entities.ModelEntity;
import cysewska.com.models.entities.OrderEntity;
import cysewska.com.models.enums.ELanguage;
import cysewska.com.models.enums.TypeOfPayment;
import cysewska.com.repositories.InvoiceRepository;
import cysewska.com.repositories.OrderRepository;
import cysewska.com.services.contractors.AddContractorWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-09-25.
 */
@Component
public class AddInvoice implements Initializable {

    private AnchorPane root;
    Stage stage;

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
    @FXML
    ComboBox c_payment;
    @Autowired
    CreateInvoices createInvoices;
    public void save() throws IOException, DocumentException {


        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String sql = "SELECT * FROM INVOICE ";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(InvoiceEntity.class);
        List<InvoiceEntity> results = query.list();

        String sqlOrder = "SELECT * FROM ORDERS ";
        SQLQuery queryOrder = session.createSQLQuery(sqlOrder);
        queryOrder.addEntity(OrderEntity.class);
        List<OrderEntity> orderEntities = queryOrder.list();

        long l = results.stream().max(Comparator.comparing(InvoiceEntity::getId)).get().getId() + 1;

        session.save(new InvoiceEntity(
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
        session.getTransaction().commit();
        session.close();

        mainView.getTableInvoice().setItems(null);
        invoiceViewImp.fillTableData();
        createInvoices.createInvoice();


    }
    @Autowired
    MainView mainView;
    @Autowired
    InvoiceViewImp invoiceViewImp;
    public void cancel() {

    }

    public void showWindow() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/add_invoice.fxml"));
            fxmlLoader.setController(this);
            root = (AnchorPane)fxmlLoader.load();
            stage = new Stage();
            stage.setTitle("Dodawanie faktury");
            stage.show();
        stage.getIcons().add(new Image("shop.png"));
            fxmlLoader.setController(AddInvoice.class);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(false);
            stage.show();
            stage.centerOnScreen();
            stage.toFront();

    }

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
        List<OrderEntity> results = query.list();

        List<String> ordersList = new ArrayList<>();
        for (OrderEntity result : results) {
            ordersList.add(result.getName());
        }
        ObservableList<String> orderEntities =
                FXCollections.observableArrayList(
                        ordersList
                );
        c_order.setItems(orderEntities);

        session.getTransaction().commit();
        session.close();
        t_term.setPrefWidth(212);
        t_createDate.setPrefWidth(212);
    }

}
