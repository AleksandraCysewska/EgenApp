package cysewska.com.views;


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

import java.util.List;

@FXMLView("/complex.fxml")
public class ComplexView extends AbstractFxmlView {

	public ComplexView() {
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
	BranchRepository branchRepositories;
	@FXML
	TableView table_contractor;
	@FXML
	private void button1(ActionEvent actionEvent){

		getData();
	}

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
	}

	}


