package cysewska.com.controllers;

import cysewska.com.models.dto.OrderDTO;
import cysewska.com.models.entities.OrderEntity;
import cysewska.com.repositories.OrderRepository;
import cysewska.com.controllers.interfaces.IOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-09-10.
 */
@Component
public class OrderViewImp implements IOrder {
    @Autowired
    MainView mainView;
    @Autowired
    OrderRepository orderRepository;
    public TableColumn departmentName, branchName, orderName, descriptionName, dateOfSubmit, dateOfExecution;


    public void createTableName() {

        this.branchName = new TableColumn("branchName");
        this.departmentName = new TableColumn("departmentName");
        this.orderName = new TableColumn("orderName");
        this.descriptionName = new TableColumn("descriptionName");
        this.dateOfSubmit = new TableColumn("dateOfSubmit");
        this.dateOfExecution = new TableColumn("dateOfExecution");


    }

    public void setTableName() {
        branchName.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("branchName"));
        departmentName.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("departmentName"));
        orderName.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("orderName"));
        descriptionName.setCellValueFactory(new PropertyValueFactory<OrderDTO, String>("descriptionName"));
        dateOfSubmit.setCellValueFactory(new PropertyValueFactory<OrderDTO, Date>("dateOfSubmit"));
        dateOfExecution.setCellValueFactory(new PropertyValueFactory<OrderDTO, Date>("dateOfExecution"));
        this.mainView.getTableOrder().getColumns().addAll(departmentName, branchName, orderName, descriptionName, dateOfSubmit, dateOfExecution);

    }

    public void fillTableData() {

        List<OrderEntity> orderEntities = orderRepository.findAll();
        System.out.println(orderEntities);
        List<OrderDTO> orderDTOs = orderEntities.stream().map(orderEntity -> new OrderDTO(orderEntity.getDepartmentEntity().getName(), orderEntity.getDepartmentEntity().getBranchEntity().getName(),
                orderEntity.getName(), orderEntity.getNote(), orderEntity.getDateOfSubmit(), orderEntity.getDateOfExecution()
        )).collect(Collectors.toList());

        mainView.getTableOrder().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<OrderDTO> data;
        data = FXCollections.observableArrayList(orderDTOs);
        mainView.getTableOrder().setItems(data);
        System.out.println(data);

    }


}
