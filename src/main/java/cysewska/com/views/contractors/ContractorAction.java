package cysewska.com.views.contractors;



import cysewska.com.dto.ContractorDTO;
import cysewska.com.entities.BranchEntity;
import cysewska.com.entities.UsersEntity;
import cysewska.com.repositories.BranchRepository;
import cysewska.com.repositories.UserRepository;
import de.felixroske.jfxsupport.AbstractFxmlView;
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
import java.util.List;

@FXMLView("/complex.fxml")
public class ContractorAction extends AbstractFxmlView {

	public ContractorAction() {
		setTitle("Aplikacja Egen");

	}
	@Autowired
	UserRepository userRepositories;
	@FXML
	TableColumn bname;
	@FXML
	TableColumn dname;
	@FXML
	TableColumn daddress;
	@Autowired
	BranchRepository branchRepository;
	@FXML
	TableView table_contractor;
	@FXML


	@Transactional
	public void button1(ActionEvent actionEvent) throws NoSuchFieldException {
		List<BranchEntity> all = branchRepository.findAll();
		System.out.println(all);
		/*for (BranchEntity branchEntity : all) {
			ContractorDTO contractorDTO =  new ContractorDTO(branchEntity.getName(), branchEntity.getDepartments().getClass().getField("name").toString(),
				branchEntity.getDepartments().getClass().getField("nip").toString(), branchEntity.getDepartments().getClass().getField("county").toString(),
					branchEntity.getDepartments().getClass().getField("city").toString(),
					branchEntity.getDepartments().getClass().getField("address").toString(),
					branchEntity.getDepartments().getClass().getField("zip").toString(),
					branchEntity.getDepartments().getClass().getField("email").toString(),
					branchEntity.getDepartments().getClass().getField("telephone").toString()
					);
			System.out.println(contractorDTO);
		}*/
	}

	/*private void button1(ActionEvent actionEvent) throws NoSuchFieldException {

		loadDataToContractor();
	//	contractorImp.loadDataToContractor();
	}*/
/*
	public void getData()
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
	}*/
	/*public void loadDataToContractor()
	{
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<BranchEntity> c = cb.createQuery(BranchEntity.class);
		Root<BranchEntity> p = c.from(BranchEntity.class);

		TypedQuery<BranchEntity> q = entityManager.createQuery(c);
		BranchEntity query = q.getSingleResult();
		System.out.println(query);
	}*/



	}


