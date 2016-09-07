package cysewska.com.views.contractors;

import cysewska.com.dto.ClothDTO;
import cysewska.com.dto.ContractorDTO;
import cysewska.com.entities.BranchEntity;
import cysewska.com.entities.ClothEntity;
import cysewska.com.entities.DepartmentEntity;
import cysewska.com.entities.ModelEntity;
import cysewska.com.enums.Model;
import cysewska.com.repositories.BranchRepository;
import cysewska.com.repositories.ClothRepository;
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
import java.util.stream.Collectors;

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
        createClothsTable();
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
            contractorDTOs.addAll(departments.stream().map(department -> new ContractorDTO(
                    branchEntity.getName().toString(),
                    department.getName().toString(),
                    department.getNip().toString(),
                    department.getCountry().toString(),
                    department.getCity().toString(),
                    department.getAddress().toString(),
                    department.getZip().toString(),
                    department.getEmail().toString(),
                    department.getTelephone().toString())).collect(Collectors.toList()));
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
/*
*
*
*
* */
@Autowired
ClothRepository clothRepository;
    @FXML
    private TableView table_cloth;
    private TableColumn model, clothNamePL, clothNameNO, clothNameENG, priceEuro, pricePl;


    public void createClothsTable() {
        createTableClothColumn();
        setTableClothColumnName();
        fillClothTableData();
    }
    public void createTableClothColumn() {
        this.model = new TableColumn("model");
        this.clothNamePL = new TableColumn("clothNamePL");
        this.clothNameNO = new TableColumn("clothNameNO");
        this.clothNameENG = new TableColumn("clothNameENG");
        this.priceEuro = new TableColumn("priceEuro");
        this.pricePl = new TableColumn("pricePl");
    }
    public void setTableClothColumnName() {
        model.setCellValueFactory(new PropertyValueFactory<ClothDTO, Model>("model"));
        clothNamePL.setCellValueFactory(new PropertyValueFactory<ClothDTO, String>("clothNamePL"));
        clothNameNO.setCellValueFactory(new PropertyValueFactory<ClothDTO, String>("clothNameNO"));
        clothNameENG.setCellValueFactory(new PropertyValueFactory<ClothDTO, String>("clothNameENG"));
        priceEuro.setCellValueFactory(new PropertyValueFactory<ClothDTO, Integer>("priceEuro"));
        pricePl.setCellValueFactory(new PropertyValueFactory<ClothDTO, Integer>("pricePl"));

        this.table_cloth.getColumns().addAll(model, clothNamePL, clothNameNO, clothNameENG, priceEuro, pricePl);
    }
    public void fillClothTableData() {

        List<ModelEntity> modelEntities = clothRepository.findAll();
        List<ClothDTO> clothDTOs = new ArrayList<>();

        for (ModelEntity  modelEntity: modelEntities) {
            Set<ClothEntity> cloths = modelEntity.getCloths();
            for (ClothEntity clothEntity : cloths) {
                System.out.println(clothEntity);
                clothDTOs.add(new ClothDTO(
                                modelEntity.getModel(),
                        clothEntity.getClothNamePL(),
                        clothEntity.getClothNameNO().toString(),
                        clothEntity.getClothNameENG().toString(),
                        clothEntity.getPriceEuro(),
                        clothEntity.getPricePl())
                );
            }
        }
        ObservableList<ClothDTO> data;
        data = FXCollections.observableArrayList(clothDTOs);
        table_cloth.setItems(data);
    }

}


