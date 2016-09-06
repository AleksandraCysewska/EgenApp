package cysewska.com.views.contractors;



import cysewska.com.dto.ContractorDTO;
import cysewska.com.entities.BranchEntity;
import cysewska.com.entities.DepartmentEntity;
import cysewska.com.entities.UsersEntity;
import cysewska.com.repositories.BranchRepository;
import cysewska.com.repositories.UserRepository;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.FXMLView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@FXMLController
@FXMLView("/complex.fxml")
public class ContractorAction extends AbstractFxmlView {

	public ContractorAction() {
		setTitle("Aplikacja Egen");

	}
	@Autowired
	UserRepository userRepositories;

	public TableColumn departmentName,

	branchName, country, nip, zip, email, telephone, address, city;

	public TableColumn bname, dname, daddress;


	@Autowired
	BranchRepository branchRepository;
	@FXML
	public TableView table_contractor ;
	@FXML
@PersistenceContext
	EntityManager entityManager;


	public void button1(ActionEvent actionEvent) throws NoSuchFieldException {
		List<BranchEntity> branchEntities = branchRepository.findAll();
		Set<DepartmentEntity> departments = null;

		this.branchName = new TableColumn("branchName");
		this.departmentName = new TableColumn("departmentName");
		this.nip = new TableColumn("nip");
		this.country = new TableColumn("country");
		this.city = new TableColumn("city");
		this.address = new TableColumn("address");
		this.zip = new TableColumn("zip");
		this.email = new TableColumn("email");
		this.telephone = new TableColumn("telephone");



		branchName.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("branchName"));
		departmentName.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("departmentName"));
		nip.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("nip"));
		country.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("country"));
		city.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("city"));
		address.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("address"));
		zip.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("zip"));
		email.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("email"));
		telephone.setCellValueFactory(new PropertyValueFactory<ContractorDTO, String>("telephone"));
	//	this.table_contractor = new TableView<ContractorDTO>();
		this.table_contractor.getColumns().addAll(branchName, departmentName, nip, country, city, address, zip, email, telephone);


		List<ContractorDTO> contractorDTOs = new ArrayList<>();
		for (BranchEntity branchEntity : branchEntities) {


			departments = branchEntity.getDepartments();

			for (DepartmentEntity department : departments) {

				System.out.println(branchEntity.getName().toString()

				+ "  "  + department.getName().toString()
						+ "  "  + department.getNip().toString()
								+ "  "  + department.getCountry().toString()
							+ "  "  +	department.getCity().toString()
								+ "  "  +		department.getAddress().toString()
								+ "  "  +		department.getZip().toString()
								+ "  "  +		department.getEmail().toString()
								+ "  "  +		department.getTelephone().toString()
				);
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




		System.out.println(departments);

		ObservableList<ContractorDTO> data ;
		 data = FXCollections.observableArrayList(contractorDTOs);

		System.out.println(data);
		table_contractor.setItems(data);
	//	getData();
	}

	/*public void getData()
	{
		this.bname  = new TableColumn("id");
		this.dname  = new TableColumn("username");
		this.daddress  = new TableColumn("password");

		bname.setCellValueFactory(new PropertyValueFactory<UsersEntity, Long>( "id"));
		dname.setCellValueFactory(new PropertyValueFactory<UsersEntity, String>( "username"));
		daddress.setCellValueFactory(new PropertyValueFactory<UsersEntity, String>( "password"));

		table_contractor.getColumns().addAll(bname, dname, daddress);
		final List<UsersEntity> all = userRepositories.findAll();
		System.out.println(all + "" + all.size());
		ObservableList<UsersEntity> data = FXCollections.observableArrayList(all);

		System.out.println(data);
		table_contractor.setItems(data);
	}
*/


	}


