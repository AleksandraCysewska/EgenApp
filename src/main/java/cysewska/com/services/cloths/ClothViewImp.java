package cysewska.com.services.cloths;

import cysewska.com.controllers.MainView;
import cysewska.com.models.dto.ClothDTO;
import cysewska.com.models.entities.ClothEntity;
import cysewska.com.models.entities.ModelEntity;
import cysewska.com.repositories.ModelRepository;
import cysewska.com.services.interfaces.ICloth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Ola on 2016-09-10.
 */
@Component
public class ClothViewImp implements ICloth {
    private TableColumn model, clothNamePL, clothNameNO, clothNameENG, priceEuro, pricePl;
    @Autowired
    ModelRepository clothRepository;
    @Autowired
    MainView mainView;

    public void createClothsTable() {
        createTableClothColumn();
        setTableClothColumnName();
        fillClothTableData();
    }
    public void createTableClothColumn() {
        this.model = new TableColumn("Model");
        this.clothNamePL = new TableColumn("Nazwa ubrania");
        this.clothNameNO = new TableColumn("Nazwa norwerska");
        this.clothNameENG = new TableColumn("Nazwa angielska");
        this.priceEuro = new TableColumn("Cena w euro");
        this.pricePl = new TableColumn("Cena polska");
    }
    public void setTableClothColumnName() {
      mainView.getTableCloth().getColumns().clear();

        model.setCellValueFactory(new PropertyValueFactory<ClothDTO, String>("model"));
        clothNamePL.setCellValueFactory(new PropertyValueFactory<ClothDTO, String>("clothNamePL"));
        clothNameNO.setCellValueFactory(new PropertyValueFactory<ClothDTO, String>("clothNameNO"));
        clothNameENG.setCellValueFactory(new PropertyValueFactory<ClothDTO, String>("clothNameENG"));
        priceEuro.setCellValueFactory(new PropertyValueFactory<ClothDTO, Integer>("priceEuro"));
        pricePl.setCellValueFactory(new PropertyValueFactory<ClothDTO, Integer>("pricePl"));
        this.mainView.getTableCloth().getColumns().addAll(model, clothNamePL, clothNameNO, clothNameENG, priceEuro, pricePl);


    }
    public void fillClothTableData() {

        List<ModelEntity> modelEntities = clothRepository.findAll();
        List<ClothDTO> clothDTOs = new ArrayList<>();

        for (ModelEntity  modelEntity: modelEntities) {
            Set<ClothEntity> cloths = modelEntity.getCloths();
            clothDTOs.addAll(cloths.stream().map(clothEntity -> new ClothDTO(
                    modelEntity.getModel(),
                    clothEntity.getClothNamePL(),
                    clothEntity.getClothNameNO().toString(),
                    clothEntity.getClothNameENG().toString(),
                    clothEntity.getPriceEuro(),
                    clothEntity.getPricePl())).collect(Collectors.toList()));
        }
        ObservableList<ClothDTO> data;
        data = FXCollections.observableArrayList(clothDTOs);
        mainView.getTableCloth().setItems(data);
        mainView.getTableCloth().setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


    }
}
