package cysewska.com.views.invoice;

import cysewska.com.dto.ContractorDTO;
import cysewska.com.dto.InvoiceDTO;
import cysewska.com.entities.BranchEntity;
import cysewska.com.entities.InvoiceEntity;
import cysewska.com.enums.TypeOfPayment;
import cysewska.com.repositories.BranchRepository;
import cysewska.com.repositories.InvoiceRepository;
import cysewska.com.views.MainView;
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
import java.util.stream.Collectors;

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

        this.name = new TableColumn("name");
        this.language = new TableColumn("language");
        this.branchName = new TableColumn("branchName");
        this.departmentName = new TableColumn("departmentName");
        this.date = new TableColumn("date");
        this.typeOfPayment = new TableColumn("typeOfPayment");
        this.termOfPayment = new TableColumn("termOfPayment");
        this.weightNetto = new TableColumn("weightNetto");
        this.weightBrutto = new TableColumn("weightBrutto");
        this.quantityOfPallet = new TableColumn("quantityOfPallet");


    }

    public void setTableName() {
        branchName.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, String>("branchName"));
        departmentName.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, String>("departmentName"));
        name.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, String>("name"));
        language.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, String>("language"));
        date.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, Date>("date"));
        typeOfPayment.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, TypeOfPayment>("typeOfPayment"));
        termOfPayment.setCellValueFactory(new PropertyValueFactory<InvoiceDTO, Date>("termOfPayment"));
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
                    invoiceEntity.getName(), invoiceEntity.getLanguage(), invoiceEntity.getDate(),
                    invoiceEntity.getTypeOfPayment(), invoiceEntity.getTermOfPayment(), invoiceEntity.getWeightNetto(),
                    invoiceEntity.getWeightBrutto(), invoiceEntity.getQuantityOfPallet()));
        }

        mainView.getTableInvoice().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<InvoiceDTO> data;
        data = FXCollections.observableArrayList(invoiceDTOs);
        mainView.getTableInvoice().setItems(data);

    }

}
