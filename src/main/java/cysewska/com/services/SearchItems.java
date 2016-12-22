package cysewska.com.services;

import cysewska.com.models.dto.ClothDTO;
import cysewska.com.models.dto.ContractorDTO;
import cysewska.com.models.dto.OrderDTO;
import cysewska.com.models.dto.TextileDTO;
import cysewska.com.models.entities.*;
import cysewska.com.repositories.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-11-08.
 */
@Component
public class SearchItems {

    @Autowired
    BranchRepository branchRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ClothRepository clothRepository;
    @Autowired
    TextilRepository textilRepository;
    public void searchItems(TextField t_search, TabPane tabbedPane, ComboBox c_search,
                            TableView table_contractor, TableView table_order, TableView table_cloth, TableView table_textile){
        t_search.textProperty().addListener((observable, oldValue, newValue) -> {
            switch(tabbedPane.getSelectionModel().getSelectedIndex())
            {
                case 0: {
                    List<BranchEntity> branchEntities = branchRepository.findAll();
                    List<BranchEntity> collect1 = null;
                    List<DepartmentEntity> collect2 = null;
                    List<DepartmentEntity> departmentEntities = departmentRepository.findAll();

                    switch (c_search.getSelectionModel().getSelectedIndex()) {
                        case 0: {
                            collect1 = branchEntities.stream().filter(
                                    (e) -> e.getName().toLowerCase().startsWith(t_search.getText().toLowerCase()) ||
                                            e.getName().toLowerCase().contains(t_search.getText().toLowerCase()))
                                    .collect(Collectors.toList());

                            collect2 = new ArrayList<>();
                            for (BranchEntity branchEntity : collect1) {
                                for (DepartmentEntity departmentEntity : branchEntity.getDepartments()) {
                                    collect2.add(
                                            new DepartmentEntity(departmentEntity.getId(),
                                                    departmentEntity.getName(),
                                                    departmentEntity.getTypeOfNip(),
                                                    departmentEntity.getNip(),
                                                    departmentEntity.getCountry(),
                                                    departmentEntity.getCity(),
                                                    departmentEntity.getAddress(),
                                                    departmentEntity.getZip(),
                                                    departmentEntity.getEmail(),
                                                    departmentEntity.getTelephone(),
                                                    branchEntity));
                                }
                            }
                            break;
                        }
                        case 1: {
                            collect2 = departmentEntities.stream().filter((e) ->
                                    e.getName().toLowerCase().startsWith(t_search.getText().toLowerCase())
                                            || e.getName().toLowerCase().contains(t_search.getText().toLowerCase())
                            ).collect(Collectors.toList());
                            break;
                        }
                        case 2: {
                            collect2 = departmentEntities.stream().filter((e) ->
                                    e.getAddress().toLowerCase().startsWith(t_search.getText().toLowerCase())
                                            || e.getAddress().toLowerCase().contains(t_search.getText().toLowerCase())
                            ).collect(Collectors.toList());
                            break;
                        }
                    }


                    List<ContractorDTO> contractorDTOs = new ArrayList<>();
                    if (collect2.size() > 0) {
                        contractorDTOs.addAll(collect2.stream().map(departmentEntity -> new ContractorDTO(
                                departmentEntity.getBranchEntity().getName(), departmentEntity.getName(),
                                departmentEntity.getNip(), departmentEntity.getCountry(),
                                departmentEntity.getCity(), departmentEntity.getAddress(),
                                departmentEntity.getZip(), departmentEntity.getEmail(),
                                departmentEntity.getTelephone()
                        )).collect(Collectors.toList()));
                    }
                    ObservableList<ContractorDTO> data;
                    data = FXCollections.observableArrayList(contractorDTOs);
                    table_contractor.setItems(data);
                    break;


                }
                case 1 : {
                    List<BranchEntity> branchEntities2 = branchRepository.findAll();
                    List<BranchEntity> collect11 = null;
                    List<OrderEntity> collect22 = null;
                    List<OrderEntity> departmentEntities2 = orderRepository.findAll();


                    switch (c_search.getSelectionModel().getSelectedIndex()) {
                        case 0: {
                            collect11 = branchEntities2.stream().filter(
                                    (e) -> e.getName().toLowerCase().startsWith(t_search.getText().toLowerCase()) ||
                                            e.getName().toLowerCase().contains(t_search.getText().toLowerCase()))
                                    .collect(Collectors.toList());

                            collect22 = new ArrayList<>();
                            for (BranchEntity branchEntity : collect11) {
                                for (DepartmentEntity departmentEntity : branchEntity.getDepartments()) {
                                    for (OrderEntity orderEntity : departmentEntity.getOrders()) {
                                        collect22.add(
                                                new OrderEntity(
                                                        orderEntity.getId(),
                                                        orderEntity.getName(),
                                                        orderEntity.getNote(),
                                                        orderEntity.getDateOfSubmit(),
                                                        orderEntity.getDateOfExecution(),
                                                        departmentEntity
                                                ));
                                    }

                                }
                            }
                            break;
                        }
                        case 1: {
                            collect22 = departmentEntities2.stream().filter((e) ->
                                    e.getDepartmentEntity().getName().toLowerCase().startsWith(t_search.getText().toLowerCase())
                                            || e.getDepartmentEntity().getName().toLowerCase().contains(t_search.getText().toLowerCase())
                            ).collect(Collectors.toList());
                            break;
                        }
                        case 2: {
                            collect22 = departmentEntities2.stream().filter((e) ->
                                    e.getName().toLowerCase().startsWith(t_search.getText().toLowerCase())
                                            || e.getName().toLowerCase().contains(t_search.getText().toLowerCase())
                            ).collect(Collectors.toList());
                            break;
                        }
                        case 3 :
                        {
                            collect22 = departmentEntities2.stream().filter((e) ->
                                    e.getDateOfSubmit().toLowerCase().startsWith(t_search.getText().toLowerCase())
                                            || e.getDateOfSubmit().toLowerCase().contains(t_search.getText().toLowerCase())
                            ).collect(Collectors.toList());
                            break;
                        }
                        case 4 :
                        {
                            collect22 = departmentEntities2.stream().filter((e) ->
                                    e.getDateOfExecution().toLowerCase().startsWith(t_search.getText().toLowerCase())
                                            || e.getDateOfExecution().toLowerCase().contains(t_search.getText().toLowerCase())
                            ).collect(Collectors.toList());
                            break;
                        }
                    }
                    List<OrderDTO> contractorDTOs2 = new ArrayList<>();
                    if (collect22.size() > 0) {
                        contractorDTOs2.addAll(collect22.stream().map(departmentEntity -> new OrderDTO(
                                departmentEntity.getDepartmentEntity().getName(),
                                departmentEntity.getDepartmentEntity().getBranchEntity().getName(),
                                departmentEntity.getName(),
                                departmentEntity.getNote(),
                                departmentEntity.getDateOfSubmit(),
                                departmentEntity.getDateOfExecution()
                        )).collect(Collectors.toList()));
                    }
                    ObservableList<OrderDTO> data2;
                    data2 = FXCollections.observableArrayList(contractorDTOs2);
                    table_order.setItems(data2);
                    break;
                }
                case 2:
                {
                    List<ClothEntity> clothEntities = clothRepository.findAll();
                    List<ClothEntity> collect1 = null;


                    switch (c_search.getSelectionModel().getSelectedIndex()) {
                        case 0: {
                            collect1 = clothEntities.stream().filter(
                                    (e) -> e.getModelEntity().getModel().toLowerCase().startsWith(t_search.getText().toLowerCase()) ||
                                            e.getModelEntity().getModel().toLowerCase().contains(t_search.getText().toLowerCase()))
                                    .collect(Collectors.toList());
                            break;
                        }
                        case 1 :
                        {
                            collect1 = clothEntities.stream().filter(
                                    (e) -> e.getClothNamePL().toLowerCase().startsWith(t_search.getText().toLowerCase()) ||
                                            e.getClothNamePL().toLowerCase().contains(t_search.getText().toLowerCase()))
                                    .collect(Collectors.toList());
                            break;
                        }
                        case 2 :
                        {
                            collect1 = clothEntities.stream().filter(
                                    (e) -> e.getPricePl().toString().startsWith(t_search.getText()))
                                    .collect(Collectors.toList());
                            break;
                        }
                    }

                    List<ClothDTO> contractorDTOs2 = new ArrayList<>();
                    if (collect1.size() > 0) {
                        contractorDTOs2.addAll(collect1.stream().map(clothEntity -> new ClothDTO(
                                clothEntity.getModelEntity().getModel(),
                                clothEntity.getClothNamePL(),
                                clothEntity.getClothNameNO(),
                                clothEntity.getClothNameENG(),
                                clothEntity.getPriceEuro(),
                                clothEntity.getPricePl()

                        )).collect(Collectors.toList()));

                    }
                    ObservableList<ClothDTO> data2;
                    data2 = FXCollections.observableArrayList(contractorDTOs2);
                    table_cloth.setItems(data2);
                    break;
                }

                case 3:
                {
                    System.out.println("faktury");
                    break;
                }

                case 4:
                {
                    List<TextileEntity> textilRepositoryAll = textilRepository.findAll();
                    List<TextileEntity> collect1 = null;


                    switch (c_search.getSelectionModel().getSelectedIndex()) {
                        case 0: {
                            collect1 = textilRepositoryAll.stream().filter(
                                    (e) -> e.getName().toLowerCase().startsWith(t_search.getText().toLowerCase()) ||
                                            e.getName().toLowerCase().contains(t_search.getText().toLowerCase()))
                                    .collect(Collectors.toList());
                            break;
                        }
                        case 1: {
                            collect1 = textilRepositoryAll.stream().filter(
                                    (e) -> e.getColors().toLowerCase().startsWith(t_search.getText().toLowerCase()) ||
                                            e.getColors().toLowerCase().contains(t_search.getText().toLowerCase()))
                                    .collect(Collectors.toList());
                            break;
                        }
                        case 2: {
                            collect1 = textilRepositoryAll.stream().filter(
                                    (e) -> e.getTextileQuantity().toString().startsWith(t_search.getText()))
                                    .collect(Collectors.toList());
                            break;
                        }
                    }

                    List<TextileDTO> contractorDTOs2 = new ArrayList<>();
                    if (collect1.size() > 0) {
                        contractorDTOs2.addAll(collect1.stream().map(clothEntity -> new TextileDTO(
                                clothEntity.getName(),
                                clothEntity.getColors(),
                                clothEntity.getTextileQuantity(),
                                clothEntity.getTextileThickness()

                        )).collect(Collectors.toList()));

                    }
                    ObservableList<TextileDTO> data2;
                    data2 = FXCollections.observableArrayList(contractorDTOs2);
                    table_textile.setItems(data2);
                    break;
                }


            }


        });
    }
}
