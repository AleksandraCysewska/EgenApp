package cysewska.com.controllers;

import cysewska.com.models.dto.ContractorDTO;
import cysewska.com.models.entities.BranchEntity;
import cysewska.com.repositories.BranchRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-09-05.
 */
@Component
public class ContractorView  {
    @Autowired
    MainView contractorAction;
    @Autowired
    BranchRepository branchRepository;

    public TableColumn departmentName, branchName, country, nip, zip, email, telephone, address, city;

    public void createTableName() {

        this.branchName = new TableColumn("branchName");
        this.departmentName = new TableColumn("departmentName");
        this.nip = new TableColumn("nip");
        this.country = new TableColumn("country");
        this.city = new TableColumn("city");
        this.address = new TableColumn("address");
        this.zip = new TableColumn("zip");
        this.email = new TableColumn("email");
        this.telephone = new TableColumn("telephone");

    }

    public void setTableName() {
        branchName.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("branchName"));
        departmentName.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("departmentName"));
        nip.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("nip"));
        country.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("country"));
        city.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("city"));
        address.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("address"));
        zip.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("zip"));
        email.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("telephone"));
        this.contractorAction.getTableContractor().getColumns().addAll(branchName, departmentName, nip, country, city, address, zip, email, telephone);

    }

    public void fillTableData() {

        List<BranchEntity> branchEntities = branchRepository.findAll();
        List<ContractorDTO> contractorDTOs = new ArrayList<>();
        for (BranchEntity branchEntity : branchEntities) {

            contractorDTOs.addAll(branchEntity.getDepartments().stream().map(department -> new ContractorDTO(
                    branchEntity.getName().toString(), department.getName().toString(),
                    department.getNip(), department.getCountry().toString(),
                    department.getCity().toString(), department.getAddress().toString(),
                    department.getZip().toString(), department.getEmail().toString(),
                    department.getTelephone())).collect(Collectors.toList()));
        }

        contractorAction.getTableContractor().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<ContractorDTO> data;
        data = FXCollections.observableArrayList(contractorDTOs);
        contractorAction.getTableContractor().setItems(data);

    }

}
