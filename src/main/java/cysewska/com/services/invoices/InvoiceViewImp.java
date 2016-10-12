package cysewska.com.services.invoices;

import cysewska.com.controllers.MainView;
import cysewska.com.models.dto.InvoiceDTO;
import cysewska.com.models.entities.InvoiceEntity;
import cysewska.com.models.enums.TypeOfPayment;
import cysewska.com.repositories.InvoiceRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ola on 2016-09-10.
 */
@Component
public class InvoiceViewImp {
    @Autowired
    MainView mainView;
    @Autowired
    InvoiceRepository invoiceRepository;

    public TableColumn branchName, departmentName, name,  language,  date,  typeOfPayment,  termOfPayment,  weightNetto,  weightBrutto,  quantityOfPallet;


    public void createTableName() {

        this.name = new TableColumn("Nazwa fakturty");
        this.language = new TableColumn("Język");
        this.branchName = new TableColumn("Filia");
        this.departmentName = new TableColumn("Oddział");
        this.date = new TableColumn("Data wystawienia");
        this.typeOfPayment = new TableColumn("Typ płatności");
        this.termOfPayment = new TableColumn("Termin platności");
        this.weightNetto = new TableColumn("Waga netto");
        this.weightBrutto = new TableColumn("Waga brutto");
        this.quantityOfPallet = new TableColumn("Ilość palet");


    }

    public void setTableName() {
        branchName.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, String>("branchName"));
        departmentName.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, String>("departmentName"));
        name.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, String>("name"));
        language.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, String>("language"));
        date.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, String>("date"));
        typeOfPayment.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, TypeOfPayment>("typeOfPayment"));
        termOfPayment.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, String>("termOfPayment"));
        quantityOfPallet.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, Integer>("quantityOfPallet"));
        weightNetto.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, Integer>("weightNetto"));
        weightBrutto.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, Integer>("weightBrutto"));

        this.mainView.getTableInvoice().getColumns().addAll(branchName, departmentName, name,  language,  date,  typeOfPayment,  termOfPayment,  weightNetto,  weightBrutto,  quantityOfPallet);

    }

    public void fillTableData() {

        List<InvoiceEntity> invoiceEntities = invoiceRepository.findAll();
        List<InvoiceDTO> invoiceDTOs = new ArrayList<>();
        for (InvoiceEntity invoiceEntity : invoiceEntities) {
            invoiceDTOs.add(new InvoiceDTO(invoiceEntity.getOrderEntity().getDepartmentEntity().getName(),
                    invoiceEntity.getOrderEntity().getDepartmentEntity().getBranchEntity().getName(),
                    invoiceEntity.getName(), invoiceEntity.getLanguage(), invoiceEntity.getDate().toString(),
                    invoiceEntity.getTypeOfPayment(), invoiceEntity.getTermOfPayment(), invoiceEntity.getWeightNetto(),
                    invoiceEntity.getWeightBrutto(), invoiceEntity.getQuantityOfPallet()));
        }

        mainView.getTableInvoice().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<InvoiceDTO> data;
        data = FXCollections.observableArrayList(invoiceDTOs);
        mainView.getTableInvoice().setItems(data);

    }

}
