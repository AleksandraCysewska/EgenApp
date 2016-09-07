package cysewska.com.views.contractors;

import cysewska.com.dto.ContractorDTO;
import cysewska.com.entities.BranchEntity;
import cysewska.com.entities.DepartmentEntity;
import cysewska.com.repositories.BranchRepository;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.FXMLView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

@FXMLView("/complex.fxml")
@FXMLController
public class ContractorImp extends AbstractFxmlView implements Initializable, IContraction {

    @Autowired
    BranchRepository branchRepository;
    @FXML
    private TableView table_contractor;
    private TableColumn departmentName, branchName, country, nip, zip, email, telephone, address, city;

    @Override
    protected void setTitle(String title) {
        super.setTitle("Aplikacja Egen");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createContractorTable();
    }

    public void createContractorTable() {
        createTableColumn();
        setTableColumnName();
        fillTableData();
    }

    public void fillTableData() {
        List<BranchEntity> branchEntities = branchRepository.findAll();

        List<ContractorDTO> contractorDTOs = new ArrayList<>();
        for (BranchEntity branchEntity : branchEntities) {
            Set<DepartmentEntity> departments = branchEntity.getDepartments();
            for (DepartmentEntity department : departments) {
                contractorDTOs.add(new ContractorDTO(
                        branchEntity.getName().toString(),
                        department.getName().toString(),
                        department.getNip().toString(),
                        department.getCountry().toString(),
                        department.getCity().toString(),
                        department.getAddress().toString(),
                        department.getZip().toString(),
                        department.getEmail().toString(),
                        department.getTelephone().toString()));
            }
        }
        ObservableList<ContractorDTO> data;
        data = FXCollections.observableArrayList(contractorDTOs);
        table_contractor.setItems(data);
    }

    public void setTableColumnName() {
        branchName.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("branchName"));
        departmentName.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("departmentName"));
        nip.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("nip"));
        country.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("country"));
        city.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("city"));
        address.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("address"));
        zip.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("zip"));
        email.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("email"));
        telephone.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("telephone"));
        this.table_contractor.getColumns().addAll(branchName, departmentName, nip, country, city, address, zip, email, telephone);
    }

    public void createTableColumn() {
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
    public void button1(ActionEvent actionEvent)
    {

    }
}


